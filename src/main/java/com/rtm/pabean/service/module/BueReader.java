package com.rtm.pabean.service.module;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rtm.pabean.dao.bue.module.TblModaTambahanDao;
import com.rtm.pabean.dao.bue.module.TblPebConDao;
import com.rtm.pabean.dao.bue.module.TblPebDheDao;
import com.rtm.pabean.dao.bue.module.TblPebDokDao;
import com.rtm.pabean.dao.bue.module.TblPebDtlDao;
import com.rtm.pabean.dao.bue.module.TblPebHdrDao;
import com.rtm.pabean.dao.bue.module.TblPebKmsDao;
import com.rtm.pabean.dao.bue.module.TblPebPjtDao;
import com.rtm.pabean.dao.bue.module.TblPkbDao;
import com.rtm.pabean.model.bue.module.TblModaTambahan;
import com.rtm.pabean.model.bue.module.TblPebCon;
import com.rtm.pabean.model.bue.module.TblPebDhe;
import com.rtm.pabean.model.bue.module.TblPebDok;
import com.rtm.pabean.model.bue.module.TblPebDtl;
import com.rtm.pabean.model.bue.module.TblPebHdr;
import com.rtm.pabean.model.bue.module.TblPebKms;
import com.rtm.pabean.model.bue.module.TblPebPjt;
import com.rtm.pabean.model.bue.module.TblPkb;

@Component
public class BueReader {
    
    @Autowired
    private TblPebHdrDao tblPebHdrDao;

    @Autowired
    private TblPebDtlDao tblPebDtlDao;
    
    @Autowired
    private TblPebConDao tblPebConDao;
    
    @Autowired
    private TblPebDheDao tblPebDheDao;
    
    @Autowired
    private TblPebDokDao tblPebDokDao;
    
    @Autowired
    private TblPebKmsDao tblPebKmsDao;
    
    @Autowired
    private TblPebPjtDao tblPebPjtDao;
    
    @Autowired
    private TblModaTambahanDao tblModaTambahanDao;
    
    @Autowired
    private TblPkbDao tblPkbDao;

    private String filePath;
    
    public void setSourceFile(String filePath) {
        this.filePath = filePath;
    }

    public TblPebHdr getTblPebHdr() throws IOException {
        tblPebHdrDao.setTargetDatabase(filePath);
        return tblPebHdrDao.getAll();
    }

    public List<TblPebDtl> getTblPebDtls() throws IOException {
        tblPebDtlDao.setTargetDatabase(filePath);
        return tblPebDtlDao.getAll();
    }

    public List<TblPebCon> getTblPebCons() throws IOException {
        tblPebConDao.setTargetDatabase(filePath);
        return tblPebConDao.getAll();
    }

    public List<TblPebDhe> getTblPebDhes() throws IOException {
        tblPebDheDao.setTargetDatabase(filePath);
        return tblPebDheDao.getAll();
    }

    public List<TblPebDok> getTblPebDoks() throws IOException {
        tblPebDokDao.setTargetDatabase(filePath);
        return tblPebDokDao.getAll();
    }

    public List<TblPebKms> getTblPebKmses() throws IOException {
        tblPebKmsDao.setTargetDatabase(filePath);
        return tblPebKmsDao.getAll();
    }

    public List<TblPebPjt> getTblPebPjts() throws IOException {
        tblPebPjtDao.setTargetDatabase(filePath);
        return tblPebPjtDao.getAll();
    }

    public List<TblModaTambahan> getTblModaTambahans() throws IOException {
        tblModaTambahanDao.setTargetDatabase(filePath);
        return tblModaTambahanDao.getAll();
    }

    public List<TblPkb> getTblPkb() throws IOException {
        tblPkbDao.setTargetDatabase(filePath);
        return tblPkbDao.getAll();
    }
}
