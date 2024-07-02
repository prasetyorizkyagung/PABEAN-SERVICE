package com.rtm.pabean.service.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.dao.reference.MstPortDao;
import com.rtm.pabean.dto.reference.DetailCode;
import com.rtm.pabean.model.reference.MstPort;

@Service
@Transactional(readOnly = true)
public class MstPortServiceImp implements MstPortService {

    private MstPortDao mstPortDao;

    @Autowired
    public MstPortServiceImp(MstPortDao mstPortDao) {
        this.mstPortDao = mstPortDao;
    }

    @Override
    @Cacheable(cacheNames = "portAll")
    public List<DetailCode> getAll() {
        List<DetailCode> references = new ArrayList<>();
        mstPortDao.findFirst10ByOrderByCode().forEach(row -> {
            DetailCode reference = new DetailCode();
            reference.setCode(row.getCode());
            reference.setDescription(row.getName());
            references.add(reference);
        });
        return references;
    }

    @SuppressWarnings("null")
    @Override
    @Cacheable(cacheNames = "portByCode", key = "#code")
    public DetailCode getByCode(String code) {
        Optional<MstPort> opt = mstPortDao.findById(code.toUpperCase());
        if (opt.isPresent()) {
            return new DetailCode(code, opt.get().getName(), opt.get().getCountryCode());
        }
        return null;
    }

    @Override
    @Cacheable(cacheNames = "portByCountryCode", key = "#countryCode")
    public List<DetailCode> getByCountryCode(String countryCode) {
        List<DetailCode> references = new ArrayList<>();
        mstPortDao.findByCountryCode(countryCode.toUpperCase()).forEach(row -> {
            DetailCode reference = new DetailCode();
            reference.setCode(row.getCode());
            reference.setDescription(row.getName());
            references.add(reference);
        });
        return references;
    }

    @Override
    @Cacheable(cacheNames = "portByName", key = "#name")
    public List<DetailCode> getByName(String name) {
        List<DetailCode> references = new ArrayList<>();
        mstPortDao.findByNameOrCode(name.toUpperCase()).forEach(row -> {
            DetailCode reference = new DetailCode();
            reference.setCode(row.getCode());
            reference.setDescription(row.getName());
            references.add(reference);
        });
        return references;
    }

}
