package com.tickethandling.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tickethandling.dto.PieChartDataCountResponseDTO;
import com.tickethandling.dto.ResponseDTO;
import com.tickethandling.dto.TicketCreateRequestDTO;
import com.tickethandling.dto.TicketInfoViewRequestDTO;
import com.tickethandling.dto.TicketProgressRequestDTO;
import com.tickethandling.services.BookmarkService;
import com.tickethandling.services.TicketService;

@RestController
@RequestMapping(value = "ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private BookmarkService bookmarkService;

	ResponseDTO responseDtoGeneral = new ResponseDTO();

	@RequestMapping(value = "createTicket", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO createTicket(@RequestBody TicketCreateRequestDTO tickRequestDTO) {

		ResponseDTO responseDto = null;
		try {
			boolean status = ticketService.createTicket(tickRequestDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("success", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);
		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getTheTicketsPieChartData", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO getTheTicketsPieChartData(@RequestParam int orgid, @RequestParam int depid) {
		ResponseDTO responseDto = null;
		try {
			List<PieChartDataCountResponseDTO> finalData = ticketService.getTheTicketsPieChartData(depid, orgid);
			responseDto = responseDtoGeneral.getSuccessResponse(finalData);
		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());
		}
		return responseDto;
	}

	@RequestMapping(value = "getTicketsDisplayInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO getTicketsDisplayInfo(@RequestBody TicketInfoViewRequestDTO ticketInfoViewRequestDTO) {
		ResponseDTO responseDto = null;
		try {
			Page<List<Map<String, Object>>> finalData = ticketService.getTicketsDisplayInfo(ticketInfoViewRequestDTO,
					ticketInfoViewRequestDTO.getPageNumber(), ticketInfoViewRequestDTO.getNumberOfElementsRequired());
			Map<String, Object> finalResp = new HashMap<String, Object>();
			finalResp.put("data", finalData.getContent());
			finalResp.put("totalPages", finalData.getTotalPages());
			responseDto = responseDtoGeneral.getSuccessResponse(finalResp);
		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());
		}
		return responseDto;
	}

	@RequestMapping(value = "bookMarkTicket", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO bookMarkTicket(@RequestBody Map<String, Object> info) {
		ResponseDTO responseDto = null;
		try {
			Long userid = Long.parseLong(info.get("userid").toString());
			Long ticketid = Long.parseLong(info.get("ticketid").toString());
			Long status = Long.parseLong(info.get("status").toString());
			boolean statusResp = false;
			if (status == 1) {
				statusResp = bookmarkService.bookMarkTicket(userid, ticketid);
			} else {
				statusResp = bookmarkService.removeBookMarkTicket(userid, ticketid);
			}

			Map<String, Object> finalResp = new HashMap<String, Object>();
			finalResp.put("updated", statusResp);
			responseDto = responseDtoGeneral.getSuccessResponse(finalResp);
		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());
		}
		return responseDto;
	}

	@RequestMapping(value = "getTicketViewInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO getTicketViewInfo(@RequestBody Map<String, Object> info) {

		ResponseDTO responseDto = null;
		try {
			Long userid = Long.parseLong(info.get("userid").toString());
			Long ticketid = Long.parseLong(info.get("ticketid").toString());
			Map<String, Object> headerInfo = ticketService.getTicketHeaderInfo(userid, ticketid);
			List<Map<String, Object>> contentInfo = ticketService.getTicketContentInfo(ticketid);
			Map<String, Object> finalResp = new HashMap<String, Object>();
			finalResp.put("headerInfo", headerInfo);
			finalResp.put("contentInfo", contentInfo);
			responseDto = responseDtoGeneral.getSuccessResponse(finalResp);
		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getTicketEditInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO getTicketEditInfo(@RequestBody Map<String, Object> info) {

		ResponseDTO responseDto = null;
		try {
			Long userid = Long.parseLong(info.get("userid").toString());
			Long ticketid = Long.parseLong(info.get("ticketid").toString());
			Map<String, Object> headerInfo = ticketService.getTicketHeaderInfo(userid, ticketid);
			List<Map<String, Object>> contentInfo = ticketService.getTicketContentInfo(ticketid);
			Map<String, Object> footerContent = ticketService.getTicketFooterInfo(userid, ticketid);
			Map<String, Object> finalResp = new HashMap<String, Object>();
			finalResp.put("headerInfo", headerInfo);
			finalResp.put("contentInfo", contentInfo);
			finalResp.put("footerInfo", footerContent);
			responseDto = responseDtoGeneral.getSuccessResponse(finalResp);
		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getEligibleUserIdsListForTicketEdit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO getEligibleUserIdsListForTicketEdit(@RequestBody Map<String, Object> info) {

		ResponseDTO responseDto = null;
		try {
			Long userid = Long.parseLong(info.get("userid").toString());
			Long ticketid = Long.parseLong(info.get("ticketid").toString());
			List<Map<String, Object>> resp = ticketService.getEligibleUserIdsListForTicketEdit(userid, ticketid);
			responseDto = responseDtoGeneral.getSuccessResponse(resp);
		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "moveTicketOpenToProgress", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO moveOpenToProgress(@RequestBody TicketProgressRequestDTO ticketProgressRequestDTO) {
		ResponseDTO responseDto = null;
		try {
			boolean status = ticketService.makeAProgressToTicket(ticketProgressRequestDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);
		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getMyTickets", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO getMyTickets(@RequestBody Map<String, Object> info) {

		ResponseDTO responseDto = null;
		try {
			Long userid = Long.parseLong(info.get("userid").toString());
			List<Map<String, Object>> resp = ticketService.getMyTickets(userid);
			responseDto = responseDtoGeneral.getSuccessResponse(resp);
		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getMyCustomerTickets", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO getMyCustomerTickets(@RequestBody Map<String, Object> info) {

		ResponseDTO responseDto = null;
		try {
			Long userid = Long.parseLong(info.get("userid").toString());
			List<Map<String, Object>> resp = ticketService.getMyCustomerTickets(userid);
			responseDto = responseDtoGeneral.getSuccessResponse(resp);
		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "searchTickets", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO searchTickets(@RequestBody Map<String, Object> info) {

		ResponseDTO responseDto = null;
		try {
			Long userid = Long.parseLong(info.get("userid").toString());
			String filterCondition = "";
			if (info.containsKey("filterCondition") && !ObjectUtils.isEmpty(info.get("filterCondition"))) {
				filterCondition = info.get("filterCondition").toString();
			}
			List<Map<String, Object>> resp = ticketService.searchTickets(userid, filterCondition);
			responseDto = responseDtoGeneral.getSuccessResponse(resp);
		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());
		}
		return responseDto;
	}

	@RequestMapping(value = "searchCustomerTickets", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO searchCustomerTickets(@RequestBody Map<String, Object> info) {

		ResponseDTO responseDto = null;
		try {
			String filterCondition = "";
			if (info.containsKey("filterCondition") && !ObjectUtils.isEmpty(info.get("filterCondition"))) {
				filterCondition = info.get("filterCondition").toString();
			}
			List<Map<String, Object>> resp = ticketService.searchCustomerTickets(filterCondition);
			responseDto = responseDtoGeneral.getSuccessResponse(resp);
		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());
		}
		return responseDto;
	}

}
