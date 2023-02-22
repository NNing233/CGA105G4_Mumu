package com.propopt2.model;
import com.propprodreview.model.*;

import java.sql.Connection;
import java.util.*;

public interface PropOptDAO_interface {
          public void insert(PropOptVO propOptVO);
          public void update(PropOptVO propOptVO);
          public void delete(Integer ProposalOptionId);
          public PropOptVO findByPrimaryKey(Integer ProposalOptionId);
          public List<PropOptVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
          
          public void insert2 (PropOptVO propOptionVO , java.sql.Connection con);
          public void insert3 (PropProdReviewVO propRrodReview , java.sql.Connection con);
          public List<PropOptVO> getPropOptByProposalId(Integer proposalId);
          
}
