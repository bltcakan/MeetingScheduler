package com.n11.test.rest;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.n11.model.Container;
import com.n11.model.Meeting;

@RunWith(SpringRunner.class)
public class MeetingSchedulerRestControllerTest {

	private RestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void when_insert_valida_data() {

		Meeting meeting = new Meeting();
		meeting.setDuration("25min");
		meeting.setMeetingName("Test_TEST");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(meeting, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/scheduler/save-eventdata",
				HttpMethod.POST, entity, String.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(201));

	}

	@Test
	public void when_insert_unvalid_duration_time() {

		Meeting meeting = new Meeting();
		meeting.setDuration("25minxcxvc");
		meeting.setMeetingName("Test_TEST");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(meeting, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/scheduler/save-eventdata",
				HttpMethod.POST, entity, String.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(400));

	}

	@Test
	public void check_scheduled_data_service() {
		ResponseEntity<Container[]> response = restTemplate
				.getForEntity("http://localhost:8080/scheduler/saved-scheduled-list", Container[].class);

		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
	}
}
