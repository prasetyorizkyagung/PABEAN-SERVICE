package com.rtm.pabean.dao.bup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bup.ItemDocument;
import com.rtm.pabean.model.bup.BupItemDocumentPK;

@Repository("bupItemDocumentDao")
public interface ItemDocumentDao extends JpaRepository<ItemDocument, BupItemDocumentPK> {

    List<ItemDocument> findByItemDocumentPKItemId(String itemId);

    long deleteByItemDocumentPKItemId(String itemId);

    long deleteByItemDocumentPKDocumentId(String documentId);

    @Query(value = "select c.document_id, c.seri, c.pib_id, c.document_code, c.document_number, c.document_date, c.facility_code, b.item_skep_seri "
            + "from import.item_detail a "
            + "inner join import.item_document b on a.item_id = b.item_id "
            + "inner join import.document c on b.document_id = c.document_id "
            + "where a.pib_id = :pibId and b.item_skep_seri is not null "
            + "order by b.item_skep_seri asc, c.seri asc", nativeQuery = true)
    List<Object[]> findDetailDokByPibId(String pibId);

    @Query(value = "select distinct c.facility_code "
            + "from import.item_detail a "
            + "inner join import.item_document b on a.item_id = b.item_id "
            + "inner join import.document c on b.document_id = c.document_id "
            + "where a.pib_id = :pibId and b.item_skep_seri is not null", nativeQuery = true)
    List<String> listFacilityCode(String pibId);

    @Query(value = "select c.document_id, a.item_id, c.seri, c.pib_id, c.document_code, c.document_number, c.document_date, c.facility_code, b.item_skep_seri "
            + "from import.item_detail a "
            + "inner join import.item_document b on a.item_id = b.item_id "
            + "inner join import.document c on b.document_id = c.document_id "
            + "where a.item_id = :itemId and c.facility_code is not null "
            + "order by b.item_id asc, c.seri asc, b.item_skep_seri asc", nativeQuery = true)
    List<Object[]> findFasilitasByItemId(String itemId);

    @Query(value = "select c.document_id, a.item_id, c.seri, c.pib_id, c.document_code, c.document_number, c.document_date, c.facility_code, b.item_skep_seri "
            + "from import.item_detail a "
            + "inner join import.item_document b on a.item_id = b.item_id "
            + "inner join import.document c on b.document_id = c.document_id "
            + "where a.item_id = :itemId and b.item_skep_seri is not null "
            + "order by b.item_id asc, c.facility_code asc, c.seri asc, b.item_skep_seri ", nativeQuery = true)
    List<Object[]> findLartasByItemId(String itemId);
}
