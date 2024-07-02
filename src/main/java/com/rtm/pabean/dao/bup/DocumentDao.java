package com.rtm.pabean.dao.bup;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bup.Document;

@Repository("bupDocumentDao")
public interface DocumentDao extends JpaRepository<Document, String> {

        List<Document> findByPibIdOrderBySeri(String pibId);

        Optional<Document> findByPibIdAndSeri(String pibId, int seri);

        Optional<Document> findByDocumentCodeAndDocumentNumberAndDocumentDate(String documentCode, String documentNumber, Date documentDate);

        long deleteByPibId(String pibId);

        long countByPibId(String pibId);

        @Query(value = "select coalesce(max(seri),0) "
                        + "from import.document "
                        + "where pib_id = :pibId", nativeQuery = true)
        int getMaxSeri(String pibId);

        @Query(value = "select distinct(facility_code) "
                        + "from import.document " 
                        + "where pib_id = :pibId and facility_code is not null", nativeQuery = true)
        String getFacilityCode(String pibId);

        List<Document> findByPibIdAndDocumentCode(String pibId, String documentCode);
}
