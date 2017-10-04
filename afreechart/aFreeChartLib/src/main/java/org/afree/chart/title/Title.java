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
 * ----------
 * Title.java
 * ----------
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
 * (C) Copyright 2000-2008, by David Berry and Contributors.
 *
 * Original Author:  David Berry;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Nicolas Brodu;
 *
 * Changes (from 21-Aug-2001)
 * --------------------------
 * 21-Aug-2001 : Added standard header (DG);
 * 18-Sep-2001 : Updated header (DG);
 * 14-Nov-2001 : Package com.jrefinery.common.ui.* changed to
 *               com.jrefinery.ui.* (DG);
 * 07-Feb-2002 : Changed blank space around title from Insets --> Spacer, to
 *               allow for relative or absolute spacing (DG);
 * 25-Jun-2002 : Removed unnecessary imports (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 14-Oct-2002 : Changed the event listener storage structure (DG);
 * 11-Sep-2003 : Took care of listeners while cloning (NB);
 * 22-Sep-2003 : Spacer cannot be null. Added nullpointer checks for this (TM);
 * 08-Jan-2003 : Renamed AbstractTitle --> Title and moved to separate
 *               package (DG);
 * 26-Oct-2004 : Refactored to implement Block interface, and removed redundant
 *               constants (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0
 *               release (DG);
 * 02-Feb-2005 : Changed Spacer --> RectangleInsets for padding (DG);
 * 03-May-2005 : Fixed problem in equals() method (DG);
 * 19-Sep-2008 : Added visibility flag (DG);
 *
 */

package org.afree.chart.title;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.afree.ui.HorizontalAlignment;
import org.afree.util.ObjectUtilities;
import org.afree.ui.RectangleEdge;
import org.afree.ui.RectangleInsets;
import org.afree.ui.VerticalAlignment;
import org.afree.chart.event.TitleChangeEvent;
import org.afree.chart.event.TitleChangeListener;
import org.afree.graphics.geom.RectShape;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * The base class for all chart titles. A chart can have multiple titles,
 * appearing at the top, bottom, left or right of the chart.
 * <P>
 * Concrete implementations of this class will render text and images, and hence
 * do the actual work of drawing titles.
 */
public abstract class Title extends LinearLayout {


    /** Storage for registered change listeners. */
    private transient List<TitleChangeListener> listenerList;
    
    /**
     * A flag that can be used to temporarily disable the listener mechanism.
     */
    private boolean notify;

    public Title(Context context) {
        super(context);
    }

    public Title(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Returns the flag that indicates whether or not the notification mechanism
     * is enabled.
     * 
     * @return The flag.
     */
    public boolean getNotify() {
        return this.notify;
    }

    /**
     * Sets the flag that indicates whether or not the notification mechanism
     * is enabled.  There are certain situations (such as cloning) where you
     * want to turn notification off temporarily.
     *
     * @param flag  the new value of the flag.
     */
    public void setNotify(boolean flag) {
        this.notify = flag;
        if (flag) {
            notifyListeners(new TitleChangeEvent(this));
        }
    }

    /**
     * Registers an object for notification of changes to the title.
     *
     * @param listener  the object that is being registered.
     */
    public void addChangeListener(TitleChangeListener listener) {
        this.listenerList.add(listener);
    }

    /**
     * Unregisters an object for notification of changes to the chart title.
     *
     * @param listener  the object that is being unregistered.
     */
    public void removeChangeListener(TitleChangeListener listener) {
        this.listenerList.remove(listener);
    }

    /**
     * Notifies all registered listeners that the chart title has changed in
     * some way.
     *
     * @param event  an object that contains information about the change to
     *               the title.
     */
    protected void notifyListeners(TitleChangeEvent event) {
        if(listenerList.size() == 0) {
            return;
        }
        if(notify){
            for (int i = listenerList.size() - 1; i >= 0; i--) {
                listenerList.get(i).titleChanged(event);
            }
        }
    }
    
    /**
     * Tests an object for equality with this title.
     *
     * @param obj  the object (<code>null</code> not permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Title)) {
            return false;
        }
        Title that = (Title) obj;

        if (this.notify != that.notify) {
            return false;
        }
        return super.equals(obj);
    }

}
