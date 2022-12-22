package com.tickethandling.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.tickethandling.auth.dto.UserDetailsDTO;
import com.tickethandling.auth.repositories.UserRepository;
import com.tickethandling.constants.AppConstants;
import com.tickethandling.dto.DepartmentsDTO;
import com.tickethandling.dto.OrganizationsDTO;
import com.tickethandling.dto.RoleTblDTO;
import com.tickethandling.dto.TagsDTO;
import com.tickethandling.repositories.RoleTblRepository;
import com.tickethandling.repositories.TagsRepository;

@Service
public class InitialData implements ApplicationRunner {

	@Autowired
	private RoleTblRepository roleTblRepository;

	private List<RoleTblDTO> roleInfo;

	private Long systemUserId = (long) -1;

	private List<TagsDTO> tagsList = null;

	@Autowired
	private OrganizationsService organizationsService;

	@Autowired
	private DepartmentsService departmentsService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TagsRepository tagsRepository;

	private void loadRoleTblInfo() {
		setRoleInfo(roleTblRepository.findAll());
		System.out.print("**********Role table information ***********" + roleInfo.toString());
	}

	private void loadSystemUserDetails() {
		List<UserDetailsDTO> usls = userRepository.findByUsername(AppConstants.SYSTEM_USERNAME);
		if (!ObjectUtils.isEmpty(usls) && usls.size() > 0) {
			this.setSystemUserId(usls.get(0).getUserid());
		}
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		loadRoleTblInfo();
		loadSystemUserDetails();
		loadTags();
	}

	public List<RoleTblDTO> getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(List<RoleTblDTO> roleInfo) {
		this.roleInfo = roleInfo;
	}

	public Long getCustomerRoleId() {
		if (!ObjectUtils.isEmpty(roleInfo)) {
			for (RoleTblDTO roleTblDTO : roleInfo) {
				if (roleTblDTO.getRolename().equalsIgnoreCase(AppConstants.CUSTOMER_ROLENAME)) {
					return roleTblDTO.getId();
				}
			}
		}
		return null;
	}

	public Long getManagerRoleId() {
		if (!ObjectUtils.isEmpty(roleInfo)) {
			for (RoleTblDTO roleTblDTO : roleInfo) {
				if (roleTblDTO.getRolename().equalsIgnoreCase(AppConstants.MANAGER_ROLENAME)) {
					return roleTblDTO.getId();
				}
			}
		}
		return null;
	}

	public Long getOrgMemberRoleId() {
		if (!ObjectUtils.isEmpty(roleInfo)) {
			for (RoleTblDTO roleTblDTO : roleInfo) {
				if (roleTblDTO.getRolename().equalsIgnoreCase(AppConstants.ORGMEMBER_ROLENAME)) {
					return roleTblDTO.getId();
				}
			}
		}
		return null;
	}

	public Map<Long, Map<String, Object>> getDepOrgMapping() throws Exception {
		try {
			List<DepartmentsDTO> dpLs = departmentsService.getAllDepList();
			List<OrganizationsDTO> orgLs = organizationsService.getAllOrgList();
			return mapTheDepOrg(dpLs, orgLs);

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public Map<Long, Map<String, Object>> mapTheDepOrg(List<DepartmentsDTO> dpLs, List<OrganizationsDTO> orgLs)
			throws Exception {
		try {
			if (!ObjectUtils.isEmpty(dpLs) && !ObjectUtils.isEmpty(orgLs)) {
				Map<Long, List<OrganizationsDTO>> orgMapping = orgLs.stream()
						.collect(Collectors.groupingBy(OrganizationsDTO::getDepid));
				Map<Long, Map<String, Object>> finalData = new HashMap<Long, Map<String, Object>>();
				for (DepartmentsDTO departmentsDTO : dpLs) {
					Long depid = departmentsDTO.getId();
					String depName = departmentsDTO.getDepname();
					Map<String, Object> finalMap = new HashMap<String, Object>();
					finalMap.put("depname", depName);
					finalMap.put("depdesc", departmentsDTO.getDepdesc());
					finalMap.put("id", depid);
					finalMap.put("orgsList", orgMapping.get(depid));
					finalData.put(depid, finalMap);
				}
				return finalData;
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return null;
	}

	public Long getSystemUserId() {
		if (this.systemUserId == -1) {
			loadSystemUserDetails();
		}
		return systemUserId;
	}

	public void setSystemUserId(Long systemUserId) {
		this.systemUserId = systemUserId;
	}

	public void loadTags() {
		setTagsList(tagsRepository.findAll());
	}

	public List<TagsDTO> getTagsList() {
		if (ObjectUtils.isEmpty(tagsList))
			loadTags();
		return tagsList;
	}

	public void setTagsList(List<TagsDTO> tagsList) {
		this.tagsList = tagsList;
	}
}
