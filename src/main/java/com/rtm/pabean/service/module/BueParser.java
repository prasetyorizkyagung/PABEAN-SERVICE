package com.rtm.pabean.service.module;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.dao.bue.BankDHEDao;
import com.rtm.pabean.dao.bue.ContainerDao;
import com.rtm.pabean.dao.bue.DocumentDao;
import com.rtm.pabean.dao.bue.EntityDao;
import com.rtm.pabean.dao.bue.ItemDetailDao;
import com.rtm.pabean.dao.bue.ItemDocumentDao;
import com.rtm.pabean.dao.bue.ItemEntityDao;
import com.rtm.pabean.dao.bue.ItemReadinessDao;
import com.rtm.pabean.dao.bue.MstFNIDao;
import com.rtm.pabean.dao.bue.PackageDao;
import com.rtm.pabean.dao.bue.PebHeaderDao;
import com.rtm.pabean.dao.bue.TariffItemDao;
import com.rtm.pabean.dao.bue.TransportDao;
import com.rtm.pabean.dao.bue.ReferenceDetailDao;
import com.rtm.pabean.enums.EntityCodeEnum;
import com.rtm.pabean.model.bue.BankDhe;
import com.rtm.pabean.model.bue.BankDhePK;
import com.rtm.pabean.model.bue.Container;
import com.rtm.pabean.model.bue.Document;
import com.rtm.pabean.model.bue.Entity_;
import com.rtm.pabean.model.bue.ItemDetail;
import com.rtm.pabean.model.bue.ItemDocument;
import com.rtm.pabean.model.bue.BueItemDocumentPK;
import com.rtm.pabean.model.bue.ItemEntity;
import com.rtm.pabean.model.bue.BueItemEntityPK;
import com.rtm.pabean.model.bue.ItemReadiness;
import com.rtm.pabean.model.bue.MstFNI;
import com.rtm.pabean.model.bue.Package;
import com.rtm.pabean.model.bue.PebHeader;
import com.rtm.pabean.model.bue.ReferenceDetail;
import com.rtm.pabean.model.bue.TariffItem;
import com.rtm.pabean.model.bue.BueTariffItemPK;
import com.rtm.pabean.model.bue.Transport;
import com.rtm.pabean.model.bue.BueTransportPK;
import com.rtm.pabean.model.bue.module.TblModaTambahan;
import com.rtm.pabean.model.bue.module.TblPebCon;
import com.rtm.pabean.model.bue.module.TblPebDhe;
import com.rtm.pabean.model.bue.module.TblPebDok;
import com.rtm.pabean.model.bue.module.TblPebDtl;
import com.rtm.pabean.model.bue.module.TblPebHdr;
import com.rtm.pabean.model.bue.module.TblPebKms;
import com.rtm.pabean.model.bue.module.TblPebPjt;
import com.rtm.pabean.model.bue.module.TblPkb;
import com.rtm.pabean.utils.ConvertUtil;
import com.rtm.pabean.utils.FormulaUtil;
import com.rtm.pabean.utils.NumberUtil;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Transactional
public class BueParser {
    
    @Autowired
    private PebHeaderDao pebHeaderDao;
    
    @Autowired
    private ItemDetailDao itemDetailDao;
    
    @Autowired
    private EntityDao entityDao;
    
    @Autowired
    private ContainerDao containerDao;
    
    @Autowired
    private BankDHEDao bankDHEDao;
    
    @Autowired
    private DocumentDao documentDao;
    
    @Autowired
    private PackageDao packageDao;
    
    @Autowired
    private ItemEntityDao itemEntityDao;
    
    @Autowired
    private TariffItemDao tariffItemDao;
    
    @Autowired
    private TransportDao transportDao;
    
    @Autowired
    private ItemDocumentDao itemDocumentDao;
    
    @Autowired
    private ReferenceDetailDao referenceDetailDao;
    
    @Autowired
    private MstFNIDao mstFNIDao;
    
    @Autowired
    private ItemReadinessDao itemReadinessDao;

    @Autowired
    private BueReader moduleReader;

    private TblPebHdr tblPebHdr;

