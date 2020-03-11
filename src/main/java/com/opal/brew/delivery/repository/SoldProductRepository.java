package com.opal.brew.delivery.repository;

import com.opal.brew.delivery.models.SoldProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface SoldProductRepository extends JpaRepository<SoldProduct,Long> {

  @Query(value = "SELECT sp FROM SoldProduct sp where sp.sellingDate > :after AND sp.sellingDate < :before")
  List<SoldProduct> soldBetween(@Param("before") Date before, @Param("after") Date after);

  @Query(value = "SELECT count(sp) FROM SoldProduct sp where sp.sellingDate > :after AND sp.sellingDate < :before")
  int soldBetweenCount(@Param("before") Date before, @Param("after") Date after);

  @Query(value = "SELECT DISTINCT sp FROM SoldProduct sp where :name = sp.productName group by sp.productName")
  List<SoldProduct> soldOrderByProductName(@Param("name") String name);

  List<SoldProduct> findAllByProductName(String name);

  List<SoldProduct> findAllBySellingDateBetween(@Param("before") Date before, @Param("after") Date after);

  List<SoldProduct> findAllBySellingDateAfter(@Param("after") Date after);
}
