package com.ispan.stayhome.HYL_collectParcel.dao;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ispan.stayhome.HYL_collectParcel.model.CollectParcel;


@Repository
public interface CollectParcelRepository extends JpaRepository<CollectParcel, Integer> {
		
	@Query(value = "select * from CollectParcel where Parcel_Name like %:parcelname%",nativeQuery = true)
	public Page<CollectParcel> findPageByParcelname(@Param(value = "parcelname")String parcelname,Pageable pageable);
	
	@Query(value = "select * from CollectParcel where Parcel_Name like %:parcelname% And state = '未領取'",nativeQuery = true)
	public Page<CollectParcel> findPageByParcelnameAndNotReceived(@Param(value = "parcelname")String parcelname,Pageable pageable);
	
	@Query(value = "select * from CollectParcel where Parcel_Name like %:parcelname% And state = '已領取'",nativeQuery = true)
	public Page<CollectParcel> findPageByParcelnameAndReceived(@Param(value = "parcelname")String parcelname,Pageable pageable);
	
	@Query(value = "select * from CollectParcel where Recipient_Id = :UserId",nativeQuery = true)
	public Page<CollectParcel> findPageByUserId(@Param(value = "UserId") Integer userId, Pageable pageable);
	
	@Query(value = "select * from CollectParcel where Recipient_Id = :UserId And state = '未領取'",nativeQuery = true)
	public Page<CollectParcel> findPageByUserIdAndNotReceived(@Param(value = "UserId") Integer userId, Pageable pageable);
	
	@Query(value = "select * from CollectParcel where Recipient_Id = :UserId And state = '已領取'",nativeQuery = true)
	public Page<CollectParcel> findPageByUserIdAndReceived(@Param(value = "UserId") Integer userId, Pageable pageable);
	
	@Query(value = "select * from CollectParcel where state = '未領取'",nativeQuery = true)
	public Page<CollectParcel> findPageByNotReceived(Pageable pageable);
	
	@Query(value = "select * from CollectParcel where state = '已領取'",nativeQuery = true)
	public Page<CollectParcel> findPageByReceived(Pageable pageable);
}