package com.microcredentials.pss.repository;

import com.microcredentials.pss.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

    Inventory findByProductId(int pid);
}
