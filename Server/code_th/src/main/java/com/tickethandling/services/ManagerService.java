package com.tickethandling.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.tickethandling.auth.repositories.DepartmentsRepository;
import com.tickethandling.auth.repositories.OrganizationsRepository;
import com.tickethandling.auth.repositories.UserRepository;
import com.tickethandling.dto.DepartmentsDTO;
import com.tickethandling.dto.OrganizationsDTO;
import com.tickethandling.dto.UserDepOrgMappingDTO;
import com.tickethandling.repositories.UserDepOrgMappingRepository;

@Service
public class ManagerService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DepartmentsRepository departmentsRepository;

	@Autowired
	private OrganizationsRepository organizationsRepository;

	@Autowired
	private InitialData initialData;

	@Autowired
	private UserDepOrgMappingRepository userDepOrgMappingRepository;

	public List<String> getManagersAssociatedtoOrgDep(int depid, int orgid) {
		try {
			List<Map<String, Object>> datals = userRepository.getManagersAssociatedtoOrgDep(depid, orgid);
			List<String> managersList = new ArrayList<String>();
			if (!ObjectUtils.isEmpty(datals)) {
				for (Map<String, Object> managerDto : datals) {
					managersList.add(managerDto.get("username").toString());
				}
			}
			return managersList;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

	public Map<Long, Map<String, Object>> getDepOrgMappingOnUserId(Long uid) throws Exception {
		List<DepartmentsDTO> departmentsDTOs = departmentsRepository.getDepartmentsOnUserId(uid);
		List<OrganizationsDTO> organizationsDTOs = organizationsRepository.gerOrgInfoOnUserid(uid);
		Map<Long, Map<String, Object>> mappingData = initialData.mapTheDepOrg(departmentsDTOs, organizationsDTOs);
		return mappingData;
	}

	public Map<Long, Map<String, Object>> getManagerEligibleJoiningDepOrg(Long uid) throws Exception {
		try {
			Map<Long, Map<String, Object>> mappingData = new HashMap<Long, Map<String, Object>>();
			List<UserDepOrgMappingDTO> depOrgAssociatedWithManager = userDepOrgMappingRepository.findByUserid(uid);
			Map<Long, Map<String, Object>> totalMapping = initialData.getDepOrgMapping();
			Map<Long, Map<String, Object>> finalMapping = new HashMap<Long, Map<String, Object>>();
			for (Long depKey : totalMapping.keySet()) {

				Map<String, Object> value = totalMapping.get(depKey);
				List<OrganizationsDTO> newOrgList = new ArrayList<OrganizationsDTO>();
				for (OrganizationsDTO orgMapping : (List<OrganizationsDTO>) value.get("orgsList")) {
					boolean isOrgAlreadyExists = false;
					for (UserDepOrgMappingDTO userDepOrgMappingDTO : depOrgAssociatedWithManager) {
						if (userDepOrgMappingDTO.getOrgid() == orgMapping.getId()) {
							isOrgAlreadyExists = true;
						}
					}
					if (!isOrgAlreadyExists) {
						newOrgList.add(orgMapping);
					}

				}
				if (newOrgList.size() > 0) {
					finalMapping.put(depKey, totalMapping.get(depKey));
					finalMapping.get(depKey).put("orgsList", newOrgList);
				}

			}

			return finalMapping;

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public boolean joinManagerInDepOrg(UserDepOrgMappingDTO userDepOrgMappingDTO) throws Exception {
		try {
			if (!ObjectUtils.isEmpty(userDepOrgMappingDTO)) {
				userDepOrgMappingRepository.save(userDepOrgMappingDTO);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;
	}

}
