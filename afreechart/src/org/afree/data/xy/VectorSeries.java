/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:
 *    AFreeChart: http://code.google.com/p/afreechart/
 *    JFreeChart: http://www.jfree.org/jfreechart/index.html
 *    JCommon   : http://www.jfree.org/jcommon/index.html
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * [Android is a trademark of Google Inc.]
 *
 * -----------------
 * VectorSeries.java
 * -----------------
 * 
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 *
 * Original Author:  shiraki  (for ICOMSYSTECH Co.,Ltd);
 * Contributor(s):   Sato Yoshiaki ;
 *                   Niwano Masayoshi;
 *
 * Changes (from 19-Nov-2010)
 * --------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2007, 2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 30-Jan-2007 : Version 1 (DG);
 * 24-May-2007 : Renamed getDeltaXValue() --> getVectorXValue(), and likewise
 *               for getDeltaYValue() (DG);
 * 25-May-2007 : Added remove(int) and clear() methods, and moved from the
 *               experimental to the main source tree (DG);
 * 27-Nov-2007 : Removed redundant clear() method (DG);
 *
 */

package org.afree.data.xy;

import org.afree.data.ComparableObjectItem;
import org.afree.data.ComparableObjectSeries;
import org.afree.data.general.SeriesChangeEvent;

/**
 * A list of (x,y, deltaX, deltaY) data items.
 *
 * @since JFreeChart 1.0.6
 *
 * @see VectorSeriesCollection
 */
public class VectorSeries extends ComparableObjectSeries {

    /**
     * 
     */
    private static final long serialVersionUID = -4905671750211685011L;

    /**
     * Creates a new empty series.
     *
     * @param key  the series key (<code>null</code> not permitted).
     */
    public VectorSeries(Comparable key) {
        this(key, false, true);
    }

    /**
     * Constructs a new series that contains no data.  You can specify
     * whether or not duplicate x-values are allowed for the series.
     *
     * @param key  the series key (<code>null</code> not permitted).
     * @param autoSort  a flag that controls whether or not the items in the
     *                  series are sorted.
     * @param allowDuplicateXValues  a flag that controls whether duplicate
     *                               x-values are allowed.
     */
    public VectorSeries(Comparable key, boolean autoSort,
            boolean allowDuplicateXValues) {
        super(key, autoSort, allowDuplicateXValues);
    }

    /**
     * Adds a data item to the series.
     *
     * @param x  the x-value.
     * @param y  the y-value.
     * @param deltaX  the vector x.
     * @param deltaY  the vector y.
     */
    public void add(double x, double y, double deltaX, double deltaY) {
        super.add(new VectorDataItem(x, y, deltaX, deltaY), true);
    }

    /**
     * Removes the item at the specified index and sends a
     * {@link SeriesChangeEvent} to all registered listeners.
     *
     * @param index  the index.
     *
     * @return The item removed.
     */
    public ComparableObjectItem remove(int index) {
        VectorDataItem result = (VectorDataItem) this.data.remove(index);
        fireSeriesChanged();
        return result;
    }

    /**
     * Returns the x-value for the specified item.
     *
     * @param index  the item index.
     *
     * @return The x-value.
     */
    public double getXValue(int index) {
        VectorDataItem item = (VectorDataItem) this.getDataItem(index);
        return item.getXValue();
    }

    /**
     * Returns the y-value for the specified item.
     *
     * @param index  the item index.
     *
     * @return The y-value.
     */
    public double getYValue(int index) {
        VectorDataItem item = (VectorDataItem) getDataItem(index);
        return item.getYValue();
    }

    /**
     * Returns the x-component of the vector for an item in the series.
     *
     * @param index  the item index.
     *
     * @return The x-component of the vector.
     */
    public double getVectorXValue(int index) {
        VectorDataItem item = (VectorDataItem) getDataItem(index);
        return item.getVectorX();
    }

    /**
     * Returns the y-component of the vector for an item in the series.
     *
     * @param index  the item index.
     *
     * @return The y-component of the vector.
     */
    public double getVectorYValue(int index) {
        VectorDataItem item = (VectorDataItem) getDataItem(index);
        return item.getVectorY();
    }

    /**
     * Returns the data item at the specified index.
     *
     * @param index  the item index.
     *
     * @return The data item.
     */
    public ComparableObjectItem getDataItem(int index) {
        // overridden to make public
        return super.getDataItem(index);
    }

}
