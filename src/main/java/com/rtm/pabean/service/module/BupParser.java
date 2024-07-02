package com.rtm.pabean.service.module;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.dao.bup.ContainerDao;
import com.rtm.pabean.dao.bup.DocumentDao;
import com.rtm.pabean.dao.bup.EntityDao;
import com.rtm.pabean.dao.bup.ItemDetailDao;
import com.rtm.pabean.dao.bup.ItemDocumentDao;
import com.rtm.pabean.dao.bup.PackageDao;
import com.rtm.pabean.dao.bup.PibHeaderDao;
import com.rtm.pabean.dao.bup.ReferenceDetailDao;
import com.rtm.pabean.dao.bup.SpecialSpecificationsDao;
import com.rtm.pabean.dao.bup.TariffItemDao;
import com.rtm.pabean.dao.bup.TransportDao;
import com.rtm.pabean.dao.bup.VdItemDao;
import com.rtm.pabean.enums.EntityCodeEnum;
import com.rtm.pabean.model.bup.BupEntityPK;
import com.rtm.pabean.model.bup.BupItemDocumentPK;
import com.rtm.pabean.model.bup.BupSpecialSpecificationPK;
import com.rtm.pabean.model.bup.BupTariffItemPK;
import com.rtm.pabean.model.bup.BupTransportPK;
import com.rtm.pabean.model.bup.BupVdItemPK;
import com.rtm.pabean.model.bup.Container;
import com.rtm.pabean.model.bup.Document;
import com.rtm.pabean.model.bup.Entity_;
import com.rtm.pabean.model.bup.ItemDetail;
import com.rtm.pabean.model.bup.ItemDocument;
import com.rtm.pabean.model.bup.Package;
import com.rtm.pabean.model.bup.PibHeader;
import com.rtm.pabean.model.bup.ReferenceDetail;
import com.rtm.pabean.model.bup.SpecialSpecifications;
import com.rtm.pabean.model.bup.TariffItem;
import com.rtm.pabean.model.bup.Transport;
import com.rtm.pabean.model.bup.VdItem;
import com.rtm.pabean.model.bup.module.TblPibAdr;
import com.rtm.pabean.model.bup.module.TblPibCon;
import com.rtm.pabean.model.bup.module.TblPibDtl;
import com.rtm.pabean.model.bup.module.TblPibDtlDok;
import com.rtm.pabean.model.bup.module.TblPibDtlSpekKhusus;
import com.rtm.pabean.model.bup.module.TblPibDtlVD;
import com.rtm.pabean.model.bup.module.TblPibFas;
import com.rtm.pabean.model.bup.module.TblPibHdr;
import com.rtm.pabean.model.bup.module.TblPibKms;
import com.rtm.pabean.model.bup.module.TblPibTrf;
import com.rtm.pabean.utils.ConvertUtil;
import com.rtm.pabean.utils.FormulaUtil;
import com.rtm.pabean.utils.NumberUtil;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Transactional
public class BupParser {
    
    @Autowired
    private ReferenceDetailDao referenceDetailDao;
    
    @Autowired
    private PibHeaderDao pibHeaderDao;
    
    @Autowired
    private ContainerDao containerDao;
    
    @Autowired
    private DocumentDao documentDao;
    
    @Autowired
    private EntityDao entityDao;
    
    @Autowired
    private ItemDetailDao itemDetailDao;
    
    @Autowired
    private ItemDocumentDao itemDocumentDao;
    
    @Autowired
    private PackageDao packageDao;
    
    @Autowired
    private TariffItemDao tariffItemDao;
    
    @Autowired
    private TransportDao transportDao;
    
    @Autowired
    private VdItemDao vdItemDao;
    
    @Autowired
    private SpecialSpecificationsDao specialSpecificationsDao;
    
    @Autowired
    private BupReader bupReader;

    private TblPibHdr tblPibHdr;
    
    private List<TblPibDtlDok> tblPibDtlDoks;

    public void setSourceFile(String filePath) throws Exception {
        bupReader.setSourceFile(filePath);
        tblPibDtlDoks = bupReader.getTblPibDtlDoks();
        tblPibHdr = bupReader.getTblPibHdr();
    }

    public TblPibHdr getTblPibHdr() {
        return tblPibHdr;
    }

    public Optional<PibHeader> getPibHeader(String orderId) throws Exception {
        return pibHeaderDao.findByOrderId(orderId);
    }

