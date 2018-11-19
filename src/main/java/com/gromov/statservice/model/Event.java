package com.gromov.statservice.model;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import com.gromov.statservice.dto.EventStatisticsDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Event entity of a visit to a web site
 */

@NamedNativeQuery(
		name = "getStatistics",
		query = "SELECT COUNT(events.id) as totalVisitsAmount, " +
				"       COUNT(DISTINCT id_user) as uniqueUsersAmount, " +
				"       (SELECT COUNT(*) FROM (SELECT unique_users.id_user, (SELECT COUNT(DISTINCT id_page) as coun " +
				"                   FROM events unique_page " +
				"                   WHERE unique_users.id_user = unique_page.id_user " +
				"                       AND unique_page.date_creation  " +
				"                       BETWEEN :startDate AND :endDate ) as pagecount " +
				"               FROM (SELECT DISTINCT id_user FROM EVENTS) unique_users) sub " +
				"               WHERE sub.pagecount >= :pageCountOfRegularUser) as permanentUsersAmount " +
				"FROM events WHERE events.date_creation  BETWEEN :startDate AND :endDate ",
		resultSetMapping = "event_statistics_dto"
)
@SqlResultSetMapping(name = "event_statistics_dto",
		classes = {
				@ConstructorResult(targetClass = EventStatisticsDto.class, columns = {
						@ColumnResult(name = "totalVisitsAmount", type = Long.class),
						@ColumnResult(name = "uniqueUsersAmount", type = Long.class),
						@ColumnResult(name = "permanentUsersAmount", type = Integer.class)
				})
		}
)
@Data
@Entity
@Table(name = "events")
@Access(AccessType.FIELD)
@NoArgsConstructor
public class Event {
	public static final int START_SEQ = 100000;

	@Id
	@SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
	private long id;

	/**
	 * User ID of WEB site
	 */
	@Column(name = "id_user", nullable = false)
	@NotNull
	private long idUser;

	/**
	 * Page ID of WEB site
	 */
	@Column(name = "id_page", nullable = false)
	@NotNull
	private long idPage;

	/**
	 * Incoming date of visit event web site
	 */
	@Column(name = "date_creation", nullable = false)
	@NotNull
	private LocalDateTime dateOfCreation;

	public Event(@NotNull long idUser, @NotNull long idPage, @NotNull LocalDateTime dateOfCreation) {
		this.idUser = idUser;
		this.idPage = idPage;
		this.dateOfCreation = dateOfCreation;
	}
}
