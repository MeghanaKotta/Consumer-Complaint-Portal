package com.tickethandling.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickethandling.dto.UserDepOrgMappingDTO;

public interface UserDepOrgMappingRepository extends JpaRepository<UserDepOrgMappingDTO, Long> {

	List<UserDepOrgMappingDTO> findByUserid(Long userId);
	
	
}
