package com.prop2.model;

import java.sql.Connection;
import java.util.*;

public interface PropDAO_interface {
          public void insert(PropVO propVO);
          public void update(PropVO propVO);
          public void delete(Integer proposalId);
          public PropVO findByPrimaryKey(Integer proposalId);
          public List<PropVO> getAll();
          public PropVO getPopularProp();
          public Integer getTotalProposalNum();
          
          public List<PropVO> getProposalFromType(Integer proposalTypeId);
          public List<PropVO> getCompositeQuery(Integer proposalTypeId,Integer min,Integer max);

          public void updateAccumulativeDonateMoney(int proposalId, int addDonateMoney, Connection con);
          public List<PropVO> getSuccessProp();
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
