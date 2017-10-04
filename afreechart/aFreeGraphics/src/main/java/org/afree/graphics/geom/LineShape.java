package org.afree.graphics.geom;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;

public class LineShape
        implements Shape {
    private float mX1;
    private float mY1;
    private float mX2;
    private float mY2;

    public LineShape() {
        this(0.0F, 0.0F, 0.0F, 0.0F);
    }

    public LineShape(PointF p1, PointF p2) {
        this(p1.x, p1.y, p2.x, p2.y);
    }

    public LineShape(float x1, float y1, float x2, float y2) {
        this.mX1 = x1;
        this.mY1 = y1;
        this.mX2 = x2;
        this.mY2 = y2;
    }

    public LineShape(double x1, double y1, double x2, double y2) {
        this.mX1 = ((float) x1);
        this.mY1 = ((float) y1);
        this.mX2 = ((float) x2);
        this.mY2 = ((float) y2);
    }

    public LineShape(LineShape lineShape) {
        this(lineShape.getX1(), lineShape.getY1(), lineShape.getX2(), lineShape.getY2());
    }

    public static boolean linesIntersect(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
        boolean flag1 = isCCW(x1, y1, x2, y2, x3, y3) ^ isCCW(x1, y1, x2, y2, x4, y4);
        boolean flag2 = isCCW(x3, y3, x4, y4, x1, y1) ^ isCCW(x3, y3, x4, y4, x2, y2);
        return (flag1) && (flag2);
    }

    private static boolean isCCW(float x1, float y1, float x2, float y2, float x3, float y3) {
        if (getSignedTriangleArea(x1, y1, x2, y2, x3, y3) > 0.0F) {
            return true;
        }
        return false;
    }

    private static float getSignedTriangleArea(float x1, float y1, float x2, float y2, float x3, float y3) {
        x1 -= x3;
        y1 -= y3;
        x2 -= x3;
        y2 -= y3;

        return (x1 * y2 - y1 * x2) * 0.5F;
    }

    public Path getPath() {
        Path path = new Path();
        path.moveTo(this.mX1, this.mY1);
        path.lineTo(this.mX2, this.mY2);
        return path;
    }

    public void clip(Canvas canvas) {
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(this.mX1, this.mY1, this.mX2, this.mY2, paint);
    }

    public void fill(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawLine(this.mX1, this.mY1, this.mX2, this.mY2, paint);
    }

    public void fillAndStroke(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawLine(this.mX1, this.mY1, this.mX2, this.mY2, paint);
    }

    public boolean contains(float x, float y) {
        return false;
    }

    public boolean contains(float x, float y, float width, float height) {
        return false;
    }

    public boolean contains(PointF point) {
        return false;
    }

    public boolean contains(RectShape rect) {
        return false;
    }

    @Deprecated
    public RectShape getBounds() {
        return new RectShape(this.mX1, this.mY1, this.mX2 - this.mX1, this.mY2 - this.mY1);
    }

    public void getBounds(RectShape rect) {
        rect.setRect(this.mX1, this.mY1, this.mX2 - this.mX1, this.mY2 - this.mY1);
    }

    public boolean intersects(float x, float y, float width, float height) {
        return intersects(new RectShape(x, y, width, height));
    }

    public boolean intersects(Rect rect) {
        return intersects(rect.left, rect.top, rect.width(), rect.height());
    }

    public boolean intersects(RectShape rect) {
        return rect.intersectsLine(getX1(), getY1(), getX2(), getY2());
    }

    public boolean intersectsLine(float x1, float y1, float x2, float y2) {
        return linesIntersect(x1, y1, x2, y2, getX1(), getY1(), getX2(),
                getY2());
    }

    public void translate(float x, float y) {
        this.mX1 += x;
        this.mY1 += y;
        this.mX2 += x;
        this.mY2 += y;
    }

    public void setLine(float x1, float y1, float x2, float y2) {
        this.mX1 = x1;
        this.mY1 = y1;
        this.mX2 = x2;
        this.mY2 = y2;
    }

    public void setLine(double x1, double y1, double x2, double y2) {
        this.mX1 = ((float) x1);
        this.mY1 = ((float) y1);
        this.mX2 = ((float) x2);
        this.mY2 = ((float) y2);
    }

    public void setLine(PointF p1, PointF p2) {
        setLine(p1.x, p1.y, p2.x, p2.y);
    }

    public float getX1() {
        return this.mX1;
    }

    public float getY1() {
        return this.mY1;
    }

    public float getX2() {
        return this.mX2;
    }

    public float getY2() {
        return this.mY2;
    }

    public PointF getP1() {
        return new PointF(this.mX1, this.mY1);
    }

    public void setP1(PointF p) {
        this.mX1 = p.x;
        this.mY1 = p.y;
    }

    public PointF getP2() {
        return new PointF(this.mX2, this.mY2);
    }

    public void setP2(PointF p) {
        this.mX2 = p.x;
        this.mY2 = p.y;
    }

    public Shape clone() {
        return new LineShape(this);
    }

    public boolean equals(Object o) {
        if ((o instanceof LineShape)) {
            LineShape lineShape = (LineShape) o;
            if ((this.mX1 == lineShape.mX1) &&
                    (this.mY1 == lineShape.mY1) &&
                    (this.mX2 == lineShape.mX2) &&
                    (this.mY2 == lineShape.mY2)) {
                return true;
            }
        }
        return false;
    }
}



/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar

 * Qualified Name:     org.afree.graphics.geom.LineShape

 * JD-Core Version:    0.7.0.1

 */