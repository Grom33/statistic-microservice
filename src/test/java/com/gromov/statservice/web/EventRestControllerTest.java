package com.gromov.statservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gromov.statservice.ApplicationTests;
import com.gromov.statservice.dto.EventDto;
import com.gromov.statservice.model.Event;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


import javax.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

public class EventRestControllerTest extends ApplicationTests {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	private String REST_URL = "/api/event";

	private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

	private final EventDto LAST_TODAY_EVENT_DTO = new EventDto(USER_1_REGULAR, 1);
	private final List<Event> EVENT_LIST_OF_11_EVENTS_TODAY;

	static {
		CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
		CHARACTER_ENCODING_FILTER.setForceEncoding(true);
	}

	{
		EVENT_LIST_OF_11_EVENTS_TODAY = new ArrayList<>();
		LongStream
				.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
				.forEach((idPage) -> EVENT_LIST_OF_11_EVENTS_TODAY.add(new Event(USER_1_REGULAR, idPage, LocalDateTime.now())));
	}

	@PostConstruct
	private void postConstruct() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.addFilter(CHARACTER_ENCODING_FILTER)
				.build();
	}

	@Test
	public void getStatistics() throws Exception {
		EVENT_LIST_OF_11_EVENTS_BY_FIRST_DAY.forEach((event -> eventService.addEvent(event)));
		EVENT_LIST_OF_25_EVENTS_BY_SECOND_DAY.forEach((event -> eventService.addEvent(event)));

		mockMvc.perform(get(REST_URL + "/statistics")
				.header("start", BEGIN_OF_FIRST_DAY.toString())
				.header("end", END_OF_SECOND_DAY.toString()))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(JSON_MAPPER.writeValueAsString(EXPECTED_STATISTICS_BETWEEN_FIRST_AND_SECOND_DAY)));
	}

	@Test
	public void addEvent() throws Exception {
		EVENT_LIST_OF_11_EVENTS_TODAY.forEach((event -> eventService.addEvent(event)));
		mockMvc.perform(post(REST_URL)
				.content(JSON_MAPPER.writeValueAsString(LAST_TODAY_EVENT_DTO))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(JSON_MAPPER.writeValueAsString(EXPECTED_STATISTICS_BY_FIRST_DAY)));
	}
}