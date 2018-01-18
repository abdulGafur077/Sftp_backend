package com.sftp.dao.audit;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sftp.dao.DBMappingConstants;
import com.sftp.dao.common.DBManager;
import com.sftp.dto.audit.AuditTrailDTO;

public class AuditTrialDAO {
	private static final Logger OUT = LoggerFactory.getLogger(AuditTrialDAO.class);

	public int addAuditInfo(AuditTrailDTO auditTrailDTO) throws Exception {
		OUT.info("Inserting in Audit Trail Table with action : {}, for user : {}", auditTrailDTO.getActionPerformed(),
				auditTrailDTO.getPerformedBy());
		DBManager.getInstance().insert(DBMappingConstants.INSERT_AUDIT_TRAIL, auditTrailDTO);
		OUT.info("Inserted in Audit Trail Table Successfully");
		return auditTrailDTO.getId();
	}

	public List<AuditTrailDTO> getAllAuditTrail(AuditTrailDTO auditTrailDTO) throws Exception {
		OUT.debug("Getting Tenant audit trial details");
		return DBManager.getInstance().getResultList(DBMappingConstants.GET_AUDIT_DETAILS, auditTrailDTO);
	}
}
