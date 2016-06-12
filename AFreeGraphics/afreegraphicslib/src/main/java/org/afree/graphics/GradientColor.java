package org.afree.graphics;

import android.graphics.Color;

public class GradientColor implements PaintType {
	private int mColor1;
	private int mColor2;
	private int mAlpha;

	public GradientColor() {
		this.mAlpha = 255;
	}

	public GradientColor(int color1, int color2) {
		this.mColor1 = color1;
		this.mColor2 = color2;
		this.mAlpha = 255;
	}

	public int getColor1() {
		return this.mColor1;
	}

	public void setColor1(int color) {
		this.mColor1 = color;
	}

	public int getColor2() {
		return this.mColor2;
	}

	public void setColor2(int color) {
		this.mColor2 = color;
	}

	public int getAlpha() {
		return this.mAlpha;
	}

	public void setAlpha(int alpha) {
		this.mAlpha = alpha;
	}

	public GradientColor getDarkerSides() {
		int c1 = Color.argb(Color.alpha(this.mColor1),
				(int) (Color.red(this.mColor1) * 0.8D),
				(int) (Color.green(this.mColor1) * 0.8D),
				(int) (Color.blue(this.mColor1) * 0.8D));
		int c2 = Color.argb(Color.alpha(this.mColor2),
				(int) (Color.red(this.mColor2) * 0.8D),
				(int) (Color.green(this.mColor2) * 0.8D),
				(int) (Color.blue(this.mColor2) * 0.8D));
		return new GradientColor(c1, c2);
	}

	public boolean equals(Object object) {
		if (!(object instanceof GradientColor)) {
			return false;
		}
		GradientColor gradientColor = (GradientColor) object;
		if ((gradientColor.getColor1() == this.mColor1)
				&& (gradientColor.getColor2() == this.mColor2)) {
			return true;
		}
		return false;
	}
}
