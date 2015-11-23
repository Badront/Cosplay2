package ru.badr.cosplay2.util;

import android.content.Context;
import android.util.AttributeSet;

import xyz.danoz.recyclerviewfastscroller.sectionindicator.title.SectionTitleIndicator;

/**
 * Created by Badr on 24.11.2015.
 */
public class TextSectionTitleIndicator extends SectionTitleIndicator<String> {
    public TextSectionTitleIndicator(Context context) {
        super(context);
    }

    public TextSectionTitleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextSectionTitleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setSection(String object) {
        setTitleText(object);
    }
}
