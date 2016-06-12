/**
 * 
 */
package org.afree.chart.demo.view;

import org.afree.chart.demo.DemoView;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @author aaronli
 *
 */
public class WeightChartView extends DemoView {

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public WeightChartView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public WeightChartView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * @param context
	 */
	public WeightChartView(Context context) {
		this(context, null);
	}

}
