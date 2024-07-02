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

import com.rtm.pabean.dao.bup.ContainerDao;
import com.rtm.pabean.dao.bup.DocumentDao;
import com.rtm.pabean.dao.bup.EntityDao;
import com.rtm.pabean.dao.bup.ItemDetailDao;
import com.rtm.pabean.dao.bup.ItemDocumentDao;
import com.rtm.pabean.dao.bup.PackageDao;
import com.rtm.pabean.dao.bup.PibHeaderDao;
import com.rtm.pabean.dao.bup.SpecialSpecificationsDao;
import com.rtm.pabean.dao.bup.TariffItemDao;
import com.rtm.pabean.dao.bup.TransportDao;
import com.rtm.pabean.dao.bup.VdItemDao;
import com.rtm.pabean.dto.ceisa.document.bup.Barang;
import com.rtm.pabean.dto.ceisa.document.bup.BarangDokumen;
import com.rtm.pabean.dto.ceisa.document.bup.BarangSpekKhusus;
import com.rtm.pabean.dto.ceisa.document.bup.BarangTarif;
import com.rtm.pabean.dto.ceisa.document.bup.BarangVd;
import com.rtm.pabean.dto.ceisa.document.bup.Dokumen;
import com.rtm.pabean.dto.ceisa.document.bup.Entitas;
import com.rtm.pabean.dto.ceisa.document.bup.Kemasan;
import com.rtm.pabean.dto.ceisa.document.bup.Kontainer;
import com.rtm.pabean.dto.ceisa.document.bup.Pengangkut;
import com.rtm.pabean.dto.ceisa.document.bup.Pib;
import com.rtm.pabean.enums.EntityCodeEnum;
import com.rtm.pabean.model.bup.BupEntityPK;
import com.rtm.pabean.model.bup.Container;
import com.rtm.pabean.model.bup.Document;
import com.rtm.pabean.model.bup.Entity_;
import com.rtm.pabean.model.bup.ItemDetail;
import com.rtm.pabean.model.bup.ItemDocument;
import com.rtm.pabean.model.bup.Package;
import com.rtm.pabean.model.bup.PibHeader;
import com.rtm.pabean.model.bup.SpecialSpecifications;
import com.rtm.pabean.model.bup.TariffItem;
import com.rtm.pabean.model.bup.Transport;
import com.rtm.pabean.model.bup.VdItem;
import com.rtm.pabean.utils.DateUtil;
import com.rtm.pabean.utils.NumberUtil;

@Service
public class PibGenerator {
    
    @Autowired
    private PibHeaderDao pibHeaderDao;
    
    @Autowired
    private ItemDetailDao itemDetailDao;
    
    @Autowired
    private DocumentDao documentDao;
    
    @Autowired
    private EntityDao entityDao;
    
    @Autowired
    private PackageDao packageDao;
    
    @Autowired
    private ContainerDao containerDao;
    
    @Autowired
    private TransportDao transportDao;
    
    @Autowired
    private VdItemDao vdItemDao;
    
    @Autowired
    private TariffItemDao tariffItemDao;
    
    @Autowired
    private ItemDocumentDao itemDocumentDao;
    
    @Autowired
    private SpecialSpecificationsDao specialSpecificationsDao;

