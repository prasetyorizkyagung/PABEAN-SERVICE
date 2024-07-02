package com.rtm.pabean.dao.bup;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bup.Container;

@Repository("bupContainerDao")
public interface ContainerDao extends JpaRepository<Container, String> {

        List<Container> findByPibIdOrderByContainerNumber(String pibId);

        long countByPibId(String pibId);

        Optional<Container> findByContainerNumberAndPibId(String containerNumber, String pibId);

        long deleteByPibId(String pibId);

        List<Container> findByPibIdOrderByContainerId(String pibId);
}
