package com.tickethandling.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tickethandling.constants.Queries;
import com.tickethandling.dto.TicketDTO;

public interface TicketRepository extends JpaRepository<TicketDTO, Long> {

	@Transactional
	@Modifying
	@Query("update TicketDTO t SET t.activestage=0, t.updatedon = NOW() where t.ticketid = :ticketid")
	void inactiveOpenTicket(Long ticketid);

	List<TicketDTO> findByTicketid(Long ticketId);

	@Query(value = Queries.TICKET_META_INFO_QUERY, countQuery = Queries.TICKET_META_INFO_QUERY_COUNT, nativeQuery = true)
	Page<List<Map<String, Object>>> getTicketsDisplayInfo(Long userid, int ticketstatus, int isBookMarkedStatus1,
			int isBookMarkedStatus2, String contentFilter, int depid, int orgid, Pageable pageable);

	@Query(value = "SELECT   " + "t.ticketid,  " + "t.title,  " + "t.content,  " + "tm.createdat,  " + "tm.tags,  "
			+ "tm.`status`,  "
			+ "(case when EXISTS( SELECT * FROM  bookmarks b WHERE b.userid = :userid AND b.ticketid = tm.ticketid ) then 1 ELSE 0 end ) isBookmarked  "
			+ " FROM  " + "ticket t  " + "INNER JOIN   " + "ticketmetadata tm ON t.ticketid = tm.ticketid  "
			+ "AND t.activestage = 1 AND tm.ticketid = :ticketid", nativeQuery = true)
	List<Map<String, Object>> getTicketInfoHeader(Long userid, Long ticketid);

	@Query(value = "SELECT   " + "t.ticketid,t.title,t.content,  "
			+ "(SELECT username FROM userdetails u WHERE u.userid = t.currentassigneeuserid) AS worked_or_workingby,  "
			+ "(case when t.assignedbyuserid IS NULL then 'SYSTEM' ELSE (SELECT username FROM userdetails u WHERE u.userid = t.currentassigneeuserid) END ) AS assignedBy,  "
			+ "(case when t.updatedon IS NULL then ' Work in Progress' ELSE t.updatedon END ) AS updatedon,  "
			+ "t.workdone,  " + "t.notes  "
			+ " FROM ticket t WHERE t.ticketid = :ticketid ORDER BY t.id DESC;", nativeQuery = true)
	List<Map<String, Object>> getTicketInfoContent(Long ticketid);

	@Query(value = "SELECT isManager,isOrgMember, isEditable,  "
			+ "(CASE WHEN isEditable =1 THEN (CASE WHEN isOrgMember = 1 THEN 1 ELSE  " + "		0 END   "
			+ "	) ELSE  " + " 0 END   " + "  " + ") AS assigneeOption,  " + "  "
			+ "(CASE WHEN isEditable =1 THEN (CASE WHEN isManager = 1 THEN 1 WHEN   "
			+ " 		currentassigneeuserid = :userid THEN 1 ELSE  " + " 	0 END   " + "   " + ") ELSE  " + " 0 END  "
			+ "  " + ") AS moveOnFeature  " + "FROM (  " + "SELECT *,  " + "(  "
			+ "CASE WHEN `status`=2 THEN 0 WHEN `status`=1 THEN   " + "	  "
			+ "	(CASE WHEN isManager = 1 THEN 1 WHEN (currentassigneeuserid = :userid) THEN 1 ELSE  " + "		0 END  "
			+ "	) ELSE  " + "	 1 END  " + "	  " + ") AS isEditable  " + "FROM (  " + "SELECT   "
			+ "t.*,tm.`status`,  " + "(CASE WHEN EXISTS(  " + "SELECT *  " + "FROM userdetails ud  "
			+ "WHERE ud.userid = :userid AND ud.roleid = (  " + "SELECT id  " + "FROM roletbl  "
			+ "WHERE rolename ='Manager')  " + ") THEN 1 ELSE 0 END) AS isManager,  " + "(CASE WHEN EXISTS(  "
			+ "SELECT *  " + "FROM userdetails ud  " + "WHERE ud.userid = :userid AND ud.roleid = (  " + "SELECT id  "
			+ "FROM roletbl  " + "WHERE rolename ='OrgMember')  " + ") THEN 1 ELSE 0 END) AS isOrgMember  "
			+ "FROM ticketmetadata tm  "
			+ "INNER JOIN ticket t ON t.ticketid = tm.ticketid AND t.activestage=1 AND t.ticketid= :ticketid  " + "  "
			+ ") a  " + ") b  ", nativeQuery = true)
	List<Map<String, Object>> getTicketFooterContent(Long userid, Long ticketid);

