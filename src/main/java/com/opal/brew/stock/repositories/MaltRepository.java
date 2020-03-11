package com.opal.brew.stock.repositories;

import com.opal.brew.stock.models.Malt;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaltRepository extends JpaRepository<Malt, Integer> {
  Optional<Malt> findAllByExpiryDateAfter(Calendar currentTime);
  List<Malt> findAll();
}
