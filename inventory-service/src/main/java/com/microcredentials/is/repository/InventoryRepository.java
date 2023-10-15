package com.microcredentials.is.repository;

import com.microcredentials.is.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

    List<Inventory> findByProductId(Long pid);
}
