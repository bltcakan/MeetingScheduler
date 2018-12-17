package com.n11.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.joda.time.LocalTime;

import com.n11.constant.MeetingSchedulerConstant;
import com.n11.model.Event;
import com.n11.model.Meeting;

public class Track {

	private List<Event> morningSessionTasks = new ArrayList<Event>();
	private List<Event> afterNoonSessionTasks = new ArrayList<Event>();

	private int getRemaingTim(List<Event> talks) {
		int remaingTime = 0;
		if (talks == null) {
			return remaingTime;
		}
		for (Event talk : talks) {
			remaingTime += talk.getDuration();
		}
		return remaingTime;
	}

	private int getFreeTimeInAfterNoonSession() {
		return MeetingSchedulerConstant.AFTERNOON_SESSION_DURATION - getRemaingTim(afterNoonSessionTasks);
	}

	private int getFreeTimeInMorningSession() {
		return MeetingSchedulerConstant.MORNING_SESSION_DURATION - getRemaingTim(morningSessionTasks);
	}

	public List<Event> getScheduledList() {
		List<Event> eventList = new ArrayList<Event>(morningSessionTasks);
		Event lunch = new Event("Launch", 60);
		LocalTime lunchstartTime = new LocalTime(12, 0);
		lunch.setStartTime(lunchstartTime);
		eventList.add(lunch);
		eventList.addAll(afterNoonSessionTasks);
		return eventList;
	}

	public void addNetworkingEventIfEnoughtFreeTime() {
		Event lastTalk = afterNoonSessionTasks.get(afterNoonSessionTasks.size() - 1);
		LocalTime startTime = lastTalk.getEndTime().isBefore(new LocalTime(16, 0)) ? new LocalTime(16, 0)
				: lastTalk.getEndTime();
		long durationOfNetworkingEvent = TimeUnit.MILLISECONDS
				.toMinutes(new LocalTime(17, 0).getMillisOfDay() - lastTalk.getEndTime().getMillisOfDay());
		if (durationOfNetworkingEvent > 1) {
			if (durationOfNetworkingEvent > 60) {
				durationOfNetworkingEvent = MeetingSchedulerConstant.MAX_NETWORKING_EVENT_INTERVAL;
			}
			Event networkingEvent = new Event("Networking Event", (int) durationOfNetworkingEvent);
			networkingEvent.setStartTime(startTime);
			afterNoonSessionTasks.add(networkingEvent);
		}

	}

	public List<Event> convert2EventList(List<Meeting> meetingList) {
		List<Event> eventList = new ArrayList<Event>();
		Event event = null;
		int duration = 0;
		for (Meeting meeting : meetingList) {
			if (meeting.getDuration().equals("lighting")) {
				duration = 5;
			} else {
				duration = Integer
						.parseInt(meeting.getDuration().substring(0, meeting.getDuration().lastIndexOf("min")));
			}
			new Event(meeting.getMeetingName(), duration);
			eventList.add(event);
		}
		return eventList;
	}

	public boolean addEvent(Event event) {
		if (getFreeTimeInMorningSession() >= event.getDuration()) {
			if (morningSessionTasks.size() == 0) {
				LocalTime startTime = new LocalTime(9, 0);
				event.setStartTime(startTime);
			} else {
				Event lastTalk = morningSessionTasks.get(morningSessionTasks.size() - 1);
				LocalTime startTime = lastTalk.getEndTime();
				event.setStartTime(startTime);
			}
			morningSessionTasks.add(event);
			return true;
		} else if (getFreeTimeInAfterNoonSession() >= event.getDuration()) {
			if (afterNoonSessionTasks.size() == 0) {
				LocalTime startTime = new LocalTime(13, 0);
				event.setStartTime(startTime);
			} else {
				Event lastEvent = afterNoonSessionTasks.get(afterNoonSessionTasks.size() - 1);
				LocalTime startTime = lastEvent.getEndTime();
				event.setStartTime(startTime);
			}
			afterNoonSessionTasks.add(event);
			return true;
		} else {
			return false;
		}
	}
}
