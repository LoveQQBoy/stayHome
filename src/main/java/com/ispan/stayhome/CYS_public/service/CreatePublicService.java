package com.ispan.stayhome.CYS_public.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.stayhome.CYS_public.dao.CreatePublicDao;
import com.ispan.stayhome.CYS_public.model.CreatePublic;


@Service
@Transactional
public class CreatePublicService {

	@Autowired
	private CreatePublicDao createPublicDao;

	
	public void save(CreatePublic createPublic) {
		createPublicDao.save(createPublic);
	}
	
	public List<CreatePublic> findAll() {
		return createPublicDao.findAll();
	}
	
	
	public  CreatePublic findById(Integer id) {
		Optional<CreatePublic> optional= createPublicDao.findById(id);
		
//		if(optional.isEmpty()) {
//			return null;
//		}
		
		return optional.get();
	}
	
		
	public void deleteById(Integer id) {
		createPublicDao.deleteById(id);
		
	}
	
	public List<CreatePublic> publicsByState(String state) {
		return createPublicDao.publicsByState(state);
	}
	
//	public List<CreatePublic> findAllStateNot2() {
//		return createPublicDao.publicsByStates("0", "1");
//	}
	
	public Page<CreatePublic> publicListByPage(String publicState, 
			String publicName, Integer pageNumber) {
		
		Pageable Pageable = PageRequest.of(pageNumber-1, 5, Sort.Direction.DESC, "date");
		
		Page<CreatePublic> page = createPublicDao.publicListByPage(publicState,
				publicName, Pageable);
						
		return page;
	}
	
}