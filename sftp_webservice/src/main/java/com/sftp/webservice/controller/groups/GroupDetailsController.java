package com.sftp.webservice.controller.groups;

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
import com.sftp.dto.groups.GroupDetailsDTO;
import com.sftp.service.groups.GroupDetailsService;
import com.sftp.webservice.common.CommonResponse;
import com.sftp.webservice.exception.GeneralError;
import com.sftp.webservice.utils.STApplicationUtils;

/***
 * 
 * @author Dayanand K
 * @since 17-01-2018
 */

@RestController
public class GroupDetailsController {
	private static final Logger OUT = LoggerFactory.getLogger(GroupDetailsController.class);

	@RequestMapping(value = URIConstants.GET_ALL_GROUP_DETAILS, method = RequestMethod.POST)
	public CommonResponse getAllCapDetails(Authentication auth) {
		OUT.info("REQ: Getting all Group details STARTED");
		CommonResponse commonResponseDTO = null;
		try {
			commonResponseDTO = new CommonResponse();
			commonResponseDTO.setResponseObject(new GroupDetailsService().getAllGroupDetails());
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: Getting all Group details COMPLETED with status :{}", commonResponseDTO.getStatus());
		return commonResponseDTO;
	}

	@RequestMapping(value = URIConstants.GET_GROUP_DETAILS, method = RequestMethod.POST)
	public CommonResponse getGroupDetailsById(Authentication auth, @PathVariable Integer id) {
		OUT.info("REQ: Getting  details for id :{} STARTED", id);
		CommonResponse commonResponseDTO = null;
		try {
			commonResponseDTO = new CommonResponse();
			commonResponseDTO.setResponseObject(new GroupDetailsService().getGroupDetailsById(id));
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: Getting all Group details COMPLETED with status :{}", commonResponseDTO.getStatus());
		return commonResponseDTO;
	}

	@RequestMapping(value = URIConstants.INSERT_GROUP_DETAILS, method = RequestMethod.POST)
	public CommonResponse addGroupDetails(Authentication auth,
			@Validated @RequestBody GroupDetailsDTO GroupDetailsObject) {
		OUT.info("REQ: Inserting Group details STARTED");
		CommonResponse commonResponseDTO = null;
		try {
			commonResponseDTO = new CommonResponse();
			new GroupDetailsService().addGroupDetails(GroupDetailsObject);
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: Inserting Group details COMPLETED with status :{}", commonResponseDTO.getStatus());
		return commonResponseDTO;
	}

	@RequestMapping(value = URIConstants.UPDATE_GROUP_DETAILS, method = RequestMethod.POST)
	public CommonResponse updateBusDetails(Authentication auth,
			@Validated @RequestBody GroupDetailsDTO GroupDetailsObject) {
		OUT.info("REQ: Updating Group details STARTED");
		CommonResponse commonResponseDTO = null;
		try {
			commonResponseDTO = new CommonResponse();
			new GroupDetailsService().updateGroupDetails(GroupDetailsObject);
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: Updating Group details COMPLETED with status :{}", commonResponseDTO.getStatus());
		return commonResponseDTO;
	}

	@RequestMapping(value = URIConstants.DELETE_GROUP_DETAILS_BY_ID, method = RequestMethod.POST)
	public CommonResponse deleteGroupDetailsById(Authentication auth, @PathVariable Integer id) {
		OUT.info("REQ: Deleting Group details for id :{} STARTED", id);
		CommonResponse commonResponseDTO = null;
		try {
			commonResponseDTO = new CommonResponse();
			new GroupDetailsService().deleteGroupDetailsById(id);
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: Deleting Group details COMPLETED with status :{}", commonResponseDTO.getStatus());
		return commonResponseDTO;
	}
}
