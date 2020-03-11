package com.opal.brew.stock.repositories;

import com.opal.brew.stock.models.CurrentMaterialStock;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CurrentMaterialStockRepository extends JpaRepository<CurrentMaterialStock, Long> {

  @Modifying
  @Transactional
  @Query(value = "update current_material_stock set quantity = quantity + ?2 where material = ?1 ", nativeQuery = true)
  void updateAmount(String material, int amount);

  @Modifying
  @Transactional
  @Query(value = "update current_material_stock set quantity = quantity - ?2, reserved = reserved + ?2 where material = ?1 ", nativeQuery = true)
  void reserveMaterial(String material, int amount);

  @Modifying
  @Transactional
  @Query(value = "update current_material_stock set quantity = quantity + ?2, reserved = reserved - ?2 where material = ?1 ", nativeQuery = true)
  void freeUpReserved(String material, int amount);

  @Modifying
  @Transactional
  @Query(value = "update current_material_stock set reserved = reserved - ?2 where material = ?1", nativeQuery = true)
  void acceptMaterial(String material, int amount);

  @Query(value = "select material from current_material_stock", nativeQuery = true)
  List<String> findAllType();
}
