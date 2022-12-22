package com.tickethandling.auth.dto;

public class UserSignUpDTO {

	private UserDetailsDTO userDetailsDTO;
	private boolean isManager;
	private ManagerAuthDTO managerAuthDTO;

	/**
	 * @return the userDetailsDTO
	 */
	public UserDetailsDTO getUserDetailsDTO() {
		return userDetailsDTO;
	}

	/**
	 * @param userDetailsDTO the userDetailsDTO to set
	 */
	public void setUserDetailsDTO(UserDetailsDTO userDetailsDTO) {
		this.userDetailsDTO = userDetailsDTO;
	}

	/**
	 * @return the isManager
	 */
	public boolean getIsManager() {
		return isManager;
	}

	/**
	 * @param isManager the isManager to set
	 */
	public void getIsManager(boolean isManager) {
		this.isManager = isManager;
	}

	/**
	 * @return the managerAuthDTO
	 */
	public ManagerAuthDTO getManagerAuthDTO() {
		return managerAuthDTO;
	}

	/**
	 * @param managerAuthDTO the managerAuthDTO to set
	 */
	public void setManagerAuthDTO(ManagerAuthDTO managerAuthDTO) {
		this.managerAuthDTO = managerAuthDTO;
	}

	/**
	 * @return the isOrgMember
	 */
	public boolean getIsOrgMember() {
		return isOrgMember;
	}

	/**
	 * @param isOrgMember the isOrgMember to set
	 */
	public void setIsOrgMember(boolean isOrgMember) {
		this.isOrgMember = isOrgMember;
	}

	public Long getManagerUserid() {
		return managerUserid;
	}

	public void setManagerUserid(Long managerUserid) {
		this.managerUserid = managerUserid;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public void setOrgMember(boolean isOrgMember) {
		this.isOrgMember = isOrgMember;
	}

	private boolean isOrgMember;
	private Long orgMemberOrgId;
	public Long getOrgMemberOrgId() {
		return orgMemberOrgId;
	}

	public void setOrgMemberOrgId(Long orgMemberOrgId) {
		this.orgMemberOrgId = orgMemberOrgId;
	}

	public Long getOrgMemberDepId() {
		return orgMemberDepId;
	}

	public void setOrgMemberDepId(Long orgMemberDepId) {
		this.orgMemberDepId = orgMemberDepId;
	}

	private Long orgMemberDepId;

	private Long managerUserid;
}
