package com.tickethandling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickethandling.dto.RoleTblDTO;

public interface RoleTblRepository extends JpaRepository<RoleTblDTO, Long> {

}