    public PibHeader savePibHeader(String orderId) throws Exception {
        PibHeader pibHeader = new PibHeader();
        pibHeader.setAdditionalCost(tblPibHdr.getBTambahan());
        pibHeader.setArrivalDate(tblPibHdr.getTgTiba());
        pibHeader.setBc11Date(tblPibHdr.getDokTupTg());
        pibHeader.setBc11Number(tblPibHdr.getDokTupNo());
        pibHeader.setBc11Pos(tblPibHdr.getPosNo());
        pibHeader.setBc11Subpos(Optional.ofNullable(tblPibHdr.getPosSub()).orElse("0000") + Optional.ofNullable(tblPibHdr.getPosSubSub()).orElse("0000"));
        pibHeader.setBrutto(tblPibHdr.getBruto());
        pibHeader.setCar(tblPibHdr.getCar());
        pibHeader.setCif(tblPibHdr.getNilInv());
        pibHeader.setCurrency(tblPibHdr.getKdVal());
        pibHeader.setCustomsOffice(tblPibHdr.getKdKpbc());
        pibHeader.setDestinationPort(tblPibHdr.getPelBkr());
        pibHeader.setFreight(tblPibHdr.getFreight());
        pibHeader.setImportType(tblPibHdr.getJnImp());
        ReferenceDetail referenceIncoterm = referenceDetailDao.findByRefCodeAndCode("003", tblPibHdr.getKdHrg());
        pibHeader.setIncotermCode(referenceIncoterm == null ? null : referenceIncoterm.getCodeDescription());
        ReferenceDetail referenceInsurance = referenceDetailDao.findByRefCodeAndCode("002", tblPibHdr.getKdAss());
        pibHeader.setInsuranceCode(referenceInsurance == null ? null : referenceInsurance.getCodeDescription());
        pibHeader.setInsuranceValue(tblPibHdr.getAsuransi());
        pibHeader.setVd("1".equals(tblPibHdr.getVd()));
        pibHeader.setLoadingPort(tblPibHdr.getPelMuat());
        pibHeader.setNdpbm(tblPibHdr.getNdpbm());
        pibHeader.setNetto(tblPibHdr.getNetto());
        pibHeader.setPaymentMethod(tblPibHdr.getCrByr());
        pibHeader.setProcedureType(tblPibHdr.getJnPib());
        pibHeader.setReductionCost(tblPibHdr.getDiskon());
        pibHeader.setSignCity("");
        pibHeader.setSignName("");
        pibHeader.setSignPosition("");
        pibHeader.setSignDate(new Date());
        pibHeader.setTpsCode(tblPibHdr.getTmpTbn());
        pibHeader.setTransactionType(tblPibHdr.getJnsTrans());
        pibHeader.setTransitPort(tblPibHdr.getPelTransit());
        pibHeader.setVdValue(tblPibHdr.getNilVd());
        pibHeader.setStatus(0);
        pibHeader.setOrderId(orderId);
        PibHeader return_ = pibHeaderDao.saveAndFlush(pibHeader);
        return return_;
    }

    public long deletePibHeader(String pibId) throws Exception {
        return pibHeaderDao.deleteByPibId(pibId);
    }

    public List<Container> saveContainer(String pibId) throws Exception {
        List<Container> containers = new ArrayList<>();
        for (TblPibCon tblPibCon : bupReader.getTblPibCons()) {
            Container container = new Container();
            container.setPibId(pibId);
            container.setContainerNumber(tblPibCon.getContNo());
            container.setContainerSize(tblPibCon.getContUkur());
            if ("F".equals(tblPibCon.getContTipe())) {
                container.setContainerType("8");
            } else if ("L".equals(tblPibCon.getContTipe())) {
                container.setContainerType("7");
            } else {
                container.setContainerType("4");
            }
            container.setContainerCodeType("1");
            containers.add(container);
        }
        List<Container> return_ = containerDao.saveAllAndFlush(containers);
        return return_;
    }

    public long deleteContainer(String pibId) throws Exception {
        return containerDao.deleteByPibId(pibId);
    }

    public List<Document> saveDocument(String pibId) throws Exception {
        List<Document> documents = new ArrayList<>();
        bupReader.getTblPibDoks().forEach(tblPibDok -> {
            Document document = new Document();
            document.setPibId(pibId);
            document.setDocumentCode(tblPibDok.getDokKd());
            document.setDocumentDate(tblPibDok.getDokTg());
            document.setDocumentNumber(tblPibDok.getDokNo());
            document.setSeri(tblPibDok.getNoUrut());
            Optional<TblPibDtlDok> tblPibDtlDokOpt = tblPibDtlDoks.stream().filter(dtlDok -> dtlDok.getNoUrut().intValue() == tblPibDok.getNoUrut()).findFirst();
            if (tblPibDtlDokOpt.isPresent()) {
                TblPibDtlDok tblPibDtlDok = tblPibDtlDokOpt.get();
                document.setFacilityCode("Y".equals(tblPibDtlDok.getKdFasDtl()) ? null : tblPibDtlDok.getKdFasDtl());
            }
            documents.add(document);
        });
        List<Document> return_ = documentDao.saveAllAndFlush(documents);
        return return_;
    }

    public long deleteDocument(String pibId) throws Exception {
        return documentDao.deleteByPibId(pibId);
    }

