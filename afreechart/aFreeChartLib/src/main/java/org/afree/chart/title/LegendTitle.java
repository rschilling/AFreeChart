/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2009, by Object Refinery Limited and Contributors.
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
 * ----------------
 * LegendTitle.java
 * ----------------
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
 * 14-Jan-2011 : Updated API docs
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2002-2009, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Pierre-Marie Le Biot;
 *
 * Changes
 * -------
 * 25-Nov-2004 : First working version (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 08-Feb-2005 : Updated for changes in RectangleConstraint class (DG);
 * 11-Feb-2005 : Implemented PublicCloneable (DG);
 * 23-Feb-2005 : Replaced chart reference with LegendItemSource (DG);
 * 16-Mar-2005 : Added itemFont attribute (DG);
 * 17-Mar-2005 : Fixed missing fillShape setting (DG);
 * 20-Apr-2005 : Added new draw() method (DG);
 * 03-May-2005 : Modified equals() method to ignore sources (DG);
 * 13-May-2005 : Added settings for legend item label and graphic padding (DG);
 * 09-Jun-2005 : Fixed serialization bug (DG);
 * 01-Sep-2005 : Added itemPaint attribute (PMLB);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Jul-2006 : Use new LegendItemBlockContainer to restore support for
 *               LegendItemEntities (DG);
 * 06-Oct-2006 : Add tooltip and URL text to legend item (DG);
 * 13-Dec-2006 : Added support for GradientPaint in legend items (DG);
 * 16-Mar-2007 : Updated border drawing for changes in AbstractBlock (DG);
 * 18-May-2007 : Pass seriesKey and dataset to legend item block (DG);
 * 15-Aug-2008 : Added getWrapper() method (DG);
 * 19-Mar-2009 : Added entity support - see patch 2603321 by Peter Kolb (DG);
 *
 */

package org.afree.chart.title;

import java.io.Serializable;
import java.util.List;

import org.afree.chart.plot.Plot;
import org.afree.ui.RectangleAnchor;
import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;
import org.afree.ui.Size2D;
import org.afree.chart.LegendItem;
import org.afree.chart.LegendItemCollection;
import org.afree.chart.LegendItemSource;
import org.afree.chart.entity.EntityCollection;
import org.afree.chart.entity.StandardEntityCollection;
import org.afree.chart.entity.TitleEntity;
import org.afree.chart.event.TitleChangeEvent;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;


/**
 * A chart title that displays a legend for the data in the chart.
 * <P>
 * The title can be populated with legend items manually, or you can assign a
 * reference to the plot, in which case the legend items will be automatically
 * created to match the dataset(s).
 */
public class LegendTitle extends Title  {

    /** The sources for legend items. */
    private LegendItemSource[] sources;
    private Plot mPlot;


    public LegendTitle(Context context, Plot plot) {
        super(context);
        mPlot = plot;
    }

    /**
     * Returns the legend item sources.
     * 
     * @return The sources.
     */
    public LegendItemSource[] getSources() {
        return this.sources;
    }

    /**
     * Sets the legend item sources and sends a {@link TitleChangeEvent} to all
     * registered listeners.
     * 
     * @param sources
     *            the sources (<code>null</code> not permitted).
     */
    public void setSources(LegendItemSource[] sources) {
        if (sources == null) {
            throw new IllegalArgumentException("Null 'sources' argument.");
        }
        this.sources = sources;
        notifyListeners(new TitleChangeEvent(this));
    }


}
