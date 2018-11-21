package com.gromov.statservice.dto;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * Data transfer object contains the statistic in response to request statistic.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ExtendedEventStatisticsDto extends EventStatisticsDto{
	private final int permanentUsersAmount;

	public ExtendedEventStatisticsDto(long totalVisitsAmount, long uniqueUsersAmount, int permanentUsersAmount) {
		super(totalVisitsAmount, uniqueUsersAmount);
		this.permanentUsersAmount = permanentUsersAmount;
	}
}
