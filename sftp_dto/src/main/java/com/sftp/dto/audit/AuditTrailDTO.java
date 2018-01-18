package com.sftp.dto.audit;

import java.util.Date;
import java.util.List;

import com.sftp.dto.IModel;

public class AuditTrailDTO implements IModel {
	private static final long serialVersionUID = 1L;

	private int id;
	private String module;
	private String message;
	private String performedBy;
	private String actionPerformed;
	private String auditValue;
	private Date actionTime;

	// Non Table Fields
	private Date fromTime;
	private Date toTime;
	private List<Object> actionsSelected;
	private List<Object> modulesSelected;
	private List<Object> usersSelected;
	private String auditMsg;
	private String durationTypeSelected;

	public int getId() {
		return id;
	}

	public String getModule() {
		return module;
	}

	public String getMessage() {
		return message;
	}

	public String getPerformedBy() {
		return performedBy;
	}

	public String getActionPerformed() {
		return actionPerformed;
	}

	public String getAuditValue() {
		return auditValue;
	}

	public Date getActionTime() {
		return actionTime;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public Date getToTime() {
		return toTime;
	}

	public List<Object> getActionsSelected() {
		return actionsSelected;
	}

	public List<Object> getModulesSelected() {
		return modulesSelected;
	}

	public List<Object> getUsersSelected() {
		return usersSelected;
	}

	public String getAuditMsg() {
		return auditMsg;
	}

	public String getDurationTypeSelected() {
		return durationTypeSelected;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}

	public void setActionPerformed(String actionPerformed) {
		this.actionPerformed = actionPerformed;
	}

	public void setAuditValue(String auditValue) {
		this.auditValue = auditValue;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	public void setActionsSelected(List<Object> actionsSelected) {
		this.actionsSelected = actionsSelected;
	}

	public void setModulesSelected(List<Object> modulesSelected) {
		this.modulesSelected = modulesSelected;
	}

	public void setUsersSelected(List<Object> usersSelected) {
		this.usersSelected = usersSelected;
	}

	public void setAuditMsg(String auditMsg) {
		this.auditMsg = auditMsg;
	}

	public void setDurationTypeSelected(String durationTypeSelected) {
		this.durationTypeSelected = durationTypeSelected;
	}

	@Override
	public String toString() {
		return "AuditTrailDTO [id=" + id + ", module=" + module + ", message=" + message + ", performedBy="
				+ performedBy + ", actionPerformed=" + actionPerformed + ", auditValue=" + auditValue + ", actionTime="
				+ actionTime + ", fromTime=" + fromTime + ", toTime=" + toTime + ", actionsSelected=" + actionsSelected
				+ ", modulesSelected=" + modulesSelected + ", usersSelected=" + usersSelected + ", auditMsg=" + auditMsg
				+ ", durationTypeSelected=" + durationTypeSelected + "]";
	}

}
