package com.opal.brew.stock.repositories;

import com.opal.brew.stock.models.Hop;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HopRepository extends JpaRepository<Hop, Integer> {
  Optional<Hop> findAllByExpiryDateAfter(Calendar currentTime);
  List<Hop> findAll();
}
