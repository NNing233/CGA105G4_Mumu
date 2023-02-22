package com.proposalreport.model;

import java.util.*;

public interface PropReportDAO_interface {
	public void insert(PropReportVO propReportVO);

	public void update(PropReportVO propReportVO);

	public void delete(Integer proposalReportID);

	public PropReportVO findByPrimaryKey(Integer proposalReportID);

	public List<PropReportVO> getAll();
}
