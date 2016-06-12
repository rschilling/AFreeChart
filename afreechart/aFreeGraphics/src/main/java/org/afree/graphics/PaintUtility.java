package org.afree.graphics;

import android.graphics.Paint;
import android.graphics.PathEffect;
import org.afree.graphics.geom.Font;

public class PaintUtility {
	public static Paint createPaint(int paintFlag, PaintType paintType,
			float stroke, PathEffect pathEffect) {
		Paint paint = new Paint(paintFlag);
		if ((paintType instanceof SolidColor)) {
			SolidColor solidColor = (SolidColor) paintType;
			paint.setColor(solidColor.getColor());
		} else if ((paintType instanceof GradientColor)) {
			GradientColor gradientColor = (GradientColor) paintType;
			paint.setColor(gradientColor.getColor1());
		}
		paint.setStrokeWidth(stroke);
		paint.setPathEffect(pathEffect);

		return paint;
	}

	public static Paint createPaint(PaintType paintType, float stroke,
			PathEffect pathEffect) {
		return createPaint(0, paintType, stroke, pathEffect);
	}

	public static Paint createPaint(int paintFlag, PaintType paintType) {
		Paint paint = new Paint(paintFlag);
		if ((paintType instanceof SolidColor)) {
			SolidColor solidColor = (SolidColor) paintType;
			paint.setColor(solidColor.getColor());
		} else if ((paintType instanceof GradientColor)) {
			GradientColor gradientColor = (GradientColor) paintType;
			paint.setColor(gradientColor.getColor1());
		}
		return paint;
	}

	public static Paint createPaint(PaintType paintType) {
		return createPaint(0, paintType);
	}

	public static Paint createPaint(int paintFlag, PaintType paintType,
			Font font) {
		Paint paint = new Paint(paintFlag);
		if ((paintType instanceof SolidColor)) {
			SolidColor solidColor = (SolidColor) paintType;
			paint.setColor(solidColor.getColor());
		} else if ((paintType instanceof GradientColor)) {
			GradientColor gradientColor = (GradientColor) paintType;
			paint.setColor(gradientColor.getColor1());
		}
		paint.setTypeface(font.getTypeFace());
		paint.setTextSize(font.getSize());

		return paint;
	}

	public static void updatePaint(Paint paint, PaintType paintType) {
		if ((paintType instanceof SolidColor)) {
			SolidColor solidColor = (SolidColor) paintType;
			paint.setColor(solidColor.getColor());
		} else if ((paintType instanceof GradientColor)) {
			GradientColor gradientColor = (GradientColor) paintType;
			paint.setColor(gradientColor.getColor1());
		}
	}
}
