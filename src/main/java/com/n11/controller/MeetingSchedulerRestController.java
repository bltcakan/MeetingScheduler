package com.n11.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.n11.configuration.MeetingSchedulerProperties;
import com.n11.model.Container;
import com.n11.model.Meeting;
import com.n11.service.MeetingSchedulerService;

@CrossOrigin
@RestController
@RequestMapping(value = "/scheduler")
public class MeetingSchedulerRestController {
	private static final Logger logger = LoggerFactory.getLogger(MeetingSchedulerRestController.class);

	@Autowired
	MeetingSchedulerService meetingSchedulerService;
	@Autowired
	MeetingSchedulerProperties meetingSchedulerProperties;

	@RequestMapping(method = RequestMethod.POST, value = "/save-eventdata", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveEventData(@RequestBody @Valid List<Meeting> meetingList) {
		logger.debug("saveEventData data conroller has been activated");
		meetingSchedulerService.createMeeting(meetingList);
		HttpHeaders responseHeader = new HttpHeaders();
		return new ResponseEntity<>(responseHeader, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/saved-scheduled-list",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Container>> getSavedScheduledList() {
		logger.debug("getSavedScheduledList data conroller has been activated");
		return ResponseEntity.ok(meetingSchedulerService.createSchuledList());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ TransactionSystemException.class })
	public ResponseEntity<Set<String>> handleConstraintViolation(Exception ex, WebRequest request) {
		Throwable cause = ((TransactionSystemException) ex).getRootCause();
		if (cause instanceof ConstraintViolationException) {
			Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause)
					.getConstraintViolations();

			Set<String> messages = new HashSet<>(constraintViolations.size());
			messages.addAll(constraintViolations.stream()
					.map(constraintViolation -> String.format("%s value '%s' %s", constraintViolation.getPropertyPath(),
							constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
					.collect(Collectors.toList()));
			logger.error("Exception has been occured" + messages.toString());

			return ResponseEntity.ok(messages);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