    public Pib generateData(String car) {
        Optional<PibHeader> pibHeaderOpt = pibHeaderDao.findByCar(car);
        if (pibHeaderOpt.isPresent()) {
            PibHeader header = pibHeaderOpt.get();
            List<Document> documents = documentDao.findByPibIdOrderBySeri(header.getPibId());
            Pib pib = new Pib();
            pib.setAsalData("S");
            pib.setAsuransi(header.getInsuranceValue().setScale(2, RoundingMode.FLOOR));
            pib.setBiayaPengurang(header.getReductionCost().setScale(2, RoundingMode.FLOOR));
            pib.setBiayaTambahan(header.getAdditionalCost().setScale(2, RoundingMode.FLOOR));
            pib.setBruto(header.getBrutto());
            pib.setCif(header.getCif().setScale(2, RoundingMode.FLOOR));
            pib.setFlagVd(header.isVd() ? "Y" : "T");
            pib.setFreight(header.getFreight().setScale(2, RoundingMode.FLOOR));
            pib.setJabatanTtd(StringUtils.isNotEmpty(header.getSignPosition()) ? header.getSignPosition() : " ");
            pib.setKodeAsuransi(header.getInsuranceCode());
            pib.setKodeCaraBayar(header.getPaymentMethod());
            pib.setKodeDokumen("20");
            pib.setKodeIncoterm(header.getIncotermCode());
            pib.setKodeJenisImpor(header.getImportType());
            pib.setKodeJenisNilai(header.getTransactionType());
            pib.setKodeJenisProsedur(header.getProcedureType());
            pib.setKodeKantor(header.getCustomsOffice());
            pib.setKodePelMuat(header.getLoadingPort());
            pib.setKodePelTujuan(header.getDestinationPort());
            pib.setKodePelTransit(header.getTransitPort());
            pib.setKodeTps(header.getTpsCode());
            pib.setKodeValuta(header.getCurrency());
            pib.setKotaTtd(StringUtils.isNotEmpty(header.getSignCity()) ? header.getSignCity() : " ");
            pib.setNamaTtd(StringUtils.isNotEmpty(header.getSignName()) ? header.getSignName() : " ");
            pib.setNdpbm(header.getNdpbm());
            pib.setNetto(header.getNetto().setScale(4, RoundingMode.FLOOR));
            pib.setNomorAju(header.getCar());
            pib.setNomorBc11(header.getBc11Number());
            pib.setPosBc11(header.getBc11Pos());
            pib.setSubposBc11(header.getBc11Subpos());
            pib.setTanggalBc11(DateUtil.toString(header.getBc11Date(), DateUtil.YYYY_MM_DD));
            pib.setTanggalTiba(DateUtil.toString(header.getArrivalDate(), DateUtil.YYYY_MM_DD));
            pib.setVd(header.getVdValue());
            pib.setKodeTutupPu("11");
            pib.setJumlahKontainer(pib.getKontainer().size());
            pib.setTanggalTtd(header.getSignDate() != null ? DateUtil.toString(header.getSignDate(), DateUtil.YYYY_MM_DD) : DateUtil.toString(new Date(), DateUtil.YYYY_MM_DD));
            pib.getBarang().addAll(generateBarang(header.getPibId(), documents, header.getNdpbm()));
            pib.getEntitas().addAll(generateEntitas(header.getPibId()));
            pib.getKemasan().addAll(generateKemasan(header.getPibId()));
            pib.getKontainer().addAll(generateKontainer(header.getPibId()));
            pib.getDokumen().addAll(generateDokumen(documents));
            pib.getPengangkut().addAll(generatePengangkut(header.getPibId()));
            return pib;
        }
        return null;
    }

