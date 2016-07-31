package vn.com.ndquang.sudoku.models;

import android.provider.BaseColumns;

/**
 * Created by NDQUANG on 7/31/2016.
 */
public abstract class SudokuColumns implements BaseColumns {
    public static final String FOLDER_ID = "folder_id";
    public static final String CREATED = "created";
    public static final String STATE = "state";
    public static  final String TIME = "time";
    public static  final String LAST_PLAYED = "last_played";
    public static  final String DATA = "data";
    public static  final String PUZZLE_NOTE = "puzzle_note";
}
