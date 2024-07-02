package com.rtm.pabean.dao.bue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bue.Document;


@Repository("bueDocumentDao")
public interface DocumentDao extends JpaRepository<Document, String> {

        List<Document> findByPebId(String pebId);

        long deleteByPebId(String pebId);

        long countByPebId(String pebId);

        @Query(value = "select count(document_id) "
                        + "from export.document "
                        + "where peb_id = :pebId and document_code not in (:documentCode) ", nativeQuery = true)
        long countByPibIdAndDocumentCodeNotInPaging(String pebId, List<String> documentCode);

        @Query(value = "select coalesce(max(seri),0) "
                        + "from export.document "
                        + "where peb_id = :pebId", nativeQuery = true)
        int getMaxSeri(String pebId);

        @Query(value = "select document_code, document_number, document_date "
                        + "from export.document "
                        + "where peb_id = :pebId order by document_id desc limit 1", nativeQuery = true)
        List<Object[]> getLastDocByPebId(String pebId);
}
