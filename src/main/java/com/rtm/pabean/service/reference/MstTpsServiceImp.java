package com.rtm.pabean.service.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rtm.pabean.dao.reference.MstTpsDao;
import com.rtm.pabean.dto.reference.DetailCode;
import com.rtm.pabean.model.reference.MstTps;

@Service
public class MstTpsServiceImp implements MstTpsService {

    private MstTpsDao mstTpsDao;

    @Autowired
    public MstTpsServiceImp(MstTpsDao mstTpsDao) {
        this.mstTpsDao = mstTpsDao;
    }

    @Override
    @Cacheable(cacheNames = "tpsByCustomsOffice", key = "#customsOffice")
    public List<DetailCode> getByCustomsOffice(String customsOffice) {
        List<DetailCode> references = new ArrayList<>();
        mstTpsDao.findFirst10ByCustomsOfficeAndIsActive(customsOffice, true).forEach(row -> {
            DetailCode reference = new DetailCode();
            reference.setCode(row.getCode());
            reference.setDescription(row.getName());
            references.add(reference);
        });
        return references;
    }

    @Override
    @Cacheable(cacheNames = "tpsByCustomsOfficeAndName", key = "#customsOffice.concat('-').concat(#name)")
    public List<DetailCode> getByCustomsOfficeAndName(String customsOffice, String name) {
        List<DetailCode> references = new ArrayList<>();
        List<MstTps> tps = new ArrayList<>();
        customsOffice = Optional.ofNullable(customsOffice).orElse("");
        if (customsOffice.isEmpty() || customsOffice.equals("0")) {
            tps.addAll(mstTpsDao.findFirst10ByCustomsOfficeAndIsActive(customsOffice, true));
        } else {
            tps.addAll(mstTpsDao.findByCustomsOfficeAndNameContainingIgnoreCaseAndIsActive(customsOffice, name, true));
        }
        tps.forEach(row -> {
            DetailCode reference = new DetailCode();
            reference.setCode(row.getCode());
            reference.setDescription(row.getName());
            references.add(reference);
        });
        return references;
    }

    @Override
    @Cacheable(cacheNames = "tpsByCustomsOfficeAndName", key = "#customsOffice.concat('-').concat(#code)")
    public DetailCode getByCustomsOfficeAndCode(String customsOffice, String code) {
        Optional<MstTps> tpsOpt = mstTpsDao.findByCustomsOfficeAndCode(customsOffice, code);
        if (tpsOpt.isPresent()) {
            DetailCode reference = new DetailCode();
            reference.setCode(tpsOpt.get().getCode());
            reference.setDescription(tpsOpt.get().getName());
            return reference;
        }
        return null;
    }

}
