package com.rtm.pabean.dao.bue;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.model.bue.PebHeader;

@Repository("buePebHeaderDao")
@Transactional
public interface PebHeaderDao extends JpaRepository<PebHeader, String> {


    Optional<PebHeader> findByCar(String car);

    Optional<PebHeader> findByOrderId(String orderId);

    long deleteByCar(String car);

    @Modifying
    @Query("update BuePebHeader p set p.idHeader = :idHeader where p.car = :car")
    void updateIdHeaderByCar(String idHeader, String car);

    long deleteByPebId(String pebId);
}
