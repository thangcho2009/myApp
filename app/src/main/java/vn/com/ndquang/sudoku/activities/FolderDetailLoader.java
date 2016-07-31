package vn.com.ndquang.sudoku.activities;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import vn.com.ndquang.sudoku.game.FolderInfo;
import vn.com.ndquang.sudoku.models.SudokuDatabase;

/**
 * Loads details of given folders on one single background thread.
 * Results are published on GUI thread via {@link FolderDetailCallback} interface.
 *
 * Please note that instance of this class has to be created on GUI thread!
 *
 * You should explicitly call {@link #destroy()} when this object is no longer needed.
 *
 */
public class FolderDetailLoader {
    private  static  final String  TAG = "FolderDetailLoader";
    private Context mContext;
    private SudokuDatabase mDatabase;
    private Handler mGuiHandler;
    private ExecutorService mLoaderService = Executors.newSingleThreadExecutor();

    public FolderDetailLoader(Context context){
        this.mContext = context;
        mDatabase = new SudokuDatabase(context);
        mGuiHandler = new Handler();
    }

    public  void loadDetailAsyn(long folderId, FolderDetailCallback loadedCallback){
        final long folderIdFinal = folderId;
        final  FolderDetailCallback loadedCallbackFinal = loadedCallback;
        mLoaderService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //final FolderInfo folderInfo = mDatabase.getFolderList(folderIdFinal);
                    mGuiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //loadedCallbackFinal.onLoaded(folderInfo);
                        }
                    });
                }catch (Exception e){
                    // this is some unimportant background stuff, do not fail
                    Log.e(TAG, "Error occured while loading full folder info.", e);
                }
            }
        });
    }

    public  interface  FolderDetailCallback{
        void onLoaded(FolderInfo folderInfo);
    }
}
