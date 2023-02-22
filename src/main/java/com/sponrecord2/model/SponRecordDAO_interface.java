package com.sponrecord2.model;

import java.util.*;

public interface SponRecordDAO_interface {
          public void insert(SponRecordVO sponRecordVO);
          public void update(SponRecordVO sponRecordVO);
          public void delete(Integer sponsorRecordId);
          public SponRecordVO findByPrimaryKey(Integer sponsorRecordId);
          public List<SponRecordVO> getAll();
          public List<SponRecordVO> getSponsorRecordByMember(int memberId);

          public Integer getSponsorNumByProposalId(Integer proposalId);
          
          public void modifySponsorRecordWithAddress(int sponsorRecordId, String address);
          public void cancelSponsorRecord(int sponsorRecordId, int sponsorTotalAmount, int proposalId);
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
