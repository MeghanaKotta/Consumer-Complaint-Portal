package com.tickethandling.auth.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tickethandling.dto.OrganizationsDTO;

public interface OrganizationsRepository extends JpaRepository<OrganizationsDTO, Long> {

	@Query(value = "SELECT o.* FROM userdetails u  " + "INNER JOIN userorgmap uo   " + "ON u.userid = uo.userid  "
			+ "INNER JOIN organizations o ON o.id = uo.orgid  " + "WHERE u.userid = :userid", nativeQuery = true)
	List<OrganizationsDTO> gerOrgInfoOnUserid(Long userid);

	@Query(value = "SELECT o.* FROM  organizations o WHERE o.id not in (:ids) ", nativeQuery = true)
	List<OrganizationsDTO> gerOrgInfoOnUseridNotIn(List<Long> ids);
	
	Optional<OrganizationsDTO> findById(Long id);

}
