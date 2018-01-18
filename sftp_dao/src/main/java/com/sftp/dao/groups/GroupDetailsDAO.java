package com.sftp.dao.groups;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sftp.dao.DBMappingConstants;
import com.sftp.dao.common.DBManager;
import com.sftp.dto.groups.GroupDetailsDTO;

public class GroupDetailsDAO {
	private static final Logger OUT = LoggerFactory.getLogger(GroupDetailsDAO.class);

	public List<GroupDetailsDTO> getAllGroupDetails() throws Exception {
		OUT.debug("Getting all Group details");
		List<GroupDetailsDTO> list = DBManager.getInstance().getResultList(DBMappingConstants.GET_ALL_GROUP_DETAILS,
				null);
		OUT.debug("Number of Group details found :{}", (list != null && !list.isEmpty()) ? list.size() : "NOT FOUND");
		return list;
	}

	public GroupDetailsDTO getGroupDetailsById(int id) throws Exception {
		OUT.debug("Getting Group details for id :{}", id);
		GroupDetailsDTO GroupDetailsDTO = (GroupDetailsDTO) DBManager.getInstance()
				.getResultAsObject(DBMappingConstants.GET_GROUP_DETAILS_BY_ID, id);
		OUT.debug("Group details found :{} for id :{}", GroupDetailsDTO != null ? GroupDetailsDTO : "NOT FOUND", id);
		return GroupDetailsDTO;
	}

	public boolean isGroupNameExist(GroupDetailsDTO GroupDetailsDTO) throws Exception {
		boolean exist = false;
		OUT.debug("Checking is Group details exist for name :{}", GroupDetailsDTO.getName());
		Integer count = DBManager.getInstance().getUniqueCount(DBMappingConstants.IS_GROUP_DETAILS_EXIST,
				GroupDetailsDTO);
		if (count != null && count > 0) {
			exist = true;
		}
		OUT.debug("Group details exist :{} for name :{}", (count != null && count > 0) ? "FOUND" : "NOT FOUND",
				GroupDetailsDTO.getName());
		return exist;
	}

	public Integer addGroupDetails(GroupDetailsDTO GroupDetailsDTO) throws Exception {
		OUT.debug("Inserting Group details with name :{}", GroupDetailsDTO.getName());
		Integer noRecords = DBManager.getInstance().insert(DBMappingConstants.INSERT_GROUP_DETAILS, GroupDetailsDTO);
		OUT.debug("Inserting Group details : {} with name :{}",
				(noRecords != null & noRecords > 0) ? "SUCCESS" : "FAILED", GroupDetailsDTO.getName());
		return GroupDetailsDTO.getId();
	}

	public Integer updateGroupDetails(GroupDetailsDTO groupDetailsDTO) throws Exception {
		OUT.debug("Updating Group details with id :{}, name :{}", groupDetailsDTO.getId(),
				groupDetailsDTO.getName());
		DBManager.getInstance().update(DBMappingConstants.UPDATE_GROUP_DETAILS, groupDetailsDTO);
		return groupDetailsDTO.getId();
	}

	public void deleteGroupDetailsById(int id) throws Exception {
		OUT.debug("Deleting Group details with id :{}", id);
		DBManager.getInstance().deleteObjectById(DBMappingConstants.DELETE_GROUP_DETAILS_BY_ID, id);
		OUT.debug("Deleted Group Details with id :{}", id);
	}
}
