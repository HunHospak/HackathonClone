package com.opal.brew.delivery.repository;

import com.opal.brew.delivery.models.CurrentProduct;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CurrentProductRepository extends JpaRepository<CurrentProduct,Long> {
  Optional<CurrentProduct> findByProductName(String productName);

  @Modifying
  @Transactional
  @Query(value = "update current_product set quantity = quantity - ?2 where product_name = ?1 ", nativeQuery = true)
  void updateAmount(String productName, int amount);

  @Modifying
  @Transactional
  @Query(value = "update current_product set quantity = quantity + ?2 where product_name = ?1 ", nativeQuery = true)
  void addAmount(String productName, int amount);

  @Query(value = "select product_name from current_product", nativeQuery = true)
  List<String> findAllName();
}
