package com.microcredentials.cs.repository;

import com.microcredentials.cs.model.ProductCatalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogueRepository extends JpaRepository<ProductCatalogue, Long> {
    ProductCatalogue findByBrandAndColor(String brand, String color);

    List<ProductCatalogue> findByCategory(String category);

    List<ProductCatalogue> findBySubcategory(String subcategory);
}
