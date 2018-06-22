package ru.badr.base.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.badr.base.SchedulersProvider;

/**
 * Created by abadretdinov
 * on 22.06.2018
 */
public class BaseMvpPresenter<VIEW extends BaseMvpView> extends MvpPresenter<VIEW> {
    protected final SchedulersProvider schedulersProvider;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public BaseMvpPresenter(
            SchedulersProvider schedulersProvider
    ) {
        this.schedulersProvider = schedulersProvider;
    }

    protected void unsubscribeOnDestroy(@NonNull Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    protected void dispose(@Nullable Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
