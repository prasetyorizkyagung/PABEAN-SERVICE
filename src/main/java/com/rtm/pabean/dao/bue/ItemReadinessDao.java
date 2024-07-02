package com.rtm.pabean.dao.bue;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bue.ItemReadiness;

@Repository("bueItemReadinessDao")
public interface ItemReadinessDao extends JpaRepository<ItemReadiness, String> {
    
    Optional<ItemReadiness> findByPebId(String pebId);

    List<ItemReadiness> findListByPebId(String pebId);

    long countByPebId(String pebId);

    long deleteByPebId(String pebId);
}
