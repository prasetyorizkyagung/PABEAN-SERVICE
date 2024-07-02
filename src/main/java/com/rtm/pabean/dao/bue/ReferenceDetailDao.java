package com.rtm.pabean.dao.bue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bue.ReferenceDetail;

@Repository("bueReferenceDetailDao")
public interface ReferenceDetailDao extends JpaRepository<ReferenceDetail, String> {

    ReferenceDetail findByRefCodeAndCodeDescription(String refCode, String codeDesc);

    ReferenceDetail findByRefCodeAndCode(String code, String codeDesc);
}
