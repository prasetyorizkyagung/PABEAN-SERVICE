package com.rtm.pabean.service.ceisa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.rtm.pabean.dao.bue.PackageDao;
import com.rtm.pabean.dao.bue.PebHeaderDao;
import com.rtm.pabean.dao.bue.TariffItemDao;
import com.rtm.pabean.dao.bue.TransportDao;
import com.rtm.pabean.dto.ceisa.document.bue.BankDevisa;
import com.rtm.pabean.dto.ceisa.document.bue.Peb;
import com.rtm.pabean.dto.ceisa.document.bue.Barang;
import com.rtm.pabean.dto.ceisa.document.bue.BarangDokumen;
import com.rtm.pabean.dto.ceisa.document.bue.BarangPemilik;
import com.rtm.pabean.dto.ceisa.document.bue.BarangTarif;
import com.rtm.pabean.dto.ceisa.document.bue.Dokumen;
import com.rtm.pabean.dto.ceisa.document.bue.Entitas;
import com.rtm.pabean.dto.ceisa.document.bue.Kemasan;
import com.rtm.pabean.dto.ceisa.document.bue.KesiapanBarang;
import com.rtm.pabean.dto.ceisa.document.bue.Kontainer;
import com.rtm.pabean.dto.ceisa.document.bue.Pengangkut;
import com.rtm.pabean.enums.EntityCodeEnum;
import com.rtm.pabean.enums.ReferenceCodeEnum;
import com.rtm.pabean.model.bue.BankDhe;
import com.rtm.pabean.model.bue.Container;
import com.rtm.pabean.model.bue.Document;
import com.rtm.pabean.model.bue.Entity_;
import com.rtm.pabean.model.bue.ItemDetail;
import com.rtm.pabean.model.bue.ItemDocument;
import com.rtm.pabean.model.bue.ItemEntity;
import com.rtm.pabean.model.bue.ItemReadiness;
import com.rtm.pabean.model.bue.Package;
import com.rtm.pabean.model.bue.PebHeader;
import com.rtm.pabean.model.bue.TariffItem;
import com.rtm.pabean.model.bue.Transport;
import com.rtm.pabean.service.reference.ReferenceService;
import com.rtm.pabean.utils.DateUtil;
import com.rtm.pabean.utils.NumberUtil;

@Service
public class PebGenerator {
    
    @Autowired
    private PebHeaderDao pebHeaderDao;
    
    @Autowired
    private ItemDetailDao itemDetailDao;
    
    @Autowired
    private ContainerDao containerDao;
    
    @Autowired
    private DocumentDao documentDao;
    
    @Autowired
    private EntityDao entityDao;
    
    @Autowired
    private ItemDocumentDao itemDocumentDao;
    
    @Autowired
    private PackageDao packageDao;
    
    @Autowired
    private TariffItemDao tariffItemDao;
    
    @Autowired
    private TransportDao transportDao;
    
    @Autowired
    private BankDHEDao bankDHEDao;
    
    @Autowired
    private ItemEntityDao itemEntityDao;
    
    @Autowired
    private ItemReadinessDao itemReadinessDao;

    @Autowired
    private ReferenceService referenceService;

