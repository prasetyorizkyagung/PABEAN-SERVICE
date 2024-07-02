package com.rtm.pabean.service.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.dao.reference.MstDocumentPermitDao;
import com.rtm.pabean.dto.reference.ReferenceDocument;
import com.rtm.pabean.model.reference.MstDocumentPermit;

@Service
@Transactional(readOnly = true)
public class MstDocumentPermitServiceImp implements MstDocumentPermitService {

    private MstDocumentPermitDao mstDocumentPermitDao;

    @Autowired
    public MstDocumentPermitServiceImp(MstDocumentPermitDao mstDocumentPermitDao) {
        this.mstDocumentPermitDao = mstDocumentPermitDao;
    }

    @Override
    @Cacheable(cacheNames = "documentByName", key = "#name")
    public List<ReferenceDocument> getByName(String name) {
        List<ReferenceDocument> referenceDocuments = new ArrayList<>();
        mstDocumentPermitDao.findByNameContaining(name.toUpperCase()).forEach(row -> {
            referenceDocuments.add(new ReferenceDocument(row.getCode(), row.getName(), row.isFacility()));
        });
        return referenceDocuments;
    }

    @Override
    @Cacheable(cacheNames = "documentByCode", key = "#code")
    public ReferenceDocument getByCode(String code) {
        Optional<MstDocumentPermit> opt = mstDocumentPermitDao.findByCode(code.toUpperCase());
        if (opt.isPresent()) {
            MstDocumentPermit mstDocumentPermit = opt.get();
            return new ReferenceDocument(mstDocumentPermit.getCode(), mstDocumentPermit.getName(),
                    mstDocumentPermit.isFacility());
        }
        return null;
    }

    @Override
    @Cacheable(cacheNames = "documentFirst10")
    public List<ReferenceDocument> getFirst10() {
        List<ReferenceDocument> referenceDocuments = new ArrayList<>();
        mstDocumentPermitDao.findFirst10ByOrderByCode().forEach(row -> {
            referenceDocuments.add(new ReferenceDocument(row.getCode(), row.getName(), row.isFacility()));
        });
        return referenceDocuments;
    }

    @Override
    @Cacheable(cacheNames = "documentByNameOrCode", key = "#val")
    public List<ReferenceDocument> getByNameOrCode(String val) {
        val = val.toUpperCase();
        List<MstDocumentPermit> result = new ArrayList<>();
        result.addAll(mstDocumentPermitDao.findByNameContaining(val));
        result.addAll(mstDocumentPermitDao.findByCodeContaining(val));
        List<ReferenceDocument> referenceDocuments = new ArrayList<>();
        result.stream().distinct().collect(Collectors.toList()).forEach(row -> {
            referenceDocuments.add(new ReferenceDocument(row.getCode(), row.getName(), row.isFacility()));
        });
        return referenceDocuments;
    }
}
