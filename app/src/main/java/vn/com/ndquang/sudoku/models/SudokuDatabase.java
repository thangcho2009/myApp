package vn.com.ndquang.sudoku.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

/**
 * Created by NDQUANG on 7/31/2016.
 */
public class SudokuDatabase {
    public static  final String DATABASE_NAME= "sudoku";
    public static final String SUDOKU_TABLE_NAME = "sudoku";
    public static final String FOLDER_TABLE_NAME = "folder";

    private DatabaseHelper mOpenHelper;

    public  SudokuDatabase(Context context){
        mOpenHelper = new DatabaseHelper(context);
    }

    /**
     * Returns list of puzzle folders.
     */
    public Cursor getFolderList(long difficulty){
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(FOLDER_TABLE_NAME);
        qb.appendWhere(FolderColumns.DIFFICULTY_ID+ "=" + difficulty);

        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        return qb.query(db, null, null, null, null, null, "created ASC");
    }
}
