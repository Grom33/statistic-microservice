package com.gromov.statservice.service;
/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import com.gromov.statservice.dto.ExtendedEventStatisticsDto;
import com.gromov.statservice.dto.EventStatisticsDto;
import com.gromov.statservice.model.Event;

import java.time.LocalDateTime;

/**
 * Service for entities of event visit web site
 */

public interface EventService {

	/**
	 * Method to add a visit event
	 */
	EventStatisticsDto addEvent(Event event);

	/**
	 * Method for obtaining statistics for the required period
	 */
	ExtendedEventStatisticsDto getStatistics(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
