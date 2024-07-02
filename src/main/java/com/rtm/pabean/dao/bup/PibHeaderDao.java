package com.rtm.pabean.dao.bup;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.model.bup.PibHeader;

@Repository("bupPibHeaderDao")
@Transactional
public interface PibHeaderDao extends JpaRepository<PibHeader, String> {

    Optional<PibHeader> findByCar(String car);

    Optional<PibHeader> findByOrderId(String orderId);

    @Query("select status from BupPibHeader p where p.car = :car")
    Optional<Integer> findStatusByCar(@Param("car") String car);

    @Modifying
    @Query("update BupPibHeader p set p.status = :status where p.car = :car")
    void updateStatusByCar(@Param("car") String car, @Param("status") int status);

    long countByCar(String car);

    @Modifying
    @Query("update BupPibHeader p set p.idHeader = :idHeader where p.car = :car")
    void updateIdHeaderByCar(String idHeader, String car);

    long deleteByPibId(String pibId);
}
