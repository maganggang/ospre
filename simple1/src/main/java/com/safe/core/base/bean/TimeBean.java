package com.safe.core.base.bean;


import java.util.Date;

import com.safe.core.utils.DateFormatUtil;

public class TimeBean {
	protected Date startTime;
	protected Date endTime;
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime1) {
		Date startTime =DateFormatUtil.parseDate(startTime1);
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime1) {
		Date endTime =DateFormatUtil.parseDate(endTime1);
		if(endTime!=null){
			if(startTime!=null){
				if(startTime.getTime()>=endTime.getTime()){
					this.endTime=null;
				}
			}else{
				startTime=new Date(0);
			}
			this.endTime = endTime;
		}else{
			if(endTime==null&&startTime==null){
				this.endTime = endTime;
			}
			this.endTime = new Date(0);
		}
	}
}