    public void setSourceFile(String filepath) throws IOException {
        moduleReader.setSourceFile(filepath);
        tblPebHdr = moduleReader.getTblPebHdr();
    }

    public TblPebHdr getTblPebHdr() {
        return tblPebHdr;
    }

    public Optional<PebHeader> getPebHeader(String orderId) throws Exception {
        return pebHeaderDao.findByOrderId(orderId);
    }

    public PebHeader savePebHeader(String orderId) throws Exception {
        PebHeader pebHeader = new PebHeader();
        pebHeader.setBrutto(tblPebHdr.getBruto());
        pebHeader.setBulkGoods(Optional.ofNullable(tblPebHdr.getCurah()).orElse("").isEmpty() ? "2" : tblPebHdr.getCurah());
        pebHeader.setCar(tblPebHdr.getCar());
        pebHeader.setCommodity("1".equals(tblPebHdr.getKmdt()) ? "2" : "1");
        pebHeader.setCountryDestination(tblPebHdr.getNegTuju());
        pebHeader.setCurrency(tblPebHdr.getKdVal());
        pebHeader.setCustomsOffice(tblPebHdr.getKdKtr());
        pebHeader.setExportOffice(tblPebHdr.getKdKtrEks());
        pebHeader.setDateCheck(tblPebHdr.getTgSiap());
        pebHeader.setDestinationPort(tblPebHdr.getPelTujuan());
        pebHeader.setExportCategory(tblPebHdr.getKatEks());
        pebHeader.setExportDate(tblPebHdr.getTgEks());
        pebHeader.setExportType(tblPebHdr.getJnEks());
        pebHeader.setExportLoadingPort(tblPebHdr.getPelMuatEks());
        pebHeader.setFob(tblPebHdr.getFob());
        Optional<MstFNI> mstFNIOpt = mstFNIDao.findByCountryCodeAndTransportType(tblPebHdr.getNegTuju(), NumberUtil.toInt(tblPebHdr.getModa()));
        if (Arrays.asList("CFR", "FOB").contains(tblPebHdr.getDelivery())) {
            if (mstFNIOpt.isPresent()) {
                pebHeader.setFreight(FormulaUtil.countFreight(tblPebHdr.getFob().doubleValue(), mstFNIOpt.get().getFreight()));
                pebHeader.setInsuranceValue(FormulaUtil.countInsurance(tblPebHdr.getFob().doubleValue(), pebHeader.getFreight().doubleValue(), mstFNIOpt.get().getInsurance()));
            } else {
                pebHeader.setFreight(BigDecimal.ZERO);
                pebHeader.setInsuranceValue(BigDecimal.ZERO);
            }
        } else {
            pebHeader.setFreight(tblPebHdr.getFreight());
            pebHeader.setInsuranceValue(tblPebHdr.getAsuransi());
        }
        pebHeader.setIncotermCode(tblPebHdr.getDelivery());
        ReferenceDetail referenceDetail = referenceDetailDao.findByRefCodeAndCode("002", tblPebHdr.getKdAss());
        if (referenceDetail != null) {
            pebHeader.setInsuranceCode(referenceDetail.getCodeDescription());
        }
        pebHeader.setLoadingPort(tblPebHdr.getPelMuat());
        pebHeader.setLocationCheckCode(tblPebHdr.getKdLokBrg());
        pebHeader.setMaklonValue(tblPebHdr.getNilMaklon());
        pebHeader.setNdpbm(tblPebHdr.getNilKurs());
        pebHeader.setNetto(tblPebHdr.getNetto());
        pebHeader.setOfficeCheck(tblPebHdr.getKdKtrPriks());
        pebHeader.setPalmValue(BigDecimal.ZERO);
        pebHeader.setPaymentMethod(tblPebHdr.getJnByr());
        pebHeader.setPaymentDescription(Optional.ofNullable(tblPebHdr.getUrCaraBayar()).orElse("").trim());
        pebHeader.setLoadingOffice(tblPebHdr.getKdKtr());
        pebHeader.setSignCity("");
        pebHeader.setSignPosition("");
        pebHeader.setStatus(0);
        pebHeader.setTradeType(Optional.ofNullable(tblPebHdr.getJnDag()).orElse("").trim().equals("3") ? "15" : tblPebHdr.getJnDag());
        pebHeader.setTpsCode(Optional.ofNullable(tblPebHdr.getGudangPlb()).orElse("").trim());
        pebHeader.setUnloadingPort(tblPebHdr.getPelBongkar());
        pebHeader.setFreight(FormulaUtil.countFreight(0, 0));
        pebHeader.setSignName(tblPebHdr.getTtdPeb());
        pebHeader.setOrderId(orderId);
        pebHeaderDao.save(pebHeader);
        return pebHeader;
    }

