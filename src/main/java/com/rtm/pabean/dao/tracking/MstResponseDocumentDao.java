package com.rtm.pabean.dao.tracking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.tracking.MstResponseDocument;

@Repository
public interface MstResponseDocumentDao extends JpaRepository<MstResponseDocument, String> {

    List<MstResponseDocument> findFirst10ByOrderByResponseId();

    List<MstResponseDocument> findByResponseNameContaining(String ResponseName);
}
