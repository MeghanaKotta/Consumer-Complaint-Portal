package com.tickethandling.auth.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tickethandling.auth.dto.UserDetailsDTO;

public interface UserRepository extends JpaRepository<UserDetailsDTO, Long> {

	List<UserDetailsDTO> findByUsername(String username);

	List<UserDetailsDTO> findByEmailid(String username);

	List<UserDetailsDTO> findByUsernameAndPassword(String username, String password);

	List<UserDetailsDTO> findByEmailidAndPassword(String emailid, String password);

	List<UserDetailsDTO> findByUserid(Long userid);

	List<UserDetailsDTO> findByUsernameOrEmailid(String username, String emailid);

	@Query(value = "SELECT u.username FROM userdetails u INNER JOIN userorgmap uo ON uo.userid = u.userid "
			+ "WHERE uo.depid= :depid AND uo.orgid = :orgid and u.emailidactivated =1", nativeQuery = true)
	List<Map<String, Object>> getManagersAssociatedtoOrgDep(int depid, int orgid);

	@Transactional
	@Modifying
	@Query("update UserDetailsDTO u set u.emailidactivated = 1 where u.userid = :userid ")
	void updateEmailActivation(Long userid);

	List<UserDetailsDTO> findByEmailidAndEmailidactivated(String emailid, int activestatus);

	@Transactional
	@Modifying
	@Query("update UserDetailsDTO u set u.password = :password where u.emailid = :emailid ")
	void updatePassworOnEmailid(String emailid, String password);

	@Query(value = "SELECT ud.emailid FROM userdetails ud   "
			+ "	INNER JOIN userorgmap um ON ud.userid = um.userid    "
			+ "	WHERE ud.emailidactivated =1 and um.depid = :depid AND um.orgid = :orgid AND ud.roleid = 1", nativeQuery = true)
	List<Map<String, Object>> getAllManagersEmailIdForAnDepAndOrg(Long depid, Long orgid);

	@Transactional
	@Modifying
	@Query("update UserDetailsDTO u set u.avatarid = :avatarid where u.userid = :userid ")
	void updateAvatar(Long userid, int avatarid);

	@Transactional
	@Modifying
	@Query("update UserDetailsDTO u set u.password = :password where u.userid = :userid ")
	void updatePassword(Long userid, String password);

}