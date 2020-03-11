package com.opal.brew.delivery.repository;

import com.opal.brew.delivery.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

  @Query(value = "SELECT p FROM Product p WHERE current_timestamp > p.expiryDate")
  List<Product> showExpiredProducts();
}
