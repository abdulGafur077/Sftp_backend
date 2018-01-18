package com.sftp.service.groups;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sftp.common.consts.ApplicationConstants;
import com.sftp.common.exception.STException;
import com.sftp.dao.groups.GroupDetailsDAO;
import com.sftp.dao.roles.RoleDetailsDAO;
import com.sftp.dto.groups.GroupDetailsDTO;
import com.sftp.dto.roles.RoleDetailsDTO;
import com.sftp.enums.ActionsEnum;
import com.sftp.enums.GeneralErrorEnum;
import com.sftp.enums.ModuleNameEnum;
import com.sftp.service.audit.AuditTrailService;

public class GroupDetailsService {
	private static final Logger OUT = LoggerFactory.getLogger(GroupDetailsService.class);

	public List<GroupDetailsDTO> getAllGroupDetails() throws Exception {
		List<Integer> Groupids = new ArrayList<Integer>();
		List<RoleDetailsDTO> temp = null;
		OUT.debug("Getting all Group details");
		List<GroupDetailsDTO> GroupList = new GroupDetailsDAO().getAllGroupDetails();
		for (GroupDetailsDTO Groups : GroupList) {
			Groupids.add(Groups.getId());
		}
		List<RoleDetailsDTO> roleDetailsId = new RoleDetailsDAO().getRoleIdList(Groupids);
		for (GroupDetailsDTO groupDetailsDTO : GroupList) {
			for (RoleDetailsDTO roleDetailsDTO : roleDetailsId) {
				if (groupDetailsDTO.getId() == roleDetailsDTO.getUsergroupId()) {
					temp = new ArrayList<RoleDetailsDTO>();
					if (groupDetailsDTO.getRoleList() != null) {
						temp = groupDetailsDTO.getRoleList();
					}
					temp.add(roleDetailsDTO);
					groupDetailsDTO.setRoleList(temp);
				}
			}
		}
		OUT.debug("Number of Group details found :{}",
				(GroupList != null && !GroupList.isEmpty()) ? GroupList.size() : "NOT FOUND");
		return GroupList;
	}

	public GroupDetailsDTO getGroupDetailsById(int id) throws Exception {
		OUT.debug("Getting Group details for id: {}", id);
		GroupDetailsDTO groupDetailsDTO = new GroupDetailsDAO().getGroupDetailsById(id);
		List<Integer> roleId = new ArrayList<Integer>();
		roleId.add(id);
		List<RoleDetailsDTO> roleDetailsId = new RoleDetailsDAO().getRoleIdList(roleId);
		groupDetailsDTO.setRoleList(roleDetailsId);
		OUT.debug("Group details found :{}", groupDetailsDTO != null ? groupDetailsDTO : "NOT FOUND");
		return groupDetailsDTO;
	}

	public GroupDetailsDTO addGroupDetails(GroupDetailsDTO groupDetailsDTO) throws Exception {
		OUT.debug("Inserting Group details with name :{}", groupDetailsDTO.getName());
		GroupDetailsDAO groupDetailsDAO = new GroupDetailsDAO();
		if (groupDetailsDAO.isGroupNameExist(groupDetailsDTO)) {
			throw new STException(GeneralErrorEnum.ALREADY_EXISTS.getErrorCode(),
					GeneralErrorEnum.ALREADY_EXISTS.getMessage());
		}
		groupDetailsDTO.setActive(true);
		Integer status = groupDetailsDAO.addGroupDetails(groupDetailsDTO);
		if (status != null && status > 0) {
			List<RoleDetailsDTO> roleList = groupDetailsDTO.getRoleList();
			for (RoleDetailsDTO roleDetailsDTO : roleList) {
				roleDetailsDTO.setUsergroupId(status);
				new RoleDetailsDAO().addRoleDetailsIntoMappingTable(roleDetailsDTO);
			}
			addAuditMsg(groupDetailsDTO, null, "Test", ActionsEnum.CREATE,
					ApplicationConstants.getAuditMsgHasCreated());
			OUT.debug("Inserted Group details with id :{} and name :{}", groupDetailsDTO.getId(),
					groupDetailsDTO.getName());
		}
		return groupDetailsDTO;
	}

	public GroupDetailsDTO updateGroupDetails(GroupDetailsDTO groupDetailsDTO) throws Exception {
		OUT.debug("Updating Group details with id :{}, name :{}", groupDetailsDTO.getId(), groupDetailsDTO.getName());
		GroupDetailsDAO groupDetailsDAO = new GroupDetailsDAO();
		if (groupDetailsDAO.isGroupNameExist(groupDetailsDTO)) {
			throw new STException(GeneralErrorEnum.ALREADY_EXISTS.getErrorCode(),
					GeneralErrorEnum.ALREADY_EXISTS.getMessage());
		}
		GroupDetailsDTO oldBusDetailsDTO = groupDetailsDAO.getGroupDetailsById(groupDetailsDTO.getId());
		Integer status = groupDetailsDAO.updateGroupDetails(groupDetailsDTO);
		if (status != null && status > 0) {
			List<RoleDetailsDTO> roleList = groupDetailsDTO.getRoleList();
			new RoleDetailsDAO().deleteMappingDetailsById(status);
			for (RoleDetailsDTO roleDetailsDTO : roleList) {
				roleDetailsDTO.setUsergroupId(status);
				new RoleDetailsDAO().addRoleDetailsIntoMappingTable(roleDetailsDTO);
			}
			addAuditMsg(groupDetailsDTO, oldBusDetailsDTO, "Test", ActionsEnum.MODIFY,
					ApplicationConstants.getAuditMsgHasMod());
			OUT.debug("Updated Group details with id :{} and registration number :{}", groupDetailsDTO.getId(),
					groupDetailsDTO.getName());
		}
		return groupDetailsDTO;
	}

	public void deleteGroupDetailsById(int id) throws Exception {
		OUT.debug("Deleting Group details with id :{}", id);
		GroupDetailsDTO oldGroupDetailsDTO = new GroupDetailsDAO().getGroupDetailsById(id);
		new RoleDetailsDAO().deleteMappingDetailsById(id);
		new GroupDetailsDAO().deleteGroupDetailsById(id);
		addAuditMsg(oldGroupDetailsDTO, oldGroupDetailsDTO, "Test", ActionsEnum.DELETE,
				ApplicationConstants.getAuditMsgHasDel());
		OUT.debug("Deleted group details with id:{}", id);
	}

	private void addAuditMsg(GroupDetailsDTO newGroupDetailsDTO, GroupDetailsDTO oldBusDetailsDTO, String userLoginId,
			ActionsEnum action, String actionMsg) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(ModuleNameEnum.CAP_DETAILS.getModuleName());
		stringBuilder.append(" ");
		stringBuilder.append(newGroupDetailsDTO.getName());
		stringBuilder.append("( ");
		stringBuilder.append(newGroupDetailsDTO.getId());
		stringBuilder.append(" )");
		stringBuilder.append(actionMsg);
		new AuditTrailService().insertAuditTrial(ModuleNameEnum.CAP_DETAILS, action.toString(),
				stringBuilder.toString(), userLoginId, new Date(), oldBusDetailsDTO, newGroupDetailsDTO);
	}
}
