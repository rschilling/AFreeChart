package org.afree.graphics.geom;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;

public class CubicCurveShape
        implements Shape {
    private float mX1;
    private float mY1;
    private float mX2;
    private float mY2;
    private float mCtrlx1;
    private float mCtrly1;
    private float mCtrlx2;
    private float mCtrly2;
    private Path mPath;
    private Region mRegion;
    private Region regionBuffer = new Region();

    public CubicCurveShape() {
        this(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    }

    public CubicCurveShape(float x1, float y1, float ctrlx1, float ctrly1, float ctrlx2, float ctrly2, float x2, float y2) {
        this.mX1 = x1;
        this.mY1 = y1;
        this.mCtrlx1 = ctrlx1;
        this.mCtrly1 = ctrly1;
        this.mCtrlx2 = ctrlx2;
        this.mCtrly2 = ctrly2;
        this.mX2 = x2;
        this.mY2 = y2;
        this.mPath = new Path();
        this.mRegion = new Region();

        update();
    }

    public CubicCurveShape(CubicCurveShape curve) {
        this.mX1 = curve.mX1;
        this.mY1 = curve.mY1;
        this.mCtrlx1 = curve.mCtrlx1;
        this.mCtrly1 = curve.mCtrly1;
        this.mCtrlx2 = curve.mCtrlx2;
        this.mCtrly2 = curve.mCtrly2;
        this.mX2 = curve.mX2;
        this.mY2 = curve.mY2;
        this.mPath = curve.mPath;
        this.mRegion = curve.mRegion;
    }

    public Path getPath() {
        return this.mPath;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(this.mPath, paint);
    }

    public void fill(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(this.mPath, paint);
    }

    public void fillAndStroke(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(this.mPath, paint);
    }

    public void clip(Canvas canvas) {
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
        return new RectShape(this.regionBuffer.getBounds());
    }

    public void getBounds(RectShape rect) {
        rect.setRect(this.regionBuffer.getBounds());
    }

    public boolean intersects(float x, float y, float width, float height) {
        Region region = new Region(this.mRegion);
        float tmpw;
        float tmpx;

        if (x < width + x) {
            tmpx = x;
            tmpw = width;
        } else {
            tmpx = width + x;
            tmpw = Math.abs(width);
        }
        float tmph;
        float tmpy;

        if (y < height + y) {
            tmpy = y;
            tmph = height;
        } else {
            tmpy = height + y;
            tmph = Math.abs(height);
        }
        return region.op((int) tmpx, (int) tmpy, (int) (tmpx + tmpw), (int) (tmpy + tmph), Region.Op.INTERSECT);
    }

    public boolean intersects(Rect rect) {
        return intersects(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }

    public boolean intersects(RectShape rect) {
        return intersects(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    public void translate(float x, float y) {
        this.mX1 += x;
        this.mY1 += y;
        this.mCtrlx1 += x;
        this.mCtrly1 += y;
        this.mCtrlx2 += x;
        this.mCtrly2 += y;
        this.mX2 += x;
        this.mY2 += y;

        update();
    }

    public void setCurve(float x1, float y1, float ctrlx1, float ctrly1, float ctrlx2, float ctrly2, float x2, float y2) {
        this.mX1 = x1;
        this.mY1 = y1;
        this.mCtrlx1 = ctrlx1;
        this.mCtrly1 = ctrly1;
        this.mCtrlx2 = ctrlx2;
        this.mCtrly2 = ctrly2;
        this.mX2 = x2;
        this.mY2 = y2;

        update();
    }

    public void setCurve(double x1, double y1, double ctrlx1, double ctrly1, double ctrlx2, double ctrly2, double x2, double y2) {
        this.mX1 = ((float) x1);
        this.mY1 = ((float) y1);
        this.mCtrlx1 = ((float) ctrlx1);
        this.mCtrly1 = ((float) ctrly1);
        this.mCtrlx2 = ((float) ctrlx2);
        this.mCtrly2 = ((float) ctrly2);
        this.mX2 = ((float) x2);
        this.mY2 = ((float) y2);

        update();
    }

    private void update() {
        this.mPath.reset();
        this.mPath.moveTo(this.mX1, this.mY1);
        this.mPath.cubicTo(this.mCtrlx1, this.mCtrly1, this.mCtrlx2, this.mCtrly2, this.mX2, this.mY2);

        RectF rect = new RectF();
        this.mPath.computeBounds(rect, false);
        this.mRegion = new Region((int) rect.left, (int) rect.top, (int) rect.right, (int) rect.bottom);
        this.regionBuffer.set(this.mRegion);

        this.mPath.lineTo(this.mX2 + 1.0F, this.mY2);
        this.mPath.cubicTo(this.mCtrlx2 + 1.0F, this.mCtrly2, this.mCtrlx1 + 1.0F, this.mCtrly1, this.mX1 + 1.0F, this.mY1);
        this.mPath.close();

        this.mRegion.setPath(this.mPath, this.mRegion);
    }

    public boolean equals(Object o) {
        if ((o instanceof CubicCurveShape)) {
            CubicCurveShape cubicCurveShape = (CubicCurveShape) o;
            if ((this.mX1 == cubicCurveShape.mX1) &&
                    (this.mY1 == cubicCurveShape.mY1) &&
                    (this.mX2 == cubicCurveShape.mX2) &&
                    (this.mY2 == cubicCurveShape.mY2) &&
                    (this.mCtrlx1 == cubicCurveShape.mCtrlx1) &&
                    (this.mCtrly1 == cubicCurveShape.mCtrly1) &&
                    (this.mCtrlx2 == cubicCurveShape.mCtrlx2) &&
                    (this.mCtrly2 == cubicCurveShape.mCtrly2)) {
                return true;
            }
        }
        return false;
    }

    public Shape clone() {
        return new CubicCurveShape(this);
    }
}



/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar

 * Qualified Name:     org.afree.graphics.geom.CubicCurveShape

 * JD-Core Version:    0.7.0.1

 */