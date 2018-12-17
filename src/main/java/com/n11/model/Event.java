package com.n11.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
public class Event implements Comparable<Event> {
	private String topic;
	private int duration;
	private LocalTime startTime;

	@JsonIgnore
	public LocalTime getEndTime() {
		return startTime.plusMinutes(duration);
	}

	public String getStartTimeStr() {
		return getStartTime().toString("hh:mma");
	}


	public String getGetEndTimeStr() {
		return getEndTime().toString("hh:mma");
	}



	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@JsonIgnore
	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Event(String topic, int duration) {
		this.topic = topic;
		this.duration = duration;
	}

	@Override
	public int compareTo(Event other) {
		return Integer.compare(this.duration, other.duration);
	}

}
