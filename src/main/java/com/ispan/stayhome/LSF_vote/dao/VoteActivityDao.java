package com.ispan.stayhome.LSF_vote.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.stayhome.LSF_vote.model.VoteActivity;

public interface VoteActivityDao extends JpaRepository<VoteActivity, Integer> {

	@Query(value = "select * from VoteActivity where Vote_Title like %:title%", nativeQuery = true)
	public Page<VoteActivity> findAllByTitle(@Param(value = "title") String title, Pageable pageable);

	@Query(value = "select * from VoteActivity where Vote_Title like %:title% AND GETDATE() < Start_Date",
		nativeQuery = true)
	public Page<VoteActivity> findUnusedByTitle(@Param(value = "title") String title, Pageable pageable);

	@Query(value = 
		"select * from VoteActivity where Vote_Title like %:title% AND GETDATE() > Start_Date AND GETDATE() < End_Date",
		nativeQuery = true)
	public Page<VoteActivity> findUsingByTitle(@Param(value = "title") String title, Pageable pageable);

	@Query(value = "select * from VoteActivity where Vote_Title like %:title% AND GETDATE() > End_Date",
			nativeQuery = true)
	public Page<VoteActivity> findUsedByTitle(@Param(value = "title") String title, Pageable pageable);
	
	@Query(value = "select * from VoteActivity where NOT( GETDATE() < Start_Date )", nativeQuery = true)
	public Page<VoteActivity> findAllForFrontPage(Pageable pageable);
	
	@Query(value = "select * from VoteActivity where GETDATE() > Start_Date AND GETDATE() < End_Date",
			nativeQuery = true)
	public Page<VoteActivity> finAllUsingForFront(Pageable pageable);
	
	@Query(value = "select * from VoteActivity where GETDATE() > End_Date",
			nativeQuery = true)
	public Page<VoteActivity> finAllUsedForFront(Pageable pageable);
}