    @Transactional
    public Peb generateData(String car) {
        Optional<PebHeader> pebHeaderOpt = pebHeaderDao.findByCar(car);
        if (pebHeaderOpt.isPresent()) {
            PebHeader header = pebHeaderOpt.get();
            List<Document> documentList = documentDao.findByPebId(header.getPebId());
            Peb peb = new Peb();
            peb.setAsalData("S");
            peb.setKodeDokumen("30");
            peb.setAsuransi(NumberUtil.setScale(2, header.getInsuranceValue(), RoundingMode.FLOOR));
            peb.setBruto(NumberUtil.setScale(4, header.getBrutto(), RoundingMode.FLOOR));
            peb.setKodeCaraDagang(header.getTradeType());
            peb.setFob(NumberUtil.setScale(2, header.getFob(), RoundingMode.FLOOR));
            peb.setFreight(NumberUtil.setScale(2, header.getFreight(), RoundingMode.FLOOR));
            peb.setKodeAsuransi(header.getInsuranceCode());
            peb.setKodeCaraBayar(header.getPaymentMethod());
            peb.setKodeJenisEkspor(header.getExportType());
            peb.setKodeKantor(header.getCustomsOffice());
            peb.setKodeKantorEkspor(header.getExportOffice());
            peb.setKodeKategoriEkspor(header.getExportCategory());
            peb.setKodePelBongkar(header.getUnloadingPort());
            peb.setKodePelMuat(header.getLoadingPort());
            peb.setKodePelTujuan(header.getDestinationPort());
            peb.setKodeValuta(header.getCurrency());
            peb.setNdpbm(NumberUtil.setScale(4, header.getNdpbm(), RoundingMode.FLOOR));
            peb.setNetto(NumberUtil.setScale(4, header.getNetto(), RoundingMode.FLOOR));
            peb.setNilaiMaklon(NumberUtil.setScale(2, header.getMaklonValue(), RoundingMode.FLOOR));
            peb.setNomorAju(header.getCar());
            peb.setFlagCurah(header.getBulkGoods());
            peb.setFlagMigas(header.getCommodity());
            peb.setTanggalEkspor(DateUtil.toString(header.getExportDate(), DateUtil.YYYY_MM_DD));
            peb.setKodeKantorPeriksa(header.getOfficeCheck());
            peb.setKodePembayar(header.getPaymentDescription());
            peb.setKodeKantorMuat(header.getLoadingOffice());
            peb.setKodeTps(header.getTpsCode());
            peb.setTanggalPeriksa(DateUtil.toString(header.getDateCheck(), DateUtil.YYYY_MM_DD));
            peb.setKodeLokasi(header.getLocationCheckCode());
            peb.setKodePelEkspor(header.getExportLoadingPort());
            peb.setJabatanTtd(StringUtils.isNotEmpty(header.getSignPosition()) ? header.getSignPosition() : " ");
            peb.setKotaTtd(StringUtils.isNotEmpty(header.getSignCity()) ? header.getSignCity() : " ");
            peb.setNamaTtd(StringUtils.isNotEmpty(header.getSignName()) ? header.getSignName() : " ");
            peb.setTanggalTtd(header.getSignDate() != null ? DateUtil.toString(header.getSignDate(), DateUtil.YYYY_MM_DD) : DateUtil.toString(new Date(), DateUtil.YYYY_MM_DD));
            peb.setBankDevisa(generateBankDevisa(header.getPebId()));
            peb.setBarang(generateBarang(header.getPebId(), documentList, peb.getNdpbm()));
            peb.setDokumen(generateDokumen(documentList));
            peb.setEntitas(generateEntitas(header.getPebId()));
            peb.setKemasan(generateKemasan(header.getPebId()));
            peb.setKontainer(generateKontainer(header.getPebId()));
            peb.setJumlahKontainer(peb.getKontainer().size());
            peb.setPengangkut(generatePengangkut(header.getPebId()));
            peb.setKesiapanBarang(generateKesiapanBarang(header.getPebId()));
            peb.setKodeIncoterm(header.getIncotermCode());
            peb.setKodeNegaraTujuan(header.getCountryDestination());
            return peb;
        }
        return null;
    }

    private List<BankDevisa> generateBankDevisa(String pebId) {
        List<BankDevisa> bankDevisas = new ArrayList<>();
        int seri = 1;
        for (BankDhe bankDhe : bankDHEDao.findByBankDhePKPebId(pebId)) {
            BankDevisa devisa = new BankDevisa();
            devisa.setKodeBank(bankDhe.getBankDhePK().getBankCode());
            devisa.setSeriBank(seri++);
            devisa.setNamaBank(referenceService
                    .generalReference(ReferenceCodeEnum.BANK_DHE.getCode(), bankDhe.getBankDhePK().getBankCode()).getDescription());
            bankDevisas.add(devisa);
        }
        return bankDevisas;
    }

