package com.opal.brew.stock.repositories;

import com.opal.brew.stock.models.Yeast;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YeastRepository extends JpaRepository<Yeast, Integer> {
  Optional<Yeast> findAllByExpiryDateAfter(Calendar currentTime);
  List<Yeast> findAll();
}
