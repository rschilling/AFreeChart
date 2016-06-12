package org.afree.graphics;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.afree.graphics.EventListenerArray.ListenerInfo;

public class EventListenerArray {
	private List<ListenerInfo> mEventListenerArray = new CopyOnWriteArrayList<ListenerInfo>();

	public ListenerInfo[] getListenerArray() {
		return (ListenerInfo[]) this.mEventListenerArray
				.toArray(new ListenerInfo[0]);
	}

	public <T extends EventListener> ListenerInfo[] getListenerArray(
			Class<T> type) {
		if (type == null) {
			throw new IllegalArgumentException("Null 'type' argument.");
		}
		List<ListenerInfo> listenerList = new ArrayList<ListenerInfo>();
		for (ListenerInfo listenerInfo : this.mEventListenerArray) {
			if (listenerInfo.getType() == type) {
				listenerList.add(listenerInfo);
			}
		}
		return (ListenerInfo[]) listenerList.toArray(new ListenerInfo[0]);
	}

	public <T extends EventListener> void append(Class<T> type,
			EventListener eventListener) {
		if (type == null) {
			throw new IllegalArgumentException("Null 'type' argument.");
		}
		if (eventListener == null) {
			throw new IllegalArgumentException("Null 'eventListener' argument.");
		}
		ListenerInfo listenerInfo = new ListenerInfo();
		listenerInfo.setType(type);
		listenerInfo.setEventLisetner(eventListener);

		this.mEventListenerArray.add(listenerInfo);
	}

	public <T extends EventListener> void remove(Class<T> type,
			EventListener eventListener) {
		if (type == null) {
			throw new IllegalArgumentException("Null 'type' argument.");
		}
		if (eventListener == null) {
			throw new IllegalArgumentException("Null 'eventListener' argument.");
		}
		for (ListenerInfo listenerInfo : this.mEventListenerArray) {
			if ((listenerInfo.getType() == type)
					&& (listenerInfo.getEventLisetner() == eventListener)) {
				this.mEventListenerArray.remove(listenerInfo);
				break;
			}
		}
	}

	public class ListenerInfo {
		private Class<? extends EventListener> type;
		private EventListener eventLisetner;

		public ListenerInfo() {
		}

		public Class<? extends EventListener> getType() {
			return this.type;
		}

		public <T extends EventListener> void setType(Class<T> type) {
			this.type = type;
		}

		public EventListener getEventLisetner() {
			return this.eventLisetner;
		}

		public void setEventLisetner(EventListener eventLisetner) {
			this.eventLisetner = eventLisetner;
		}
	}
}