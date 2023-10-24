package com.microcredentials.pss.repository;

import com.microcredentials.pss.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

    Optional<Inventory> findByProductId(int pid);
}
