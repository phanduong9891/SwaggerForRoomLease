package com.axonactive.roomLeaseManagement.repository;

import com.axonactive.roomLeaseManagement.entity.Assets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetsRepository extends JpaRepository<Assets,Integer> {
}
