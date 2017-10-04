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
 * -----------------------------
 * LegendItemBlockContainer.java
 * -----------------------------
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
 * (C) Copyright 2006-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Jul-2006 : Version 1 (DG);
 * 06-Oct-2006 : Added tooltip and URL text fields (DG);
 * 18-May-2007 : Added seriesKey and dataset fields (DG);
 *
 */

package org.afree.chart.title;


import org.afree.data.general.Dataset;


/**
 * A container that holds all the pieces of a single legend item.
 * 
 * @since JFreeChart 1.0.2
 */
public class LegendItemBlockContainer {

    /**
     * 
     */
    private static final long serialVersionUID = -8133931573818868206L;

    /**
     * The dataset.
     * 
     * @since JFreeChart 1.0.6
     */
    private Dataset dataset;

    /**
     * The series key.
     * 
     * @since JFreeChart 1.0.6
     */
    private Comparable seriesKey;

    /** The series index. */
    private int series;

    /** The tool tip text (can be <code>null</code>). */
    private String toolTipText;

    /** The URL text (can be <code>null</code>). */
    private String urlText;

    /**
     * Creates a new legend item block.
     *
     * @param dataset
     *            the dataset.
     * @param seriesKey
     *            the series key.
     * 
     * @since JFreeChart 1.0.6
     */
    public LegendItemBlockContainer(Dataset dataset,
            Comparable seriesKey) {
        this.dataset = dataset;
        this.seriesKey = seriesKey;
    }

    /**
     * Returns a reference to the dataset for the associated legend item.
     * 
     * @return A dataset reference.
     * 
     * @since JFreeChart 1.0.6
     */
    public Dataset getDataset() {
        return this.dataset;
    }

    /**
     * Returns the series key.
     * 
     * @return The series key.
     * 
     * @since JFreeChart 1.0.6
     */
    public Comparable getSeriesKey() {
        return this.seriesKey;
    }

    /**
     * Returns the series index.
     * 
     * @return The series index.
     */
    public int getSeriesIndex() {
        return this.series;
    }

    /**
     * Returns the tool tip text.
     * 
     * @return The tool tip text (possibly <code>null</code>).
     * 
     * @since JFreeChart 1.0.3
     */
    public String getToolTipText() {
        return this.toolTipText;
    }

    /**
     * Sets the tool tip text.
     * 
     * @param text
     *            the text (<code>null</code> permitted).
     * 
     * @since JFreeChart 1.0.3
     */
    public void setToolTipText(String text) {
        this.toolTipText = text;
    }

    /**
     * Returns the URL text.
     * 
     * @return The URL text (possibly <code>null</code>).
     * 
     * @since JFreeChart 1.0.3
     */
    public String getURLText() {
        return this.urlText;
    }

    /**
     * Sets the URL text.
     * 
     * @param text
     *            the text (<code>null</code> permitted).
     * 
     * @since JFreeChart 1.0.3
     */
    public void setURLText(String text) {
        this.urlText = text;
    }


}
