package com.sftp.dto.roles;

import java.util.List;

import com.sftp.dto.IModel;
import com.sftp.dto.capabilities.CapabilitiesDetailsDTO;

public class RoleDetailsDTO implements IModel {
	private static final long serialVersionUID = 1L;
	private int id;
	private String roleName;
	private String shortName;
	private String description;
	private boolean isActive;

	// Non Table Fields
	private List<CapabilitiesDetailsDTO> capabilitesList;
	private int usergroupId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<CapabilitiesDetailsDTO> getCapabilitesList() {
		return capabilitesList;
	}

	public void setCapabilitesList(List<CapabilitiesDetailsDTO> capabilitesList) {
		this.capabilitesList = capabilitesList;
	}

	public int getUsergroupId() {
		return usergroupId;
	}

	public void setUsergroupId(int usergroupId) {
		this.usergroupId = usergroupId;
	}
}
