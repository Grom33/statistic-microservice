package com.gromov.statservice;

import com.gromov.statservice.dto.ExtendedEventStatisticsDto;
import com.gromov.statservice.dto.EventStatisticsDto;
import com.gromov.statservice.model.Event;
import com.gromov.statservice.service.EventService;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public abstract class ApplicationTests {

	@Autowired
	protected EventService eventService;

	protected final long USER_1_REGULAR = 1;
	private final long USER_2 = 2;
	private final long USER_3_REGULAR = 3;
	protected final long USER_4 = 4;
	private final long USER_5 = 5;
	protected final long ID_OF_SOME_WEB_PAGE = 1;
	protected final List<Event> EVENT_LIST_OF_11_EVENTS_BY_FIRST_DAY;
	protected final List<Event> EVENT_LIST_OF_25_EVENTS_BY_SECOND_DAY;
	protected final List<Event> EVENT_LIST_OF_20_EVENTS_BY_THIRD_DAY;
	protected final LocalDateTime FIRST_DAY = LocalDateTime.of(2018, 11, 16, 12, 5);
	protected final LocalDateTime SECOND_DAY = LocalDateTime.of(2018, 11, 17, 12, 15);
	protected final LocalDateTime THIRD_DAY = LocalDateTime.of(2018, 11, 18, 12, 25);
	protected final LocalDateTime BEGIN_OF_FIRST_DAY = LocalDateTime.of(2018, 11, 16, 0, 0);
	protected final LocalDateTime END_OF_SECOND_DAY = LocalDateTime.of(2018, 11, 17, 23, 59, 59);
	protected final EventStatisticsDto EXPECTED_STATISTICS_BY_FIRST_DAY = new EventStatisticsDto(12, 1);
	protected final EventStatisticsDto EXPECTED_STATISTICS_BY_SECOND_DAY = new EventStatisticsDto(26, 3);
	protected final EventStatisticsDto EXPECTED_STATISTICS_BY_THIRD_DAY = new EventStatisticsDto(21, 2);
	protected final ExtendedEventStatisticsDto EXPECTED_STATISTICS_BY_ALL_TIME = new ExtendedEventStatisticsDto(56, 5, 2);
	protected final ExtendedEventStatisticsDto EXPECTED_STATISTICS_BETWEEN_FIRST_AND_SECOND_DAY = new ExtendedEventStatisticsDto(36, 3, 2);

	{
		EVENT_LIST_OF_11_EVENTS_BY_FIRST_DAY = new ArrayList<>();
		LongStream
				.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
				.forEach((idPage) -> EVENT_LIST_OF_11_EVENTS_BY_FIRST_DAY.add(new Event(USER_1_REGULAR, idPage, FIRST_DAY)));

		EVENT_LIST_OF_25_EVENTS_BY_SECOND_DAY = new ArrayList<>();
		LongStream
				.of(11, 11, 11, 17, 18, 19, 20, 21, 21, 21)
				.forEach((idPage) -> EVENT_LIST_OF_25_EVENTS_BY_SECOND_DAY.add(new Event(USER_2, idPage, SECOND_DAY)));
		LongStream
				.of(1, 2, 3, 4, 5)
				.forEach((idPage) -> EVENT_LIST_OF_25_EVENTS_BY_SECOND_DAY.add(new Event(USER_1_REGULAR, idPage, SECOND_DAY)));
		LongStream
				.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
				.forEach((idPage) -> EVENT_LIST_OF_25_EVENTS_BY_SECOND_DAY.add(new Event(USER_3_REGULAR, idPage, SECOND_DAY)));

		EVENT_LIST_OF_20_EVENTS_BY_THIRD_DAY = new ArrayList<>();
		LongStream
				.of(11, 12, 13, 14, 15, 15, 15, 15, 15, 15)
				.forEach((idPage) -> EVENT_LIST_OF_20_EVENTS_BY_THIRD_DAY.add(new Event(USER_4, idPage, THIRD_DAY)));
		LongStream
				.of(11, 12, 13, 14, 15, 15, 15, 15, 15, 15)
				.forEach((idPage) -> EVENT_LIST_OF_20_EVENTS_BY_THIRD_DAY.add(new Event(USER_5, idPage, THIRD_DAY)));
	}
}
