package com.opal.brew.production.repositories;

import com.opal.brew.production.models.InProgressProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InProgressRepository extends JpaRepository<InProgressProduct, Integer> {

  List<InProgressProduct> findAllByIsApproved(boolean approved);

}
