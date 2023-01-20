package com.ebuozturk.productcategory.repository;

import com.ebuozturk.productcategory.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, String> {}
