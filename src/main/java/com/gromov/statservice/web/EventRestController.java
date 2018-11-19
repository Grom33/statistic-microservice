package com.gromov.statservice.web;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import com.gromov.statservice.dto.EventDto;
import com.gromov.statservice.dto.EventStatisticsDto;
import com.gromov.statservice.dto.ShortEventStatisticsDto;
import com.gromov.statservice.service.EventService;
import com.gromov.statservice.util.EventUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/event")
@RequiredArgsConstructor
public class EventRestController {

	@Autowired
	private EventService eventService;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping(value = "/statistics")
	public EventStatisticsDto getStatistics(@RequestHeader("start")
	                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
			                                        LocalDateTime startDate,
	                                        @RequestHeader("end")
	                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
			                                        LocalDateTime endDate) {
		log.info("GET Request for statistic between {} and {}", startDate, endDate);
		return eventService.getStatistics(startDate, endDate);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ShortEventStatisticsDto addEvent(@RequestBody EventDto eventDto) {
		log.info("POST Request for adding the event: {}", eventDto);
		return eventService.addEvent(EventUtil.createNewFromDto(eventDto));
	}
}
