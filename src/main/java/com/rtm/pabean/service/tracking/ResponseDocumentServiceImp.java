package com.rtm.pabean.service.tracking;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rtm.pabean.dao.tracking.MstResponseDocumentDao;
import com.rtm.pabean.model.tracking.MstResponseDocument;

@Service
public class ResponseDocumentServiceImp implements ResponseDocumentService {

    private MstResponseDocumentDao mstResponseDocumentDao;

    @Autowired
    public ResponseDocumentServiceImp(MstResponseDocumentDao mstResponseDocumentDao) {
        this.mstResponseDocumentDao = mstResponseDocumentDao;
    }

    @Override
    @Cacheable(cacheNames = "responseByName", key = "#name")
    public List<MstResponseDocument> getByName(String name) {
        return mstResponseDocumentDao.findByResponseNameContaining(name.toUpperCase());
    }

    @Override
    @Cacheable(cacheNames = "responseFirst10")
    public List<MstResponseDocument> getFirst10() {
        return mstResponseDocumentDao.findFirst10ByOrderByResponseId();
    }

    @SuppressWarnings("null")
    @Override
    @Cacheable(cacheNames = "responseByCode", key = "#code")
    public MstResponseDocument getByCode(String code) {
        Optional<MstResponseDocument> ResponseOpt = mstResponseDocumentDao.findById(code.toUpperCase());
        if (ResponseOpt.isPresent()) {
            return ResponseOpt.get();
        }
        return null;
    }

    @Override
    public List<MstResponseDocument> getAll() {
        return mstResponseDocumentDao.findAll();
    }

}
