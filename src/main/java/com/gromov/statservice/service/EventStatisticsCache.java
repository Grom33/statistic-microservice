package com.gromov.statservice.service;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import com.gromov.statservice.dto.ShortEventStatisticsDto;
import com.gromov.statservice.model.Event;

/**
 * This service caches statistics on events.
 */

public interface EventStatisticsCache {

	/**
	 * The method adds information about the events of each visit.
	 * In response method sends an entity with statistics at the time of receiving the event entity
	 */
	ShortEventStatisticsDto cache(Event event);
}
