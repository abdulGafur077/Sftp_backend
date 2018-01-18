package com.sftp.service.roles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sftp.common.consts.ApplicationConstants;
import com.sftp.common.exception.STException;
import com.sftp.dao.capabilities.CapabilitiesDetailsDAO;
import com.sftp.dao.roles.RoleDetailsDAO;
import com.sftp.dto.capabilities.CapabilitiesDetailsDTO;
import com.sftp.dto.roles.RoleDetailsDTO;
import com.sftp.enums.ActionsEnum;
import com.sftp.enums.GeneralErrorEnum;
import com.sftp.enums.ModuleNameEnum;
import com.sftp.service.audit.AuditTrailService;

public class RoleDetailsService {
	private static final Logger OUT = LoggerFactory.getLogger(RoleDetailsService.class);

	public List<RoleDetailsDTO> getAllRoleDetails() throws Exception {
		List<Integer> roleids = new ArrayList<Integer>();
		List<CapabilitiesDetailsDTO> temp = null;
		OUT.debug("Getting all Role details");
		List<RoleDetailsDTO> roleList = new RoleDetailsDAO().getAllRoleDetails();
		for (RoleDetailsDTO roles : roleList) {
			roleids.add(roles.getId());
		}
		List<CapabilitiesDetailsDTO> capDetailsId = new CapabilitiesDetailsDAO().getCapabilitiesIdList(roleids);
		for (RoleDetailsDTO roleDetailsDTO : roleList) {
			for (CapabilitiesDetailsDTO capabilitiesDetailsDTO : capDetailsId) {
				if (roleDetailsDTO.getId() == capabilitiesDetailsDTO.getRoleId()) {
					temp = new ArrayList<CapabilitiesDetailsDTO>();
					if (roleDetailsDTO.getCapabilitesList() != null) {
						temp = roleDetailsDTO.getCapabilitesList();
					}
					temp.add(capabilitiesDetailsDTO);
					roleDetailsDTO.setCapabilitesList(temp);
				}
			}
		}
		OUT.debug("Number of Role details found :{}",
				(roleList != null && !roleList.isEmpty()) ? roleList.size() : "NOT FOUND");
		return roleList;
	}

	public RoleDetailsDTO getRoleDetailsById(int id) throws Exception {
		OUT.debug("Getting Role details for id: {}", id);
		RoleDetailsDTO roleDetailsDTO = new RoleDetailsDAO().getRoleDetailsById(id);
		List<Integer> capId=new ArrayList<Integer>();
		capId.add(id);
		List<CapabilitiesDetailsDTO> capDetailsId = new CapabilitiesDetailsDAO().getCapabilitiesIdList(capId);
		roleDetailsDTO.setCapabilitesList(capDetailsId);
		OUT.debug("Role details found :{}", roleDetailsDTO != null ? roleDetailsDTO : "NOT FOUND");
		return roleDetailsDTO;
	}

	public RoleDetailsDTO addRoleDetails(RoleDetailsDTO roleDetailsDTO) throws Exception {
		OUT.debug("Inserting Role details with name :{}", roleDetailsDTO.getRoleName());
		RoleDetailsDAO roleDetailsDAO = new RoleDetailsDAO();
		if (roleDetailsDAO.isRoleNameExist(roleDetailsDTO)) {
			throw new STException(GeneralErrorEnum.ALREADY_EXISTS.getErrorCode(),
					GeneralErrorEnum.ALREADY_EXISTS.getMessage());
		}
		roleDetailsDTO.setActive(true);
		Integer status = roleDetailsDAO.addRoleDetails(roleDetailsDTO);
		if (status != null && status > 0) {
			List<CapabilitiesDetailsDTO> capList = roleDetailsDTO.getCapabilitesList();
			for(CapabilitiesDetailsDTO capdto : capList)
			{
				capdto.setRoleId(status);
				new CapabilitiesDetailsDAO().addCapDetailsIntoMappingTable(capdto);
			}
			addAuditMsg(roleDetailsDTO, null, "Test", ActionsEnum.CREATE, ApplicationConstants.getAuditMsgHasCreated());
			OUT.debug("Inserted Role details with id :{} and name :{}", roleDetailsDTO.getId(),
					roleDetailsDTO.getRoleName());
		}
		return roleDetailsDTO;
	}

	public RoleDetailsDTO updateRoleDetails(RoleDetailsDTO roleDetailsDTO) throws Exception {
		OUT.debug("Updating Role details with id :{}, name :{}", roleDetailsDTO.getId(),
				roleDetailsDTO.getRoleName());
		RoleDetailsDAO roleDetailsDAO = new RoleDetailsDAO();
		if (roleDetailsDAO.isRoleNameExist(roleDetailsDTO)) {
			throw new STException(GeneralErrorEnum.ALREADY_EXISTS.getErrorCode(),
					GeneralErrorEnum.ALREADY_EXISTS.getMessage());
		}
		RoleDetailsDTO oldBusDetailsDTO = roleDetailsDAO.getRoleDetailsById(roleDetailsDTO.getId());
		Integer status = roleDetailsDAO.updateRoleDetails(roleDetailsDTO);
		if (status != null && status > 0) {
			List<CapabilitiesDetailsDTO> capList = roleDetailsDTO.getCapabilitesList();
			new CapabilitiesDetailsDAO().deleteMappingDetailsById(status);
			for(CapabilitiesDetailsDTO capdto : capList)
			{
				capdto.setRoleId(status);
				new CapabilitiesDetailsDAO().addCapDetailsIntoMappingTable(capdto);
			}
			addAuditMsg(roleDetailsDTO, oldBusDetailsDTO, "Test", ActionsEnum.MODIFY,
					ApplicationConstants.getAuditMsgHasMod());
			OUT.debug("Updated Role details with id :{} and registration number :{}", roleDetailsDTO.getId(),
					roleDetailsDTO.getRoleName());
		}
		return roleDetailsDTO;
	}

	public void deleteRoleDetailsById(int id) throws Exception {
		OUT.debug("Deleting Role details with id :{}", id);
		RoleDetailsDTO oldRoleDetailsDTO = new RoleDetailsDAO().getRoleDetailsById(id);
		new CapabilitiesDetailsDAO().deleteMappingDetailsById(id);
		new RoleDetailsDAO().deleteRoleDetailsById(id);
		addAuditMsg(oldRoleDetailsDTO, oldRoleDetailsDTO, "Test", ActionsEnum.DELETE,
				ApplicationConstants.getAuditMsgHasDel());
	}

	private void addAuditMsg(RoleDetailsDTO newRoleDetailsDTO, RoleDetailsDTO oldBusDetailsDTO, String userLoginId,
			ActionsEnum action, String actionMsg) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(ModuleNameEnum.CAP_DETAILS.getModuleName());
		stringBuilder.append(" ");
		stringBuilder.append(newRoleDetailsDTO.getRoleName());
		stringBuilder.append("( ");
		stringBuilder.append(newRoleDetailsDTO.getId());
		stringBuilder.append(" )");
		stringBuilder.append(actionMsg);
		new AuditTrailService().insertAuditTrial(ModuleNameEnum.CAP_DETAILS, action.toString(),
				stringBuilder.toString(), userLoginId, new Date(), oldBusDetailsDTO, newRoleDetailsDTO);
	}
}
