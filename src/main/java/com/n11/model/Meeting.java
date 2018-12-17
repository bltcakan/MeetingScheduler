package com.n11.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.n11.validation.ValidDuration;

@Entity
@Table(name = "t_meeting")
public class Meeting implements Comparable<Meeting> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SchedulerSeqGen")
	@SequenceGenerator(name = "SchedulerSeqGen", sequenceName = "scheduler_Seq")
	private Long id;

	@Column(name = "meeting_name")
	@NotNull(message="Meeting name not be null")
	@Size(max = 140,message="Meeting name must be shorter tahn 140 character")
	private String meetingName;

	@NotNull
	@Size(min = 4, max = 10)
	@ValidDuration(message="Duration is not valid format")
	private String duration;

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getDuration() {

		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Meeting [id=" + id + ", meetingName=" + meetingName + ", durationInMinutes=" + duration + "]";
	}

	@Override
	public int compareTo(Meeting o) {
		int currentDuration = this.getDuration().equals("lightning") ? 5
				: Integer.parseInt(this.getDuration().substring(0, this.getDuration().lastIndexOf("min")));
		int nextDuration = o.getDuration().equals("lightning") ? 5
				: Integer.parseInt(o.getDuration().substring(0, o.getDuration().lastIndexOf("min")));

		return Integer.compare(currentDuration, nextDuration);
	}

}
