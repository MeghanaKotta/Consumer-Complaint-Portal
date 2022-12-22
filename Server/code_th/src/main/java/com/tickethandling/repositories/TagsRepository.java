package com.tickethandling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tickethandling.dto.TagsDTO;

public interface TagsRepository extends JpaRepository<TagsDTO, Long> {

}