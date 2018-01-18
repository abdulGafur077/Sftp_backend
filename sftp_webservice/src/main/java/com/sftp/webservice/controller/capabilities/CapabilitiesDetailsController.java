package com.sftp.webservice.controller.capabilities;

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
import com.sftp.dto.capabilities.CapabilitiesDetailsDTO;
import com.sftp.service.capabilities.CapabilitiesDetailsService;
import com.sftp.webservice.common.CommonResponse;
import com.sftp.webservice.exception.GeneralError;
import com.sftp.webservice.utils.STApplicationUtils;

@RestController
public class CapabilitiesDetailsController {
	private static final Logger OUT = LoggerFactory.getLogger(CapabilitiesDetailsController.class);

	@RequestMapping(value = URIConstants.GET_ALL_CAP_DETAILS, method = RequestMethod.POST)
	public CommonResponse getAllCapDetails(Authentication auth) {

		OUT.info("REQ: Getting all Capabilities details STARTED");
		CommonResponse commonResponseDTO = null;
		try {
			commonResponseDTO = new CommonResponse();
			commonResponseDTO.setResponseObject(new CapabilitiesDetailsService().getAllCapabilitiesDetails());
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: Getting all Capabilities details COMPLETED with status :{}", commonResponseDTO.getStatus());
		return commonResponseDTO;
	}

	@RequestMapping(value = URIConstants.GET_CAP_DETAILS, method = RequestMethod.POST)
	public CommonResponse getCapabilitiesDetailsById(Authentication auth, @PathVariable Integer id) {
		OUT.info("REQ: Getting  details for id :{} STARTED", id);
		CommonResponse commonResponseDTO = null;
		try {
			commonResponseDTO = new CommonResponse();
			commonResponseDTO.setResponseObject(new CapabilitiesDetailsService().getCapabilitiesDetailsById(id));
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: Getting all Capabilities details COMPLETED with status :{}", commonResponseDTO.getStatus());
		return commonResponseDTO;
	}

	@RequestMapping(value = URIConstants.INSERT_CAP_DETAILS, method = RequestMethod.POST)
	public CommonResponse addCapabilitiesDetails(Authentication auth,
			@Validated @RequestBody CapabilitiesDetailsDTO capDetailsObject) {
		OUT.info("REQ: Inserting Capabilities details STARTED");
		CommonResponse commonResponseDTO = null;
		try {
			commonResponseDTO = new CommonResponse();
			commonResponseDTO
					.setResponseObject(new CapabilitiesDetailsService().addCapabilitiesDetails(capDetailsObject));
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: Inserting Capabilities details COMPLETED with status :{}", commonResponseDTO.getStatus());
		return commonResponseDTO;
	}

	@RequestMapping(value = URIConstants.UPDATE_CAP_DETAILS, method = RequestMethod.POST)
	public CommonResponse updateBusDetails(Authentication auth,
			@Validated @RequestBody CapabilitiesDetailsDTO capDetailsObject) {
		OUT.info("REQ: Updating Capabilities details STARTED");
		CommonResponse commonResponseDTO = null;
		try {
			commonResponseDTO = new CommonResponse();
			commonResponseDTO
					.setResponseObject(new CapabilitiesDetailsService().updateCapabilitiesDetails(capDetailsObject));
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: Updating Capabilities details COMPLETED with status :{}", commonResponseDTO.getStatus());
		return commonResponseDTO;
	}

	@RequestMapping(value = URIConstants.DELETE_CAP_DETAILS_BY_ID, method = RequestMethod.POST)
	public CommonResponse deleteCapabilitiesDetailsById(Authentication auth, @PathVariable Integer id) {
		OUT.info("REQ: Deleting Capabilities details for id :{} STARTED", id);
		CommonResponse commonResponseDTO = null;
		try {
			commonResponseDTO = new CommonResponse();
			new CapabilitiesDetailsService().deleteCapabilitiesDetailsById(id);
			commonResponseDTO.setStatus(ApplicationConstants.getSuccess());
		} catch (STException e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, e.getMessage(), e.getErrorMessage());
		} catch (Exception e) {
			OUT.error(ApplicationConstants.getException(), e);
			STApplicationUtils.setErrorResponse(commonResponseDTO, GeneralError.INTERNAL_ERROR.getErrorCode(),
					e.getMessage());
		}
		OUT.info("RESP: Deleting Capabilities details COMPLETED with status :{}", commonResponseDTO.getStatus());
		return commonResponseDTO;
	}
}
