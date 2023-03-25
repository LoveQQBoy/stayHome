package com.ispan.stayhome.CYS_repair.sevice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.stayhome.CYS_repair.dao.RepairRequiteDao;
import com.ispan.stayhome.CYS_repair.model.RepairRequite;

@Service
@Transactional
public class RepairRequiteService {

	@Autowired
	private RepairRequiteDao repairRequiteDao;
	
		
	public void save(RepairRequite repair) {
		repairRequiteDao.save(repair);
	}
	
	public RepairRequite findById(Integer id) {
		Optional<RepairRequite> optional = repairRequiteDao.findById(id);
		return optional.get();
	}
	
	
	public Page<RepairRequite> residentListByPage(Integer residentId, String date, 
			String replyState, Integer pageNumber) {
		Pageable Pageable = PageRequest.of(pageNumber-1, 5, Sort.Direction.DESC, "date");
		
		Page<RepairRequite> page = repairRequiteDao.residentListByPage(residentId, 
				date, replyState, Pageable);
						
		return page;
	}
	
	public Page<RepairRequite> backListByPage(String date, String replyState, String name, 
			String phone, Integer pageNumber) {
		Pageable Pageable = PageRequest.of(pageNumber-1, 5, Sort.Direction.DESC, "date");
		
		Page<RepairRequite> page = repairRequiteDao.backListByPage(date, replyState, name,
				phone, Pageable);
						
		return page;
	}
	
	
	
	
}
