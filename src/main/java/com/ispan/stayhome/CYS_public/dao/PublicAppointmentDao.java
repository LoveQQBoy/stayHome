package com.ispan.stayhome.CYS_public.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.stayhome.CYS_public.model.PublicAppointment;

public interface PublicAppointmentDao extends JpaRepository<PublicAppointment, Integer>{		
	
	@Query(value=" select sum([Appointment_Number])	from [PublicAppointment] "
			+ " where [FK_Public_Id] = :id  and [Appointment_Time] = :t ", nativeQuery = true)
	public Integer sumAppointmentNumber(@Param("id")Integer publicId, @Param("t")String appointmentTime);	
	
//	@Query(value=" select * from [PublicAppointment] where [FK_Resident_Id] = :id "
//			+ " and [FK_Public_Id] in (select [Public_Id] from CreatePublic where "
//			+ " [State] = 1) ", nativeQuery = true)
//	public List<PublicAppointment> findByUserIdAndPublicState1(@Param("id")Integer userId);
	
	@Query(value=" select * from [PublicAppointment] where [Appointment_State] = '已預約' ", nativeQuery = true)
	public List<PublicAppointment> getByStateAppointment();
	
	@Query(value=" select * from [PublicAppointment] where [FK_Resident_Id] = :id and "
			+ " CAST([Appointment_Date] AS DATE) >= :d and [Appointment_State] like '%' + :as + '%' "
			+ " and [FK_Public_Id] in (select [Public_Id] from CreatePublic where "
			+ " [State] = 1 and [Public_Name] like '%' + :pn + '%') ", nativeQuery = true)
	public Page<PublicAppointment> userListAndPublicState1ByPage(@Param("id") Integer userId, 
			@Param("d") String date, @Param("as") String apState, @Param("pn") String publicName, 
			Pageable pageable);
	
	@Query(value=" select * from [PublicAppointment] where CAST([Appointment_Date] AS DATE) >= :d "
			+ " and [Appointment_State] like '%' + :as + '%' "
			+ " and [FK_Public_Id] in (select [Public_Id] from CreatePublic where "
			+ " [Public_Name] like '%' + :pn + '%') "
			+ " and [FK_Resident_Id] in (select [P_Id] from HouseholdData where "
			+ " name like '%' + :n + '%' and phone like '%' + :ph + '%') ", nativeQuery = true)
	public Page<PublicAppointment> backListByPage(@Param("d") String date, 
			@Param("as") String apState, @Param("pn") String publicName, @Param("n") String name,
			@Param("ph") String phone, Pageable pageable);
	
}