    public List<Entity_> saveEntity(String pibId) throws Exception {
        Entity_ importer = new Entity_();
        BupEntityPK impoterPK = new BupEntityPK();
        impoterPK.setEntityCode(EntityCodeEnum.IMPORTIR.getCode());
        impoterPK.setPibId(pibId);
        importer.setEntityPK(impoterPK);
        importer.setEntityAddress(tblPibHdr.getImpAlmt());
        importer.setApiType(String.format("0%s", tblPibHdr.getApiKd()));
        importer.setIdentityType(tblPibHdr.getImpId());
        ReferenceDetail referenceDetail = referenceDetailDao.findByRefCodeAndCode("005", tblPibHdr.getImpStatus());
        importer.setStatus(referenceDetail == null ? "LAINNYA" : referenceDetail.getCodeDescription());
        importer.setEntityName(tblPibHdr.getImpNama());
        importer.setNib(tblPibHdr.getApiNo());
        importer.setIdentityNumber(tblPibHdr.getImpNpwp());
        Entity_ owner = new Entity_();
        BupEntityPK ownerPK = new BupEntityPK();
        ownerPK.setEntityCode(EntityCodeEnum.PEMILIK.getCode());
        ownerPK.setPibId(pibId);
        owner.setEntityPK(ownerPK);
        owner.setEntityAddress(Optional.ofNullable(tblPibHdr.getIndAlmt()).orElse("").trim());
        owner.setIdentityType(tblPibHdr.getIndId());
        owner.setEntityName(Optional.ofNullable(tblPibHdr.getIndNama()).orElse("").trim());
        owner.setIdentityNumber(tblPibHdr.getIndNpwp());
        Entity_ sender = new Entity_();
        BupEntityPK senderPK = new BupEntityPK();
        senderPK.setEntityCode(EntityCodeEnum.PENGIRIM.getCode());
        senderPK.setPibId(pibId);
        sender.setEntityPK(senderPK);
        sender.setEntityAddress(Optional.ofNullable(tblPibHdr.getPasokAlmt()).orElse("").trim());
        sender.setCountryCode(tblPibHdr.getPasokNeg());
        sender.setEntityName(Optional.ofNullable(tblPibHdr.getPasokNama()).orElse("").trim());
        Entity_ seller = new Entity_();
        BupEntityPK sellerPK = new BupEntityPK();
        sellerPK.setEntityCode(EntityCodeEnum.PENJUAL.getCode());
        sellerPK.setPibId(pibId);
        seller.setEntityPK(sellerPK);
        seller.setEntityAddress(Optional.ofNullable(tblPibHdr.getPenjualAlmt()).orElse("").trim());
        seller.setCountryCode(tblPibHdr.getPenjualNeg());
        seller.setEntityName(Optional.ofNullable(tblPibHdr.getPenjualNama()).orElse("").trim());
        Entity_ centralization = new Entity_();
        BupEntityPK centralizationPK = new BupEntityPK();
        centralizationPK.setEntityCode(EntityCodeEnum.PEMUSATAN.getCode());
        centralizationPK.setPibId(pibId);
        if (Optional.ofNullable(tblPibHdr.getBillNpwp()).orElse("").trim().isEmpty()) {
            centralization.setEntityPK(centralizationPK);
            centralization.setEntityAddress(tblPibHdr.getImpAlmt());
            centralization.setIdentityType("5");
            centralization.setEntityName(tblPibHdr.getImpNama());
            centralization.setIdentityNumber(tblPibHdr.getImpNpwp());
        } else {
            centralization.setEntityPK(centralizationPK);
            centralization.setEntityAddress(Optional.ofNullable(tblPibHdr.getBillAlmt()).orElse("").trim());
            centralization.setIdentityType("5");
            centralization.setEntityName(Optional.ofNullable(tblPibHdr.getBillNama()).orElse("").trim());
            centralization.setIdentityNumber(Optional.ofNullable(tblPibHdr.getBillNpwp()).orElse("").trim());
        }
        Entity_ ppjk = new Entity_();
        BupEntityPK ppjkPK = new BupEntityPK();
        ppjkPK.setEntityCode(EntityCodeEnum.PPJK.getCode());
        ppjkPK.setPibId(pibId);
        ppjk.setEntityPK(ppjkPK);
        ppjk.setEntityAddress(Optional.ofNullable(tblPibHdr.getPpjkAlmt()).orElse("").trim());
        ppjk.setIdentityType(tblPibHdr.getPpjkId());
        ppjk.setEntityName(Optional.ofNullable(tblPibHdr.getPpjkNama()).orElse("").trim());
        ppjk.setIdentityNumber(Optional.ofNullable(tblPibHdr.getPpjkNpwp()).orElse("").trim());
        List<Entity_> entityList = new ArrayList<>();
        entityList.add(importer);
        entityList.add(owner);
        entityList.add(sender);
        entityList.add(seller);
        entityList.add(centralization);
        if (!ppjk.getIdentityNumber().isEmpty()) {
            entityList.add(ppjk);
        }
        List<Entity_> return_ = entityDao.saveAllAndFlush(entityList);
        return return_;
    }

    public long deleteEntity(String pibId) throws Exception {
        return entityDao.deleteByEntityPKPibId(pibId);
    }

    public List<Entity_> saveAddresses(String pibId) throws Exception {
        List<String> entityCodesToUpdate = Arrays.asList(EntityCodeEnum.IMPORTIR.getCode(), EntityCodeEnum.PENGIRIM.getCode(), EntityCodeEnum.PENJUAL.getCode(),
                    EntityCodeEnum.PENGUSAHA.getCode(), EntityCodeEnum.PPJK.getCode(), EntityCodeEnum.PEMASOK.getCode(), EntityCodeEnum.PEMBELI.getCode(), 
                    EntityCodeEnum.PEMILIK.getCode(), EntityCodeEnum.PENERIMA.getCode(), EntityCodeEnum.PEMUSATAN.getCode());
        List<Entity_> listEntity = entityDao.findByEntityPKPibIdAndEntityPKEntityCodeIn(pibId, entityCodesToUpdate);
        List<Entity_> entitas = new ArrayList<>();
        List<TblPibAdr> tblPibAdrs = bupReader.getTblPibAdrs();

        if (tblPibAdrs == null || tblPibAdrs.isEmpty()) {
            return new ArrayList<>();
        }

        for (TblPibAdr tblPibAdr : tblPibAdrs) {
            for (Entity_ entity : listEntity) {
                String entityCode = String.valueOf(tblPibAdr.getEntityCode());
                String entityPKCode = entity.getEntityPK().getEntityCode();
                if (entityPKCode.equals(entityCode)) {
                    entity.setEntityName(tblPibAdr.getName());
                    entity.setEntityAddress(tblPibAdr.getAddress());
                }
                entitas.add(entity);
            }
        }
        List<Entity_> return_ = entityDao.saveAllAndFlush(entitas);
        return return_;
    }

