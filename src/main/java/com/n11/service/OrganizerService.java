package com.n11.service;

import java.util.List;

import com.n11.model.Container;
import com.n11.model.Event;
import com.n11.model.Meeting;

public interface OrganizerService {
	public List<Event> meetingToEventListConvertor(List<Meeting> meetingList);
	
	public List<Container> createSchuledList();
}
