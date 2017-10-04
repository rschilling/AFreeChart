package org.afree.graphics;

import android.graphics.Color;


/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar
 * Qualified Name:     org.afree.graphics.SolidColor
 * JD-Core Version:    0.7.0.1
 */
public class SolidColor
        implements PaintType {
    private int mColor;

    public SolidColor() {
        this.mColor = 0;
    }

    public SolidColor(int color) {
        this.mColor = color;
    }

    public SolidColor(SolidColor solidColor) {
        this.mColor = solidColor.mColor;
    }

    public int getColor() {
        return this.mColor;
    }

    public int getAlpha() {
        return Color.alpha(this.mColor);
    }

    public void setAlpha(int alpha) {
        this.mColor = Color.argb(
                alpha,
                Color.red(this.mColor),
                Color.green(this.mColor),
                Color.blue(this.mColor));
    }

    public SolidColor getDarkerSides() {
        int c = Color.argb(
                Color.alpha(this.mColor),
                (int) (Color.red(this.mColor) * 0.8D),
                (int) (Color.green(this.mColor) * 0.8D),
                (int) (Color.blue(this.mColor) * 0.8D));
        return new SolidColor(c);
    }

    public boolean equals(Object object) {
        if (!(object instanceof SolidColor)) {
            return false;
        }
        SolidColor gradientColor = (SolidColor) object;
        if (gradientColor.getColor() == this.mColor) {
            return true;
        }
        return false;
    }
}
