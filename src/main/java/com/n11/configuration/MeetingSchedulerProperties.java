package com.n11.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@ConfigurationProperties
@Order(value=4)
public class MeetingSchedulerProperties {

	@Value("${app.lighting}")
	private String lightning;

	@Value("${app.minute.postfix}")
	private String minutePostfix;

	@Value("${app.lunchname}")
	private String lunchName;

	@Value("${app.networkingeventname}")
	private String networkinEventName;

	public String SPACE = " ";

	@Value("${app.timepatern}")
	private String timePatern;

	@Value("${app.morning_duration}")
	private int morningMaxInterval;

	@Value("${app.lunch_duration}")
	private int launchDuration;

	@Value("${app.afternoon_duration}")
	private int afternoonDuration;
	
	@Value("${app.morning_duration}")
	private int morningDuration;

	@Value("${app.morning__start_time}")
	private int morningStartTime;

	@Value("${app.lunch_start_time}")
	private int launchStartTime;

	@Value("${app.afternoon_start_time}")
	private int afternoonStartTime;

	@Value("${app.max_event_duration}")
	private int maxEventDuration;

	public String getLightning() {
		return lightning;
	}

	public void setLightning(String lightning) {
		this.lightning = lightning;
	}

	public String getMinutePostfix() {
		return minutePostfix;
	}

	public void setMinutePostfix(String minutePostfix) {
		this.minutePostfix = minutePostfix;
	}

	public String getLunchName() {
		return lunchName;
	}

	public void setLunchName(String lunchName) {
		this.lunchName = lunchName;
	}

	public String getNetworkinEventName() {
		return networkinEventName;
	}

	public void setNetworkinEventName(String networkinEventName) {
		this.networkinEventName = networkinEventName;
	}

	public String getSPACE() {
		return SPACE;
	}

	public void setSPACE(String sPACE) {
		SPACE = sPACE;
	}

	public String getTimePatern() {
		return timePatern;
	}

	public void setTimePatern(String timePatern) {
		this.timePatern = timePatern;
	}

	public int getMorningMaxInterval() {
		return morningMaxInterval;
	}

	public void setMorningMaxInterval(int morningMaxInterval) {
		this.morningMaxInterval = morningMaxInterval;
	}

	public int getLaunchDuration() {
		return launchDuration;
	}

	public void setLaunchDuration(int launchDuration) {
		this.launchDuration = launchDuration;
	}

	public int getAfternoonDuration() {
		return afternoonDuration;
	}

	public void setAfternoonDuration(int afternoonDuration) {
		this.afternoonDuration = afternoonDuration;
	}

	public int getMorningStartTime() {
		return morningStartTime;
	}

	public void setMorningStartTime(int morningStartTime) {
		this.morningStartTime = morningStartTime;
	}

	public int getLaunchStartTime() {
		return launchStartTime;
	}

	public void setLaunchStartTime(int launchStartTime) {
		this.launchStartTime = launchStartTime;
	}

	public int getAfternoonStartTime() {
		return afternoonStartTime;
	}

	public void setAfternoonStartTime(int afternoonStartTime) {
		this.afternoonStartTime = afternoonStartTime;
	}

	public int getMaxEventDuration() {
		return maxEventDuration;
	}

	public void setMaxEventDuration(int maxEventDuration) {
		this.maxEventDuration = maxEventDuration;
	}

	public int getMorningDuration() {
		return morningDuration;
	}

	public void setMorningDuration(int morningDuration) {
		this.morningDuration = morningDuration;
	}
	

}
