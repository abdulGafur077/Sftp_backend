package com.sftp.service.capabilities;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sftp.common.consts.ApplicationConstants;
import com.sftp.common.exception.STException;
import com.sftp.dao.capabilities.CapabilitiesDetailsDAO;
import com.sftp.dto.capabilities.CapabilitiesDetailsDTO;
import com.sftp.enums.ActionsEnum;
import com.sftp.enums.GeneralErrorEnum;
import com.sftp.enums.ModuleNameEnum;
import com.sftp.service.audit.AuditTrailService;

public class CapabilitiesDetailsService {
	private static final Logger OUT = LoggerFactory.getLogger(CapabilitiesDetailsService.class);

	public List<CapabilitiesDetailsDTO> getAllCapabilitiesDetails() throws Exception {
		OUT.debug("Getting all capabilities details");
		List<CapabilitiesDetailsDTO> list = new CapabilitiesDetailsDAO().getAllCapabilitiesDetails();
		OUT.debug("Number of Capabilities details found :{}",
				(list != null && !list.isEmpty()) ? list.size() : "NOT FOUND");
		return list;
	}

	public CapabilitiesDetailsDTO getCapabilitiesDetailsById(int id) throws Exception {
		OUT.debug("Getting Capabilities details for id: {}", id);
		CapabilitiesDetailsDTO capDetailsDTO = new CapabilitiesDetailsDAO().getCapabilitiesDetailsById(id);
		OUT.debug("Capabilities details found :{}", capDetailsDTO != null ? capDetailsDTO : "NOT FOUND");
		return capDetailsDTO;
	}

	public CapabilitiesDetailsDTO addCapabilitiesDetails(CapabilitiesDetailsDTO capDetailsDTO) throws Exception {
		OUT.debug("Inserting Capabilities details with name :{}", capDetailsDTO.getName());
		CapabilitiesDetailsDAO capDetailsDAO = new CapabilitiesDetailsDAO();
		if (capDetailsDAO.isCapabilitiesNameExist(capDetailsDTO)) {
			throw new STException(GeneralErrorEnum.ALREADY_EXISTS.getErrorCode(),
					GeneralErrorEnum.ALREADY_EXISTS.getMessage());
		}
		capDetailsDTO.setActive(true);
		Integer status = capDetailsDAO.addCapabilitiesDetails(capDetailsDTO);
		if (status != null && status > 0) {
			addAuditMsg(capDetailsDTO, null, "Test", ActionsEnum.CREATE, ApplicationConstants.getAuditMsgHasCreated());
			OUT.debug("Inserted Capabilities details with id :{} and name :{}", capDetailsDTO.getCapabilitiesId(),
					capDetailsDTO.getName());
		}
		return capDetailsDTO;
	}

	public CapabilitiesDetailsDTO updateCapabilitiesDetails(CapabilitiesDetailsDTO capDetailsDTO) throws Exception {
		OUT.debug("Updating Capabilities details with id :{}, name :{}", capDetailsDTO.getCapabilitiesId(),
				capDetailsDTO.getName());
		CapabilitiesDetailsDAO capDetailsDAO = new CapabilitiesDetailsDAO();
		if (capDetailsDAO.isCapabilitiesNameExist(capDetailsDTO)) {
			throw new STException(GeneralErrorEnum.ALREADY_EXISTS.getErrorCode(),
					GeneralErrorEnum.ALREADY_EXISTS.getMessage());
		}
		CapabilitiesDetailsDTO oldBusDetailsDTO = capDetailsDAO.getCapabilitiesDetailsById(capDetailsDTO.getRoleId());
		Integer status = capDetailsDAO.updateCapabilitiesDetails(capDetailsDTO);
		if (status != null && status > 0) {
			addAuditMsg(capDetailsDTO, oldBusDetailsDTO, "Test", ActionsEnum.MODIFY,
					ApplicationConstants.getAuditMsgHasMod());
			OUT.debug("Updated Capabilities details with id :{} and registration number :{}", capDetailsDTO.getRoleId(),
					capDetailsDTO.getName());
		}
		return capDetailsDTO;
	}

	public void deleteCapabilitiesDetailsById(int id) throws Exception {
		OUT.debug("Deleting Capabilities details with id :{}", id);
		CapabilitiesDetailsDTO oldCapabilitiesDetailsDTO = new CapabilitiesDetailsDAO().getCapabilitiesDetailsById(id);
		new CapabilitiesDetailsDAO().deleteCapabilitiesDetailsById(id);
		addAuditMsg(oldCapabilitiesDetailsDTO, oldCapabilitiesDetailsDTO, "Test", ActionsEnum.DELETE,
				ApplicationConstants.getAuditMsgHasDel());
	}

	private void addAuditMsg(CapabilitiesDetailsDTO newCapabilitiesDetailsDTO, CapabilitiesDetailsDTO oldBusDetailsDTO, String userLoginId,
			ActionsEnum action, String actionMsg) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(ModuleNameEnum.CAP_DETAILS.getModuleName());
		stringBuilder.append(" ");
		stringBuilder.append(newCapabilitiesDetailsDTO.getName());
		stringBuilder.append("( ");
		stringBuilder.append(newCapabilitiesDetailsDTO.getCapabilitiesId());
		stringBuilder.append(" )");
		stringBuilder.append(actionMsg);
		new AuditTrailService().insertAuditTrial(ModuleNameEnum.CAP_DETAILS, action.toString(),
				stringBuilder.toString(), userLoginId, new Date(), oldBusDetailsDTO, newCapabilitiesDetailsDTO);
	}
}