    public List<Package> savePackage(String pibId) throws Exception {
        List<Package> packages = new ArrayList<>();
        for (TblPibKms tblPibKms : bupReader.getTblPibKmss()) {
            Package package_ = new Package();
            package_.setPibId(pibId);
            package_.setPackType(tblPibKms.getJnKemas());
            package_.setNumberPack((int) tblPibKms.getJmKemas());
            String packBrand = Optional.ofNullable(tblPibKms.getMerkKemas()).orElse("").trim();
            package_.setPackBrand(packBrand);
            packages.add(package_);
        }
        List<Package> return_ = packageDao.saveAllAndFlush(packages);
        return return_;
    }
    
    public long deletePackage(String pibId) throws Exception {
        return packageDao.deleteByPibId(pibId);
    }

    public Transport saveTransport(String pibId) throws Exception {
        Transport transport = new Transport();
        BupTransportPK transportPK = new BupTransportPK();
        transportPK.setPibId(pibId);
        transportPK.setTransportNumber(tblPibHdr.getAngkutNo());
        transport.setTransportPK(transportPK);
        transport.setFlagCode(tblPibHdr.getAngkutFl());
        transport.setTransportName(tblPibHdr.getAngkutNama());
        transport.setTransportType(tblPibHdr.getModa());
        Transport return_ = transportDao.saveAndFlush(transport);
        return return_;
    }

    public long deleteTransport(String pibId) throws Exception {
        return transportDao.deleteByTransportPKPibId(pibId);
    }

    public List<ItemDetail> saveItemDetail(List<Document> documents, PibHeader pibHeader) throws Exception {
        List<ItemDetail> itemDetails = new ArrayList<>();
        List<ItemDocument> itemDocuments = new ArrayList<>();
        List<VdItem> vdItems = new ArrayList<>();
        List<TariffItem> tariffItems = new ArrayList<>();
        List<SpecialSpecifications> specialSpecifications = new ArrayList<>();
        List<TblPibDtlVD> tblPibDtlVDs = bupReader.getTblPibDtlVds();
        List<TblPibTrf> tblPibTrfs = bupReader.getTblPibTrfs();
        List<TblPibFas> tblpibfass = bupReader.getTblPibFass();
        List<TblPibDtlSpekKhusus> tblPibDtlSpekKhususs = bupReader.getTblPibDtlSpekKhususs();
        for (TblPibDtl tblPibDtl : bupReader.getTblPibDtls()) {
            ItemDetail itemDetail = new ItemDetail();
            itemDetail.setBrand(Optional.ofNullable(tblPibDtl.getMerk()).orElse(""));
            itemDetail.setPibId(pibHeader.getPibId());
            itemDetail.setDescription(tblPibDtl.getBrgUrai());
            itemDetail.setHsCode(tblPibDtl.getNoHs());
            itemDetail.setInitialBalance(tblPibDtl.getSaldoAwalPC());
            itemDetail.setItemCondition(tblPibDtl.getFlBarangBaru());
            itemDetail.setItemUnit(tblPibDtl.getKdSat());
            itemDetail.setLartas("Y".equals(tblPibDtl.getFlLartas()));
            itemDetail.setLatestBalance(tblPibDtl.getSaldoAkhirPC());
            itemDetail.setNetto(tblPibDtl.getNettoDtl());
            itemDetail.setNumberPackage(tblPibDtl.getKemasJm().intValue());
            itemDetail.setNumberUnit(tblPibDtl.getJmlSat());
            itemDetail.setOriginCountry(tblPibDtl.getBrgAsal());
            itemDetail.setPackageType(tblPibDtl.getKemasJn());
            itemDetail.setType(Optional.ofNullable(tblPibDtl.getTipe()).orElse("").trim());
            itemDetail.setOtherSpecification(Optional.ofNullable(tblPibDtl.getSpfLain()).orElse("").trim());
            itemDocuments.addAll(getItemDocument(documents, tblPibDtl.getSerial().intValue(), itemDetail.getItemId(), tblPibDtlDoks));
            VdItem vdItem = getVdItem(tblPibDtlVDs, tblPibDtl.getSerial().intValue(), itemDetail.getItemId());
            BigDecimal vdValue = BigDecimal.ZERO;
            if (vdItem != null) {
                vdItems.add(vdItem);
                vdValue = vdItem.getVdValue();
            }
            itemDetail.setCif(tblPibDtl.getDNilInv().subtract(vdValue));
            double divAmount = itemDetail.getCif().doubleValue() / pibHeader.getCif().doubleValue();
            double otherCost = divAmount * (pibHeader.getAdditionalCost().doubleValue() - pibHeader.getReductionCost().doubleValue());
            double fob = tblPibDtl.getDNilInv().doubleValue() + otherCost;
            itemDetail.setFob(NumberUtil.toBigDecimal(fob).setScale(2, RoundingMode.HALF_UP));
            double freight = divAmount * pibHeader.getFreight().doubleValue();
            itemDetail.setFreight(NumberUtil.toBigDecimal(freight).setScale(2, RoundingMode.HALF_UP));
            double insurance = divAmount * pibHeader.getInsuranceValue().doubleValue();
            itemDetail.setInsuranceValue(NumberUtil.toBigDecimal(insurance).setScale(2, RoundingMode.HALF_UP));
            double unitPrice = fob / tblPibDtl.getJmlSat().doubleValue();
            itemDetail.setUnitPrice(NumberUtil.toBigDecimal(unitPrice).setScale(2, RoundingMode.HALF_UP));
            BigDecimal cifIDR = (itemDetail.getFob().add(itemDetail.getFreight()).add(itemDetail.getInsuranceValue())).multiply(pibHeader.getNdpbm()).setScale(2, RoundingMode.HALF_UP);
            tariffItems.addAll(getTariffItem(tblPibDtl, itemDetail.getItemId(), tblPibTrfs, tblpibfass, cifIDR));
            specialSpecifications.addAll(getSpecialSpec(tblPibDtl, itemDetail.getItemId(), tblPibDtlSpekKhususs));
            itemDetails.add(itemDetail);
        }
        itemDetailDao.saveAllAndFlush(itemDetails);
        vdItemDao.saveAllAndFlush(vdItems);
        tariffItemDao.saveAllAndFlush(tariffItems);
        itemDocumentDao.saveAllAndFlush(itemDocuments);
        specialSpecificationsDao.saveAllAndFlush(specialSpecifications);
        return itemDetails;
    }

