package com.tickethandling.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.tickethandling.constants.AppConstants;
import com.tickethandling.constants.ErrorMsgs;
import com.tickethandling.dto.PieChartDataCountResponseDTO;
import com.tickethandling.dto.TicketCreateRequestDTO;
import com.tickethandling.dto.TicketDTO;
import com.tickethandling.dto.TicketInfoViewRequestDTO;
import com.tickethandling.dto.TicketMetaDataDTO;
import com.tickethandling.dto.TicketProgressRequestDTO;
import com.tickethandling.repositories.JdbcQueryService;
import com.tickethandling.repositories.TicketMetaDataRepository;
import com.tickethandling.repositories.TicketRepository;

@Service
public class TicketService {

	@Autowired
	private DateUtility dateUtility;

	@Autowired
	private TicketMetaDataRepository ticketMetaDataRepository;

	@Autowired
	private InitialData initialData;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private JdbcQueryService jdbcQueryService;

	@Autowired
	private PageableService pageableService;

	@Autowired
	private GenericEmailService genericEmailService;

	public boolean createTicket(TicketCreateRequestDTO ticketRequestDTO) throws Exception {
		try {

			TicketMetaDataDTO ticketMetaDataDTO = generateTicketMetaDataFromTicketRequest(ticketRequestDTO);
			TicketMetaDataDTO savedTicket = ticketMetaDataRepository.save(ticketMetaDataDTO);
			Long ticketId = savedTicket.getTicketid();
			TicketDTO ticketDTO = generateTicketDTO(ticketRequestDTO, ticketId);
			ticketRepository.save(ticketDTO);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;
	}

	private TicketDTO generateTicketDTO(TicketCreateRequestDTO ticketRequestDTO, Long ticketId) throws Exception {
		TicketDTO ticketDTO = new TicketDTO();
		ticketDTO.setTicketid(ticketId);
		ticketDTO.setTitle(ticketRequestDTO.getTitle());
		ticketDTO.setContent(ticketRequestDTO.getContent());
		ticketDTO.setAssignedbyuserid(initialData.getSystemUserId());
		ticketDTO.setAssignedon(dateUtility.getCurrentDateAndTime());
		ticketDTO.setAssignedbyroleid(initialData.getCustomerRoleId());
		ticketDTO.setActivestage(AppConstants.DEFAULT_ACTIVE_STATUS);
		ticketDTO.setClosedbyroleid(null);
		ticketDTO.setClosedbyuserid(null);

		return ticketDTO;

	}

	public TicketMetaDataDTO generateTicketMetaDataFromTicketRequest(TicketCreateRequestDTO ticketRequestDTO)
			throws Exception {
		TicketMetaDataDTO ticketMetaDataDTO = new TicketMetaDataDTO();
		ticketMetaDataDTO.setCreatedat(dateUtility.getCurrentDateAndTime());
		ticketMetaDataDTO.setCreatoruserid(ticketRequestDTO.getCreatoruserid());
		ticketMetaDataDTO.setCreatorroleid(ticketRequestDTO.getCreatorroleid());
		ticketMetaDataDTO.setOrgid(ticketRequestDTO.getOrgid());
		ticketMetaDataDTO.setDepid(ticketRequestDTO.getDepid());
		ticketMetaDataDTO.setStatus(AppConstants.OPEN_STATUS);
		ticketMetaDataDTO.setTags(ticketRequestDTO.getTags());
		return ticketMetaDataDTO;

	}

	public List<PieChartDataCountResponseDTO> getTheTicketsPieChartData(int depid, int orgid) {
		try {
			return jdbcQueryService.getTheTicketsPieChartData(depid, orgid);

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

	public Page<List<Map<String, Object>>> getTicketsDisplayInfo(TicketInfoViewRequestDTO ticketInfoViewRequestDTO,
			int pageNumber, int numberOfEelementsRequired) {
		Pageable pageRef = pageableService.getPageableRef(pageNumber - 1, numberOfEelementsRequired);
		Page<List<Map<String, Object>>> pageTuts;
		Long userid = ticketInfoViewRequestDTO.getUserid();
		int ticketStatus = ticketInfoViewRequestDTO.getTicketstatus();
		int isBookMarkedStatus1 = 0;
		int isBookMarkedStatus2 = 1;
		String contentFilter = "";
		String titleFilter = "";
		if (ticketInfoViewRequestDTO.getIsBookMarkedStatus() == 1) {
			isBookMarkedStatus1 = 1;
			isBookMarkedStatus2 = 1;
		}
		if (ticketInfoViewRequestDTO.getIsBookMarkedStatus() == 0) {
			isBookMarkedStatus1 = 0;
			isBookMarkedStatus2 = 0;
		}
		if (!ObjectUtils.isEmpty(ticketInfoViewRequestDTO.getContentFilter())
				&& ticketInfoViewRequestDTO.getContentFilter().trim().length() > 0) {
			contentFilter = ticketInfoViewRequestDTO.getContentFilter();

		}
		if (!ObjectUtils.isEmpty(ticketInfoViewRequestDTO.getTitleFlter())
				&& ticketInfoViewRequestDTO.getTitleFlter().trim().length() > 0) {
			contentFilter = ticketInfoViewRequestDTO.getTitleFlter();

		}
		pageTuts = ticketRepository.getTicketsDisplayInfo(userid, ticketStatus, isBookMarkedStatus1,
				isBookMarkedStatus2, contentFilter, ticketInfoViewRequestDTO.getDepId(),
				ticketInfoViewRequestDTO.getOrgId(), pageRef);
		return pageTuts;

	}

	public Map<String, Object> getTicketHeaderInfo(Long userid, Long ticketid) {
		try {
			List<Map<String, Object>> headerInfo = ticketRepository.getTicketInfoHeader(userid, ticketid);
			if (!ObjectUtils.isEmpty(headerInfo)) {
				return headerInfo.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return null;
	}

	public List<Map<String, Object>> getTicketContentInfo(Long ticketid) {
		try {
			List<Map<String, Object>> contentInfo = ticketRepository.getTicketInfoContent(ticketid);
			return contentInfo;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public Map<String, Object> getTicketFooterInfo(Long userid, Long ticketid) {
		try {
			List<Map<String, Object>> contentInfo = ticketRepository.getTicketFooterContent(userid, ticketid);
			if (!ObjectUtils.isEmpty(contentInfo)) {
				return contentInfo.get(0);
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return null;
	}

	public List<Map<String, Object>> getEligibleUserIdsListForTicketEdit(Long userid, Long ticketid) {
		try {
			List<Map<String, Object>> contentInfo = ticketRepository.getEligibleUserIdsListForTicketEdit(
					ticketid);
			return contentInfo;

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public boolean moveTicketOpenToProgress(TicketProgressRequestDTO ticketProgressRequestDTO) {
		// TODO Auto-generated method stub
		// update previous

		List<TicketMetaDataDTO> ticketMetaDataDTOs = ticketMetaDataRepository
				.findByTicketid(ticketProgressRequestDTO.getTicketid());
		if (!ObjectUtils.isEmpty(ticketMetaDataDTOs) && ticketMetaDataDTOs.size() > 0) {
			TicketMetaDataDTO ticketMetaDataDTO = ticketMetaDataDTOs.get(0);
			ticketMetaDataDTO.setStatus(AppConstants.PROGRESS_STATUS);
			ticketMetaDataRepository.save(ticketMetaDataDTO);
			ticketRepository.inactiveOpenTicket(ticketProgressRequestDTO.getTicketid()); // updating old ticket here
			// insert new
			List<TicketDTO> ticketDTOList = ticketRepository.findByTicketid(ticketProgressRequestDTO.getTicketid());
			if (!ObjectUtils.isEmpty(ticketDTOList) && ticketDTOList.size() > 0) {
				TicketDTO ticketDTO = new TicketDTO();
				BeanUtils.copyProperties(ticketDTOList.get(0), ticketDTO);
				ticketDTO.setId(null);
				ticketDTO.setUpdatedon(null);
				ticketDTO.setCurrentassigneeuserid(ticketProgressRequestDTO.getCurrentassigneeuserid());
				ticketDTO.setCurrentassigneeroleid(ticketProgressRequestDTO.getCurrentassigneeroleid());
				ticketDTO.setActivestage(AppConstants.DEFAULT_ACTIVE_STATUS);
				ticketDTO.setAssignedbyroleid(ticketProgressRequestDTO.getAssignedbyroleid());
				ticketDTO.setAssignedbyuserid(ticketProgressRequestDTO.getAssignedbyuserid());
				ticketRepository.save(ticketDTO);
			}
		}
		return true;
	}

	public boolean makeAProgressToTicket(TicketProgressRequestDTO ticketProgressRequestDTO) throws Exception {
		try {
			List<TicketMetaDataDTO> ticketMetaDataDTOs = ticketMetaDataRepository
					.findByTicketid(ticketProgressRequestDTO.getTicketid());
			if (!ObjectUtils.isEmpty(ticketMetaDataDTOs) && ticketMetaDataDTOs.size() > 0) {
				/*
				 * There are 3 stages , first move the ticketMetadata, then change then prev
				 * ticketm, then add the record
				 */
				TicketMetaDataDTO ticketMetaDataDTO = ticketMetaDataDTOs.get(0);
				processTicketMetaData_FirstStage(ticketMetaDataDTO, ticketProgressRequestDTO.getClosing());
				if (ticketProgressRequestDTO.getClosing()) {
					closeticket(ticketProgressRequestDTO);
					genericEmailService.sendAMailOnTicketClose(ticketProgressRequestDTO.getTicketid(),
							ticketProgressRequestDTO.getCurrentassigneeuserid());
				} else {
					inactivatePreviousTicketForProgressTicket(ticketProgressRequestDTO);
					TicketDTO inactivatedTicketDTO = getPrevTicket(ticketProgressRequestDTO.getTicketid());
					insertNewRecordForTicketProgress(ticketProgressRequestDTO, inactivatedTicketDTO);
					genericEmailService.sendAMailOnTicketMadeAProgress(ticketProgressRequestDTO.getTicketid(),
							ticketProgressRequestDTO.getAssignedbyuserid(),
							ticketProgressRequestDTO.getCurrentassigneeuserid());

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;
	}

	public TicketDTO getPrevTicket(Long ticketid) throws Exception {
		try {
			TicketDTO inactivatedTicketDTO = ticketRepository.getPrevInactivatedTicket(ticketid);
			return inactivatedTicketDTO;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

	public void processTicketMetaData_FirstStage(TicketMetaDataDTO ticketMetaDataDTO, boolean isClosing)
			throws Exception {
		try {
			if (!ObjectUtils.isEmpty(ticketMetaDataDTO)) {
				Long ticketid = ticketMetaDataDTO.getTicketid();
				int status = ticketMetaDataDTO.getStatus();
				if (status == 2) {
					throw new Exception(ErrorMsgs.TICKET_ALREADY_CLOSED);
				} else {
					if (isClosing) {
						status = 2;

					} else {
						if (status == 0) {
							status = 1;
						}
					}

					ticketMetaDataRepository.moveTicketMetaDataToNextStage(ticketid, status);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void inactivatePreviousTicketForProgressTicket(TicketProgressRequestDTO ticketProgressRequestDTO)
			throws Exception {
		try {
			String workdone = ticketProgressRequestDTO.getWorkdone();
			String notes = ticketProgressRequestDTO.getNotes();
			Long ticketid = ticketProgressRequestDTO.getTicketid();
			ticketRepository.moveTicketToNextStage_PrevTicket(ticketid, workdone, notes);

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public void insertNewRecordForTicketProgress(TicketProgressRequestDTO ticketProgressRequestDTO,
			TicketDTO inactivatedPreviousTicketDTO) throws Exception {
		try {
			TicketDTO ticketDTO = new TicketDTO();
			BeanUtils.copyProperties(inactivatedPreviousTicketDTO, ticketDTO);
			ticketDTO.setId(null);
			ticketDTO.setUpdatedon(null);
			ticketDTO.setCurrentassigneeuserid(ticketProgressRequestDTO.getCurrentassigneeuserid());
			ticketDTO.setCurrentassigneeroleid(ticketProgressRequestDTO.getCurrentassigneeroleid());
			ticketDTO.setActivestage(AppConstants.DEFAULT_ACTIVE_STATUS);
			ticketDTO.setAssignedbyroleid(ticketProgressRequestDTO.getAssignedbyroleid());
			ticketDTO.setAssignedbyuserid(ticketProgressRequestDTO.getAssignedbyuserid());
			ticketDTO.setWorkdone(null);
			ticketDTO.setNotes(null);
			ticketRepository.save(ticketDTO);

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public void closeticket(TicketProgressRequestDTO ticketProgressRequestDTO) throws Exception {
		try {
			String workdone = ticketProgressRequestDTO.getWorkdone();
			String notes = ticketProgressRequestDTO.getNotes();
			ticketRepository.closetTicket(ticketProgressRequestDTO.getTicketid(), workdone, notes,
					ticketProgressRequestDTO.getCurrentassigneeuserid(),
					ticketProgressRequestDTO.getCurrentassigneeroleid());

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public List<Map<String, Object>> getMyTickets(Long userid) {
		try {
			List<Map<String, Object>> contentInfo = ticketRepository.getMyTickets(userid);
			return contentInfo;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public List<Map<String, Object>> getMyCustomerTickets(Long userid) {
		try {
			List<Map<String, Object>> contentInfo = ticketRepository.getMyCustomerTickets(userid);
			return contentInfo;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public List<Map<String, Object>> searchTickets(Long userid, String filterCondition) {
		try {
			List<Map<String, Object>> contentInfo = ticketRepository.searchTickets(userid, filterCondition);
			return contentInfo;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public List<Map<String, Object>> searchCustomerTickets(String filterCondition) {
		try {
			List<Map<String, Object>> contentInfo = ticketRepository.searchCustomerTickets(filterCondition);
			return contentInfo;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

}
