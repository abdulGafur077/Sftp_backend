package com.sftp.dao.capabilities;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sftp.dao.DBMappingConstants;
import com.sftp.dao.common.DBManager;
import com.sftp.dto.capabilities.CapabilitiesDetailsDTO;

public class CapabilitiesDetailsDAO {
	private static final Logger OUT = LoggerFactory.getLogger(CapabilitiesDetailsDAO.class);

	public List<CapabilitiesDetailsDTO> getAllCapabilitiesDetails() throws Exception {
		OUT.debug("Getting all Capabilities details");
		List<CapabilitiesDetailsDTO> list = DBManager.getInstance()
				.getResultList(DBMappingConstants.GET_ALL_CAP_DETAILS, null);
		OUT.debug("Number of Capabilities details found :{}",
				(list != null && !list.isEmpty()) ? list.size() : "NOT FOUND");
		return list;
	}

	public CapabilitiesDetailsDTO getCapabilitiesDetailsById(int id) throws Exception {
		OUT.debug("Getting Capabilities details for id :{}", id);
		CapabilitiesDetailsDTO capDetailsDTO = (CapabilitiesDetailsDTO) DBManager.getInstance()
				.getResultAsObject(DBMappingConstants.GET_CAP_DETAILS_BY_ID, id);
		OUT.debug("Capabilities details found :{} for id :{}", capDetailsDTO != null ? capDetailsDTO : "NOT FOUND", id);
		return capDetailsDTO;
	}

	public boolean isCapabilitiesNameExist(CapabilitiesDetailsDTO capDetailsDTO) throws Exception {
		boolean exist = false;
		OUT.debug("Checking is Capabilities details exist for name :{}", capDetailsDTO.getName());
		Integer count = DBManager.getInstance().getUniqueCount(DBMappingConstants.IS_CAP_DETAILS_EXIST, capDetailsDTO);
		if (count != null && count > 0) {
			exist = true;
		}
		OUT.debug("Capabilities details exist :{} for name :{}", (count != null && count > 0) ? "FOUND" : "NOT FOUND",
				capDetailsDTO.getName());
		return exist;
	}

	public Integer addCapabilitiesDetails(CapabilitiesDetailsDTO capDetailsDTO) throws Exception {
		OUT.debug("Inserting Capabilities details with name :{}", capDetailsDTO.getName());
		Integer noRecords = DBManager.getInstance().insert(DBMappingConstants.INSERT_CAP_DETAILS, capDetailsDTO);
		OUT.debug("Inserting Capabilities details : {} with name :{}",
				(noRecords != null & noRecords > 0) ? "SUCCESS" : "FAILED", capDetailsDTO.getName());
		return capDetailsDTO.getCapabilitiesId();
	}

	public Integer updateCapabilitiesDetails(CapabilitiesDetailsDTO capDetailsDTO) throws Exception {
		OUT.debug("Updating Capabilities details with id :{}, name :{}", capDetailsDTO.getCapabilitiesId(),
				capDetailsDTO.getName());
		Integer noRecords = DBManager.getInstance().update(DBMappingConstants.UPDATE_CAP_DETAILS, capDetailsDTO);
		return noRecords;
	}

	public void deleteCapabilitiesDetailsById(int id) throws Exception {
		OUT.debug("Deleting Capabilities details with id :{}", id);
		DBManager.getInstance().deleteObjectById(DBMappingConstants.DELETE_CAP_DETAILS_BY_ID, id);
	}

	public List<CapabilitiesDetailsDTO> getCapabilitiesIdList(List<Integer> roleids) throws Exception {
		OUT.debug("Getting Capabilities details with role id :{}", roleids);
		List<CapabilitiesDetailsDTO> capList = DBManager.getInstance()
				.getResultList(DBMappingConstants.GET_ALL_CAP_DETAILS_BY_ROLE_ID, roleids);
		return capList;
	}
	
	public Integer addCapDetailsIntoMappingTable(CapabilitiesDetailsDTO capDetailsDTO) throws Exception {
		OUT.debug("Inserting Mapping table details with role id :{}", capDetailsDTO.getRoleId());
		Integer noRecords = DBManager.getInstance().insert(DBMappingConstants.INSERT_CAP_DETAILS_TO_MAPPING, capDetailsDTO);
		OUT.debug("Inserting Mapping table details : {} with roleid :{}",
				(noRecords != null & noRecords > 0) ? "SUCCESS" : "FAILED", capDetailsDTO.getRoleId());
		return capDetailsDTO.getCapabilitiesId();
	}

	public void deleteMappingDetailsById(int id) throws Exception {
		OUT.debug("Deleting Mapping table details with roleid :{}",id);
		DBManager.getInstance().deleteObjectById(DBMappingConstants.DELETE_CAP_DETAILS_FROM_MAPPING, id);
		OUT.debug("Deleted Mapping details with roleid:{}", id);
	}
	
}
