package com.rtm.pabean.dao.bue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bue.ItemDocument;
import com.rtm.pabean.model.bue.BueItemDocumentPK;

@Repository("bueItemDocumentDao")
public interface ItemDocumentDao extends JpaRepository<ItemDocument, BueItemDocumentPK> {

    List<ItemDocument> findByItemDocumentPKItemId(String itemId);

    long deleteByItemDocumentPKItemId(String itemId);

    long deleteByItemDocumentPKDocumentId(String documentId);

    long deleteByItemDocumentPKDocumentIdAndItemDocumentPKItemId(String documentId, String itemId);

    @Query(value = "select count(id.document_id) "
            + "from export.item_document id "
            + "inner join export.document d on d.document_id = id.document_id "
            + "where id.item_id = :itemId ", nativeQuery = true)
    long countByItemId(String itemId);
}
