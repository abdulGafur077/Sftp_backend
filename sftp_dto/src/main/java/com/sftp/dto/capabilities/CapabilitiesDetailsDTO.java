package com.sftp.dto.capabilities;

import com.sftp.dto.IModel;

public class CapabilitiesDetailsDTO implements IModel {
	private static final long serialVersionUID = 1L;

	private int capabilitiesId;
	private String name;
	private String shortName;
	private String description;
	private boolean isActive;

	// Non table field
	private int roleId;
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getCapabilitiesId() {
		return capabilitiesId;
	}

	public void setCapabilitiesId(int capabilitiesId) {
		this.capabilitiesId = capabilitiesId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "BusDetailsDTO [capabilitiesId=" + capabilitiesId + ", name=" + name + ", shortName=" + shortName
				+ ", description=" + description + ", isActive=" + isActive + ", roleId=" + roleId + "]";
	}

}