    public long deleteItemDetail(String pibId) throws Exception {
        List<ItemDetail> itemDetails = itemDetailDao.findByPibId(pibId);
        for (ItemDetail itemDetail : itemDetails) {
            vdItemDao.deleteByVdItemPKItemId(itemDetail.getItemId());
            tariffItemDao.deleteByTariffItemPKItemId(itemDetail.getItemId());
            itemDocumentDao.deleteByItemDocumentPKItemId(itemDetail.getItemId());
            specialSpecificationsDao.deleteBySpecialSpecificationPKItemId(itemDetail.getItemId());
        }
        return itemDetailDao.deleteByPibId(pibId);
    }

    private List<ItemDocument> getItemDocument(List<Document> documentList, int seri, String itemId,
            List<TblPibDtlDok> tblPibDtlDokList) {
        List<ItemDocument> itemDocumentList = new ArrayList<>();
        List<TblPibDtlDok> tblPibDtlDoks = new ArrayList<>();
        tblPibDtlDoks.addAll(tblPibDtlDokList.stream().filter(dtlDok -> seri == dtlDok.getSerial().intValue()).collect(Collectors.toList()));
        for (TblPibDtlDok tblPibDtlDok : tblPibDtlDoks) {
            ItemDocument itemDocument = new ItemDocument();
            Optional<Document> documentOpt = documentList.stream().filter(doc -> (doc.getSeri() == tblPibDtlDok.getNoUrut().intValue())).findFirst();
            if (documentOpt.isPresent()) {
                BupItemDocumentPK itemDocumentPK = new BupItemDocumentPK();
                itemDocumentPK.setDocumentId(documentOpt.get().getDocumentId());
                itemDocumentPK.setItemId(itemId);
                itemDocument.setItemDocumentPK(itemDocumentPK);
                itemDocument.setItemSkepSeri(NumberUtil.toInt(tblPibDtlDok.getNoSeriBrgSkep()));
                itemDocumentList.add(itemDocument);
            }
        }
        return itemDocumentList;
    }

    private List<SpecialSpecifications> getSpecialSpec(TblPibDtl tblPibDtl, String itemId, List<TblPibDtlSpekKhusus> tblPibDtlSpekKhususList) {
        List<SpecialSpecifications> specList = new ArrayList<>();
        Optional<TblPibDtlSpekKhusus> spekKhususOpt = tblPibDtlSpekKhususList.stream()
                .filter(spek -> tblPibDtl.getSerial().intValue() == spek.getSerial()).findFirst();
        if (spekKhususOpt.isPresent()) {
            TblPibDtlSpekKhusus spekKhusus = spekKhususOpt.get();
            SpecialSpecifications specialSpec1 = new SpecialSpecifications();
            String desc = String.format("%s-%s-%s", spekKhusus.getCas1(), spekKhusus.getCas2().substring(0, 2),
                    spekKhusus.getCas2().substring(2));
            specialSpec1.setDescription(desc);
            BupSpecialSpecificationPK spec1PK = new BupSpecialSpecificationPK();
            spec1PK.setItemId(itemId);
            spec1PK.setSpecificationCode("15");
            specialSpec1.setSpecialSpecificationPK(spec1PK);
            specList.add(specialSpec1);
            SpecialSpecifications specialSpec2 = new SpecialSpecifications();
            specialSpec2.setDescription(tblPibDtl.getKatLartas());
            BupSpecialSpecificationPK spec2PK = new BupSpecialSpecificationPK();
            spec2PK.setItemId(itemId);
            spec2PK.setSpecificationCode("16");
            specialSpec2.setSpecialSpecificationPK(spec2PK);
            specList.add(specialSpec2);
        }
        return specList;
    }

