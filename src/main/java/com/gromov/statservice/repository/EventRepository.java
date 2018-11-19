package com.gromov.statservice.repository;
/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import com.gromov.statservice.dto.EventStatisticsDto;
import com.gromov.statservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for storing entities events of visit web site
 */

@Repository
@Transactional
public interface EventRepository extends JpaRepository<Event, Long> {

	/**
	 * Method for obtaining statistic of visits for the required period
	 */
	@Transactional(readOnly = true)
	@Query(name = "getStatistics", nativeQuery = true)
	EventStatisticsDto getEventStatisticsBetweenDate(@Param("pageCountOfRegularUser") long pageCountOfRegularUser,
	                                                 @Param("startDate") LocalDateTime startDate,
	                                                 @Param("endDate") LocalDateTime endDate);

	/**
	 * Method for obtaining the number of unique visitors for the required period
	 * Used to initialize the cache.
	 */
	@Transactional(readOnly = true)
	@Query("SELECT e.idUser FROM Event e WHERE e.dateOfCreation BETWEEN :startDate AND :endDate ")
	List<Long> geUniqueUsersBetweenDate(LocalDateTime startDate, LocalDateTime endDate);

	/**
	 * Method to get the number of visits events for the required period
	 * Used to initialize the cache.
	 */
	@Transactional(readOnly = true)
	@Query("SELECT COUNT(e.id) FROM Event e WHERE e.dateOfCreation BETWEEN :startDate AND :endDate ")
	Long getCountEventsBetweenDate(LocalDateTime startDate, LocalDateTime endDate);
}
