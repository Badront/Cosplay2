package ru.badr.cosplay2.util;

import android.content.Context;
import android.util.AttributeSet;

import ru.badr.base.entity.LineItem;
import xyz.danoz.recyclerviewfastscroller.sectionindicator.title.SectionTitleIndicator;

/**
 * Created by Badr on 24.11.2015.
 */
public class LineItemSectionTitleIndicator extends SectionTitleIndicator<LineItem> {
    public LineItemSectionTitleIndicator(Context context) {
        super(context);
    }

    public LineItemSectionTitleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineItemSectionTitleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setSection(LineItem object) {
        setTitleText(object.data.toString());
    }
}
