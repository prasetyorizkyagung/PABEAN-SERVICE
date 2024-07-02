package com.rtm.pabean.dao.bup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bup.ReferenceDetail;

@Repository
public interface ReferenceDetailDao extends JpaRepository<ReferenceDetail, String> {

    ReferenceDetail findByRefCodeAndCodeDescription(String refCode, String codeDesc);

    ReferenceDetail findByRefCodeAndCode(String code, String codeDesc);
}
