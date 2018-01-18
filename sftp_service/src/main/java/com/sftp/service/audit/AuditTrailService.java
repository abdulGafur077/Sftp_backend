package com.sftp.service.audit;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.json.JSONArray;
import org.json.JSONObject;

import com.sftp.dao.audit.AuditTrialDAO;
import com.sftp.dto.audit.AuditTrailDTO;
import com.sftp.enums.CustomFilterEnum;
import com.sftp.enums.FixedDurationFilterEnum;
import com.sftp.enums.ModuleNameEnum;


public class AuditTrailService
{
	public void insertAuditTrial(ModuleNameEnum moduleName, String actionPerformed, String message, String performedBy, Date actionTime, Object oldObj,
			Object newObj) throws Exception
	{
		AuditTrailDTO auditTrailDTO = new AuditTrailDTO();
		auditTrailDTO.setModule(moduleName.getModuleName());
		auditTrailDTO.setActionPerformed(actionPerformed);
		auditTrailDTO.setActionTime(actionTime);
		auditTrailDTO.setMessage(message);
		auditTrailDTO.setPerformedBy(performedBy);

		if (oldObj != null && newObj != null)
		{
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject;
			Diff compare = JaversBuilder.javers().build().compare(oldObj, newObj);
			for (ValueChange javersValue : compare.getChangesByType(ValueChange.class))
			{
				jsonObject = new JSONObject();
				jsonObject.put("propertyName", javersValue.getPropertyName());
				jsonObject.put("oldValue", javersValue.getLeft());
				jsonObject.put("newValue", javersValue.getRight());
				jsonArray.put(jsonObject);
			}
			auditTrailDTO.setAuditValue(jsonArray.toString());
		}
		new AuditTrialDAO().addAuditInfo(auditTrailDTO);
	}

