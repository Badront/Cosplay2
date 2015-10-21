package ru.badr.cosplay2.activity;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import ru.badr.base.util.BackListener;
import ru.badr.cosplay2.R;

/**
 * Created by ABadretdinov
 * 28.08.2015
 * 15:38
 */

//todo https://github.com/RubenGees/Introduction
//todo https://github.com/MiguelCatalan/MaterialSearchView
//todo http://opencon14.cosplay2.ru/cards
public class MainActivity extends FragmentWrapperActivity {
    private boolean mDoubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (
                currentFragment == null
                        || !(currentFragment instanceof BackListener)
                        || !((BackListener) currentFragment).onBackPressed()) {
            if (mDoubleBackToExitPressedOnce) {
                finish();
            } else {
                mDoubleBackToExitPressedOnce = true;
                Toast.makeText(this, getString(R.string.back_toast), Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mDoubleBackToExitPressedOnce = false;

                    }
                }, 2000);
            }
        }

    }
}