    private List<TariffItem> getTariffItem(TblPibDtl tblPibDtl, String itemId, List<TblPibTrf> tblPibTrfList, List<TblPibFas> tblPibFasList, BigDecimal cifIDR) {
        List<TariffItem> tariffItemList = new ArrayList<>();
        Optional<TblPibTrf> tblPibTrfOpt = tblPibTrfList.stream().filter(trf -> (tblPibDtl.getSeriTrp().intValue() == trf.getSeriTrp() && tblPibDtl.getNoHs().equals(trf.getNoHs()))).findFirst();
        Optional<TblPibFas> tblPibFasOpt = tblPibFasList.stream().filter(fas -> tblPibDtl.getSerial().equals(fas.getSerial())).findFirst();
        if (tblPibFasOpt.isPresent() && tblPibTrfOpt.isPresent()) {
            TblPibFas tblPibFas = tblPibFasOpt.get();
            TblPibTrf tblPibTrf = tblPibTrfOpt.get();
            TariffItem bmTariff = new TariffItem();
            bmTariff.setFacilityTariff(tblPibFas.getFasBM());
            BupTariffItemPK bmTariffPK = new BupTariffItemPK();
            bmTariffPK.setItemId(itemId);
            bmTariffPK.setTaxType("BM");
            bmTariff.setTariffItemPK(bmTariffPK);
            bmTariff.setNumberUnit(tblPibDtl.getSatBmJm());
            bmTariff.setTariffFacilityCode(ConvertUtil.convertTariffFacilityCode(tblPibFas.getKdFasBM()));
            bmTariff.setTariffType(tblPibTrf.getKdTrpBM());
            bmTariff.setTariffValue(NumberUtil.toBigDecimal(tblPibTrf.getTrpBM()));
            double bm = FormulaUtil.countBM(bmTariff.getTariffType(), cifIDR.doubleValue(), bmTariff.getNumberUnit().doubleValue(), tblPibTrf.getTrpBM());
            double tariffFacilityBm = bmTariff.getFacilityTariff().doubleValue() / 100;
            double facilityValueBm = bm * tariffFacilityBm;
            if (bmTariff.getTariffFacilityCode().equals("1")) {
                bmTariff.setFacilityTariff(NumberUtil.toBigDecimal(100));
                bmTariff.setPaymentValue(NumberUtil.toBigDecimal(bm));
                bmTariff.setFacilityValue(BigDecimal.ZERO);
            } else {
                bmTariff.setPaymentValue(NumberUtil.toBigDecimal(bm - facilityValueBm));
                bmTariff.setFacilityValue(NumberUtil.toBigDecimal(facilityValueBm));
            }
            bmTariff.setUnitCode(Optional.ofNullable(tblPibDtl.getKdSat()).orElse(null));
            tariffItemList.add(bmTariff);

            TariffItem bmadTariff = new TariffItem();
            bmadTariff.setFacilityTariff(tblPibFas.getFasBMAD());
            BupTariffItemPK bmadTariffPK = new BupTariffItemPK();
            bmadTariffPK.setItemId(itemId);
            bmadTariffPK.setTaxType("BMAD");
            bmadTariff.setTariffItemPK(bmadTariffPK);
            bmadTariff.setNumberUnit(tblPibDtl.getSatBmJm());
            bmadTariff.setTariffFacilityCode(ConvertUtil.convertTariffFacilityCode(tblPibFas.getKdFasBMAD()));
            bmadTariff.setTariffType(tblPibTrf.getKdTrpBMAD());
            bmadTariff.setTariffValue(NumberUtil.toBigDecimal(tblPibTrf.getTrpBMAD()));
            double bmad = FormulaUtil.countBM(bmadTariff.getTariffType(), cifIDR.doubleValue(), bmadTariff.getNumberUnit().doubleValue(), tblPibTrf.getTrpBMAD());
            double tariffFacilityBmad = bmadTariff.getFacilityTariff().doubleValue() / 100;
            double facilityValueBmad = bmad * tariffFacilityBmad;
            if (bmadTariff.getTariffFacilityCode().equals("1")) {
                bmadTariff.setFacilityTariff(NumberUtil.toBigDecimal(100));
                bmadTariff.setPaymentValue(NumberUtil.toBigDecimal(bmad));
                bmadTariff.setFacilityValue(BigDecimal.ZERO);
            } else {
                bmadTariff.setPaymentValue(NumberUtil.toBigDecimal(bmad - facilityValueBmad));
                bmadTariff.setFacilityValue(NumberUtil.toBigDecimal(facilityValueBmad));
            }
            bmadTariff.setUnitCode(Optional.ofNullable(tblPibDtl.getKdSat()).orElse(null));
            bmadTariff.setTemporary("1".equals(tblPibFas.getBMADS()));
            if (bmadTariff.getPaymentValue().doubleValue() > 0 || bmadTariff.getFacilityValue().doubleValue() > 0) {
                tariffItemList.add(bmadTariff);
            }

            TariffItem bmiTariff = new TariffItem();
            bmiTariff.setFacilityTariff(tblPibFas.getFasBMIM());
            BupTariffItemPK bmiTariffPK = new BupTariffItemPK();
            bmiTariffPK.setItemId(itemId);
            bmiTariffPK.setTaxType("BMI");
            bmiTariff.setTariffItemPK(bmiTariffPK);
            bmiTariff.setNumberUnit(tblPibDtl.getSatBmJm());
            bmiTariff.setTariffFacilityCode(ConvertUtil.convertTariffFacilityCode(tblPibFas.getKdFasBMIM()));
            bmiTariff.setTariffType(tblPibTrf.getKdTrpBMIM());
            bmiTariff.setTariffValue(NumberUtil.toBigDecimal(tblPibTrf.getTrpBMIM()));
            double bmi = FormulaUtil.countBM(bmiTariff.getTariffType(), cifIDR.doubleValue(), bmiTariff.getNumberUnit().doubleValue(), tblPibTrf.getTrpBMIM());
            double tariffFacilityBmi = bmiTariff.getFacilityTariff().doubleValue() / 100;
            double facilityValueBmi = bmad * tariffFacilityBmi;
            if (bmiTariff.getTariffFacilityCode().equals("1")) {
                bmiTariff.setFacilityTariff(NumberUtil.toBigDecimal(100));
                bmiTariff.setPaymentValue(NumberUtil.toBigDecimal(bmi));
                bmiTariff.setFacilityValue(BigDecimal.ZERO);
            } else {
                bmiTariff.setPaymentValue(NumberUtil.toBigDecimal(bmi - facilityValueBmi));
                bmiTariff.setFacilityValue(NumberUtil.toBigDecimal(facilityValueBmi));
            }
            bmiTariff.setUnitCode(Optional.ofNullable(tblPibDtl.getKdSat()).orElse(null));
            bmiTariff.setTemporary("1".equals(tblPibFas.getBMIMS()));
            if (bmiTariff.getPaymentValue().doubleValue() > 0 || bmiTariff.getFacilityValue().doubleValue() > 0) {
                tariffItemList.add(bmiTariff);
            }

            TariffItem bmpTariff = new TariffItem();
            bmpTariff.setFacilityTariff(tblPibFas.getFasBMPB());
            BupTariffItemPK bmpTariffPK = new BupTariffItemPK();
            bmpTariffPK.setItemId(itemId);
            bmpTariffPK.setTaxType("BMP");
            bmpTariff.setTariffItemPK(bmpTariffPK);
            bmpTariff.setNumberUnit(tblPibDtl.getSatBmJm());
            bmpTariff.setTariffFacilityCode(ConvertUtil.convertTariffFacilityCode(tblPibFas.getKdFasBMPB()));
            bmpTariff.setTariffType(tblPibTrf.getKdTrpBMPB());
            bmpTariff.setTariffValue(NumberUtil.toBigDecimal(tblPibTrf.getTrpBMPB()));
            double bmp = FormulaUtil.countBM(bmpTariff.getTariffType(), cifIDR.doubleValue(), bmpTariff.getNumberUnit().doubleValue(), tblPibTrf.getTrpBMPB());
            double tariffFacilityBmp = bmpTariff.getFacilityTariff().doubleValue() / 100;
            double facilityValueBmp = bmp * tariffFacilityBmp;
            if (bmpTariff.getTariffFacilityCode().equals("1")) {
                bmpTariff.setFacilityTariff(NumberUtil.toBigDecimal(100));
                bmpTariff.setPaymentValue(NumberUtil.toBigDecimal(bmp));
                bmpTariff.setFacilityValue(BigDecimal.ZERO);
            } else {
                bmpTariff.setPaymentValue(NumberUtil.toBigDecimal(bmp - facilityValueBmp));
                bmpTariff.setFacilityValue(NumberUtil.toBigDecimal(facilityValueBmp));
            }
            bmpTariff.setUnitCode(Optional.ofNullable(tblPibDtl.getKdSat()).orElse(null));
            if (bmpTariff.getPaymentValue().doubleValue() > 0 || bmpTariff.getFacilityValue().doubleValue() > 0) {
                tariffItemList.add(bmpTariff);
            }

            TariffItem bmtpTariff = new TariffItem();
            bmtpTariff.setFacilityTariff(tblPibFas.getFasBMTP());
            BupTariffItemPK bmtpTariffPK = new BupTariffItemPK();
            bmtpTariffPK.setItemId(itemId);
            bmtpTariffPK.setTaxType("BMTP");
            bmtpTariff.setTariffItemPK(bmtpTariffPK);
            bmtpTariff.setNumberUnit(tblPibDtl.getSatBmJm());
            bmtpTariff.setTariffFacilityCode(ConvertUtil.convertTariffFacilityCode(tblPibFas.getKdFasBMTP()));
            bmtpTariff.setTariffType(tblPibTrf.getKdTrpBMTP());
            bmtpTariff.setTariffValue(NumberUtil.toBigDecimal(tblPibTrf.getTrpBMTP()));
            double bmtp = FormulaUtil.countBM(bmtpTariff.getTariffType(), cifIDR.doubleValue(), bmtpTariff.getNumberUnit().doubleValue(), tblPibTrf.getTrpBMTP());
            double tariffFacilityBmtp = bmtpTariff.getFacilityTariff().doubleValue() / 100;
            double facilityValueBmtp = bmtp * tariffFacilityBmtp;
            if (bmtpTariff.getTariffFacilityCode().equals("1")) {
                bmtpTariff.setFacilityTariff(NumberUtil.toBigDecimal(100));
                bmtpTariff.setPaymentValue(NumberUtil.toBigDecimal(bmtp));
                bmtpTariff.setFacilityValue(BigDecimal.ZERO);
            } else {
                bmtpTariff.setPaymentValue(NumberUtil.toBigDecimal(bmtp - facilityValueBmtp));
                bmtpTariff.setFacilityValue(NumberUtil.toBigDecimal(facilityValueBmtp));
            }
            bmtpTariff.setUnitCode(Optional.ofNullable(tblPibDtl.getKdSat()).orElse(null));
            bmtpTariff.setTemporary("1".equals(tblPibFas.getBMTPS()));
            if (bmtpTariff.getPaymentValue().doubleValue() > 0 || bmtpTariff.getFacilityValue().doubleValue() > 0) {
                tariffItemList.add(bmtpTariff);
            }

            double totalBm = bmTariff.getPaymentValue().doubleValue() + bmadTariff.getPaymentValue().doubleValue()
                    + bmiTariff.getPaymentValue().doubleValue() + bmpTariff.getPaymentValue().doubleValue()
                    + bmtpTariff.getPaymentValue().doubleValue();

            TariffItem ppnTariff = new TariffItem();
            ppnTariff.setTariffType("1");
            ppnTariff.setFacilityTariff(tblPibFas.getFasPPN());
            BupTariffItemPK ppnTariffPK = new BupTariffItemPK();
            ppnTariffPK.setItemId(itemId);
            ppnTariffPK.setTaxType("PPN");
            ppnTariff.setTariffItemPK(ppnTariffPK);
            ppnTariff.setTariffFacilityCode(ConvertUtil.convertTariffFacilityCode(tblPibFas.getKdFasPPN()));
            ppnTariff.setTariffValue(NumberUtil.toBigDecimal(tblPibTrf.getTrpPPN()));
            double ppn = FormulaUtil.countPPN(cifIDR.doubleValue(), totalBm, tblPibTrf.getTrpPPN());
            double tariffFacilityPpn = ppnTariff.getFacilityTariff().doubleValue() / 100;
            double facilityValuePpn = ppn * tariffFacilityPpn;
            if (ppnTariff.getTariffFacilityCode().equals("1")) {
                ppnTariff.setFacilityTariff(NumberUtil.toBigDecimal(100));
                ppnTariff.setPaymentValue(NumberUtil.toBigDecimal(ppn));
                ppnTariff.setFacilityValue(BigDecimal.ZERO);
            } else {
                ppnTariff.setPaymentValue(NumberUtil.toBigDecimal(ppn - facilityValuePpn));
                ppnTariff.setFacilityValue(NumberUtil.toBigDecimal(facilityValuePpn));
            }
            if (ppnTariff.getPaymentValue().intValue() > 0 || ppnTariff.getFacilityValue().intValue() > 0) {
                tariffItemList.add(ppnTariff);
            }

            TariffItem pphTariff = new TariffItem();
            pphTariff.setTariffType("1");
            pphTariff.setFacilityTariff(tblPibFas.getFasPPH());
            BupTariffItemPK pphTariffPK = new BupTariffItemPK();
            pphTariffPK.setItemId(itemId);
            pphTariffPK.setTaxType("PPH");
            pphTariff.setTariffItemPK(pphTariffPK);
            pphTariff.setTariffFacilityCode(ConvertUtil.convertTariffFacilityCode(tblPibFas.getKdFasPPH()));
            pphTariff.setTariffValue(NumberUtil.toBigDecimal(tblPibTrf.getTrpPPH()));
            double pph = FormulaUtil.countPPH(cifIDR.doubleValue(), totalBm, tblPibTrf.getTrpPPH());
            double tariffFacilityPph = pphTariff.getFacilityTariff().doubleValue() / 100;
            double facilityValuePph = pph * tariffFacilityPph;
            if (pphTariff.getTariffFacilityCode().equals("1")) {
                pphTariff.setFacilityTariff(NumberUtil.toBigDecimal(100));
                pphTariff.setPaymentValue(NumberUtil.toBigDecimal(pph));
                pphTariff.setFacilityValue(BigDecimal.ZERO);
            } else {
                pphTariff.setPaymentValue(NumberUtil.toBigDecimal(pph - facilityValuePph));
                pphTariff.setFacilityValue(NumberUtil.toBigDecimal(facilityValuePph));
            }
            if (pphTariff.getPaymentValue().doubleValue() > 0 || pphTariff.getFacilityValue().doubleValue() > 0) {
                tariffItemList.add(pphTariff);
            }

            TariffItem ppnBmTariff = new TariffItem();
            ppnBmTariff.setTariffType("1");
            ppnBmTariff.setFacilityTariff(tblPibFas.getFasPBM());
            BupTariffItemPK ppnBmTariffPK = new BupTariffItemPK();
            ppnBmTariffPK.setItemId(itemId);
            ppnBmTariffPK.setTaxType("PPNBM");
            ppnBmTariff.setTariffItemPK(ppnBmTariffPK);
            ppnBmTariff.setTariffFacilityCode(ConvertUtil.convertTariffFacilityCode(tblPibFas.getKdFasPBM()));
            ppnBmTariff.setTariffValue(NumberUtil.toBigDecimal(tblPibTrf.getTrpPBM()));
            double ppnBm = FormulaUtil.countPPN(cifIDR.doubleValue(), totalBm, tblPibTrf.getTrpPBM());
            double tariffFacilityPpnBm = ppnBmTariff.getFacilityTariff().doubleValue() / 100;
            double facilityValuePpnBm = ppnBm * tariffFacilityPpnBm;
            if (ppnBmTariff.getTariffFacilityCode().equals("1")) {
                ppnBmTariff.setFacilityTariff(NumberUtil.toBigDecimal(100));
                ppnBmTariff.setPaymentValue(NumberUtil.toBigDecimal(ppnBm));
                ppnBmTariff.setFacilityValue(BigDecimal.ZERO);
            } else {
                ppnBmTariff.setPaymentValue(NumberUtil.toBigDecimal(ppnBm - facilityValuePpnBm));
                ppnBmTariff.setFacilityValue(NumberUtil.toBigDecimal(facilityValuePpnBm));
            }
            if (ppnBmTariff.getPaymentValue().intValue() > 0 || ppnBmTariff.getFacilityValue().intValue() > 0) {
                tariffItemList.add(ppnBmTariff);
            }
        }
        return tariffItemList;
    }

    private VdItem getVdItem(List<TblPibDtlVD> tblPibDtlVDList, int seri, String itemId) {
        Optional<TblPibDtlVD> tblPibDtlVDOpt = tblPibDtlVDList.stream().filter(dtlVd -> seri == dtlVd.getSerial().intValue()).findFirst();
        if (tblPibDtlVDOpt.isPresent()) {
            TblPibDtlVD tblPibDtlVD = tblPibDtlVDOpt.get();
            VdItem vdItem = new VdItem();
            vdItem.setDueDate(tblPibDtlVD.getTgJatuhTempo());
            BupVdItemPK vdItemPK = new BupVdItemPK();
            vdItemPK.setItemId(itemId);
            vdItemPK.setVdType(tblPibDtlVD.getJenis());
            vdItem.setVdItemPK(vdItemPK);
            vdItem.setVdValue(tblPibDtlVD.getNilai());
            return vdItem;
        }
        return null;
    }
}