    private List<Entitas> generateEntitas(String pibId) {
        List<Entitas> listEntitas = new ArrayList<>();
        BupEntityPK id = new BupEntityPK();
        id.setPibId(pibId);
        id.setEntityCode(EntityCodeEnum.IMPORTIR.getCode());
        Optional<Entity_> importerOpt = entityDao.findById(id);
        if (importerOpt.isPresent()) {
            Entity_ importer = importerOpt.get();
            Entitas entitas = new Entitas();
            entitas.setAlamatEntitas(importer.getEntityAddress());
            entitas.setKodeEntitas(importer.getEntityPK().getEntityCode());
            entitas.setKodeJenisApi(importer.getApiType());
            entitas.setKodeJenisIdentitas(importer.getIdentityType());
            entitas.setKodeStatus(importer.getStatus());
            entitas.setNamaEntitas(importer.getEntityName());
            entitas.setNibEntitas(importer.getNib());
            entitas.setNomorIdentitas(importer.getIdentityNumber());
            entitas.setSeriEntitas(Integer.parseInt(importer.getEntityPK().getEntityCode()));
            entitas.setKodeNegara(importer.getCountryCode());
            listEntitas.add(entitas);
        }
        id.setEntityCode(EntityCodeEnum.PEMILIK.getCode());
        Optional<Entity_> ownerOpt = entityDao.findById(id);
        if (ownerOpt.isPresent()) {
            Entity_ owner = ownerOpt.get();
            Entitas entitas = new Entitas();
            entitas.setAlamatEntitas(owner.getEntityAddress());
            entitas.setKodeEntitas(owner.getEntityPK().getEntityCode());
            entitas.setKodeJenisApi(owner.getApiType());
            entitas.setKodeJenisIdentitas(owner.getIdentityType());
            entitas.setKodeStatus(owner.getStatus());
            entitas.setNamaEntitas(owner.getEntityName());
            entitas.setNibEntitas(owner.getNib());
            entitas.setNomorIdentitas(owner.getIdentityNumber());
            entitas.setSeriEntitas(Integer.parseInt(owner.getEntityPK().getEntityCode()));
            entitas.setKodeNegara(owner.getCountryCode());
            listEntitas.add(entitas);
        }
        id.setEntityCode(EntityCodeEnum.PENGIRIM.getCode());
        Optional<Entity_> senderOpt = entityDao.findById(id);
        if (senderOpt.isPresent()) {
            Entity_ sender = senderOpt.get();
            Entitas entitas = new Entitas();
            entitas.setAlamatEntitas(sender.getEntityAddress());
            entitas.setKodeEntitas(sender.getEntityPK().getEntityCode());
            entitas.setKodeJenisApi(sender.getApiType());
            entitas.setKodeJenisIdentitas(sender.getIdentityType());
            entitas.setKodeStatus(sender.getStatus());
            entitas.setNamaEntitas(sender.getEntityName());
            entitas.setNibEntitas(sender.getNib());
            entitas.setNomorIdentitas(sender.getIdentityNumber());
            entitas.setSeriEntitas(Integer.parseInt(sender.getEntityPK().getEntityCode()));
            entitas.setKodeNegara(sender.getCountryCode());
            listEntitas.add(entitas);
        }
        id.setEntityCode(EntityCodeEnum.PENJUAL.getCode());
        Optional<Entity_> sellerOpt = entityDao.findById(id);
        if (sellerOpt.isPresent()) {
            Entity_ seller = sellerOpt.get();
            Entitas entitas = new Entitas();
            entitas.setAlamatEntitas(seller.getEntityAddress());
            entitas.setKodeEntitas(seller.getEntityPK().getEntityCode());
            entitas.setKodeJenisApi(seller.getApiType());
            entitas.setKodeJenisIdentitas(seller.getIdentityType());
            entitas.setKodeStatus(seller.getStatus());
            entitas.setNamaEntitas(seller.getEntityName());
            entitas.setNibEntitas(seller.getNib());
            entitas.setNomorIdentitas(seller.getIdentityNumber());
            entitas.setSeriEntitas(Integer.parseInt(seller.getEntityPK().getEntityCode()));
            entitas.setKodeNegara(seller.getCountryCode());
            listEntitas.add(entitas);
        }
        id.setEntityCode(EntityCodeEnum.PEMUSATAN.getCode());
        Optional<Entity_> centralizationOpt = entityDao.findById(id);
        if (centralizationOpt.isPresent()) {
            Entity_ centralization = centralizationOpt.get();
            Entitas entitas = new Entitas();
            entitas.setAlamatEntitas(centralization.getEntityAddress());
            entitas.setKodeEntitas(centralization.getEntityPK().getEntityCode());
            entitas.setKodeJenisApi(centralization.getApiType());
            entitas.setKodeJenisIdentitas(centralization.getIdentityType());
            entitas.setKodeStatus(centralization.getStatus());
            entitas.setNamaEntitas(centralization.getEntityName());
            entitas.setNibEntitas(centralization.getNib());
            entitas.setNomorIdentitas(centralization.getIdentityNumber());
            entitas.setSeriEntitas(Integer.parseInt(centralization.getEntityPK().getEntityCode()));
            entitas.setKodeNegara(centralization.getCountryCode());
            listEntitas.add(entitas);
        }
        id.setEntityCode(EntityCodeEnum.PPJK.getCode());
        Optional<Entity_> ppjkOpt = entityDao.findById(id);
        if (ppjkOpt.isPresent()) {
            Entity_ ppjk = ppjkOpt.get();
            Entitas entitas = new Entitas();
            entitas.setAlamatEntitas(ppjk.getEntityAddress());
            entitas.setKodeEntitas(ppjk.getEntityPK().getEntityCode());
            entitas.setKodeJenisApi(ppjk.getApiType());
            entitas.setKodeJenisIdentitas(ppjk.getIdentityType());
            entitas.setKodeStatus(ppjk.getStatus());
            entitas.setNamaEntitas(ppjk.getEntityName());
            entitas.setNibEntitas(ppjk.getNib());
            entitas.setNomorIdentitas(ppjk.getIdentityNumber());
            entitas.setSeriEntitas(Integer.parseInt(ppjk.getEntityPK().getEntityCode()));
            entitas.setKodeNegara(ppjk.getCountryCode());
            listEntitas.add(entitas);
        }

        return listEntitas;
    }

