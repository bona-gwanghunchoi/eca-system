package com.bonacamp.ecasystem.domain.access.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonacamp.ecasystem.domain.access.entity.AccessInfo;


@Repository
public interface AccessInfoRepository extends JpaRepository<AccessInfo, Long>{
	
}
