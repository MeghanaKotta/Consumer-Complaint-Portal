package com.tickethandling.auth.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickethandling.auth.dto.ManagerAuthDTO;

public interface ManagerAccessRepository extends JpaRepository<ManagerAuthDTO, String> {

	List<ManagerAuthDTO> findByUsernameAndKey(String username,String key);


}