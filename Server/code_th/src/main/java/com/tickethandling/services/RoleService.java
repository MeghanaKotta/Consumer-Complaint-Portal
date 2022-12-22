package com.tickethandling.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tickethandling.dto.RoleTblDTO;
import com.tickethandling.repositories.RoleTblRepository;

@Service
public class RoleService {

	@Autowired
	private RoleTblRepository roleTblRepository;
	
	private List<RoleTblDTO> getRolesInfo() throws Exception
	{
		try {
			return roleTblRepository.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
}