    private List<Barang> generateBarang(String pebId, List<Document> documents, BigDecimal ndpbm) {
        List<Barang> barangs = new ArrayList<>();
        List<ItemDetail> itemDetails = itemDetailDao.findByPebIdOrderBySeri(pebId);
        List<Entity_> owners = entityDao.findByPebIdAndEntityCode(pebId, EntityCodeEnum.PEMILIK.getCode());
        int seri = 1;
        for (ItemDetail detail : itemDetails) {
            Barang barang = new Barang();
            barang.setBarangDokumen(generateBarangDokumen(detail.getItemId(), documents));
            barang.setBarangPemilik(generateBarangPemilik(detail.getItemId(), owners));
            barang.setBarangTarif(generateBarangTarif(detail.getItemId()));
            barang.setFob(NumberUtil.setScale(2, detail.getFob(), RoundingMode.FLOOR));
            barang.setHargaPatokan(NumberUtil.setScale(4, detail.getStandardPrice(), RoundingMode.FLOOR));
            barang.setHargaSatuan(NumberUtil.setScale(2, detail.getUnitPrice(), RoundingMode.FLOOR));
            barang.setJumlahKemasan(detail.getNumberPackage());
            barang.setKodeBarang(detail.getItemCode());
            barang.setKodeDaerahAsal(detail.getOriginPlace());
            barang.setKodeNegaraAsal(detail.getOriginCountry());
            barang.setMerk(detail.getBrand());
            barang.setNetto(NumberUtil.setScale(4, detail.getNetto(), RoundingMode.FLOOR));
            barang.setNilaiDanaSawit(NumberUtil.setScale(2, detail.getPalmValue(), RoundingMode.FLOOR));
            barang.setPosTarif(detail.getHsCode());
            barang.setSeriBarang(seri++);
            barang.setTipe(detail.getType());
            barang.setUkuran(detail.getSize());
            barang.setUraian(detail.getDescription());
            barang.setVolume(NumberUtil.setScale(4, detail.getVolume(), RoundingMode.FLOOR));
            barang.setCif(BigDecimal.ZERO);
            barang.setJumlahSatuan(detail.getNumberUnit());
            barang.setKodeSatuanBarang(detail.getUnitCode());
            barang.setKodeJenisKemasan(detail.getPackageType());
            barang.setSpesifikasiLain("");
            barangs.add(barang);
        }
        return barangs;
    }

    private List<BarangDokumen> generateBarangDokumen(String itemId, List<Document> documents) {
        List<BarangDokumen> barangDokumens = new ArrayList<>();
        for (ItemDocument itemDocument : itemDocumentDao.findByItemDocumentPKItemId(itemId)) {
            Optional<Document> document = documents.stream()
                    .filter(doc -> doc.getDocumentId().equals(itemDocument.getItemDocumentPK().getDocumentId()))
                    .findFirst();
            if (document.isPresent()) {
                BarangDokumen barangDokumen = new BarangDokumen();
                barangDokumen.setSeriDokumen(Integer.toString(document.get().getSeri()));
                barangDokumen.setIdDokumen(barangDokumen.getSeriDokumen());
                barangDokumens.add(barangDokumen);
            }
        }
        return barangDokumens;
    }

    private List<BarangPemilik> generateBarangPemilik(String itemId, List<Entity_> entities) {
        List<BarangPemilik> barangPemiliks = new ArrayList<>();
        for (ItemEntity itemEntity : itemEntityDao.findByItemEntityPKItemId(itemId)) {
            Optional<Entity_> entityOpt = entities.stream()
                    .filter(entity -> entity.getEntityId().compareTo(itemEntity.getItemEntityPK().getEntityId()) == 0)
                    .findFirst();
            if (entityOpt.isPresent()) {
                BarangPemilik barangPemilik = new BarangPemilik();
                barangPemilik.setSeriEntitas(NumberUtil.toInt(entityOpt.get().getEntityCode()));
                barangPemiliks.add(barangPemilik);
            }
        }
        return barangPemiliks;
    }

    private List<BarangTarif> generateBarangTarif(String itemId) {
        List<BarangTarif> listBarangTarif = new ArrayList<>();
        for (TariffItem tariffItem : tariffItemDao.findByTariffItemPKItemId(itemId)) {
            BarangTarif barangTarif = new BarangTarif();
            barangTarif.setJumlahSatuan(tariffItem.getNumberUnit());
            barangTarif.setKodeJenisPungutan(tariffItem.getTariffItemPK().getTaxType());
            barangTarif.setKodeJenisTarif(tariffItem.getTariffType());
            barangTarif.setTarif(tariffItem.getTariffValue());
            barangTarif.setKodeSatuanBarang(tariffItem.getUnitCode());
            barangTarif.setNilaiBayar(tariffItem.getPaymentValue());
            barangTarif.setKodeFasilitasTarif(tariffItem.getTariffFacilityCode());
            listBarangTarif.add(barangTarif);
        }
        return listBarangTarif;
    }