    public long deletePebHeader(String pebId) throws Exception {
        return pebHeaderDao.deleteByPebId(pebId);
    }

    public List<Container> saveContainer(String pebId) throws Exception {
        List<Container> containers = new ArrayList<>();
        Map<String, String> containerMap = new HashMap<>();
        containerMap.put("E", "4");
        containerMap.put("F", "8");
        containerMap.put("L", "7");
        for (TblPebCon tblPebCon : moduleReader.getTblPebCons()) {
            Container container = new Container();
            container.setContainerNumber(tblPebCon.getNoCont());
            container.setPebId(pebId);
            container.setContainerCodeType(tblPebCon.getType());
            container.setContainerSize(tblPebCon.getSize());
            container.setContainerType(containerMap.get(Optional.ofNullable(tblPebCon.getStuff()).orElse("-")));
            containers.add(container);
        }
        if (!containers.isEmpty()) {
            containerDao.saveAll(containers);
        }
        return containers;
    }

    public long deleteContainer(String pebId) throws Exception {
        return containerDao.deleteByPebId(pebId);
    }

    public List<Document> saveDocument(String pebId) throws Exception {
        List<Document> documents = new ArrayList<>();
        long seri = documentDao.countByPebId(pebId);
        if (seri == 0) {
            seri = 1;
        }
        for (TblPebDok tblPebDok : moduleReader.getTblPebDoks()) {
            Document document = new Document();
            document.setPebId(pebId);
            document.setDocumentCode(tblPebDok.getKdDok());
            document.setDocumentDate(tblPebDok.getTgDok());
            document.setDocumentNumber(tblPebDok.getNoDok());
            document.setSeri((int) seri++);
            documents.add(document);
        }
        if (!documents.isEmpty()) {
            documentDao.saveAll(documents);
        }
        return documents;
    }

    public long deleteDocument(String pebId) throws Exception {
        return documentDao.deleteByPebId(pebId);
    }

