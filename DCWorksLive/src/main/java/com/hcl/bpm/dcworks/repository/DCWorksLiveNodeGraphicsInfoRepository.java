package com.hcl.bpm.dcworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.bpm.dcworks.entity.DCWorksLiveActivityNodeGraphicsInfo;

@Repository
public interface DCWorksLiveNodeGraphicsInfoRepository extends JpaRepository<DCWorksLiveActivityNodeGraphicsInfo, Long> {

}