    private List<Pengangkut> generatePengangkut(String pebId) {
        List<Pengangkut> listPengangkut = new ArrayList<>();
        int seri = 1;
        for (Transport transport : transportDao.findByTransportPKPebId(pebId)) {
            Pengangkut pengangkut = new Pengangkut();
            pengangkut.setKodeBendera(
                    Optional.ofNullable(transport.getFlagCode()).orElse("").isEmpty() ? "-" : transport.getFlagCode());
            pengangkut.setKodeCaraAngkut(transport.getTransportType());
            pengangkut.setNamaPengangkut(Optional.ofNullable(transport.getTransportName()).orElse("").isEmpty() ? "-"
                    : transport.getTransportName());
            pengangkut.setNomorPengangkut(
                    Optional.ofNullable(transport.getTransportPK().getTransportNumber()).orElse("").isEmpty() ? "-"
                            : transport.getTransportPK().getTransportNumber());
            pengangkut.setSeriPengangkut(seri++);
            listPengangkut.add(pengangkut);
        }
        return listPengangkut;
    }

    private List<Dokumen> generateDokumen(List<Document> documents) {
        List<Document> invoices = documents.stream().filter(doc -> "380".equals(doc.getDocumentCode()))
                .collect(Collectors.toList());
        List<Document> hostBls = documents.stream()
                .filter(doc -> Arrays.asList("217").contains(doc.getDocumentCode()))
                .collect(Collectors.toList());
        List<Document> others = documents.stream()
                .filter(doc -> !Arrays.asList("380", "217").contains(doc.getDocumentCode()))
                .collect(Collectors.toList());
        List<Dokumen> listDokumen = new ArrayList<>();
        if (!invoices.isEmpty()) {
            Dokumen dokumen = new Dokumen();
            Document document = invoices.get(0);
            dokumen.setKodeDokumen(document.getDocumentCode());
            dokumen.setNomorDokumen(document.getDocumentNumber());
            dokumen.setSeriDokumen(document.getSeri());
            dokumen.setTanggalDokumen(DateUtil.toString(document.getDocumentDate(), DateUtil.YYYY_MM_DD));
            dokumen.setIdDokumen(Integer.toString(document.getSeri()));
            dokumen.setKodeFasilitas(document.getFacilityCode());
            listDokumen.add(dokumen);
        }
        if (!hostBls.isEmpty()) {
            Dokumen dokumen = new Dokumen();
            Document document = hostBls.get(0);
            dokumen.setKodeDokumen(document.getDocumentCode());
            dokumen.setNomorDokumen(document.getDocumentNumber());
            dokumen.setSeriDokumen(document.getSeri());
            dokumen.setTanggalDokumen(DateUtil.toString(document.getDocumentDate(), DateUtil.YYYY_MM_DD));
            dokumen.setIdDokumen(Integer.toString(document.getSeri()));
            dokumen.setKodeFasilitas(document.getFacilityCode());
            listDokumen.add(dokumen);
        }
        for (Document document : others) {
            Dokumen dokumen = new Dokumen();
            dokumen.setKodeDokumen(document.getDocumentCode());
            dokumen.setNomorDokumen(document.getDocumentNumber());
            dokumen.setSeriDokumen(document.getSeri());
            dokumen.setTanggalDokumen(DateUtil.toString(document.getDocumentDate(), DateUtil.YYYY_MM_DD));
            dokumen.setIdDokumen(Integer.toString(document.getSeri()));
            dokumen.setKodeFasilitas(document.getFacilityCode());
            listDokumen.add(dokumen);
        }
        if (invoices.size() > 1) {
            for (int i = 1; i < invoices.size(); i++) {
                Dokumen dokumen = new Dokumen();
                Document document = invoices.get(i);
                dokumen.setKodeDokumen(document.getDocumentCode());
                dokumen.setNomorDokumen(document.getDocumentNumber());
                dokumen.setSeriDokumen(document.getSeri());
                dokumen.setTanggalDokumen(DateUtil.toString(document.getDocumentDate(), DateUtil.YYYY_MM_DD));
                dokumen.setIdDokumen(Integer.toString(document.getSeri()));
                dokumen.setKodeFasilitas(document.getFacilityCode());
                listDokumen.add(dokumen);
            }
        }
        return listDokumen;
    }

