package com.sftp.webservice.controller.roles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sftp.common.consts.ApplicationConstants;
import com.sftp.common.consts.URIConstants;
import com.sftp.common.exception.STException;
import com.sftp.dto.roles.RoleDetailsDTO;
import com.sftp.service.roles.RoleDetailsService;
import com.sftp.webservice.common.CommonResponse;
import com.sftp.webservice.exception.GeneralError;
import com.sftp.webservice.utils.STApplicationUtils;

/***
 * 
 * @author Dayanand K
 * @since 17-01-2018
 */

@RestController
public class RoleDetailsController {
	private static final Logger OUT = LoggerFactory.getLogger(RoleDetailsController.class);

	@RequestMapping(value = URIConstants.GET_ALL_ROLE_DETAILS, method = RequestMethod.POST)
	public CommonResponse getAllCapDetails(Authentication auth) {
		OUT.info("REQ: Getting all Role details STARTED");
		CommonResponse commonResponseDTO = null;
		try {
			commonResponseDTO = new CommonResponse();
			commonResponseDTO.setResponseObject(new RoleDetailsService().getAllRoleDetails());
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: Getting all Role details COMPLETED with status :{}", commonResponseDTO.getStatus());
		return commonResponseDTO;
	}

	@RequestMapping(value = URIConstants.GET_ROLE_DETAILS, method = RequestMethod.POST)
	public CommonResponse getRoleDetailsById(Authentication auth, @PathVariable Integer id) {
		OUT.info("REQ: Getting  details for id :{} STARTED", id);
		CommonResponse commonResponseDTO = null;
		try {
			commonResponseDTO = new CommonResponse();
			commonResponseDTO.setResponseObject(new RoleDetailsService().getRoleDetailsById(id));
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: Getting all Role details COMPLETED with status :{}", commonResponseDTO.getStatus());
		return commonResponseDTO;
	}

	@RequestMapping(value = URIConstants.INSERT_ROLE_DETAILS, method = RequestMethod.POST)
	public CommonResponse addRoleDetails(Authentication auth,
			@Validated @RequestBody RoleDetailsDTO roleDetailsObject) {
		OUT.info("REQ: Inserting Role details STARTED");
		CommonResponse commonResponseDTO = null;
		try {
			commonResponseDTO = new CommonResponse();
			new RoleDetailsService().addRoleDetails(roleDetailsObject);
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: Inserting Role details COMPLETED with status :{}", commonResponseDTO.getStatus());
		return commonResponseDTO;
	}

	@RequestMapping(value = URIConstants.UPDATE_ROLE_DETAILS, method = RequestMethod.POST)
	public CommonResponse updateBusDetails(Authentication auth,
			@Validated @RequestBody RoleDetailsDTO roleDetailsObject) {
		OUT.info("REQ: Updating Role details STARTED");
		CommonResponse commonResponseDTO = null;
		try {
			commonResponseDTO = new CommonResponse();
			new RoleDetailsService().updateRoleDetails(roleDetailsObject);
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: Updating Role details COMPLETED with status :{}", commonResponseDTO.getStatus());
		return commonResponseDTO;
	}

	@RequestMapping(value = URIConstants.DELETE_ROLE_DETAILS_BY_ID, method = RequestMethod.POST)
	public CommonResponse deleteRoleDetailsById(Authentication auth, @PathVariable Integer id) {
		OUT.info("REQ: Deleting Role details for id :{} STARTED", id);
		CommonResponse commonResponseDTO = null;
		try {
			commonResponseDTO = new CommonResponse();
			new RoleDetailsService().deleteRoleDetailsById(id);
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: Deleting Role details COMPLETED with status :{}", commonResponseDTO.getStatus());
		return commonResponseDTO;
	}
}
