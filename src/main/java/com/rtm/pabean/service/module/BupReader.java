package com.rtm.pabean.service.module;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rtm.pabean.dao.bup.module.TblPibAdrDao;
import com.rtm.pabean.dao.bup.module.TblPibConDao;
import com.rtm.pabean.dao.bup.module.TblPibDokDao;
import com.rtm.pabean.dao.bup.module.TblPibDtlDao;
import com.rtm.pabean.dao.bup.module.TblPibDtlDokDao;
import com.rtm.pabean.dao.bup.module.TblPibDtlSpekKhususDao;
import com.rtm.pabean.dao.bup.module.TblPibDtlVDDao;
import com.rtm.pabean.dao.bup.module.TblPibFasDao;
import com.rtm.pabean.dao.bup.module.TblPibHdrDao;
import com.rtm.pabean.dao.bup.module.TblPibKmsDao;
import com.rtm.pabean.dao.bup.module.TblPibTrfDao;
import com.rtm.pabean.model.bup.module.TblPibAdr;
import com.rtm.pabean.model.bup.module.TblPibCon;
import com.rtm.pabean.model.bup.module.TblPibDok;
import com.rtm.pabean.model.bup.module.TblPibDtl;
import com.rtm.pabean.model.bup.module.TblPibDtlDok;
import com.rtm.pabean.model.bup.module.TblPibDtlSpekKhusus;
import com.rtm.pabean.model.bup.module.TblPibDtlVD;
import com.rtm.pabean.model.bup.module.TblPibFas;
import com.rtm.pabean.model.bup.module.TblPibHdr;
import com.rtm.pabean.model.bup.module.TblPibKms;
import com.rtm.pabean.model.bup.module.TblPibTrf;

@Component
public class BupReader {
    
    @Autowired
    private TblPibHdrDao tblPibHdrDao;
    
    @Autowired
    private TblPibConDao tblPibConDao;
    
    @Autowired
    private TblPibDokDao tblPibDokDao;
    
    @Autowired
    private TblPibDtlDao tblPibDtlDao;
    
    @Autowired
    private TblPibDtlDokDao tblPibDtlDokDao;
    
    @Autowired
    private TblPibDtlVDDao tblPibDtlVDDao;
    
    @Autowired
    private TblPibFasDao tblPibFasDao;
    
    @Autowired
    private TblPibKmsDao tblPibKmsDao;
    
    @Autowired
    private TblPibTrfDao tblPibTrfDao;
    
    @Autowired
    private TblPibDtlSpekKhususDao tblPibDtlSpekKhususDao;
    
    @Autowired
    private TblPibAdrDao tblPibAdrDao;

    private String filePath;

    public void setSourceFile(String filePath) {
        this.filePath = "";
        this.filePath = filePath;
    }

    public TblPibHdr getTblPibHdr() throws IOException {
        tblPibHdrDao.setTargetDatabase(filePath);
        return tblPibHdrDao.getAll();
    }

    public List<TblPibCon> getTblPibCons() throws IOException {
        tblPibConDao.setTargetDatabase(filePath);
        return tblPibConDao.getAll();
    }

    public List<TblPibDok> getTblPibDoks() throws IOException {
        tblPibDokDao.setTargetDatabase(filePath);
        return tblPibDokDao.getAll();
    }

    public List<TblPibDtl> getTblPibDtls() throws IOException {
        tblPibDtlDao.setTargetDatabase(filePath);
        return tblPibDtlDao.getAll();
    }

    public List<TblPibDtlDok> getTblPibDtlDoks() throws IOException {
        tblPibDtlDokDao.setTargetDatabase(filePath);
        return tblPibDtlDokDao.getAll();
    }

    public List<TblPibDtlVD> getTblPibDtlVds() throws IOException {
        tblPibDtlVDDao.setTargetDatabase(filePath);
        return tblPibDtlVDDao.getAll();
    }

    public List<TblPibFas> getTblPibFass() throws IOException {
        tblPibFasDao.setTargetDatabase(filePath);
        return tblPibFasDao.getAll();
    }

    public List<TblPibKms> getTblPibKmss() throws IOException {
        tblPibKmsDao.setTargetDatabase(filePath);
        return tblPibKmsDao.getAll();
    }

    public List<TblPibTrf> getTblPibTrfs() throws IOException {
        tblPibTrfDao.setTargetDatabase(filePath);
        return tblPibTrfDao.getAll();
    }

    public List<TblPibDtlSpekKhusus> getTblPibDtlSpekKhususs() throws IOException {
        tblPibDtlSpekKhususDao.setTargetDatabase(filePath);
        return tblPibDtlSpekKhususDao.getAll();
    }

    public List<TblPibAdr> getTblPibAdrs() throws IOException {
        tblPibAdrDao.setTargetDatabase(filePath);
        return tblPibAdrDao.getAll();
    }
}