    private List<Kontainer> generateKontainer(String pebId) {
        List<Kontainer> listKontainer = new ArrayList<>();
        int seri = 1;
        for (Container container : containerDao.findByPebIdOrderByContainerNumber(pebId)) {
            Kontainer kontainer = new Kontainer();
            kontainer.setKodeJenisKontainer(container.getContainerType());
            kontainer.setKodeUkuranKontainer(container.getContainerSize());
            kontainer.setNomorKontainer(container.getContainerNumber());
            kontainer.setSeriKontainer(seri++);
            kontainer.setKodeTipeKontainer(container.getContainerCodeType());
            listKontainer.add(kontainer);
        }
        return listKontainer;
    }

    private List<Kemasan> generateKemasan(String pebId) {
        List<Kemasan> listKemasan = new ArrayList<>();
        int seri = 1;
        for (Package package_ : packageDao.findByPebIdOrderByPackType(pebId)) {
            Kemasan kemasan = new Kemasan();
            kemasan.setJumlahKemasan(package_.getNumberPack());
            kemasan.setKodeJenisKemasan(Optional.ofNullable(package_.getPackType()).orElse(""));
            kemasan.setMerkKemasan(Optional.ofNullable(package_.getPackBrand()).orElse(""));
            kemasan.setSeriKemasan(seri++);
            listKemasan.add(kemasan);
        }
        return listKemasan;
    }

    private List<KesiapanBarang> generateKesiapanBarang(String pebId) {
        List<KesiapanBarang> listKesiapan = new ArrayList<>();
        for (ItemReadiness itemReadiness : itemReadinessDao.findListByPebId(pebId)) {
            KesiapanBarang kesiapanBarang = new KesiapanBarang();
            kesiapanBarang.setKodeJenisBarang(itemReadiness.getItemTypeCode());
            kesiapanBarang.setKodeJenisGudang(itemReadiness.getTpsTypeCode());
            kesiapanBarang.setNamaPic(itemReadiness.getPicName());
            kesiapanBarang.setAlamat(itemReadiness.getAddress());
            kesiapanBarang.setNomorTelpPic(itemReadiness.getPhoneNumber());
            kesiapanBarang.setLokasiSiapPeriksa(itemReadiness.getLocationCheck());
            kesiapanBarang.setKodeCaraStuffing(itemReadiness.getStuffingCode());
            kesiapanBarang.setTanggalPkb(DateUtil.toString(itemReadiness.getPkbDate(), DateUtil.YYYY_MM_DD));
            kesiapanBarang
                    .setWaktuSiapPeriksa(DateUtil.toString(itemReadiness.getDateCheck(), DateUtil.YYYY_MM_DD_T_HHMMSS));
            kesiapanBarang.setKodeJenisPartOf(itemReadiness.getCodeTypePart());
            kesiapanBarang.setJumlahContainer20(itemReadiness.getNumberContainer20());
            kesiapanBarang.setJumlahContainer40(itemReadiness.getNumberContainer40());
            listKesiapan.add(kesiapanBarang);
        }
        return listKesiapan;
    }

