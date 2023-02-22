package com.propreport.model;

import java.util.*;

import com.prop.model.PropVO;

public interface PropReportDAO_interface {
	
	public void insertU(PropReportVO propReportVO);
	
	public void update(PropReportVO propReportVO);
	public void updateStatus(PropReportVO propReportVO);

	public void delete(Integer proposalReportID);

	public PropReportVO findByPrimaryKey(Integer proposalReportID);

	public List<PropReportVO> getAll();
}
