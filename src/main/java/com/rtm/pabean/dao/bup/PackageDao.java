package com.rtm.pabean.dao.bup;

import java.util.List;
import java.util.Optional;

import com.rtm.pabean.model.bup.Package;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("bupPackageDao")
public interface PackageDao extends JpaRepository<Package, String> {

        List<Package> findByPibIdOrderByPackType(String pibId);

        long countByPibId(String pibId);

        Optional<Package> findByPackTypeAndPibId(String packType, String pibId);

        long deleteByPibId(String pibId);

        @Query(value = "select count(number_pack) "
                        + "from import.package "
                        + "where pib_id = :pibId and coalesce(cast(number_pack as varchar),'') like %:numberPack% "
                        + "and coalesce(pack_type,'') like %:packType% and coalesce(pack_brand,'') like %:packBrand%", nativeQuery = true)
        long countByField(String pibId, String numberPack, String packType, String packBrand);

        List<Package> findByPibIdOrderByPackageId(String pibId);
}
