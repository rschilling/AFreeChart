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
    public String toolTipText;

    /** The URL. */
    public String url;


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
    public static void addEntity(PlotRenderingInfo info,
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


}
