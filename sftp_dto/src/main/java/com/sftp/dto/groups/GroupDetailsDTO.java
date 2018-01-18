package com.sftp.dto.groups;

import java.util.List;


import com.sftp.dto.IModel;
import com.sftp.dto.roles.RoleDetailsDTO;

public class GroupDetailsDTO implements IModel{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String shortName;
	private String description;
	private boolean isActive;
	//Non table field
	private List<RoleDetailsDTO> roleList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public List<RoleDetailsDTO> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<RoleDetailsDTO> roleList) {
		this.roleList = roleList;
	}
}
