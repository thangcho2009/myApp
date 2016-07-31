package vn.com.ndquang.sudoku;

import android.app.Application;

/**
 * Created by NDQUANG on 7/30/2016.
 */
public class SudokuApplication extends Application {
    private static SudokuApplication mInstance;

    public static synchronized SudokuApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
