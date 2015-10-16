package ru.badr.base.util;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;

import ru.badr.base.R;

/**
 * Created by ABadretdinov
 * 23.07.2015
 * 13:00
 */
public class UiUtils {

    public static void minimizeView(final View view) {
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.scale_down);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

    public static void maximizeView(final View view) {
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.scale_up);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

    public static void expandView(View expandableView, View button) {
        expandView(expandableView, button, true);
    }

    public static void expandView(final View expandableView, View button, boolean animated) {
        int duration;
        if (animated) {
            expandableView.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            final int targetHeight = expandableView.getMeasuredHeight();

            expandableView.getLayoutParams().height = 0;
            expandableView.setVisibility(View.VISIBLE);
            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    expandableView.getLayoutParams().height = interpolatedTime == 1
                            ? ViewGroup.LayoutParams.WRAP_CONTENT
                            : (int) (targetHeight * interpolatedTime);
                    expandableView.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            // 1dp/ms
            duration = (int) (targetHeight / expandableView.getContext().getResources().getDisplayMetrics().density);
            a.setDuration(duration);
            expandableView.startAnimation(a);
        } else {
            duration = 0;
            expandableView.setVisibility(View.VISIBLE);
        }
        rotateView(button, duration, 0f, 180f);

    }

    public static void collapseView(View collapseView, View button) {
        collapseView(collapseView, button, true);
    }

    public static void collapseView(final View collapseView, View button, boolean animated) {
        int duration;
        if (animated) {
            final int initialHeight = collapseView.getMeasuredHeight();

            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    if (interpolatedTime == 1) {
                        collapseView.setVisibility(View.GONE);
                    } else {
                        collapseView.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                        collapseView.requestLayout();
                    }
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            // 1dp/ms
            duration = (int) (initialHeight / collapseView.getContext().getResources().getDisplayMetrics().density);
            a.setDuration(duration);
            collapseView.startAnimation(a);
        } else {
            duration = 0;
            collapseView.setVisibility(View.GONE);
        }
        rotateView(button, duration, 180f, 0f);
    }

    public static void hideView(final View viewToHide) {
        final int initialHeight = viewToHide.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    viewToHide.setVisibility(View.GONE);
                } else {
                    viewToHide.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    viewToHide.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        int duration = (int) (initialHeight / viewToHide.getContext().getResources().getDisplayMetrics().density);
        a.setDuration(duration);
        viewToHide.startAnimation(a);
    }

    public static void rotateView(View view, int duration, float fromDegree, float toDegree) {
        RotateAnimation rotate =
                new RotateAnimation(
                        fromDegree,
                        toDegree,
                        Animation.RELATIVE_TO_SELF,
                        0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f);
        rotate.setFillAfter(true); // prevents View from restoring to original direction.
        rotate.setDuration(duration);
        view.startAnimation(rotate);
    }

}
