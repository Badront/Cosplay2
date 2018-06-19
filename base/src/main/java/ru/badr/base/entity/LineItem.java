package ru.badr.base.entity;

/**
 * Created by ABadretdinov
 * 28.07.2015
 * 14:41
 */
public class LineItem {
    public int sectionFirstPosition;
    public boolean isHeader;
    public boolean isEnabled;
    public Object data;

    public LineItem(int sectionFirstPosition, boolean isHeader, Object data, boolean isEnabled) {
        this.sectionFirstPosition = sectionFirstPosition;
        this.isHeader = isHeader;
        this.data = data;
        this.isEnabled = isEnabled;
    }

    public LineItem(int sectionFirstPosition, boolean isHeader, Object data) {
        this(sectionFirstPosition, isHeader, data, true);
    }
}
