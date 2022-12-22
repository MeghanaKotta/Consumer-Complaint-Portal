package com.tickethandling.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tickethandling.dto.ResponseDTO;
import com.tickethandling.dto.RoleTblDTO;
import com.tickethandling.dto.TagsDTO;
import com.tickethandling.services.InitialData;
import com.tickethandling.services.ManagerService;

@RestController
@RequestMapping(value = "data")
public class DataController {

	ResponseDTO responseDtoGeneral = new ResponseDTO();

	@Autowired
	private InitialData initialData;

	@Autowired
	private ManagerService managerService;

	@RequestMapping(value = "getRoleInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO register() {

		ResponseDTO responseDto = null;
		try {
			List<RoleTblDTO> roleInfo = initialData.getRoleInfo();
			responseDto = responseDtoGeneral.getSuccessResponse(roleInfo);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getDepOrgMapping", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO getDepOrgMapping() {

		ResponseDTO responseDto = null;
		try {
			Map<Long, Map<String, Object>> finalData = initialData.getDepOrgMapping();
			responseDto = responseDtoGeneral.getSuccessResponse(finalData);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getTagsList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO getTagsList() {

		ResponseDTO responseDto = null;
		try {
			List<TagsDTO> finalData = initialData.getTagsList();
			responseDto = responseDtoGeneral.getSuccessResponse(finalData);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	

}
