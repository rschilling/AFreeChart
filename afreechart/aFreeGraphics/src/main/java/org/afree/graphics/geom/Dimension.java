package org.afree.graphics.geom;

public class Dimension {
    public float mWidth;
    public float mHeight;

    public Dimension(float width, float height) {
        this.mWidth = width;
        this.mHeight = height;
    }

    public void setSize(Dimension dimension) {
        this.mWidth = dimension.mWidth;
        this.mHeight = dimension.mHeight;
    }

    public void setSize(float width, float height) {
        this.mWidth = width;
        this.mHeight = height;
    }

    public boolean equals(Object o) {
        if ((o instanceof Dimension)) {
            Dimension dimension = (Dimension) o;
            if ((this.mWidth == dimension.mWidth) &&
                    (this.mHeight == dimension.mHeight)) {
                return true;
            }
        }
        return false;
    }
}



/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar

 * Qualified Name:     org.afree.graphics.geom.Dimension

 * JD-Core Version:    0.7.0.1

 */