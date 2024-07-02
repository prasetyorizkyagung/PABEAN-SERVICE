package com.rtm.pabean.dao.bue;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bue.Entity_;

@Repository("bueEntityDao")
public interface EntityDao extends JpaRepository<Entity_, String> {

        @Query(value = "select count(peb_id) "
                        + "from export.entity "
                        + "where entity_code = '7' and peb_id = :pebId ", nativeQuery = true)
        long countOwner(String pebId);

        List<Entity_> findByPebId(String pebId);

        List<Entity_> findByPebIdAndEntityCode(String pebId, String entityCode);

        List<Entity_> findByPebIdAndEntityCodeIn(String pebId, Collection<String> entityCode);

        long deleteByPebId(String pebId);
}
