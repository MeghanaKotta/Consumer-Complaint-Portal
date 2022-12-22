package com.tickethandling.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tickethandling.dto.TicketMetaDataDTO;

public interface TicketMetaDataRepository extends JpaRepository<TicketMetaDataDTO, Long> {

	List<TicketMetaDataDTO> findByTicketid(Long ticketid);

	@Transactional
	@Modifying
	@Query("update TicketMetaDataDTO t set t.status= :ticketstatus  where t.ticketid = :ticketid ")
	void moveTicketMetaDataToNextStage(Long ticketid, int ticketstatus);

}