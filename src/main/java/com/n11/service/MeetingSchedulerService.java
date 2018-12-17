package com.n11.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.n11.model.Container;
import com.n11.model.Meeting;

public interface MeetingSchedulerService {

	public List generateScheduledEventData(List<Meeting> meetings);

	public void createMeeting(List<Meeting> meetings);
		
	public List getAllMettingList();
	
	public List<Container> createSchuledList();

}
