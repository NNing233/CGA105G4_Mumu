package com.sponrecord.model;

import java.util.*;

public interface SponRecordDAO_interface {
          public void insert(SponRecordVO sponRecordVO);
          public void update(SponRecordVO sponRecordVO);
          public void delete(Integer sponsorRecordId);
          public SponRecordVO findByPrimaryKey(Integer sponsorRecordId);
          public List<SponRecordVO> getAll();
          public List<SponRecordVO> getTheOptionSponrecords(Integer proposalOptionId);
          public SponRecordVO getTheOptionSponrecord(Integer proposalOptionId);
          public void updateSponRecordStatus(Integer proposalId, Integer sponRecordStatus);
          public void updateTheSponRecordStatus(Integer sponsorRecordId, Integer sponRecordStatus);
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
