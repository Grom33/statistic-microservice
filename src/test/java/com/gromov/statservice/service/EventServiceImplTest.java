package com.gromov.statservice.service;

import com.gromov.statservice.ApplicationTests;
import com.gromov.statservice.dto.EventStatisticsDto;
import com.gromov.statservice.dto.ShortEventStatisticsDto;
import com.gromov.statservice.model.Event;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

public class EventServiceImplTest extends ApplicationTests {

	@Test
	public void addEventByFirstDay() {
		EVENT_LIST_OF_11_EVENTS_BY_FIRST_DAY.forEach((event -> eventService.addEvent(event)));
		Event lastEvent = new Event(USER_1_REGULAR, ID_OF_SOME_WEB_PAGE, FIRST_DAY);
		ShortEventStatisticsDto ACTUAL_EVENT_STATISTICS_DTO = eventService.addEvent(lastEvent);
		Assert.assertEquals(EXPECTED_STATISTICS_BY_FIRST_DAY, ACTUAL_EVENT_STATISTICS_DTO);
	}

	@Test
	public void addEventBySecondDay() {
		EVENT_LIST_OF_25_EVENTS_BY_SECOND_DAY.forEach((event -> eventService.addEvent(event)));
		Event lastEvent = new Event(USER_1_REGULAR, ID_OF_SOME_WEB_PAGE, SECOND_DAY);
		ShortEventStatisticsDto actualEventStatisticsDto = eventService.addEvent(lastEvent);
		Assert.assertEquals(EXPECTED_STATISTICS_BY_SECOND_DAY, actualEventStatisticsDto);
	}

	@Test
	public void addEventByThirdDay() {
		EVENT_LIST_OF_20_EVENTS_BY_THIRD_DAY.forEach((event -> eventService.addEvent(event)));
		Event lastEvent = new Event(USER_4, ID_OF_SOME_WEB_PAGE, THIRD_DAY);
		ShortEventStatisticsDto actualEventStatisticsDto = eventService.addEvent(lastEvent);
		Assert.assertEquals(EXPECTED_STATISTICS_BY_THIRD_DAY, actualEventStatisticsDto);
	}

	@Test
	public void getStatisticsByAllTime() {
		EVENT_LIST_OF_11_EVENTS_BY_FIRST_DAY.forEach((event -> eventService.addEvent(event)));
		EVENT_LIST_OF_25_EVENTS_BY_SECOND_DAY.forEach((event -> eventService.addEvent(event)));
		EVENT_LIST_OF_20_EVENTS_BY_THIRD_DAY.forEach((event -> eventService.addEvent(event)));
		EventStatisticsDto actualEventStatisticsDto = eventService.getStatistics(BEGIN_OF_FIRST_DAY, THIRD_DAY);
		Assert.assertEquals(EXPECTED_STATISTICS_BY_ALL_TIME, actualEventStatisticsDto);
	}

	@Test
	public void getStatisticsBetweenFirstAndSecondDay() {
		EVENT_LIST_OF_11_EVENTS_BY_FIRST_DAY.forEach((event -> eventService.addEvent(event)));
		EVENT_LIST_OF_25_EVENTS_BY_SECOND_DAY.forEach((event -> eventService.addEvent(event)));
		EventStatisticsDto actualEventStatisticsDto = eventService.getStatistics(BEGIN_OF_FIRST_DAY, END_OF_SECOND_DAY);
		Assert.assertEquals(EXPECTED_STATISTICS_BETWEEN_FIRST_AND_SECOND_DAY, actualEventStatisticsDto);
	}
}