package com.rtm.pabean.dao.bue;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bue.Container;

@Repository("bueContainerDao")
public interface ContainerDao extends JpaRepository<Container, String> {

        List<Container> findByPebIdOrderByContainerNumber(String pebId);

        long countByPebId(String pebId);

        Optional<Container> findByContainerNumber(String containerNumber);

        long deleteByPebId(String pebId);
}
