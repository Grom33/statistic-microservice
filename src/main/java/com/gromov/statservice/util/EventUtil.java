package com.gromov.statservice.util;
/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import com.gromov.statservice.dto.EventDto;
import com.gromov.statservice.model.Event;

import java.time.LocalDateTime;

/**
 * Utility class for converting the DTO into the entity
 */

public class EventUtil {

	public static Event createNewFromDto(EventDto dto){
		return new Event( dto.getUser(), dto.getUrl(), LocalDateTime.now());
	}
}
