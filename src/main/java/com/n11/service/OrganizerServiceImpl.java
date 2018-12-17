package com.n11.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n11.configuration.MeetingSchedulerProperties;
import com.n11.dto.Track;
import com.n11.model.Container;
import com.n11.model.Event;
import com.n11.model.Meeting;
import com.n11.repository.MeetingRepository;

@Service
public class OrganizerServiceImpl implements OrganizerService {

	@Autowired
	MeetingSchedulerProperties meetingSchedulerProperties;

	@Autowired
	MeetingRepository meetingRepository;

	@Override
	public List<Container> createSchuledList() {

		List<Meeting> meetingList = (List<Meeting>) meetingRepository.findAll();
		List<Container> containerList = new ArrayList<>();
		List<Track> trackList = new ArrayList<Track>();
		List<Event> eventList = meetingToEventListConvertor(meetingList);
		Collections.sort(eventList);
		Collections.reverse(eventList);

		for (Event talk : eventList) {

			boolean isAdded = false;

			for (Track track : trackList) {
				if (track.addEvent(talk)) {
					isAdded = true;
					break;
				}
			}
			if (!isAdded) {
				Track track = new Track();
				track.addEvent(talk);
				trackList.add(track);
			}
		}
		for (Track track : trackList) {
			track.addNetworkingEventIfEnoughtFreeTime();
		}

		int count = 0;
		String baseName = "Salon";
		for (Track track : trackList) {

			count++;
			baseName = baseName + Integer.toString(count);
			containerList.add(new Container(baseName, track.getScheduledList()));
		}

		return containerList;
	}

	@Override
	public List<Event> meetingToEventListConvertor(List<Meeting> meetingList) {
		List<Event> eventList = new ArrayList<Event>();

		int duration = 0;

		for (Meeting meeting : meetingList) {
			if (meeting.getDuration().equals(meetingSchedulerProperties.getLightning())) {
				duration = 5;
			} else {
				duration = Integer.parseInt(meeting.getDuration().substring(0,
						meeting.getDuration().lastIndexOf(meetingSchedulerProperties.getMinutePostfix())));
			}
			eventList.add(new Event(meeting.getMeetingName(), duration));
		}

		return eventList;

	}

}
