package com.propopt.model;
import com.propprodreview.model.*;

import java.sql.Connection;
import java.util.*;

public interface PropOptDAO_interface {
          public void insert(PropOptVO propOptVO);
          public void update(PropOptVO propOptVO);
          public void delete(Integer ProposalOptionId);
          public PropOptVO findByPrimaryKey(Integer ProposalOptionId);
          public List<PropOptVO> getAll();
         
//        新增
          public void updateReview(Integer reviewProductId, Integer proposalOptionId);
          public List<PropOptVO> getAllOption(Integer proposalId); //以提案案號找其所有的option
          
          public List<PropOptVO> getPropOptByProposalId(Integer proposalId);
         
}
