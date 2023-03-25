package com.ispan.stayhome.CYS_public.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.stayhome.CYS_public.model.CreatePublic;

public interface CreatePublicDao extends JpaRepository<CreatePublic, Integer>{
			
	@Query(value=" select * from CreatePublic where [State] = ?1 ", nativeQuery = true)
	public List<CreatePublic> publicsByState(String state);	
	
//	@Query(value=" select * from CreatePublic where [State] = ?1 or [State] = ?2", nativeQuery = true)
//	public List<CreatePublic> publicsByStates(String state1, String state2);	
	
	@Query(value=" select * from CreatePublic where [State] like '%' + :s and [State] !=2 "
			+ " and [Public_Name] like '%' + :pn ", nativeQuery = true)
	public Page<CreatePublic> publicListByPage(@Param("s") String publicState, 
			@Param("pn") String publicName, Pageable pageable);
	
	
	
	
	
}
