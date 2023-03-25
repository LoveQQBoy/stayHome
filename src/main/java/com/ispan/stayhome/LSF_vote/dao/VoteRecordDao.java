package com.ispan.stayhome.LSF_vote.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ispan.stayhome.LSF_vote.model.VoteRecord;

public interface VoteRecordDao extends JpaRepository<VoteRecord, Integer> {

}
