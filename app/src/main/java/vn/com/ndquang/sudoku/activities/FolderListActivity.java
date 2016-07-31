package vn.com.ndquang.sudoku.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import vn.com.ndquang.sudoku.R;
import vn.com.ndquang.sudoku.models.FolderColumns;
import vn.com.ndquang.sudoku.models.SudokuDatabase;

/**
 * Created by NDQUANG on 7/31/2016.
 */
public class FolderListActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static  final String EXTRA_DIFFICULTY_ID = "EXTRA_DIFFICULTY_ID";
    private static  final  String TAG = "FolderListActivity";

    /** Mapping of fields to views */
    private final String[] mListFields = new String[] {
            FolderColumns.NAME,
            FolderColumns._ID
    };

    private final int[] mListViews = new int[]{
            R.id.name,
            R.id.detail
    };

    private long mDifficulty;
    private Cursor mCursor;
    private SudokuDatabase mDatabase;

    /**List adapter. */
    private SimpleCursorAdapter mAdapter;

    /** The list */
    private ListView mListView;

    private FolderListViewBinder mFolderListBinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_list);

        mListView = (ListView)findViewById(android.R.id.list);
        mDatabase = new SudokuDatabase(FolderListActivity.this);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_DIFFICULTY_ID)) {
            mDifficulty = intent.getLongExtra(EXTRA_DIFFICULTY_ID, 0);
        } else {
            Log.d(TAG, "No 'folder_id' extra provided, exiting.");
            finish();
            return;
        }

        mCursor = mDatabase.getFolderList(mDifficulty);
        setupAdapter();


    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void setupAdapter(){
        mAdapter = new SimpleCursorAdapter(
                FolderListActivity.this,
                R.layout.folder_list_item,
                mCursor,
                mListFields,
                mListViews
        );
        mFolderListBinder = new FolderListViewBinder(FolderListActivity.this);
        mAdapter.setViewBinder(mFolderListBinder);
        mListView.setAdapter(mAdapter);
    }

    private static  class FolderListViewBinder implements SimpleCursorAdapter.ViewBinder {
        private Context mContext;

        FolderListViewBinder(Context context){
            this.mContext = context;
        }
        @Override
        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
            switch (view.getId()){
                case R.id.name:
                    ((TextView)view).setText(cursor.getString(columnIndex));
                    break;
                case R.id.detail:
                    final  long folderId = cursor.getLong(columnIndex);
                    final  TextView detailView = (TextView)view;
                    detailView.setText(mContext.getString(R.string.loading));

                    break;
            }
            return true;
        }
    }
}
