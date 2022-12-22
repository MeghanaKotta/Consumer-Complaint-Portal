package com.tickethandling.auth.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tickethandling.auth.dto.LoginDTO;
import com.tickethandling.auth.dto.SessionDetailsDTO;
import com.tickethandling.auth.dto.UserSignUpDTO;
import com.tickethandling.auth.services.UserService;
import com.tickethandling.dto.ResponseDTO;

@RestController
@RequestMapping(value = "user")
public class UserController {

	ResponseDTO responseDtoGeneral = new ResponseDTO();

	@Autowired
	private UserService userService;

	@RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO register(@RequestBody UserSignUpDTO userSignUpDTO) {

		ResponseDTO responseDto = null;
		try {
			boolean status = userService.register(userSignUpDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("success", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
		ResponseDTO responseDto = null;
		try {
			Map<String, Object> finalData = userService.login(loginDTO, request);
			responseDto = responseDtoGeneral.getSuccessResponse(finalData);

		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "checksession", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO checkSession(@RequestBody SessionDetailsDTO sessionDetailsDTO) {
		ResponseDTO responseDto = null;
		try {
			Map<String, Object> res = userService.checkSession(sessionDetailsDTO.getSessionkey());
			responseDto = responseDtoGeneral.getSuccessResponse(res);

		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO logout(@RequestBody SessionDetailsDTO sessionDetailsDTO) {
		ResponseDTO responseDto = null;
		try {
			boolean loggedout = userService.logout(sessionDetailsDTO.getSessionkey());
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("loggedout", loggedout);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "updateAvatar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO updateAvatar(@RequestBody Map<String, Object> info) {
		ResponseDTO responseDto = null;
		try {
			Long userid = Long.parseLong(info.get("userid").toString());
			int avatarid = Integer.parseInt(info.get("avatarid").toString());
			boolean status = userService.updateAvatar(userid, avatarid);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "updatePassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO updatePassword(@RequestBody Map<String, Object> info) {
		ResponseDTO responseDto = null;
		try {
			Long userid = Long.parseLong(info.get("userid").toString());
			String password = info.get("password").toString();
			boolean status = userService.updatePassword(userid, password);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}
}
