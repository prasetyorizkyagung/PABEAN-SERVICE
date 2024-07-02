package com.rtm.pabean.service.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.dao.reference.MstDetailDao;
import com.rtm.pabean.dto.reference.DetailCode;
import com.rtm.pabean.model.reference.MstDetail;
import com.rtm.pabean.model.reference.MstDetailPK;

@Service
@Transactional(readOnly = true)
public class MstDetailServiceImp implements MstDetailService {

    private MstDetailDao mstDetailDao;

    @Autowired
    public MstDetailServiceImp(MstDetailDao mstDetailDao){
        this.mstDetailDao = mstDetailDao;
    }

    @Override
    @Cacheable(cacheNames = "detailByHeader", key = "#headerId")
    public List<DetailCode> getByHeaderId(String headerId) {
        List<DetailCode> reference = new ArrayList<>();
        mstDetailDao.findByMstDetailPKHeaderIdOrderBySeri(headerId).forEach(detail -> {
            reference.add(new DetailCode(detail.getMstDetailPK().getCode(), detail.getName(), null));
        });
        return reference;
    }

    @Override
    @Cacheable(cacheNames = "detailByName", key = "#headerId.concat('-').concat(#name)")
    public List<DetailCode> getByName(String headerId, String name) {
        List<DetailCode> reference = new ArrayList<>();
        mstDetailDao.findByMstDetailPKHeaderIdAndNameContaining(headerId, name.toUpperCase()).forEach(detail -> {
            reference.add(new DetailCode(detail.getMstDetailPK().getCode(), detail.getName(), null));
        });
        return reference;
    }

    @Override
    @Cacheable(cacheNames = "detailByCode", key = "#headerId.concat('-').concat(#code)")
    public DetailCode getByHeaderIdCode(String headerId, String code) {
        MstDetailPK mstDetailPK = new MstDetailPK();
        mstDetailPK.setCode(code.toUpperCase());
        mstDetailPK.setHeaderId(headerId);
        Optional<MstDetail> opt = mstDetailDao.findById(mstDetailPK);
        if (opt.isPresent()) {
            return new DetailCode(opt.get().getMstDetailPK().getCode(), opt.get().getName(), null);
        }
        return null;
    }

    @Override
    @Cacheable(cacheNames = "detailByName", key = "#headerId.concat('-').concat(#parameter)")
    public List<DetailCode> getByCodeOrName(String headerId, String parameter) {
        List<DetailCode> reference = new ArrayList<>();
        mstDetailDao.findByNameOrCode(headerId, parameter.toUpperCase()).forEach(detail -> {
            reference.add(new DetailCode(detail.getMstDetailPK().getCode(), detail.getName(), null));
        });
        return reference;
    }

}
