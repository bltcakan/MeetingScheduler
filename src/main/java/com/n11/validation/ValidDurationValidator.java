package com.n11.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.n11.constant.MeetingSchedulerConstant;

@Component
public class ValidDurationValidator implements ConstraintValidator<ValidDuration, String> {

	private int minLenght;

	int duration;

	@Override
	public void initialize(ValidDuration validDuration) {
		minLenght = validDuration.min();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value != null && value.length() >= minLenght) {
			if (value.equalsIgnoreCase("lightning")) {
				return true;
			} else {
				try {
					duration = Integer.parseInt(value.substring(0, value.lastIndexOf("min")));

					if (duration > MeetingSchedulerConstant.AFTERNOON_SESSION_DURATION || duration < 1) {
						return false;
					}
					return true;
				} catch (Exception e) {
					return false;
				}

			}
		}
		return false;
	}

}
