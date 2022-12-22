package com.tickethandling.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tickethandling.dto.ResponseDTO;
import com.tickethandling.dto.UserDepOrgMappingDTO;
import com.tickethandling.services.InitialData;
import com.tickethandling.services.ManagerService;

@RestController
@RequestMapping(value = "manager")
public class ManagerController {

	ResponseDTO responseDtoGeneral = new ResponseDTO();

	@Autowired
	private InitialData initialData;

	@Autowired
	private ManagerService managerService;

	@RequestMapping(value = "getManagersAssociatedtoOrgDep", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO getManagersAssociatedtoOrgDepsc(@RequestParam int orgid, @RequestParam int depid) {

		ResponseDTO responseDto = null;
		try {
			List<String> finalData = managerService.getManagersAssociatedtoOrgDep(depid, orgid);
			responseDto = responseDtoGeneral.getSuccessResponse(finalData);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getDepOrgMappingOnUserId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO getDepOrgMappingOnUserId(@RequestParam Long uid) {

		ResponseDTO responseDto = null;
		try {
			Map<Long, Map<String, Object>> finalData = managerService.getDepOrgMappingOnUserId(uid);
			responseDto = responseDtoGeneral.getSuccessResponse(finalData);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "joinManagerInDepOrg", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO joinManagerInDepOrg(@RequestBody UserDepOrgMappingDTO userDepOrgMappingDTO) {
		ResponseDTO responseDto = null;
		try {
			boolean status = managerService.joinManagerInDepOrg(userDepOrgMappingDTO);
			Map<String, Object> finalResp = new HashMap<String, Object>();
			finalResp.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalResp);
		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());
		}
		return responseDto;
	}

	@RequestMapping(value = "getManagerEligibleJoiningDepOrg", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO getManagerEligibleJoiningDepOrg(@RequestParam Long uid) {
		ResponseDTO responseDto = null;
		try {
			Map<Long, Map<String, Object>> finalData = managerService.getManagerEligibleJoiningDepOrg(uid);
			responseDto = responseDtoGeneral.getSuccessResponse(finalData);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

}