	@Query(value = "SELECT tm.ticketid,ud.userid,ud.username,ud.avatarid, ud.roleid ,'OrgMember'                  FROM            "
			+ "			             ticketmetadata tm                       INNER JOIN userorgmap uo ON tm.depid = uo.depid AND tm.orgid = uo.orgid           "
			+ "			             INNER JOIN userdetails ud ON ud.userid = uo.userid            "
			+ "			             INNER JOIN ticket t ON t.ticketid = tm.ticketid AND t.activestage = 1 AND tm.`status` !=2 AND  "
			+ "							  ( t.currentassigneeuserid is null or t.currentassigneeuserid != ud.userid ) AND t.ticketid = :ticketid         "
			+ "			             WHERE (ud.roleid IN (SELECT id FROM roletbl WHERE rolename = 'OrgMember'))                       UNION            "
			+ "			             SELECT tm.ticketid,ud.userid,ud.username,ud.avatarid, ud.roleid ,'Manager'                        FROM            "
			+ "			             ticketmetadata tm                       INNER JOIN userorgmap uo ON tm.depid = uo.depid AND tm.orgid = uo.orgid           "
			+ "			             INNER JOIN userdetails ud ON ud.userid = uo.userid            "
			+ "			             INNER JOIN ticket t ON t.ticketid = tm.ticketid AND t.activestage = 1 AND tm.`status` !=2 AND  "
			+ "							  ( t.currentassigneeuserid is null or t.currentassigneeuserid != ud.userid ) AND t.ticketid = :ticketid         "
			+ "			             WHERE (ud.roleid IN (SELECT id FROM roletbl WHERE rolename = 'Manager'))                                                              ", nativeQuery = true)
	List<Map<String, Object>> getEligibleUserIdsListForTicketEdit( Long ticketid);

	@Transactional
	@Modifying
	@Query("update TicketDTO t set t.notes= :notes , t.workdone = :workdone, t.updatedon = now(),activestage=0  where t.ticketid = :ticketid and t.activestage = 1  ")
	void moveTicketToNextStage_PrevTicket(Long ticketid, String workdone, String notes);

	@Query(value = "select t.* from ticket t where t.ticketid = :ticketid and t.activestage = 0 order by id desc limit 1 ", nativeQuery = true)
	TicketDTO getPrevInactivatedTicket(Long ticketid);

	@Transactional
	@Modifying
	@Query("update TicketDTO t set t.notes= :notes , t.workdone = :workdone, t.updatedon = now(),t.closedon = now(),t.closedbyuserid=:closedbyuserid,"
			+ "t.closedbyroleid =  :closedbyroleid   where t.ticketid = :ticketid and t.activestage = 1")
	void closetTicket(Long ticketid, String workdone, String notes, Long closedbyuserid, Long closedbyroleid);

	@Query(value = "select t.* from ticket t where t.ticketid = :ticketid and t.activestage = 1 order by id desc limit 1 ", nativeQuery = true)
	TicketDTO getLatestTicket(Long ticketid);

