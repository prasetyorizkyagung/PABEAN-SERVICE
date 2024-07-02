package com.rtm.pabean.service.bue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.dao.bue.InboxDao;
import com.rtm.pabean.enums.InboxStatusEnum;
import com.rtm.pabean.model.bue.Inbox;

@Service("bueInboxServiceImp")
public class InboxServiceImp implements InboxService {

    private InboxDao inboxDao;

    @Autowired
    public InboxServiceImp(InboxDao inboxDao) {
        this.inboxDao = inboxDao;
    }
  
    @Override
    @Transactional
    public void saveOrUpdate(Inbox inbox) throws Exception {
        if (inbox == null) {
            throw new Exception("Entity Inbox cannot be null");
        }
        inboxDao.save(inbox);
    }

    @Override
    @Transactional
    public void updateStatus(String inboxId, String status, String message) throws Exception {
        if (inboxId == null && status == null) {
            throw new Exception("InboxId and Status cannot be null");
        }
        inboxDao.updateStatus(status, message, inboxId);
    }

    @Override
    public List<Inbox> getQueue() {
        return inboxDao.findTop5ByStatusOrderByCreatedDate(InboxStatusEnum.QUEUE.getCode());
    }
    
}
