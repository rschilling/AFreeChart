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
 * ---------------
 * AFreeChart.java
 * ---------------
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
 * 14-Jan-2011 : renamed method name
 * 14-Jan-2011 : Updated API docs
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2000-2009, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Andrzej Porebski;
 *                   David Li;
 *                   Wolfgang Irler;
 *                   Christian W. Zuckschwerdt;
 *                   Klaus Rheinwald;
 *                   Nicolas Brodu;
 *                   Peter Kolb (patch 2603321);
 *
 * NOTE: The above list of contributors lists only the people that have
 * contributed to this source file (JFreeChart.java) - for a list of ALL
 * contributors to the project, please see the README.txt file.
 *
 * Changes (from 20-Jun-2001)
 * --------------------------
 * 20-Jun-2001 : Modifications submitted by Andrzej Porebski for legend
 *               placement;
 * 21-Jun-2001 : Removed JFreeChart parameter from Plot constructors (DG);
 * 22-Jun-2001 : Multiple titles added (original code by David Berry, with
 *               reworkings by DG);
 * 18-Sep-2001 : Updated header (DG);
 * 15-Oct-2001 : Moved data source classes into new package
 *               com.jrefinery.data.* (DG);
 * 18-Oct-2001 : New factory method for creating VerticalXYBarChart (DG);
 * 19-Oct-2001 : Moved series paint and stroke methods to the Plot class (DG);
 *               Moved static chart creation methods to new ChartFactory
 *               class (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 *               Fixed bug where chart isn't registered with the dataset (DG);
 * 07-Nov-2001 : Fixed bug where null title in constructor causes
 *               exception (DG);
 *               Tidied up event notification code (DG);
 * 17-Nov-2001 : Added getLegendItemCount() method (DG);
 * 21-Nov-2001 : Set clipping in draw method to ensure that nothing gets drawn
 *               outside the chart area (DG);
 * 11-Dec-2001 : Added the createBufferedImage() method, taken from the
 *               JFreeChartServletDemo class (DG);
 * 13-Dec-2001 : Added tooltips (DG);
 * 16-Jan-2002 : Added handleClick() method (DG);
 * 22-Jan-2002 : Fixed bug correlating legend labels with pie data (DG);
 * 05-Feb-2002 : Removed redundant tooltips code (DG);
 * 19-Feb-2002 : Added accessor methods for the backgroundImage and
 *               backgroundImageAlpha attributes (DG);
 * 21-Feb-2002 : Added static fields for INFO, COPYRIGHT, LICENCE, CONTRIBUTORS
 *               and LIBRARIES.  These can be used to display information about
 *               JFreeChart (DG);
 * 06-Mar-2002 : Moved constants to JFreeChartConstants interface (DG);
 * 18-Apr-2002 : PieDataset is no longer sorted (oldman);
 * 23-Apr-2002 : Moved dataset to the Plot class (DG);
 * 13-Jun-2002 : Added an extra draw() method (DG);
 * 25-Jun-2002 : Implemented the Drawable interface and removed redundant
 *               imports (DG);
 * 26-Jun-2002 : Added another createBufferedImage() method (DG);
 * 18-Sep-2002 : Fixed issues reported by Checkstyle (DG);
 * 23-Sep-2002 : Added new contributor (DG);
 * 28-Oct-2002 : Created main title and subtitle list to replace existing title
 *               list (DG);
 * 08-Jan-2003 : Added contributor (DG);
 * 17-Jan-2003 : Added new constructor (DG);
 * 22-Jan-2003 : Added ChartColor class by Cameron Riley, and background image
 *               alignment code by Christian W. Zuckschwerdt (DG);
 * 11-Feb-2003 : Added flag to allow suppression of chart change events, based
 *               on a suggestion by Klaus Rheinwald (DG);
 * 04-Mar-2003 : Added small fix for suppressed chart change events (see bug id
 *               690865) (DG);
 * 10-Mar-2003 : Added Benoit Xhenseval to contributors (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 15-Jul-2003 : Added an optional border for the chart (DG);
 * 11-Sep-2003 : Took care of listeners while cloning (NB);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 22-Sep-2003 : Added nullpointer checks.
 * 25-Sep-2003 : Added nullpointer checks too (NB).
 * 03-Dec-2003 : Legends are now registered by this class instead of using the
 *               old constructor way (TM);
 * 03-Dec-2003 : Added anchorPoint to draw() method (DG);
 * 08-Jan-2004 : Reworked title code, introducing line wrapping (DG);
 * 09-Feb-2004 : Created additional createBufferedImage() method (DG);
 * 05-Apr-2004 : Added new createBufferedImage() method (DG);
 * 27-May-2004 : Moved constants from JFreeChartConstants.java back to this
 *               class (DG);
 * 25-Nov-2004 : Updates for changes to Title class (DG);
 * 06-Jan-2005 : Change lookup for default background color (DG);
 * 31-Jan-2005 : Added Don Elliott to contributors (DG);
 * 02-Feb-2005 : Added clearSubtitles() method (DG);
 * 03-Feb-2005 : Added Mofeed Shahin to contributors (DG);
 * 08-Feb-2005 : Updated for RectangleConstraint changes (DG);
 * 28-Mar-2005 : Renamed Legend --> OldLegend (DG);
 * 12-Apr-2005 : Added methods to access legend(s) in subtitle list (DG);
 * 13-Apr-2005 : Added removeLegend() and removeSubtitle() methods (DG);
 * 20-Apr-2005 : Modified to collect chart entities from titles and
 *               subtitles (DG);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 06-Jun-2005 : Added addLegend() method and padding attribute, fixed equals()
 *               method (DG);
 * 24-Nov-2005 : Removed OldLegend and related code - don't want to support
 *               this in 1.0.0 final (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 27-Jan-2006 : Updated version number (DG);
 * 07-Dec-2006 : Added some missing credits (DG);
 * 17-Jan-2007 : Added Darren Jung to contributor list (DG);
 * 05-Mar-2007 : Added Sergei Ivanov to the contributor list (DG);
 * 16-Mar-2007 : Modified initial legend border (DG);
 * 22-Mar-2007 : New methods for text anti-aliasing (DG);
 * 16-May-2007 : Fixed argument check in getSubtitle(), copy list in
 *               get/setSubtitles(), and added new addSubtitle(int, Title)
 *               method (DG);
 * 05-Jun-2007 : Add change listener to default legend (DG);
 * 04-Dec-2007 : In createBufferedImage() methods, make the default image type
 *               BufferedImage.TYPE_INT_ARGB (thanks to Klaus Rheinwald) (DG);
 * 05-Dec-2007 : Fixed bug 1749124 (not registering as listener with
 *               TextTitle) (DG);
 * 23-Apr-2008 : Added new contributor (Diego Pierangeli) (DG);
 * 16-May-2008 : Added new contributor (Michael Siemer) (DG);
 * 19-Sep-2008 : Check for title visibility (DG);
 * 18-Dec-2008 : Use ResourceBundleWrapper - see patch 1607918 by
 *               Jess Thrysoee (DG);
 * 19-Mar-2009 : Added entity support - see patch 2603321 by Peter Kolb (DG);
 *
 */

package org.afree.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.afree.ui.Align;
import org.afree.ui.HorizontalAlignment;
import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;
import org.afree.ui.Size2D;
import org.afree.ui.VerticalAlignment;
import org.afree.util.ObjectUtilities;
import org.afree.util.PaintTypeUtilities;
import org.afree.data.Range;
import org.afree.chart.entity.EntityCollection;
import org.afree.chart.entity.AFreeChartEntity;
import org.afree.chart.event.ChartChangeEvent;
import org.afree.chart.event.ChartChangeListener;
import org.afree.chart.event.ChartProgressEvent;
import org.afree.chart.event.ChartProgressListener;
import org.afree.chart.event.PlotChangeEvent;
import org.afree.chart.event.PlotChangeListener;
import org.afree.chart.event.TitleChangeEvent;
import org.afree.chart.event.TitleChangeListener;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.Plot;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.title.LegendTitle;
import org.afree.chart.title.Title;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.PaintType;
import org.afree.graphics.PaintUtility;
import org.afree.graphics.SolidColor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Region.Op;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A chart class implemented using the Android APIs. The current version
 * supports bar charts, line charts, pie charts and xy plots (including time
 * series data).
 * <P>
 * AFreeChart coordinates several objects to achieve its aim of being able to
 * draw a chart on a graphics device: a list of {@link Title} objects
 * (which often includes the chart's legend), a {@link Plot} and a
 * {@link org.afree.data.general.Dataset} (the plot in turn manages a domain
 * axis and a range axis).
 * <P>
 * The {@link ChartFactory} class contains static methods for creating
 * 'ready-made' charts.
 * 
 * @see ChartFactory
 * @see Title
 * @see Plot
 */
public class AFreeChart extends LinearLayout implements TitleChangeListener,
        PlotChangeListener  {


    /** Draws the visual representation of the data. */
    private Plot plot;

    /** Storage for registered change listeners. */
    private transient List<ChartChangeListener> changeListeners;

    /** Storage for registered progress listeners. */
    private transient List<ChartProgressListener> progressListeners;

    private Title mTitle;
    private Title mSubtitle;
    private LegendTitle mLegend;

    /**
     * A flag that can be used to enable/disable notification of chart change
     * events.
     */
    private boolean notify;


    public AFreeChart(Context context) {
        super(context);
    }

    public AFreeChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Creates a new chart with the given title and plot. The
     * <code>createLegend</code> argument specifies whether or not a legend
     * should be added to the chart. <br>
     * <br>
     * Note that the {@link ChartFactory} class contains a range of static
     * methods that will return ready-made charts, and often this is a more
     * convenient way to create charts than using this constructor.
     * 
     * @param title
     *            the chart title (<code>null</code> permitted).
     * @param plot
     *            controller of the visual representation of the data (
     *            <code>null</code> not permitted).
     * @param createLegend
     *            a flag indicating whether or not a legend should be created
     *            for the chart.
     */
    public AFreeChart(Context ctx, Title title, Plot plot,
                      boolean createLegend) {

        super(ctx);
        if (plot == null) {
            throw new NullPointerException("Null 'plot' argument.");
        }
        // create storage for listeners...
        this.progressListeners = new CopyOnWriteArrayList<ChartProgressListener>();
        this.changeListeners = new CopyOnWriteArrayList<ChartChangeListener>();
        this.notify = true; // default is to notify listeners when the

        this.plot = plot;
        plot.addChangeListener(this);

        // create a legend, if requested...
        if (createLegend) {
            LegendTitle legend = new LegendTitle(ctx, this.plot);
            addView(legend);
            legend.addChangeListener(this);
        }

        // add the chart title, if one has been specified...
        if (title != null) {
            mTitle = title;
            addView(mTitle);

        }

    }

    /**
     * Creates a new chart with the given title and plot.  The chart will
     * have a legend added automatically.
     * <br><br>
     * Note that the {@link ChartFactory} class contains a range
     * of static methods that will return ready-made charts, and often this
     * is a more convenient way to create charts than using this constructor.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param plot  the plot (<code>null</code> not permitted).
     */
    public AFreeChart(Context ctx, Title title, Plot plot) {
        this(ctx, title, plot, true);
    }
    
    /**
     * Creates a new chart based on the supplied plot.  The chart will have
     * a legend added automatically, but no title (although you can easily add
     * one later).
     * <br><br>
     * Note that the  {@link ChartFactory} class contains a range
     * of static methods that will return ready-made charts, and often this
     * is a more convenient way to create charts than using this constructor.
     *
     * @param plot  the plot (<code>null</code> not permitted).
     */
    public AFreeChart(Context ctx, Plot plot) {
        this(ctx, null, plot, true);
    }

    /**
     * Returns the main chart title. Very often a chart will have just one
     * title, so we make this case simple by providing accessor methods for the
     * main title. However, multiple titles are supported - see the
     * {@link #addSubtitle(Title)} method.
     * 
     * @return The chart title (possibly <code>null</code>).
     *
     */
    public Title getTitle() {
        return mTitle;
    }

    /**
     * Sets the main title for the chart and sends a {@link ChartChangeEvent} to
     * all registered listeners. If you do not want a title for the chart, set
     * it to <code>null</code>. If you want more than one title on a chart, use
     * the {@link #addSubtitle(Title)} method.
     * 
     * @param title
     *            the title (<code>null</code> permitted).
     * 
     * @see #getTitle()
     */
    public void setTitle(Title title) {
       mTitle = title;
        fireChartChanged();
    }


    /**
     * Adds a legend to the plot and sends a {@link ChartChangeEvent} to all
     * registered listeners.
     * 
     * @param legend
     *            the legend (<code>null</code> not permitted).
     *
     */
    public void addLegend(LegendTitle legend) {
        addSubtitle(legend);
    }

    public void addSubtitle(Title subtitle) {
        if (subtitle == null) {
            throw new IllegalArgumentException("Null 'subtitle' argument.");
        }

        if (mSubtitle != null){
            removeView(mSubtitle);
        }
        mSubtitle = subtitle;
        addView(mSubtitle);
        subtitle.addChangeListener(this);
        fireChartChanged();
    }


    /**
     * Returns the legend for the chart, if there is one. Note that a chart can
     * have more than one legend - this method returns the first.
     * 
     * @return The legend (possibly <code>null</code>).
     *
     */
    public LegendTitle getLegend() {
        return mLegend;
    }

    /**
     * Returns the plot for the chart. The plot is a class responsible for
     * coordinating the visual representation of the data, including the axes
     * (if any).
     * 
     * @return The plot.
     */
    public Plot getPlot() {
        return this.plot;
    }

    /**
     * Returns the plot cast as a {@link CategoryPlot}.
     * <p>
     * NOTE: if the plot is not an instance of {@link CategoryPlot}, then a
     * <code>ClassCastException</code> is thrown.
     * 
     * @return The plot.
     * 
     * @see #getPlot()
     */
    public CategoryPlot getCategoryPlot() {
        return (CategoryPlot) this.plot;
    }

    /**
     * Returns the plot cast as an {@link XYPlot}.
     * <p>
     * NOTE: if the plot is not an instance of {@link XYPlot}, then a
     * <code>ClassCastException</code> is thrown.
     * 
     * @return The plot.
     * 
     * @see #getPlot()
     */
    public XYPlot getXYPlot() {
        return (XYPlot) this.plot;
    }



    /**
     * Registers an object for notification of changes to the chart.
     *
     * @param listener  the listener (<code>null</code> not permitted).
     *
     * @see #removeChangeListener(ChartChangeListener)
     */
    public void addChangeListener(ChartChangeListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Null 'listener' argument.");
        }
        this.changeListeners.add(listener);
    }

    /**
     * Deregisters an object for notification of changes to the chart.
     *
     * @param listener  the listener (<code>null</code> not permitted)
     *
     * @see #addChangeListener(ChartChangeListener)
     */
    public void removeChangeListener(ChartChangeListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Null 'listener' argument.");
        }
        this.changeListeners.remove(listener);
    }

    /**
     * Sends a default {@link ChartChangeEvent} to all registered listeners.
     * <P>
     * This method is for convenience only.
     */
    public void fireChartChanged() {
        ChartChangeEvent event = new ChartChangeEvent(this);
        notifyListeners(event);
    }

    /**
     * Sends a {@link ChartChangeEvent} to all registered listeners.
     *
     * @param event  information about the event that triggered the
     *               notification.
     */
    protected void notifyListeners(ChartChangeEvent event) {
        if(changeListeners.size() == 0) {
            return;
        }
        if (this.notify) {
            for (int i = changeListeners.size() - 1; i >= 0; i--) {
                changeListeners.get(i).chartChanged(event);
            }

        }
    }

    /**
     * Registers an object for notification of progress events relating to the
     * chart.
     *
     * @param listener  the object being registered.
     *
     * @see #removeProgressListener(ChartProgressListener)
     */
    public void addProgressListener(ChartProgressListener listener) {
        this.progressListeners.add(listener);
    }

    /**
     * Deregisters an object for notification of changes to the chart.
     *
     * @param listener  the object being deregistered.
     *
     * @see #addProgressListener(ChartProgressListener)
     */
    public void removeProgressListener(ChartProgressListener listener) {
        this.progressListeners.remove(listener);
    }

    /**
     * Sends a {@link ChartProgressEvent} to all registered listeners.
     *
     * @param event  information about the event that triggered the
     *               notification.
     */
    protected void notifyListeners(ChartProgressEvent event) {
        if(progressListeners.size() == 0) {
            return;
        }
        for (int i = progressListeners.size() - 1; i >= 0; i--) {
            progressListeners.get(i).chartProgress(event);
        }
    }

    /**
     * Receives notification that a chart title has changed, and passes this
     * on to registered listeners.
     *
     * @param event  information about the chart title change.
     */
    public void titleChanged(TitleChangeEvent event) {
        event.setChart(this);
        notifyListeners(event);
    }

    /**
     * Receives notification that the plot has changed, and passes this on to
     * registered listeners.
     *
     * @param event  information about the plot change.
     */
    public void plotChanged(PlotChangeEvent event) {
        event.setChart(this);
        notifyListeners(event);
    }

    /**
     * Sets a flag that controls whether or not listeners receive
     * {@link ChartChangeEvent} notifications.
     * 
     * @param notify
     *            a boolean.
     */
    public void setNotify(boolean notify) {
        this.notify = notify;
        // if the flag is being set to true, there may be queued up changes...
        if (notify) {
            notifyListeners(new ChartChangeEvent(this));
        }
    }

}