    public List<ItemDetail> saveItemDetail(String pebId) throws Exception {
        List<ItemDetail> itemDetails = new ArrayList<>();
        List<Entity_> entity = saveEntity(pebId);
        List<Entity_> owners = entity.stream().filter(owner -> "7".equals(owner.getEntityCode())).collect(Collectors.toList());
        long seriDoc = documentDao.countByPebId(pebId);
        if (seriDoc == 0) {
            seriDoc = 1;
        }
        for (TblPebDtl tblPebDtl : moduleReader.getTblPebDtls()) {
            ItemDetail itemDetail = new ItemDetail();
            itemDetail.setBrand(tblPebDtl.getdMerk());
            String desc1 = Optional.ofNullable(tblPebDtl.getUrBrg1()).orElse("");
            String desc2 = Optional.ofNullable(tblPebDtl.getUrBrg2()).orElse("");
            String desc3 = Optional.ofNullable(tblPebDtl.getUrBrg3()).orElse("");
            String desc4 = Optional.ofNullable(tblPebDtl.getUrBrg4()).orElse("");
            itemDetail.setDescription(desc1 + desc2 + desc3 + desc4);
            itemDetail.setFob(tblPebDtl.getFobPerBrg());
            itemDetail.setHsCode(tblPebDtl.getHs());
            itemDetail.setItemCode(tblPebDtl.getKdBrg());
            itemDetail.setNdpbm(tblPebDtl.getNilValPe());
            itemDetail.setNetto(tblPebDtl.getNetDet());
            itemDetail.setNumberPackage(tblPebDtl.getJmKoli().toBigInteger());
            itemDetail.setNumberUnit(tblPebDtl.getJmSatuan());
            itemDetail.setOriginCountry(tblPebDtl.getNegAsal());
            itemDetail.setOriginPlace(tblPebDtl.getDrhAsalBrg());
            itemDetail.setSize(tblPebDtl.getSize());
            itemDetail.setStandardPrice(tblPebDtl.gethPatok());
            itemDetail.setType(tblPebDtl.getType());
            itemDetail.setUnitCode(tblPebDtl.getJnSatuan());
            itemDetail.setUnitPrice(tblPebDtl.getFobPerSat());
            itemDetail.setVolume(tblPebDtl.getdVolume());
            itemDetail.setPackageType(tblPebDtl.getJnKoli());
            itemDetail.setSeri(tblPebDtl.getSeriBrg().intValue());
            itemDetail.setPebId(pebId);
            itemDetails.add(itemDetail);
            itemDetailDao.save(itemDetail);
            saveItemDocument(tblPebDtl, pebId, itemDetail.getItemId(), seriDoc++);
            Optional<TblPebPjt> tblPebPjtOpt = moduleReader.getTblPebPjts().stream().filter(pjt -> pjt.getSeriBrg() == tblPebDtl.getSeriBrg().intValue()).findFirst();
            if (tblPebPjtOpt.isPresent()) {
                saveItemEntity(owners, itemDetail.getItemId(), tblPebPjtOpt.get());
            }
            if (tblPebDtl.getKdPe() != null) {
                saveTariffItem(tblPebDtl, itemDetail.getItemId());
            }
        }
        return itemDetails;
    }

    public long deleteItemDetail(String pebId) throws Exception {
        List<ItemDetail> itemDetails = itemDetailDao.findByPebId(pebId);
        for (ItemDetail itemDetail : itemDetails) {
            itemDocumentDao.deleteByItemDocumentPKItemId(itemDetail.getItemId());
            itemEntityDao.deleteByItemEntityPKItemId(itemDetail.getItemId());
            tariffItemDao.deleteByTariffItemPKItemId(itemDetail.getItemId());
        }
        entityDao.deleteByPebId(pebId);
        return itemDetailDao.deleteByPebId(pebId);
    }

    public List<Package> savePackage(String pebId) throws Exception {
        List<Package> packages = new ArrayList<>();
        for (TblPebKms tblPebKms : moduleReader.getTblPebKmses()) {
            Package package_ = new Package();
            package_.setPackType(tblPebKms.getJnKemas());
            package_.setPebId(pebId);
            package_.setNumberPack(Optional.ofNullable(tblPebKms.getJmKemas()).orElse(BigInteger.ZERO).intValue());
            String packBrand = Optional.ofNullable(tblPebKms.getMerkKemas()).orElse("").trim();
            package_.setPackBrand(packBrand);
            packages.add(package_);
        }
        if (!packages.isEmpty()) {
            packageDao.saveAll(packages);
        }
        return packages;
    }

    public long deletePackage(String pebId) throws Exception {
        return packageDao.deleteByPebId(pebId);
    }

    public List<Transport> saveTransport(String pebId) throws Exception {
        List<Transport> transports = new ArrayList<>();
        BueTransportPK transportPK = new BueTransportPK();
        transportPK.setPebId(pebId);
        transportPK.setTransportNumber(Optional.ofNullable(tblPebHdr.getVoy()).orElse("").trim());
        Transport transport = new Transport();
        transport.setTransportPK(transportPK);
        transport.setFlagCode(tblPebHdr.getBendera());
        transport.setTransportName(Optional.ofNullable(tblPebHdr.getCarrier()).orElse("").trim());
        transport.setTransportType(tblPebHdr.getModa());
        if (!transport.isNull()) {
            transports.add(transport);
        }
        for (TblModaTambahan moda : moduleReader.getTblModaTambahans()) {
            BueTransportPK pk = new BueTransportPK();
            pk.setPebId(pebId);
            pk.setTransportNumber(Optional.ofNullable(moda.getVoy()).orElse("").trim());
            Transport trans = new Transport();
            trans.setTransportPK(pk);
            trans.setFlagCode(moda.getBendera());
            trans.setTransportName(Optional.ofNullable(moda.getCarrier()).orElse("").trim());
            trans.setTransportType(moda.getModa());
            transports.add(trans);
        }
        if (!transports.isEmpty()) {
            transportDao.saveAll(transports);
        }
        return transports;
    }

