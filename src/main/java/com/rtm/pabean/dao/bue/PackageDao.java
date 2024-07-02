package com.rtm.pabean.dao.bue;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bue.Package;

@Repository("buePackageDao")
public interface PackageDao extends JpaRepository<Package, String> {

    List<Package> findByPebIdOrderByPackType(String pebId);

    long countByPebId(String pebId);

    Optional<Package> findByPackTypeAndPebId(String packType, String pebId);

    long deleteByPebId(String pebId);

    @Query(value = "select count(number_pack) "
            + "from export.package "
            + "where peb_id = :pebId and coalesce(cast(number_pack as varchar),'') like %:numberPack% "
            + "and coalesce(pack_type,'') like %:packType% and coalesce(pack_brand,'') like %:packBrand%", nativeQuery = true)
    long countByField(String pebId, String numberPack, String packType, String packBrand);
}
