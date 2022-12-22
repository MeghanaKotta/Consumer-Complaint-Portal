package com.tickethandling.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tickethandling.auth.repositories.OrganizationsRepository;
import com.tickethandling.dto.OrganizationsDTO;

@Service
public class OrganizationsService {
	
	@Autowired
	private OrganizationsRepository organizationsRepository;
	
	public List<OrganizationsDTO> getAllOrgList() throws Exception{
		try {
			return organizationsRepository.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

}
