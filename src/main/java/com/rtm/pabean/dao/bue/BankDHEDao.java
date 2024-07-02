package com.rtm.pabean.dao.bue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bue.BankDhe;
import com.rtm.pabean.model.bue.BankDhePK;


@Repository("bueBankDHEDao")
public interface BankDHEDao extends JpaRepository<BankDhe, BankDhePK>{
    
    List<BankDhe> findByBankDhePKPebId(String pebId);

    long countByBankDhePKPebId(String pebId);

    long deleteByBankDhePKPebId(String pebId);
}
