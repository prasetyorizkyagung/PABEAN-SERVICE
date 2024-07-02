package com.rtm.pabean.dao.bue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bue.Outbox;

@Repository("bueOutboxDao")
public interface OutboxDao extends JpaRepository<Outbox, String> {
    
    List<Outbox> findTop5ByStatusOrderByCreatedDate(String status);

    @Modifying(clearAutomatically = true)
    @Query("update BueOutbox o set o.status = :status, message = :message where o.outboxId = :outboxId")
    int updateStatus(String status, String message, String outboxId);

    Outbox findTop1ByCarAndStatusOrderByCreatedDateDesc(String car, String status);
}
