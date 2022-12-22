package com.tickethandling.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tickethandling.auth.repositories.DepartmentsRepository;
import com.tickethandling.dto.DepartmentsDTO;

@Service
public class DepartmentsService {

	@Autowired
	private DepartmentsRepository departmentsRepository;


	public List<DepartmentsDTO> getAllDepList() throws Exception {
		try {
			return departmentsRepository.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

}
