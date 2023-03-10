package com.tickethandling.auth.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tickethandling.auth.dto.SessionDetailsDTO;


public interface SessionDetailsRepository extends JpaRepository<SessionDetailsDTO, Integer> {

	List<SessionDetailsDTO> findBySessionkey(String sessionKey);

	List<SessionDetailsDTO> findBySessionkeyAndActive(String sessionKey, int active);

	@Transactional
	@Modifying
	@Query("update SessionDetailsDTO s set s.active = 0, s.loggedoutat = :loggedoutat where s.sessionkey = :sessionKey")
	void updateSessionEnd(String sessionKey, Date loggedoutat);

	@Transactional
	@Modifying
	@Query("UPDATE SessionDetailsDTO s SET s.active=0 WHERE UNIX_TIMESTAMP(NOW()) - UNIX_TIMESTAMP(s.loggedinat) >= :sessionExpityTime")
	void updateExpiredSession(Long sessionExpityTime);

}