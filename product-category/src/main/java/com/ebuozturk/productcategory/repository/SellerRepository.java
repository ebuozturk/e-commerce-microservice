package com.ebuozturk.productcategory.repository;

import com.ebuozturk.productcategory.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, String> {}
