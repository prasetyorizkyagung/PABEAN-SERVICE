package com.rtm.pabean.service.bue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.dao.bue.OutboxDao;
import com.rtm.pabean.enums.InboxStatusEnum;
import com.rtm.pabean.model.bue.Outbox;

@Service("bueOutboxServiceImp")
public class OutboxServiceImp implements OutboxService {

    private OutboxDao outboxDao;

    @Autowired
    public OutboxServiceImp(OutboxDao outboxDao) {
        this.outboxDao = outboxDao;
    }

    @Override
    @Transactional
    public void saveOrUpdate(Outbox outbox) throws Exception {
        if (outbox == null) {
            throw new Exception("Entity Outbox cannot be null");
        }
        outboxDao.save(outbox);
    }

    @Override
    @Transactional
    public void updateStatus(String outboxId, String status, String message) throws Exception {
        if (outboxId == null && status == null) {
            throw new Exception("outboxId and Status cannot be null");
        }
        outboxDao.updateStatus(status, message, outboxId);
    }

    @Override
    @Transactional
    public List<Outbox> getQueue() {
        return outboxDao.findTop5ByStatusOrderByCreatedDate(InboxStatusEnum.QUEUE.getCode());
    }
    
}
