package com.ispan.stayhome.CYS_public.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.stayhome.CYS_public.dao.PublicAppointmentDao;
import com.ispan.stayhome.CYS_public.model.CreatePublic;
import com.ispan.stayhome.CYS_public.model.PublicAppointment;
import com.ispan.stayhome.CYS_repair.model.RepairRequite;


@Service
@Transactional
public class PublicAppointmentService {

	@Autowired
	private PublicAppointmentDao publicAppointmentDao;
	@Autowired
	private CreatePublicService createPublicService;
	
	
	public void add(PublicAppointment pa) {
		CreatePublic cp = createPublicService.findById(pa.getPublicId());
		pa.setPublic_(cp);
		publicAppointmentDao.save(pa);
	}
	
	
	public void save(PublicAppointment pa) {		
		publicAppointmentDao.save(pa);
	}
		
	
	public List<String> publicTimes(Integer publicId) {
		List<String> timeList= new ArrayList<>(); 
		
		CreatePublic cp = createPublicService.findById(publicId);
		String interTimes = cp.getInterTimes();
		Integer limitNumber = cp.getLimitNumber();
		
		String[] TimesArray = interTimes.split(",");			
		for(String interTime : TimesArray) {
			Integer sumAN= publicAppointmentDao.sumAppointmentNumber(publicId,interTime);
			if(sumAN==null) {
				sumAN = 0;
			}
			if(sumAN<limitNumber) {
				timeList.add(interTime);
			}
		}		
		return timeList;		
	}
	
	
	public Integer okAppointmentNumber(Integer publicId, String interTime) {		
		CreatePublic cp = createPublicService.findById(publicId);		
		Integer limitNumber = cp.getLimitNumber();		
		Integer sumAN= publicAppointmentDao.sumAppointmentNumber(publicId,interTime);
		
		if(sumAN==null) {
			sumAN = 0;
		}
		
		Integer OkNumber = limitNumber-sumAN;		
		return OkNumber;		
	}
	
	
//	public List<PublicAppointment> findByUserIdAndPublicState1(Integer UserId) {
//		return publicAppointmentDao.findByUserIdAndPublicState1(UserId);
//	}
	
	
	public Page<PublicAppointment> userListAndPublicState1ByPage(Integer userId, String date, 
			String apState, String publicName, Integer pageNumber) {
		
		Pageable Pageable = PageRequest.of(pageNumber-1, 5, Sort.Direction.DESC, "date");
		
		Page<PublicAppointment> page = publicAppointmentDao.userListAndPublicState1ByPage(userId,
				date, apState, publicName, Pageable);
						
		return page;
	}
	
	
	public Page<PublicAppointment> backListByPage(String date, String apState, String publicName,
			String name, String phone, Integer pageNumber) {
		
		Pageable Pageable = PageRequest.of(pageNumber-1, 5, Sort.Direction.DESC, "date");
		
		Page<PublicAppointment> page = publicAppointmentDao.backListByPage(
				date, apState, publicName, name, phone, Pageable);
						
		return page;
	}
	
	
	public PublicAppointment findById(Integer id) {
		Optional<PublicAppointment> optional= publicAppointmentDao.findById(id);		
//		if(optional.isEmpty()) {
//			return null;
//		}		
		return optional.get();
	}
	
		
	public void transStateAppos() {
		List<PublicAppointment> appos = publicAppointmentDao.getByStateAppointment();
		for(PublicAppointment appo : appos) {
			Date appoDate = appo.getAppointmentDate();
			String appoTime = appo.getAppointmentTime();
			
			SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd ");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date completeDate = null;
            try {
				completeDate = df.parse( ds.format(appoDate) + appoTime );
			} catch (ParseException e) {
				e.printStackTrace();
			}
            if( new Date().after(completeDate) || new Date().equals(completeDate) ) {
            	appo.setAppointmentState("預約結束");
            }
		}

	}
	
	
//	public List<PublicAppointment> findAll() {
//		return publicAppointmentDao.findAll();
//	}
	
}