    private List<Entitas> generateEntitas(String pebId) {
        List<Entity_> entities = entityDao.findByPebId(pebId);
        Optional<Entity_> exporter = entities.stream().filter(ent -> EntityCodeEnum.EKSPORTIR.getCode().equals(ent.getEntityCode()))
                .findFirst();
        Optional<Entity_> owner = entities.stream().filter(ent -> EntityCodeEnum.PEMILIK.getCode().equals(ent.getEntityCode()))
                .findFirst();
        Optional<Entity_> ppjk = entities.stream().filter(ent -> EntityCodeEnum.PPJK.getCode().equals(ent.getEntityCode()))
                .findFirst();
        Optional<Entity_> recipient = entities.stream().filter(ent -> EntityCodeEnum.PENERIMA.getCode().equals(ent.getEntityCode()))
                .findFirst();
        Optional<Entity_> buyer = entities.stream().filter(ent -> EntityCodeEnum.PEMBELI.getCode().equals(ent.getEntityCode()))
                .findFirst();
        List<Entitas> listEntitas = new ArrayList<>();
        if (exporter.isPresent()) {
            Entitas entitas = new Entitas();
            entitas.setAlamatEntitas(exporter.get().getEntityAddress());
            entitas.setKodeEntitas(exporter.get().getEntityCode());
            entitas.setKodeJenisApi(exporter.get().getApiType());
            entitas.setKodeJenisIdentitas(exporter.get().getIdentityType());
            entitas.setKodeStatus(exporter.get().getStatus());
            entitas.setNamaEntitas(exporter.get().getEntityName());
            entitas.setNibEntitas(exporter.get().getNib());
            entitas.setNomorIdentitas(exporter.get().getIdentityNumber());
            entitas.setSeriEntitas(Integer.parseInt(exporter.get().getEntityCode()));
            entitas.setKodeNegara(exporter.get().getCountryCode());
            listEntitas.add(entitas);
        }
        if (owner.isPresent()) {
            Entitas entitas = new Entitas();
            entitas.setAlamatEntitas(owner.get().getEntityAddress());
            entitas.setKodeEntitas(owner.get().getEntityCode());
            entitas.setKodeJenisApi(owner.get().getApiType());
            entitas.setKodeJenisIdentitas(owner.get().getIdentityType());
            entitas.setKodeStatus(owner.get().getStatus());
            entitas.setNamaEntitas(owner.get().getEntityName());
            entitas.setNibEntitas(owner.get().getNib());
            entitas.setNomorIdentitas(owner.get().getIdentityNumber());
            entitas.setSeriEntitas(Integer.parseInt(owner.get().getEntityCode()));
            entitas.setKodeNegara(owner.get().getCountryCode());
            listEntitas.add(entitas);
        }
        if (recipient.isPresent()) {
            Entitas entitas = new Entitas();
            entitas.setAlamatEntitas(recipient.get().getEntityAddress());
            entitas.setKodeEntitas(recipient.get().getEntityCode());
            entitas.setKodeJenisApi(recipient.get().getApiType());
            entitas.setKodeJenisIdentitas(recipient.get().getIdentityType());
            entitas.setKodeStatus(recipient.get().getStatus());
            entitas.setNamaEntitas(recipient.get().getEntityName());
            entitas.setNibEntitas(recipient.get().getNib());
            entitas.setNomorIdentitas(recipient.get().getIdentityNumber());
            entitas.setSeriEntitas(Integer.parseInt(recipient.get().getEntityCode()));
            entitas.setKodeNegara(recipient.get().getCountryCode());
            listEntitas.add(entitas);
        }
        if (buyer.isPresent()) {
            Entitas entitas = new Entitas();
            entitas.setAlamatEntitas(buyer.get().getEntityAddress());
            entitas.setKodeEntitas(buyer.get().getEntityCode());
            entitas.setKodeJenisApi(buyer.get().getApiType());
            entitas.setKodeJenisIdentitas(buyer.get().getIdentityType());
            entitas.setKodeStatus(buyer.get().getStatus());
            entitas.setNamaEntitas(buyer.get().getEntityName());
            entitas.setNibEntitas(buyer.get().getNib());
            entitas.setNomorIdentitas(buyer.get().getIdentityNumber());
            entitas.setSeriEntitas(Integer.parseInt(buyer.get().getEntityCode()));
            entitas.setKodeNegara(buyer.get().getCountryCode());
            listEntitas.add(entitas);
        }
        if (ppjk.isPresent()) {
            Entitas entitas = new Entitas();
            entitas.setAlamatEntitas(ppjk.get().getEntityAddress());
            entitas.setKodeEntitas(ppjk.get().getEntityCode());
            entitas.setKodeJenisApi(ppjk.get().getApiType());
            entitas.setKodeJenisIdentitas(ppjk.get().getIdentityType());
            entitas.setKodeStatus(ppjk.get().getStatus());
            entitas.setNamaEntitas(ppjk.get().getEntityName());
            entitas.setNibEntitas(ppjk.get().getNib());
            entitas.setNomorIdentitas(ppjk.get().getIdentityNumber());
            entitas.setSeriEntitas(Integer.parseInt(ppjk.get().getEntityCode()));
            entitas.setKodeNegara(ppjk.get().getCountryCode());
            listEntitas.add(entitas);
        }
        return listEntitas;
    }
}
