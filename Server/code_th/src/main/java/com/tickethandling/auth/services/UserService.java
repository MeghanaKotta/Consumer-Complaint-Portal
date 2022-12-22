package com.tickethandling.auth.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.tickethandling.auth.dto.LoginDTO;
import com.tickethandling.auth.dto.ManagerAuthDTO;
import com.tickethandling.auth.dto.SessionDetailsDTO;
import com.tickethandling.auth.dto.UserDetailsDTO;
import com.tickethandling.auth.dto.UserSignUpDTO;
import com.tickethandling.auth.repositories.DepartmentsRepository;
import com.tickethandling.auth.repositories.ManagerAccessRepository;
import com.tickethandling.auth.repositories.OrganizationsRepository;
import com.tickethandling.auth.repositories.SessionDetailsRepository;
import com.tickethandling.auth.repositories.UserRepository;
import com.tickethandling.constants.AppConstants;
import com.tickethandling.constants.ErrorMsgs;
import com.tickethandling.dto.DepartmentsDTO;
import com.tickethandling.dto.OrganizationsDTO;
import com.tickethandling.dto.UserDepOrgMappingDTO;
import com.tickethandling.email.EmailService;
import com.tickethandling.repositories.UserDepOrgMappingRepository;
import com.tickethandling.services.DateUtility;
import com.tickethandling.services.InitialData;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InitialData initialData;

	@Autowired
	private UtilityService utilityService;

	@Autowired
	private ManagerAccessRepository managerAccessRepository;

	@Autowired
	private SessionDetailsRepository sessionDetailsRepository;

	@Autowired
	private UserDepOrgMappingRepository userOrgMappingRepository;

	@Autowired
	private DateUtility dateUtility;

	@Autowired
	private EmailService emailService;

	@Autowired
	private InitialData intialData;

	@Autowired
	private DepartmentsRepository departmentsRepository;

	@Autowired
	private OrganizationsRepository organizationsRepository;

	public boolean register(UserSignUpDTO userSignUpDTO) throws Exception {
		// TODO Auto-generated method stub
		try {
			if (!ObjectUtils.isEmpty(userSignUpDTO) && !ObjectUtils.isEmpty(userSignUpDTO.getUserDetailsDTO())) {

				if (!ObjectUtils.isEmpty(userSignUpDTO.getIsManager()) && userSignUpDTO.getIsManager()) {
					return registerManager(userSignUpDTO);
				} else {
					registerOrgMemOrCustomer(userSignUpDTO);
				}

			} else {
				throw new Exception(ErrorMsgs.INVALIDETAILS);
			}

		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	public void registerOrgMemOrCustomer(UserSignUpDTO userSignUpDTO) throws Exception {
		UserDetailsDTO userDetailsDTO = userSignUpDTO.getUserDetailsDTO();
		isUsernameAndEmailNotFound(userDetailsDTO);
		if (userSignUpDTO.getIsOrgMember()) {
			userDetailsDTO.setRoleid(initialData.getOrgMemberRoleId());
			userDetailsDTO.setCreatedby(userSignUpDTO.getManagerUserid());
			userDetailsDTO.setEmailidactivated(AppConstants.DEFAULT_ACTIVE_STATUS);
		} else {
			userDetailsDTO.setRoleid(initialData.getCustomerRoleId());
			userDetailsDTO.setCreatedby((long) AppConstants.CREATEDBY_SELF_VALUE);
		}
		userDetailsDTO.setPassword(utilityService.generateEncodedPassword(userDetailsDTO.getPassword()));
		UserDetailsDTO userDetailsDTO2 = userRepository.save(userDetailsDTO);
		if (userSignUpDTO.getIsOrgMember()) {
			UserDepOrgMappingDTO userOrgMappingDTO = new UserDepOrgMappingDTO();
			userOrgMappingDTO.setUserid(userDetailsDTO2.getUserid());
			userOrgMappingDTO.setDepid(userSignUpDTO.getOrgMemberDepId());
			userOrgMappingDTO.setOrgid(userSignUpDTO.getOrgMemberOrgId());
			userOrgMappingRepository.save(userOrgMappingDTO);

		}
		if (!userSignUpDTO.getIsOrgMember()) {
			emailService.sendActivationEmail(userDetailsDTO2.getUserid(), userDetailsDTO2.getEmailid());
		}

	}

	public Map<String, Object> login(LoginDTO loginDTO, HttpServletRequest request) throws Exception {
		try {
			if (!ObjectUtils.isEmpty(loginDTO)) {

				String password = utilityService.generateEncodedPassword(loginDTO.getPassword());
				List<UserDetailsDTO> userDetailsDTOList = null;
				if (!ObjectUtils.isEmpty(loginDTO.getUsername())) {
					String username = loginDTO.getUsername();
					userDetailsDTOList = userRepository.findByUsernameAndPassword(username, password);
				} else {
					String emailId = loginDTO.getEmailid();
					userDetailsDTOList = userRepository.findByEmailidAndPassword(emailId, password);
				}
				if (!ObjectUtils.isEmpty(userDetailsDTOList)) {
					SessionDetailsDTO sessionDetailsDTO = new SessionDetailsDTO();
					sessionDetailsDTO.setLoggedinat(dateUtility.getCurrentDateAndTime());
					sessionDetailsDTO.setActive(1);
					sessionDetailsDTO.setUserid(userDetailsDTOList.get(0).getUserid());
					String sessionKey = utilityService
							.encryptMsg(String.valueOf((new Date()).getTime()) + sessionDetailsDTO.getUserid());
					sessionDetailsDTO.setSessionkey(sessionKey);
					sessionDetailsRepository.save(sessionDetailsDTO);

					Map<String, Object> finalData = new HashMap<String, Object>();
					finalData.put("sessionkey", sessionKey);
					userDetailsDTOList.get(0).setPassword(null);
					finalData.put("userdetails", userDetailsDTOList.get(0));
					List<DepartmentsDTO> departmentsDTO = departmentsRepository
							.getDepartmentsOnUserId(userDetailsDTOList.get(0).getUserid());
					List<OrganizationsDTO> organizationsDTOs = organizationsRepository
							.gerOrgInfoOnUserid(userDetailsDTOList.get(0).getUserid());

					finalData.put("associatedDepOrgs", initialData.mapTheDepOrg(departmentsDTO, organizationsDTOs));
					return finalData;

				} else {
					throw new Exception(ErrorMsgs.LOGINAUTHFAILED);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return null;
	}

	public boolean registerManager(UserSignUpDTO userSignUpDTO) throws Exception {
		try {
			if (!ObjectUtils.isEmpty(userSignUpDTO) && !ObjectUtils.isEmpty(userSignUpDTO.getUserDetailsDTO())
					&& !ObjectUtils.isEmpty(userSignUpDTO.getManagerAuthDTO())
					&& !ObjectUtils.isEmpty(userSignUpDTO.getManagerAuthDTO().getUsername())
					&& !ObjectUtils.isEmpty(userSignUpDTO.getManagerAuthDTO().getKey())) {
				isUsernameAndEmailNotFound(userSignUpDTO.getUserDetailsDTO());
				// Auth and save
				String username = userSignUpDTO.getManagerAuthDTO().getUsername();
				String key = userSignUpDTO.getManagerAuthDTO().getKey();
				boolean isManagerAccessApproved = authenticateManagerAccess(username, key);
				if (isManagerAccessApproved) {
					Long managerRoleId = initialData.getManagerRoleId();
					UserDetailsDTO userDetailsDTO = userSignUpDTO.getUserDetailsDTO();
					userDetailsDTO.setRoleid(managerRoleId);
					userDetailsDTO.setCreatedby((long) AppConstants.CREATEDBY_SELF_VALUE);
					userDetailsDTO.setPassword(utilityService.generateEncodedPassword(userDetailsDTO.getPassword()));
					UserDetailsDTO udto = userRepository.save(userDetailsDTO);
					emailService.sendActivationEmail(udto.getUserid(), udto.getEmailid());

				} else {
					throw new Exception(ErrorMsgs.MANAGER_AUTH_FAILED);
				}

			} else {
				throw new Exception(ErrorMsgs.INVALIDETAILS);
			}
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	public Map<String, Object> checkSession(String sessionKey) throws Exception {
		try {
			Map<String, Object> finalData = new HashMap<String, Object>();
			finalData.put("userdetails", null);
			finalData.put("sessionkey", sessionKey);
			finalData.put("valid", false);
			List<SessionDetailsDTO> sessionRecords = sessionDetailsRepository.findBySessionkeyAndActive(sessionKey, 1);
			if (ObjectUtils.isEmpty(sessionRecords) || sessionRecords.size() < 1) {
				return finalData;
			} else {
				SessionDetailsDTO sessionRecord = sessionRecords.get(0);
				List<UserDetailsDTO> userDetailsDTOList = userRepository.findByUserid(sessionRecord.getUserid());
				userDetailsDTOList.get(0).setPassword(null);
				finalData.put("userdetails", userDetailsDTOList.get(0));
				finalData.put("valid", true);
				List<DepartmentsDTO> departmentsDTO = departmentsRepository
						.getDepartmentsOnUserId(userDetailsDTOList.get(0).getUserid());
				List<OrganizationsDTO> organizationsDTOs = organizationsRepository
						.gerOrgInfoOnUserid(userDetailsDTOList.get(0).getUserid());

				finalData.put("associatedDepOrgs", initialData.mapTheDepOrg(departmentsDTO, organizationsDTOs));
				return finalData;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public boolean logout(String sessionKey) throws Exception {
		try {
			sessionDetailsRepository.updateSessionEnd(sessionKey, dateUtility.getCurrentDateAndTime());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;
	}

	public void isUsernameAndEmailNotFound(UserDetailsDTO userDetailsDTO) throws Exception {
		try {

			if (ObjectUtils.isEmpty(userDetailsDTO.getUsername()) && ObjectUtils.isEmpty(userDetailsDTO.getEmailid())) {
				throw new Exception(ErrorMsgs.INVALIDETAILS);
			}
			List<UserDetailsDTO> usersList = null;
			if (!ObjectUtils.isEmpty(userDetailsDTO.getUsername())) {

				usersList = userRepository.findByUsername(userDetailsDTO.getUsername());
				if (!ObjectUtils.isEmpty(usersList)) {
					throw new Exception(ErrorMsgs.USERNAMEFOUND);
				}
			}
			if (!ObjectUtils.isEmpty(userDetailsDTO.getUsername())) {
				usersList = userRepository.findByEmailid(userDetailsDTO.getEmailid());
				if (!ObjectUtils.isEmpty(usersList)) {
					throw new Exception(ErrorMsgs.EMAILIDFOUND);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public boolean authenticateManagerAccess(String username, String key) throws Exception {
		try {
			if (!ObjectUtils.isEmpty(username) && !ObjectUtils.isEmpty(key)) {
				List<ManagerAuthDTO> managerAuthDTOs = managerAccessRepository.findByUsernameAndKey(username, key);
				if (ObjectUtils.isEmpty(managerAuthDTOs)) {
					return false;
				}
				return true;

			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public boolean updateAvatar(Long userid, int avatarid) {
		try {
			userRepository.updateAvatar(userid, avatarid);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;
	}

	public boolean updatePassword(Long userid, String password) {
		try {
			String encodedPswd = utilityService.generateEncodedPassword(password);
			userRepository.updatePassword(userid, encodedPswd);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;
	}
}
