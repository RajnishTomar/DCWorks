package com.hcl.bpm.dcworks.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.bpm.dcworks.entity.DCWorksLiveProcess;

@Repository
public interface DCWorksLiveProcessRepository extends JpaRepository<DCWorksLiveProcess, Long>{

	@Query(value = "select * from DCWORKSLIVE_PROCESS where DCWORKSLIVE_POOL_ID = :dcWorksPoolId", nativeQuery = true)
	DCWorksLiveProcess findProcess(@Param("dcWorksPoolId") String dcWorksPoolId);
}
