package com.rtm.pabean.dao.bup;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bup.BupEntityPK;
import com.rtm.pabean.model.bup.Entity_;

@Repository("bupEntityDao")
public interface EntityDao extends JpaRepository<Entity_, BupEntityPK> {

    List<Entity_> findByEntityPKPibId(String pibId);

    List<Entity_> findByEntityPKPibIdAndEntityPKEntityCodeIn(String pibId, Collection<String> entityCode);

    Optional<Entity_> findByEntityPKPibIdAndEntityPKEntityCode(String pibId, String entityCode);

    long deleteByEntityPKPibId(String pibId);

    long deleteByEntityPKPibIdAndEntityPKEntityCode(String pibId, String entityCode);
}
