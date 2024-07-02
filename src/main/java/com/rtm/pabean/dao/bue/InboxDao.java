package com.rtm.pabean.dao.bue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bue.Inbox;

@Repository("bueInboxDao")
public interface InboxDao extends JpaRepository<Inbox, String>{
    
    List<Inbox> findTop5ByStatusOrderByCreatedDate(String status);

    @Modifying(clearAutomatically = true)
    @Query("update BueInbox i set i.status = :status, i.message = :message where i.inboxId = :inboxId")
    int updateStatus(String status, String message, String inboxId);
}