    public long deleteTransport(String pebId) throws Exception {
        return transportDao.deleteByTransportPKPebId(pebId);
    }

    public List<BankDhe> saveBankDhe(String pebId) throws Exception {
        List<BankDhe> bankDhes = new ArrayList<>();
        for (TblPebDhe tblPebDhe : moduleReader.getTblPebDhes()) {
            BankDhe bankDhe = new BankDhe();
            BankDhePK bankDhePK = new BankDhePK();
            bankDhePK.setBankCode(NumberUtil.toInt(tblPebDhe.getKdBank()) + "");
            bankDhePK.setPebId(pebId);
            bankDhe.setBankDhePK(bankDhePK);
            bankDhe.setBankDescription(tblPebDhe.getUrBank());
            bankDhes.add(bankDhe);
        }
        if (!bankDhes.isEmpty()) {
            bankDHEDao.saveAll(bankDhes);
        }
        return bankDhes;
    }

    public long deleteBankDhe(String pebId) throws Exception {
        return bankDHEDao.deleteByBankDhePKPebId(pebId);
    }

    public List<ItemReadiness> saveItemReadiness(String pebId, Date dateTimeCheck) throws Exception {
        List<ItemReadiness> itemReadiness = new ArrayList<>();
        for (TblPkb tblPkb : moduleReader.getTblPkb()) {
            ItemReadiness item = new ItemReadiness();
            item.setPebId(pebId);
            item.setItemTypeCode(
                    Optional.ofNullable(tblPkb.getJnBrgGab()).orElse("").isEmpty() ? "1" : tblPkb.getJnBrgGab());
            item.setTpsTypeCode(tblPkb.getGudang());
            item.setPicName(tblPkb.getPetugas());
            item.setAddress(tblPkb.getAlmtSiap());
            item.setPhoneNumber(tblPkb.getNoPhone());
            item.setNumberContainer20(tblPkb.getJmCont20());
            item.setNumberContainer40(tblPkb.getJmCont40());
            item.setLocationCheck(tblPkb.getAlmtStuff());
            item.setStuffingCode(ConvertUtil.convertStuffingCode(tblPkb.getStuff()));
            item.setCodeTypePart(tblPkb.getJnPartOf());
            item.setPkbDate(tblPkb.getTgStuff());
            item.setDateCheck(dateTimeCheck);
            itemReadiness.add(item);
        }
        if (!itemReadiness.isEmpty()) {
            itemReadinessDao.saveAll(itemReadiness);
        }
        return itemReadiness;
    }

    public long deleteItemReadiness(String pebId) throws Exception {
        return itemReadinessDao.deleteByPebId(pebId);
    }

    private ItemDocument saveItemDocument(TblPebDtl tblPebDtl, String pebId, String itemId, long seriDoc)
            throws Exception {
        if (!Optional.ofNullable(tblPebDtl.getKdIzin()).orElse("").trim().isEmpty()) {
            Document document = new Document();
            document.setDocumentCode(tblPebDtl.getKdIzin());
            document.setDocumentDate(tblPebDtl.getTgIzin());
            document.setDocumentNumber(tblPebDtl.getNoIzin());
            document.setPebId(pebId);
            document.setSeri((int) seriDoc);
            ItemDocument itemDocument = new ItemDocument();
            BueItemDocumentPK itemDocumentPK = new BueItemDocumentPK();
            itemDocumentPK.setDocumentId(document.getDocumentId());
            itemDocumentPK.setItemId(itemId);
            itemDocument.setItemDocumentPK(itemDocumentPK);
            documentDao.save(document);
            return itemDocumentDao.save(itemDocument);
        }
        return null;
    }