	@Query(value = "SELECT t.ticketid,t.title,t.content, (case when (tm.`status`=1) then 'Edit' ELSE 'Closed' END ) AS ticketStatus ,t.assignedon ,t.closedon ,tm.`status` ,t.currentassigneeuserid   "
			+ " FROM ticketmetadata tm   " + "INNER JOIN ticket t   "
			+ "ON t.ticketid = tm.ticketid AND ( tm.`status` = 1 OR tm.`status` = 2) AND t.activestage = 1   "
			+ "WHERE (t.currentassigneeuserid = :userid) or ( t.closedbyuserid  = :userid)   "
			+ "ORDER BY assignedon asc   " + "   " + "  ", nativeQuery = true)
	List<Map<String, Object>> getMyTickets(Long userid);

	@Query(value = "SELECT tm.tags, t.ticketid,t.title,t.content,tm.depid,tm.orgid,  (case when (tm.`status`=0) then 'Open' when (tm.`status`=1) then 'Progress' ELSE 'Closed' END ) AS ticketStatus ,t.assignedon, t.closedon ,tm.`status` ,  "
			+ "(SELECT username FROM userdetails ud WHERE ud.userid = t.currentassigneeuserid ) AS currentassignee, "
			+ "   "
			+ "(SELECT ud.avatarid FROM userdetails ud WHERE ud.userid = t.currentassigneeuserid ) AS currentassigneeavatarid,"
			+ "(  " + "  "
			+ "SELECT rolename FROM roletbl r WHERE r.id = (SELECT ud.roleid FROM userdetails ud WHERE ud.userid = t.currentassigneeuserid)  "
			+ ") AS role  " + "  " + " FROM ticketmetadata tm  " + "INNER JOIN ticket t  "
			+ "ON t.ticketid = tm.ticketid  AND t.activestage = 1  " + "WHERE tm.creatoruserid = :userid  "
			+ "ORDER BY ticketStatus asc  " + "  " + "  " + " ", nativeQuery = true)
	List<Map<String, Object>> getMyCustomerTickets(Long userid);

	@Query(value = "SELECT t.title,t.content, tm.ticketid, (  " + "SELECT username  " + "FROM userdetails ud  "
			+ "WHERE ud.userid = tm.creatoruserid) AS creator, (  " + "SELECT avatarid  " + "FROM userdetails ud  "
			+ "WHERE ud.userid = tm.creatoruserid) AS creatoravatarid  " + "FROM ticketmetadata tm  "
			+ "INNER JOIN ticket t ON t.ticketid = tm.ticketid AND t.activestage = 1  "
			+ "INNER JOIN userorgmap uo ON uo.depid = tm.depid AND uo.orgid = tm.orgid AND uo.userid = :userid where (t.title LIKE CONCAT('%',:filterCondition,'%')) OR (t.content LIKE CONCAT('%',:filterCondition,'%'))  "
			+ "  GROUP BY t.ticketid   ORDER BY t.updatedon DESC LIMIT 5  " + " ", nativeQuery = true)
	List<Map<String, Object>> searchTickets(Long userid, String filterCondition);

	@Query(value = "SELECT t.title,t.content, tm.ticketid, tm.status, (  " + "SELECT username  "
			+ "FROM userdetails ud  " + "WHERE ud.userid = tm.creatoruserid) AS creator, (  " + "SELECT avatarid  "
			+ "FROM userdetails ud  " + "WHERE ud.userid = tm.creatoruserid) AS creatoravatarid  "
			+ "FROM ticketmetadata tm  "
			+ "INNER JOIN ticket t ON t.ticketid = tm.ticketid AND t.activestage = 1 and tm.status = 2 "
			+ " where (t.title LIKE CONCAT('%',:filterCondition,'%')) OR (t.content LIKE CONCAT('%',:filterCondition,'%'))  GROUP BY t.ticketid    "
			+ "ORDER BY t.updatedon DESC LIMIT 5  " + " ", nativeQuery = true)
	List<Map<String, Object>> searchCustomerTickets(String filterCondition);

}
