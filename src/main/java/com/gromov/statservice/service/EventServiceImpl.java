package com.gromov.statservice.service;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import com.gromov.statservice.dto.EventStatisticsDto;
import com.gromov.statservice.dto.ShortEventStatisticsDto;
import com.gromov.statservice.model.Event;
import com.gromov.statservice.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	@Autowired
	private final EventRepository eventRepository;

	@Autowired
	private final EventStatisticsCache eventStatisticsCache;

	private final Logger log = LoggerFactory.getLogger(getClass());
	private final int PAGE_COUNT_OF_REGULAR_USER = 10;

	@Override
	public ShortEventStatisticsDto addEvent(Event event) {
		log.info("Add event: {}", event);
		eventRepository.save(event);
		return eventStatisticsCache.cache(event);
	}

	@Override
	public EventStatisticsDto getStatistics(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		log.info("Get statistics between {} and {}", startDateTime, endDateTime);
		return eventRepository.getEventStatisticsBetweenDate(
				PAGE_COUNT_OF_REGULAR_USER, startDateTime, endDateTime);
	}
}
