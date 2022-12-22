package com.tickethandling.auth.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tickethandling.dto.DepartmentsDTO;

public interface DepartmentsRepository extends JpaRepository<DepartmentsDTO, Long> {

	@Query(value = "SELECT d.* FROM userdetails u  " + "INNER JOIN userorgmap uo   " + "ON u.userid = uo.userid  "
			+ "INNER JOIN departments d ON d.id = uo.depid  "
			+ "WHERE u.userid = :userid  GROUP BY d.id; ", nativeQuery = true)
	List<DepartmentsDTO> getDepartmentsOnUserId(Long userid);

	@Query(value = "SELECT d.* FROM departments d WHERE d.id not in (:ids) ", nativeQuery = true)
	List<DepartmentsDTO> getDepartmentsOnUserIdNotIn(List<Long> ids);

	Optional<DepartmentsDTO> findById(Long id);

}
