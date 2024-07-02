package com.rtm.pabean.service.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.dao.reference.MstKpbcDao;
import com.rtm.pabean.dto.reference.DetailCode;
import com.rtm.pabean.model.reference.MstKpbc;

@Service
@Transactional(readOnly = true)
public class MstKpbcServiceImp implements MstKpbcService {

    private MstKpbcDao mstKpbcDao;

    @Autowired
    public MstKpbcServiceImp(MstKpbcDao mstKpbcDao){
        this.mstKpbcDao = mstKpbcDao;
    }
    
    @Override
    @Cacheable(cacheNames = "kpbcAll")
    public List<DetailCode> getAll() {
        List<DetailCode> referenceKpbc = new ArrayList<>();
        mstKpbcDao.findFirst10ByOrderByCode().forEach(kpbc -> {
            DetailCode reference = new DetailCode();
            reference.setCode(kpbc.getCode());
            reference.setDescription(kpbc.getName());
            referenceKpbc.add(reference);
        });
        return referenceKpbc;
    }

    @SuppressWarnings("null")
    @Override
    @Cacheable(cacheNames = "kpbcByCode", key = "#code")
    public DetailCode getByCode(String code) {
        Optional<MstKpbc> kpbcOpt = mstKpbcDao.findById(code.toUpperCase());
        if (kpbcOpt.isPresent()) {
            return new DetailCode(code, kpbcOpt.get().getName(), null);
        }
        return null;
    }

    @Override
    @Cacheable(cacheNames = "kpbcByPort", key = "#portCode")
    public DetailCode getByPortCode(String portCode) {
        Optional<MstKpbc> kpbcOpt = mstKpbcDao.findByPortCode(portCode.toUpperCase());
        if (kpbcOpt.isPresent()) {
            return new DetailCode(kpbcOpt.get().getCode(), kpbcOpt.get().getName(), null);
        }
        return null;
    }

    @Override
    @Cacheable(cacheNames = "kpbcByName", key = "#name")
    public List<DetailCode> getByName(String name) {
        List<DetailCode> referenceKpbc = new ArrayList<>();
        mstKpbcDao.findByNameOrCode(name.toUpperCase()).forEach(kpbc -> {
            DetailCode reference = new DetailCode();
            reference.setCode(kpbc.getCode());
            reference.setDescription(kpbc.getName());
            referenceKpbc.add(reference);
        });
        return referenceKpbc;
    }

}