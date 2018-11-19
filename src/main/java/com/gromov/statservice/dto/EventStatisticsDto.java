package com.gromov.statservice.dto;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data transfer object contains the statistic in response to request statistic.
 */

@Data
@AllArgsConstructor
public class EventStatisticsDto {
	private final long totalVisitsAmount;
	private final long uniqueUsersAmount;
	private final int permanentUsersAmount;
}