    private List<Kemasan> generateKemasan(String pibId) {
        List<Kemasan> listKemasan = new ArrayList<>();
        int seri = 1;
        for (Package package_ : packageDao.findByPibIdOrderByPackType(pibId)) {
            Kemasan kemasan = new Kemasan();
            kemasan.setJumlahKemasan(package_.getNumberPack());
            kemasan.setKodeJenisKemasan(Optional.ofNullable(package_.getPackType()).orElse(""));
            kemasan.setMerkKemasan(Optional.ofNullable(package_.getPackBrand()).orElse(""));
            kemasan.setSeriKemasan(seri++);
            listKemasan.add(kemasan);
        }
        return listKemasan;
    }

    private List<Kontainer> generateKontainer(String pibId) {
        List<Kontainer> listKontainer = new ArrayList<>();
        int seri = 1;
        for (Container container : containerDao.findByPibIdOrderByContainerNumber(pibId)) {
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

    private List<Dokumen> generateDokumen(List<Document> documents) {
        List<Dokumen> listDokumen = new ArrayList<>();
        List<Document> invoices = documents.stream().filter(doc -> "380".equals(doc.getDocumentCode()))
                .collect(Collectors.toList());
        List<Document> hostBls = documents.stream()
                .filter(doc -> Arrays.asList("705", "740").contains(doc.getDocumentCode()))
                .collect(Collectors.toList());
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
        List<Document> others = documents.stream()
                .filter(doc -> !Arrays.asList("380", "705", "740").contains(doc.getDocumentCode()))
                .collect(Collectors.toList());
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
        if (hostBls.size() > 1) {
            for (int i = 1; i < hostBls.size(); i++) {
                Dokumen dokumen = new Dokumen();
                Document document = hostBls.get(i);
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

    private List<Pengangkut> generatePengangkut(String pibId) {
        List<Pengangkut> listPengangkut = new ArrayList<>();
        int seri = 1;
        for (Transport transport : transportDao.findByTransportPKPibId(pibId)) {
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

    private List<Barang> generateBarang(String pibId, List<Document> documents, BigDecimal ndpbm) {
        List<Barang> listBarang = new ArrayList<>();
        List<ItemDetail> detail = itemDetailDao.findByPibIdOrderByItemId(pibId);
        int seri = 1;
        for (ItemDetail itemDetail : detail) {
            Barang barang = new Barang();
            barang.setAsuransi(itemDetail.getInsuranceValue());
            barang.getBarangDokumen().addAll(generateBarangDokumen(itemDetail.getItemId(), documents));
            barang.getBarangTarif().addAll(generateBarangTarif(itemDetail.getItemId()));
            barang.getBarangVd().addAll(generateBarangVd(itemDetail.getItemId()));
            barang.getBarangSpekKhusus().addAll(generateSpekKhusus(itemDetail.getItemId()));
            barang.setCif(itemDetail.getCif());
            barang.setFob(itemDetail.getFob());
            barang.setFreight(itemDetail.getFreight());
            barang.setHargaSatuan(itemDetail.getUnitPrice());
            barang.setJumlahKemasan(itemDetail.getNumberPackage());
            barang.setJumlahSatuan(itemDetail.getNumberUnit());
            barang.setKodeJenisKemasan(itemDetail.getPackageType());
            barang.setKodeKondisiBarang(itemDetail.getItemCondition());
            barang.setKodeNegaraAsal(itemDetail.getOriginCountry());
            barang.setKodeSatuanBarang(itemDetail.getItemUnit());
            barang.setMerk(itemDetail.getBrand());
            barang.setNetto(itemDetail.getNetto().setScale(4, RoundingMode.FLOOR));
            barang.setPernyataanLartas(itemDetail.isLartas() ? "Y" : "T");
            barang.setPosTarif(itemDetail.getHsCode());
            barang.setSaldoAkhir(itemDetail.getLatestBalance());
            barang.setSaldoAwal(itemDetail.getInitialBalance());
            barang.setSeriBarang(seri++);
            barang.setTipe(itemDetail.getType());
            barang.setUraian(itemDetail.getDescription());
            barang.setUkuran(itemDetail.getSize());
            barang.setSpesifikasiLain(itemDetail.getOtherSpecification());
            barang.setKodeBarang(itemDetail.getItemCode());
            BigDecimal cifIDR = (itemDetail.getFob().add(itemDetail.getFreight()).add(itemDetail.getInsuranceValue())).multiply(ndpbm);
            barang.setCifRupiah(cifIDR);
            listBarang.add(barang);
        }
        return listBarang;
    }

    private List<BarangDokumen> generateBarangDokumen(String itemId, List<Document> documents) {
        List<BarangDokumen> listBarangDokumen = new ArrayList<>();
        for (ItemDocument itemDocument : itemDocumentDao.findByItemDocumentPKItemId(itemId)) {
            Optional<Document> document = documents.stream().filter(doc -> doc.getDocumentId().compareTo(itemDocument.getItemDocumentPK().getDocumentId()) == 0).findFirst();
            if (document.isPresent()) {
                BarangDokumen barangDokumen = new BarangDokumen();
                barangDokumen.setSeriDokumen(Integer.toString(document.get().getSeri()));
                barangDokumen.setIdDokumen(barangDokumen.getSeriDokumen());
                barangDokumen.setSeriIjin(itemDocument.getItemSkepSeri());
                listBarangDokumen.add(barangDokumen);
            }
        }
        return listBarangDokumen;
    }

    private List<BarangTarif> generateBarangTarif(String itemId) {
        List<BarangTarif> listBarangTarif = new ArrayList<>();
        for (TariffItem tariffItem : tariffItemDao.findByTariffItemPKItemIdOrderByTariffItemPKTaxType(itemId)) {
            BarangTarif barangTarif = new BarangTarif();
            barangTarif.setJumlahSatuan(tariffItem.getNumberUnit());
            barangTarif.setKodeFasilitasTarif(tariffItem.getTariffFacilityCode());
            barangTarif.setKodeJenisPungutan(tariffItem.getTariffItemPK().getTaxType());
            barangTarif.setKodeJenisTarif(tariffItem.getTariffType());
            barangTarif.setTarif(NumberUtil.round(tariffItem.getTariffValue(), 2, RoundingMode.FLOOR));
            barangTarif.setTarifFasilitas(NumberUtil.round(tariffItem.getFacilityTariff(), 2, RoundingMode.FLOOR));
            barangTarif.setKodeSatuanBarang(tariffItem.getUnitCode());
            barangTarif.setFlagBmtSementara(tariffItem.isTemporary() ? "Y" : "T");
            barangTarif.setNilaiBayar(NumberUtil.round(tariffItem.getPaymentValue(), 2, RoundingMode.FLOOR));
            barangTarif.setNilaiFasilitas(NumberUtil.round(tariffItem.getFacilityValue(), 2, RoundingMode.FLOOR));
            listBarangTarif.add(barangTarif);
        }
        return listBarangTarif;
    }

    private List<BarangVd> generateBarangVd(String itemId) {
        List<BarangVd> listBarangVd = new ArrayList<>();
        for (VdItem vdItem : vdItemDao.findByVdItemPKItemId(itemId)) {
            BarangVd barangVd = new BarangVd();
            barangVd.setJatuhTempoRoyalti(DateUtil.toString(vdItem.getDueDate(), DateUtil.YYYY_MM_DD));
            barangVd.setKodeJenisVd(vdItem.getVdItemPK().getVdType());
            barangVd.setNilaiBarangVd(NumberUtil.round(vdItem.getVdValue(), 4, RoundingMode.FLOOR));
            listBarangVd.add(barangVd);
        }
        return listBarangVd;
    }

    private List<BarangSpekKhusus> generateSpekKhusus(String itemId) {
        List<BarangSpekKhusus> listSpekKhusus = new ArrayList<>();
        for (SpecialSpecifications specialSpec : specialSpecificationsDao.findBySpecialSpecificationPKItemId(itemId)) {
            BarangSpekKhusus spekKhusus = new BarangSpekKhusus();
            spekKhusus.setKodeSpekKhusus(
                    NumberUtil.toInt(specialSpec.getSpecialSpecificationPK().getSpecificationCode()));
            spekKhusus.setUraianBarangSpekKhusus(specialSpec.getDescription());
            listSpekKhusus.add(spekKhusus);
        }
        return listSpekKhusus;
    }
}
