package vn.com.ndquang.sudoku.activities;

import android.os.Bundle;

import vn.com.ndquang.sudoku.R;
import vn.com.ndquang.sudoku.fragments.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.container, new MainFragment()).commit();
        }
    }
}
