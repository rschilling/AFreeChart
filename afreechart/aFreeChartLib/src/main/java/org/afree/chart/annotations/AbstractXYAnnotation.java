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
 * -------------------------
 * AbstractXYAnnotation.java
 * -------------------------
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
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 29-Sep-2004 : Version 1 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Mar-2007 : Implemented hashCode() (DG);
 *
 */

package org.afree.chart.annotations;

import org.afree.chart.axis.ValueAxis;
import org.afree.chart.entity.ChartEntity;
import org.afree.chart.entity.XYAnnotationEntity;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.plot.XYPlot;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;

import android.content.Context;
import android.graphics.Canvas;

import java.util.List;


/**
 * The interface that must be supported by annotations that are to be added to
 * an {@link XYPlot}.
 */
public abstract class AbstractXYAnnotation implements XYAnnotation {

    /** The tool tip text. */
    private String toolTipText;

    /** The URL. */
    private String url;

    /**
     * Creates a new instance that has no tool tip or URL specified.
     */
    protected AbstractXYAnnotation() {
        this.toolTipText = null;
        this.url = null;
    }

    /**
     * Returns the tool tip text for the annotation.  This will be displayed in
     * a {@code org.afree.chart.demo.DemoView} when the mouse pointer hovers over
     * the annotation.
     *
     * @return The tool tip text (possibly <code>null</code>).
     *
     * @see #setToolTipText(String)
     */
    public String getToolTipText() {
        return this.toolTipText;
    }

    /**
     * Sets the tool tip text for the annotation.
     *
     * @param text  the tool tip text (<code>null</code> permitted).
     *
     * @see #getToolTipText()
     */
    public void setToolTipText(String text) {
        this.toolTipText = text;
    }

    /**
     * Returns the URL for the annotation.  This URL will be used to provide
     * hyperlinks when an HTML image map is created for the chart.
     *
     * @return The URL (possibly <code>null</code>).
     *
     * @see #setURL(String)
     */
    public String getURL() {
        return this.url;
    }

    /**
     * Sets the URL for the annotation.
     *
     * @param url  the URL (<code>null</code> permitted).
     *
     * @see #getURL()
     */
    public void setURL(String url) {
        this.url = url;
    }

    /**
     * Draws the annotation.
     *
     * @param canvas  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the data area.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param rendererIndex  the renderer index.
     * @param info  if supplied, this info object will be populated with
     *              entity information.
     */
    public abstract void draw(Canvas canvas, XYPlot plot, RectShape dataArea,
                              ValueAxis domainAxis, ValueAxis rangeAxis,
                              int rendererIndex,
                              PlotRenderingInfo info);

    /**
     * A utility method for adding an {@link XYAnnotationEntity} to
     * a {@link PlotRenderingInfo} instance.
     *
     * @param info  the plot rendering info (<code>null</code> permitted).
     * @param hotspot  the hotspot area.
     * @param rendererIndex  the renderer index.
     * @param toolTipText  the tool tip text.
     * @param urlText  the URL text.
     */
    protected void addEntity(PlotRenderingInfo info,
                             Shape hotspot, int rendererIndex,
                             String toolTipText, String urlText) {
        if (info == null) {
            return;
        }
        List<ChartEntity> entities = info.getOwner().entities;
        if (entities == null) {
            return;
        }
        XYAnnotationEntity entity = new XYAnnotationEntity(hotspot,
                rendererIndex, toolTipText, urlText);
        entities.add(entity);
    }

    /**
     * Tests this annotation for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractXYAnnotation)) {
            return false;
        }
              return true;
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    public int hashCode() {
        int result = 193;
        if (this.toolTipText != null) {
            result = 37 * result + this.toolTipText.hashCode();
        }
        if (this.url != null) {
            result = 37 * result + this.url.hashCode();
        }
        return result;
    }

}
