package ru.badr.cosplay2.activity;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.Date;

import ru.badr.base.CurrentTimeTask;
import ru.badr.base.util.BackListener;
import ru.badr.base.util.retrofit.LocalSpiceService;
import ru.badr.cosplay2.util.Utils;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 28.08.2015
 * 15:38
 */

//todo https://github.com/RubenGees/Introduction
//todo https://github.com/MiguelCatalan/MaterialSearchView
public class MainActivity extends FragmentWrapperActivity implements RequestListener<Date> {

    private SpiceManager mSpiceManger = new SpiceManager(LocalSpiceService.class);
    private boolean mDoubleBackToExitPressedOnce = false;

    @Override
    protected void onStart() {
        super.onStart();
        if (!mSpiceManger.isStarted()) {
            mSpiceManger.start(this);
            mSpiceManger.execute(new CurrentTimeTask(this), this);
        }
    }

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
                }, DurationInMillis.ONE_SECOND * 2);
            }
        }

    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        Utils.APP_START_TIME = new Date();
        reInflateMenu();
        initFragment();
    }

    @Override
    public void onRequestSuccess(Date date) {
        Utils.APP_START_TIME = date;
        reInflateMenu();
        initFragment();
    }
}
