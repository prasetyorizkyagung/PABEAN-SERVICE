package com.rtm.pabean.dao.reference;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.reference.MstDocumentPermit;

@Repository
public interface MstDocumentPermitDao extends JpaRepository<MstDocumentPermit, String> {

    List<MstDocumentPermit> findByNameContaining(String name);

    List<MstDocumentPermit> findByCodeContaining(String code);

    Optional<MstDocumentPermit> findByCode(String code);

    List<MstDocumentPermit> findFirst10ByOrderByCode();
}
