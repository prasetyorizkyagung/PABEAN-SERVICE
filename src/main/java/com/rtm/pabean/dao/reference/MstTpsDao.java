package com.rtm.pabean.dao.reference;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.reference.MstTps;

@Repository
public interface MstTpsDao extends JpaRepository<MstTps, String> {

    List<MstTps> findFirst10ByCustomsOfficeAndIsActive(String customsOffice, boolean isActive);

    List<MstTps> findByCustomsOfficeAndNameContainingIgnoreCaseAndIsActive(String customsOffice, String name,
            boolean isActive);

    Optional<MstTps> findByCustomsOfficeAndCode(String customsOffice, String code);
}
