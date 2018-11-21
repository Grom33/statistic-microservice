package com.gromov.statservice.service;

import com.gromov.statservice.ApplicationTests;
import com.gromov.statservice.dto.ExtendedEventStatisticsDto;
import com.gromov.statservice.dto.EventStatisticsDto;
import com.gromov.statservice.model.Event;
import org.junit.Assert;
import org.junit.Test;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

public class EventServiceImplTest extends ApplicationTests {

	@Test
	public void addEventByFirstDay() {
		EVENT_LIST_OF_11_EVENTS_BY_FIRST_DAY.forEach((event -> eventService.addEvent(event)));
		Event lastEvent = new Event(USER_1_REGULAR, ID_OF_SOME_WEB_PAGE, FIRST_DAY);
		EventStatisticsDto ACTUAL_EVENT_STATISTICS_DTO = eventService.addEvent(lastEvent);
		Assert.assertEquals(EXPECTED_STATISTICS_BY_FIRST_DAY, ACTUAL_EVENT_STATISTICS_DTO);
	}

	@Test
	public void addEventBySecondDay() {
		EVENT_LIST_OF_25_EVENTS_BY_SECOND_DAY.forEach((event -> eventService.addEvent(event)));
		Event lastEvent = new Event(USER_1_REGULAR, ID_OF_SOME_WEB_PAGE, SECOND_DAY);
		EventStatisticsDto actualEventStatisticsDto = eventService.addEvent(lastEvent);
		Assert.assertEquals(EXPECTED_STATISTICS_BY_SECOND_DAY, actualEventStatisticsDto);
	}

	@Test
	public void addEventByThirdDay() {
		EVENT_LIST_OF_20_EVENTS_BY_THIRD_DAY.forEach((event -> eventService.addEvent(event)));
		Event lastEvent = new Event(USER_4, ID_OF_SOME_WEB_PAGE, THIRD_DAY);
		EventStatisticsDto actualEventStatisticsDto = eventService.addEvent(lastEvent);
		Assert.assertEquals(EXPECTED_STATISTICS_BY_THIRD_DAY, actualEventStatisticsDto);
	}

	@Test
	public void getStatisticsByAllTime() {
		EVENT_LIST_OF_11_EVENTS_BY_FIRST_DAY.forEach((event -> eventService.addEvent(event)));
		EVENT_LIST_OF_25_EVENTS_BY_SECOND_DAY.forEach((event -> eventService.addEvent(event)));
		EVENT_LIST_OF_20_EVENTS_BY_THIRD_DAY.forEach((event -> eventService.addEvent(event)));
		ExtendedEventStatisticsDto actualExtendedEventStatisticsDto = eventService.getStatistics(BEGIN_OF_FIRST_DAY, THIRD_DAY);
		Assert.assertEquals(EXPECTED_STATISTICS_BY_ALL_TIME, actualExtendedEventStatisticsDto);
	}

	@Test
	public void getStatisticsBetweenFirstAndSecondDay() {
		EVENT_LIST_OF_11_EVENTS_BY_FIRST_DAY.forEach((event -> eventService.addEvent(event)));
		EVENT_LIST_OF_25_EVENTS_BY_SECOND_DAY.forEach((event -> eventService.addEvent(event)));
		ExtendedEventStatisticsDto actualExtendedEventStatisticsDto = eventService.getStatistics(BEGIN_OF_FIRST_DAY, END_OF_SECOND_DAY);
		Assert.assertEquals(EXPECTED_STATISTICS_BETWEEN_FIRST_AND_SECOND_DAY, actualExtendedEventStatisticsDto);
	}
}