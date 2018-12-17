package com.n11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.n11.model.Container;
import com.n11.model.Meeting;
import com.n11.repository.MeetingRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class MeetingSchedulerServiceImpl implements MeetingSchedulerService {

	@Autowired
	MeetingRepository meetingRepository;

	@Autowired
	OrganizerService organizerService;

	@Override
	public List generateScheduledEventData(List<Meeting> meetings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createMeeting(List<Meeting> meetings) {

		meetingRepository.saveAll(meetings);

	}

	@Override
	public List<Meeting> getAllMettingList() {
		// TODO Auto-generated method stub
		return (List<Meeting>) meetingRepository.findAll();
	}

	@Override
	public List<Container> createSchuledList() {

		return organizerService.createSchuledList();
	}

}
