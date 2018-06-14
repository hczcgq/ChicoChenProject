package com.chic.android.ui.feature.paint;

import android.graphics.Path;

/**
 * @author: 陈国权
 * @date: 2018/6/14 15:47
 * @description:
 */

public class FingerPath {

    public int color;
    public boolean emboss;
    public boolean blur;
    public int strokeWidth;
    public Path path;

    public FingerPath(int color, boolean emboss, boolean blur, int strokeWidth, Path path) {
        this.color = color;
        this.emboss = emboss;
        this.blur = blur;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }
}
