package com.sftp.dao.roles;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sftp.dao.DBMappingConstants;
import com.sftp.dao.common.DBManager;
import com.sftp.dto.roles.RoleDetailsDTO;

public class RoleDetailsDAO {
	private static final Logger OUT = LoggerFactory.getLogger(RoleDetailsDAO.class);

	public List<RoleDetailsDTO> getAllRoleDetails() throws Exception {
		OUT.debug("Getting all Role details");
		List<RoleDetailsDTO> list = DBManager.getInstance().getResultList(DBMappingConstants.GET_ALL_ROLE_DETAILS,
				null);
		OUT.debug("Number of Role details found :{}", (list != null && !list.isEmpty()) ? list.size() : "NOT FOUND");
		return list;
	}

	public RoleDetailsDTO getRoleDetailsById(int id) throws Exception {
		OUT.debug("Getting Role details for id :{}", id);
		RoleDetailsDTO roleDetailsDTO = (RoleDetailsDTO) DBManager.getInstance()
				.getResultAsObject(DBMappingConstants.GET_ROLE_DETAILS_BY_ID, id);
		OUT.debug("Role details found :{} for id :{}", roleDetailsDTO != null ? roleDetailsDTO : "NOT FOUND", id);
		return roleDetailsDTO;
	}

	public boolean isRoleNameExist(RoleDetailsDTO roleDetailsDTO) throws Exception {
		boolean exist = false;
		OUT.debug("Checking is Role details exist for name :{}", roleDetailsDTO.getRoleName());
		Integer count = DBManager.getInstance().getUniqueCount(DBMappingConstants.IS_ROLE_DETAILS_EXIST,
				roleDetailsDTO);
		if (count != null && count > 0) {
			exist = true;
		}
		OUT.debug("Role details exist :{} for name :{}", (count != null && count > 0) ? "FOUND" : "NOT FOUND",
				roleDetailsDTO.getRoleName());
		return exist;
	}

	public Integer addRoleDetails(RoleDetailsDTO roleDetailsDTO) throws Exception {
		OUT.debug("Inserting Role details with name :{}", roleDetailsDTO.getRoleName());
		Integer noRecords = DBManager.getInstance().insert(DBMappingConstants.INSERT_ROLE_DETAILS, roleDetailsDTO);
		OUT.debug("Inserting Role details : {} with name :{}",
				(noRecords != null & noRecords > 0) ? "SUCCESS" : "FAILED", roleDetailsDTO.getRoleName());
		return roleDetailsDTO.getId();
	}

	public Integer updateRoleDetails(RoleDetailsDTO roleDetailsDTO) throws Exception {
		OUT.debug("Updating Role details with id :{}, name :{}", roleDetailsDTO.getId(), roleDetailsDTO.getRoleName());
		DBManager.getInstance().update(DBMappingConstants.UPDATE_ROLE_DETAILS, roleDetailsDTO);
		return roleDetailsDTO.getId();
	}

	public void deleteRoleDetailsById(int id) throws Exception {
		OUT.debug("Deleting Role details with id :{}", id);
		DBManager.getInstance().deleteObjectById(DBMappingConstants.DELETE_ROLE_DETAILS_BY_ID, id);
		OUT.debug("Deleted Role Details with id :{}", id);
	}

	public List<RoleDetailsDTO> getRoleIdList(List<Integer> roleids) throws Exception {
		OUT.debug("Getting Role details with group id :{}", roleids);
		List<RoleDetailsDTO> roleList = DBManager.getInstance()
				.getResultList(DBMappingConstants.GET_ALL_ROLE_DETAILS_BY_ROLE_ID, roleids);
		return roleList;
	}

	public Integer addRoleDetailsIntoMappingTable(RoleDetailsDTO roleDetailsDTO) throws Exception {
		OUT.debug("Inserting Mapping table details with group id :{}", roleDetailsDTO.getUsergroupId());
		Integer noRecords = DBManager.getInstance().insert(DBMappingConstants.INSERT_ROLE_DETAILS_TO_MAPPING,
				roleDetailsDTO);
		OUT.debug("Inserting Mapping table details : {} with roleid :{}",
				(noRecords != null & noRecords > 0) ? "SUCCESS" : "FAILED", roleDetailsDTO.getUsergroupId());
		return roleDetailsDTO.getId();
	}

	public void deleteMappingDetailsById(int id) throws Exception {
		OUT.debug("Deleting Mapping table details with group id:{}", id);
		DBManager.getInstance().deleteObjectById(DBMappingConstants.DELETE_ROLE_DETAILS_FROM_MAPPING, id);
		OUT.debug("Deleted Mapping details with group id:{}", id);
	}
}
