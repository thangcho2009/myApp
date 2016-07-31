package vn.com.ndquang.sudoku.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.com.ndquang.sudoku.R;
import vn.com.ndquang.sudoku.activities.FolderListActivity;

/**
 * Created by NDQUANG on 7/30/2016.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    private Button[] buttons;

    private static final long DIFFCULTY_EASY = 1l;
    private static final long DIFFCULTY_NORMAL = 2l;
    private static final long DIFFCULTY_HARD = 3l;
    private static final long DIFFCULTY_EVERY_HARD = 4l;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (buttons == null) {
            buttons = new Button[4];
        }

        buttons[0] = (Button) view.findViewById(R.id.btnEasy);
        buttons[1] = (Button) view.findViewById(R.id.btnNormal);
        buttons[2] = (Button) view.findViewById(R.id.btnHard);
        buttons[3] = (Button) view.findViewById(R.id.btnEveryHard);

        for (Button button : buttons) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEasy:
                setIntent(DIFFCULTY_EASY);
                break;
            case R.id.btnNormal:
                setIntent(DIFFCULTY_NORMAL);
                break;
            case R.id.btnHard:
                setIntent(DIFFCULTY_HARD);
                break;
            case R.id.btnEveryHard:
                setIntent(DIFFCULTY_EVERY_HARD);
                break;
        }
    }

    private void setIntent(long difficulty) {
        Intent i = new Intent(getContext(), FolderListActivity.class);
        i.putExtra(FolderListActivity.EXTRA_DIFFICULTY_ID, difficulty);
        startActivity(i);
    }
}
