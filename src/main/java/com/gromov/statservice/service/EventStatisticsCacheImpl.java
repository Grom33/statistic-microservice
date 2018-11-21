package com.gromov.statservice.service;
/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import com.gromov.statservice.dto.EventStatisticsDto;
import com.gromov.statservice.model.Event;
import com.gromov.statservice.repository.EventRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
@RequiredArgsConstructor
public class EventStatisticsCacheImpl implements EventStatisticsCache {
	@Autowired
	private final EventRepository eventRepository;

	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Keep statistics for today and yesterday, in case the requests come on the border of days
	 */
	private final Map<LocalDate, DayStatistic> dayStatisticsMap = new HashMap<>();


	/**
	 * The method of initializing the cache after running the application.
	 */
	@PostConstruct
	private void init() {
		DayStatistic dayStatistic = new DayStatistic();
		LocalDateTime startDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
		LocalDateTime endDate = LocalDateTime.now();
		dayStatistic.getEventCounter().set(
				eventRepository.getCountEventsBetweenDate(startDate, endDate));
		dayStatistic.getUniqueUserSet().addAll(
				eventRepository.geUniqueUsersBetweenDate(startDate, endDate));
		dayStatisticsMap.put(LocalDate.now(), dayStatistic);
		log.info("Init event statistics cache on date {}, event count {}, count of unique users {}", endDate,
				dayStatistic.getEventCounter().get(), dayStatistic.getUniqueUserSet().size());
	}

	@Override
	public synchronized EventStatisticsDto cache(Event event) {
		log.info("Add event {} to event cache statistics", event);
		DayStatistic dayStatistic = getDayStatistic(event.getDateOfCreation().toLocalDate());
		long eventCount = dayStatistic.getEventCounter().incrementAndGet();
		dayStatistic.getUniqueUserSet().add(event.getIdUser());
		int uniqueUserCount = dayStatistic.getUniqueUserSet().size();
		return new EventStatisticsDto(eventCount, uniqueUserCount);
	}

	private DayStatistic getDayStatistic(LocalDate localDate) {
		log.info("Call day statistics event map to date {}", localDate);
		DayStatistic dayStatistic = dayStatisticsMap.get(localDate);
		if (dayStatistic == null) {
			log.info("There is a first event on {}, new DayStatistic entity will be created", localDate);
			dayStatistic = new DayStatistic();
			dayStatisticsMap.put(localDate, dayStatistic);
		}
		if (dayStatisticsMap.size() > 2) trimDayStatisticsMap();
		return dayStatistic;
	}

	private void trimDayStatisticsMap() {
		log.info("There are more then two DayStatistic entity, old DayStatistic entity will be removed");
		dayStatisticsMap.remove(dayStatisticsMap.keySet().stream()
				.min(Comparator.comparing(LocalDate::toEpochDay))
				.get());
	}

	@Data
	private class DayStatistic {
		private final AtomicLong eventCounter = new AtomicLong(0);
		private final Set<Long> uniqueUserSet = new HashSet<>();
	}
}
