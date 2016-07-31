package vn.com.ndquang.sudoku.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vn.com.ndquang.sudoku.R;
import vn.com.ndquang.sudoku.game.SudokuGame;

/**
 * This class helps open, create, and upgrade the database file.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    public static final int DATABASE_VERSION = 8;

    private Context mContext;

    DatabaseHelper(Context context) {
        super(context, SudokuDatabase.DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + SudokuDatabase.FOLDER_TABLE_NAME + " ("
                        + FolderColumns._ID + " INTEGER PRIMARY KEY,"
                        + FolderColumns.NAME + " TEXT,"
                        + FolderColumns.DIFFICULTY_ID + " INTEGER,"
                        + FolderColumns.DIFFICULTY_NAME + " TEXT,"
                        + FolderColumns.CREATED + " INTEGER"
                        + ");"
        );

        db.execSQL("CREATE TABLE " + SudokuDatabase.SUDOKU_TABLE_NAME + " ("
                        + SudokuColumns._ID + " INTEGER PRIMARY KEY,"
                        + SudokuColumns.FOLDER_ID + " INTEGER,"
                        + SudokuColumns.CREATED + " INTEGER,"
                        + SudokuColumns.STATE + " INTEGER,"
                        + SudokuColumns.TIME + " INTEGER,"
                        + SudokuColumns.LAST_PLAYED + " INTEGER,"
                        + SudokuColumns.DATA + " TEXT,"
                        + SudokuColumns.PUZZLE_NOTE + " TEXT"
                        + ");"
        );

        int id = 1;
        String pack = "Pack";
        for (int i = 1; i < 4; i++) {
            insertFolder(db, id++, pack + "1", 1, mContext.getString(R.string.difficulty_easy));
            insertFolder(db, id++, pack + "2", 2, mContext.getString(R.string.difficulty_normal));
            insertFolder(db, id++, pack + "3", 3, mContext.getString(R.string.difficulty_hard));
            insertFolder(db, id++, pack + "4", 4, mContext.getString(R.string.difficulty_every_hard));
        }
        createIndex(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void insertFolder(SQLiteDatabase db, long folderId, String folderName, long difficultyId, String difficultyName) {
        long now = System.currentTimeMillis();
        db.execSQL("INSERT INTO " + SudokuDatabase.FOLDER_TABLE_NAME
                        + " VALUES ("
                        + folderId + ", '"
                        + folderName + "', "
                        + difficultyId + ", '"
                        + difficultyName + "', "
                        + now + ");"
        );
    }

    private void insertSudoku(SQLiteDatabase db, long folderID, long sudokuID, String sudokuName, String data) {
        String sql = "INSERT INTO " + SudokuDatabase.SUDOKU_TABLE_NAME
                + " VALUES (" + sudokuID + ", " + folderID + ", 0, " + SudokuGame.GAME_STATE_NOT_STARTED + ", 0, null, '" + data + "', null);";
        db.execSQL(sql);
    }

    private void createIndex(SQLiteDatabase db) {
        db.execSQL("CREATE INDEX " + SudokuDatabase.SUDOKU_TABLE_NAME + "_idx1 on "
                + SudokuDatabase.SUDOKU_TABLE_NAME + " (" + SudokuColumns.FOLDER_ID + ");");
    }
}