	public List<AuditTrailDTO> getAllAuditTrail(AuditTrailDTO auditTrailDTO) throws Exception
	{
		if (auditTrailDTO.getDurationTypeSelected().equalsIgnoreCase(FixedDurationFilterEnum.TODAY.getValue()))
		{
			Calendar day = Calendar.getInstance();
			auditTrailDTO.setFromTime(getFromTime(day));
			auditTrailDTO.setToTime(getMinToTime(day));
		}
		else if (auditTrailDTO.getDurationTypeSelected().equalsIgnoreCase(FixedDurationFilterEnum.YESTERDAY.getValue()))
		{
			Calendar day = Calendar.getInstance();
			day.add(Calendar.DAY_OF_MONTH, -1);
			auditTrailDTO.setFromTime(getFromTime(day));
			auditTrailDTO.setToTime(getMinToTime(day));
		}
		else if (auditTrailDTO.getDurationTypeSelected().equalsIgnoreCase(FixedDurationFilterEnum.CURRENT_WEEK.getValue()))
		{
			Calendar day = Calendar.getInstance();
			Calendar fromDate = Calendar.getInstance();
			if (fromDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			{
				fromDate.add(Calendar.WEEK_OF_MONTH, -1);
			}
			fromDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			auditTrailDTO.setFromTime(getFromTime(fromDate));
			auditTrailDTO.setToTime(getMinToTime(day));
		}
		else if (auditTrailDTO.getDurationTypeSelected().equalsIgnoreCase(FixedDurationFilterEnum.LAST_WEEK.getValue()))
		{
			Calendar fromDate = Calendar.getInstance();
			fromDate.add(Calendar.DAY_OF_MONTH, -1);
			fromDate.add(Calendar.WEEK_OF_MONTH, -1);
			fromDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

			Calendar toDate = Calendar.getInstance();
			toDate.add(Calendar.WEEK_OF_MONTH, -1);
			toDate.set(Calendar.DAY_OF_WEEK, toDate.getActualMaximum(Calendar.DAY_OF_WEEK));
			toDate.add(Calendar.DAY_OF_WEEK, 1);

			auditTrailDTO.setFromTime(getFromTime(fromDate));
			auditTrailDTO.setToTime(getMinToTime(toDate));

		}
		else if (auditTrailDTO.getDurationTypeSelected().equalsIgnoreCase(FixedDurationFilterEnum.CURRENT_MONTH.getValue()))
		{
			Calendar fromDate = Calendar.getInstance();
			fromDate.set(Calendar.DAY_OF_MONTH, fromDate.getActualMinimum(Calendar.DAY_OF_MONTH));

			Calendar toDate = Calendar.getInstance();

			auditTrailDTO.setFromTime(getFromTime(fromDate));
			auditTrailDTO.setToTime(getMinToTime(toDate));
		}
		else if (auditTrailDTO.getDurationTypeSelected().equalsIgnoreCase(FixedDurationFilterEnum.LAST_MONTH.getValue()))
		{
			Calendar fromDate = Calendar.getInstance();
			fromDate.add(Calendar.MONTH, -1);
			fromDate.set(Calendar.DAY_OF_MONTH, fromDate.getActualMinimum(Calendar.DAY_OF_MONTH));

			Calendar toDate = Calendar.getInstance();
			toDate.add(Calendar.MONTH, -1);
			toDate.set(Calendar.DAY_OF_MONTH, toDate.getActualMaximum(Calendar.DAY_OF_MONTH));

			auditTrailDTO.setFromTime(getFromTime(fromDate));
			auditTrailDTO.setToTime(getMinToTime(toDate));
		}
		else if (auditTrailDTO.getDurationTypeSelected().equalsIgnoreCase(FixedDurationFilterEnum.CURRENT_YEAR.getValue()))
		{
			Calendar fromDate = Calendar.getInstance();
			fromDate.set(Calendar.DAY_OF_YEAR, fromDate.getActualMinimum(Calendar.DAY_OF_YEAR));

			Calendar toDate = Calendar.getInstance();

			auditTrailDTO.setFromTime(getFromTime(fromDate));
			auditTrailDTO.setToTime(getMinToTime(toDate));

		}
		else if (auditTrailDTO.getDurationTypeSelected().equalsIgnoreCase(FixedDurationFilterEnum.LAST_YEAR.getValue()))
		{
			Calendar fromDate = Calendar.getInstance();
			fromDate.add(Calendar.YEAR, -1);
			fromDate.set(Calendar.DAY_OF_YEAR, fromDate.getActualMinimum(Calendar.DAY_OF_YEAR));

			Calendar toDate = Calendar.getInstance();
			toDate.add(Calendar.YEAR, -1);
			toDate.set(Calendar.MONTH, Calendar.DECEMBER);
			toDate.set(Calendar.DAY_OF_MONTH, toDate.getActualMaximum(Calendar.DAY_OF_MONTH));
			auditTrailDTO.setFromTime(getFromTime(fromDate));
			auditTrailDTO.setToTime(getMinToTime(toDate));
		}
		else if (auditTrailDTO.getDurationTypeSelected().equalsIgnoreCase(CustomFilterEnum.DAY.getValue()))
		{
			Calendar fromDate = Calendar.getInstance();
			fromDate.setTime(auditTrailDTO.getFromTime());
			Calendar toDate = Calendar.getInstance();
			toDate.setTime(auditTrailDTO.getFromTime());
			auditTrailDTO.setFromTime(getFromTime(fromDate));
			auditTrailDTO.setToTime(getMinToTime(toDate));

		}
		else if (auditTrailDTO.getDurationTypeSelected().equalsIgnoreCase(CustomFilterEnum.WEEK.getValue()))
		{
			Calendar fromDate = Calendar.getInstance();
			fromDate.setTime(auditTrailDTO.getFromTime());
			Calendar toDate = Calendar.getInstance();
			toDate.setTime(auditTrailDTO.getToTime());
			auditTrailDTO.setFromTime(getFromTime(fromDate));
			auditTrailDTO.setToTime(getMinToTime(toDate));
		}
		else if (auditTrailDTO.getDurationTypeSelected().equalsIgnoreCase(CustomFilterEnum.MONTH.getValue()))
		{
			Calendar fromDate = Calendar.getInstance();
			fromDate.setTime(auditTrailDTO.getFromTime());
			Calendar toDate = Calendar.getInstance();
			toDate.setTime(auditTrailDTO.getToTime());
			auditTrailDTO.setFromTime(getFromTime(fromDate));
			auditTrailDTO.setToTime(getMinToTime(toDate));
		}
		else if (auditTrailDTO.getDurationTypeSelected().equalsIgnoreCase(CustomFilterEnum.YEAR.getValue()))
		{
			Calendar fromDate = Calendar.getInstance();
			fromDate.setTime(auditTrailDTO.getFromTime());
			Calendar toDate = Calendar.getInstance();
			toDate.setTime(auditTrailDTO.getToTime());
			auditTrailDTO.setFromTime(getFromTime(fromDate));
			auditTrailDTO.setToTime(getMinToTime(toDate));
		}
		else if (auditTrailDTO.getDurationTypeSelected().equalsIgnoreCase(CustomFilterEnum.RANGE.getValue()))
		{
			Calendar fromDate = Calendar.getInstance();
			fromDate.setTime(auditTrailDTO.getFromTime());
			Calendar toDate = Calendar.getInstance();
			toDate.setTime(auditTrailDTO.getToTime());
			auditTrailDTO.setFromTime(getFromTime(fromDate));
			auditTrailDTO.setToTime(getMinToTime(toDate));
		}

		List<AuditTrailDTO> finalList = new AuditTrialDAO().getAllAuditTrail(auditTrailDTO);
		return finalList;
	}

	private Date getMinToTime(Calendar day)
	{
		day.set(Calendar.HOUR_OF_DAY, day.getActualMaximum(Calendar.HOUR_OF_DAY));
		day.set(Calendar.MINUTE, day.getActualMaximum(Calendar.MINUTE));
		day.set(Calendar.SECOND, day.getActualMaximum(Calendar.SECOND));
		day.set(Calendar.MILLISECOND, day.getActualMaximum(Calendar.MILLISECOND) - 1);
		return day.getTime();
	}

	private Date getFromTime(Calendar day)
	{
		day.set(Calendar.HOUR_OF_DAY, day.getActualMinimum(Calendar.HOUR_OF_DAY));
		day.set(Calendar.MINUTE, day.getActualMinimum(Calendar.MINUTE));
		day.set(Calendar.SECOND, day.getActualMinimum(Calendar.SECOND));
		day.set(Calendar.MILLISECOND, day.getActualMinimum(Calendar.MILLISECOND));
		return day.getTime();
	}
}
