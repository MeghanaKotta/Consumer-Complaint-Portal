package com.tickethandling.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tickethandling.dto.BookMarkDTO;

public interface BookmarkReository extends JpaRepository<BookMarkDTO, Long> {
	List<BookMarkDTO> findByUseridAndTicketid(Long userid, Long ticketid);

	@Transactional
	@Modifying
	@Query("DELETE  FROM  BookMarkDTO b WHERE b.userid = :userid and b.ticketid = :ticketid ")
	void deleteByUseridAndTicketid(Long userid, Long ticketid);
}