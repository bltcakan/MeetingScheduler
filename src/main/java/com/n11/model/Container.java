package com.n11.model;

import java.util.List;

import org.springframework.stereotype.Component;


public class Container {

	public Container(String trackName, List<Event> eventList) {

		this.trackName = trackName;
		this.eventList = eventList;
	}

	private String trackName;

	private List<Event> eventList;

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public List<Event> getEventList() {
		return eventList;
	}

	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}

}
