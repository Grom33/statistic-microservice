package com.gromov.statservice.dto;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  Data transfer object contains the statistics in response to adding an event.
 */

@Data
@AllArgsConstructor
public class EventStatisticsDto {
	private final long totalVisitsAmount;
	private final long uniqueUsersAmount;

}