    private List<Entity_> saveEntity(String pebId) throws Exception {
        Entity_ exporter = new Entity_();
        exporter.setPebId(pebId);
        exporter.setEntityAddress(tblPebHdr.getAlmtEks());
        exporter.setEntityCode(EntityCodeEnum.EKSPORTIR.getCode());
        exporter.setIdentityType(tblPebHdr.getIdEks());
        exporter.setEntityName(tblPebHdr.getNamaEks());
        exporter.setIdentityNumber(tblPebHdr.getNpwpEks());
        Entity_ owner = new Entity_();
        owner.setPebId(pebId);
        owner.setEntityAddress(tblPebHdr.getAlmtQq());
        owner.setEntityCode(EntityCodeEnum.PEMILIK.getCode());
        owner.setIdentityType(tblPebHdr.getIdQq());
        owner.setEntityName(tblPebHdr.getNamaQq());
        owner.setIdentityNumber(tblPebHdr.getNpwpQq());
        Entity_ ppjk = new Entity_();
        ppjk.setPebId(pebId);
        ppjk.setEntityAddress(tblPebHdr.getAlmtPpjk());
        ppjk.setEntityCode(EntityCodeEnum.PPJK.getCode());
        ppjk.setIdentityType(tblPebHdr.getIdPpjk());
        ppjk.setEntityName(tblPebHdr.getNamaPpjk());
        ppjk.setIdentityNumber(tblPebHdr.getNpwpPpjk());
        Entity_ recipient = new Entity_();
        recipient.setPebId(pebId);
        recipient.setEntityAddress(tblPebHdr.getAlmtBeli());
        recipient.setEntityCode(EntityCodeEnum.PENERIMA.getCode());
        recipient.setCountryCode(tblPebHdr.getNegBeli());
        recipient.setEntityName(tblPebHdr.getNamaBeli());
        Entity_ buyer = new Entity_();
        buyer.setPebId(pebId);
        buyer.setEntityAddress(tblPebHdr.getAlmtBeli2());
        buyer.setEntityCode(EntityCodeEnum.PEMBELI.getCode());
        buyer.setCountryCode(tblPebHdr.getNegBeli2());
        buyer.setEntityName(tblPebHdr.getNamaBeli2());
        List<Entity_> entitys = new ArrayList<>();
        entitys.add(exporter);
        entitys.add(owner);
        entitys.add(recipient);
        entitys.add(buyer);
        if (ppjk.getIdentityNumber() != null) {
            entitys.add(ppjk);
        }
        return entityDao.saveAll(entitys);
    }

    private ItemEntity saveItemEntity(List<Entity_> owners, String itemId, TblPebPjt tblPebPjt) throws Exception {
        Optional<Entity_> ownerOpt = owners.stream().filter(owner -> owner.getIdentityNumber().equals(tblPebPjt.getNpwpEksT())).findFirst();
        if (ownerOpt.isPresent()) {
            ItemEntity itemEntity = new ItemEntity();
            BueItemEntityPK itemEntityPK = new BueItemEntityPK();
            itemEntityPK.setEntityId(ownerOpt.get().getEntityId());
            itemEntityPK.setItemId(itemId);
            itemEntity.setItemEntityPK(itemEntityPK);
            return itemEntityDao.save(itemEntity);
        }
        return null;
    }

    private TariffItem saveTariffItem(TblPebDtl tblPebDtl, String itemId) throws Exception {
        BueTariffItemPK tariffItemPK = new BueTariffItemPK();
        tariffItemPK.setItemId(itemId);
        tariffItemPK.setTaxType("BK");
        TariffItem tariffItem = new TariffItem();
        tariffItem.setTariffItemPK(tariffItemPK);
        tariffItem.setTariffType(tblPebDtl.getKdPe());
        tariffItem.setNumberUnit(tblPebDtl.getJmSatPe());
        tariffItem.setUnitCode(tblPebDtl.getJnSatPe());
        tariffItem.setTariffFacilityCode("1");
        tariffItem.setTariffValue(tblPebDtl.getTarifPe());
        tariffItem.setPaymentValue(tblPebDtl.getPePerBrg());
        return tariffItemDao.save(tariffItem);
    }
}
