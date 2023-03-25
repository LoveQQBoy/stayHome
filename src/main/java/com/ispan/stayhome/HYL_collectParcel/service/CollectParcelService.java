package com.ispan.stayhome.HYL_collectParcel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ispan.stayhome.HYL_collectParcel.dao.CollectParcelRepository;
import com.ispan.stayhome.HYL_collectParcel.dao.PackHouseholdDao;
import com.ispan.stayhome.HYL_collectParcel.model.CollectParcel;
import com.ispan.stayhome.HYL_collectParcel.model.PackHouseholdData;

@Service
public class CollectParcelService {

    @Autowired
    private CollectParcelRepository collectParcelRepository;
    
    @Autowired
    private PackHouseholdDao packHouseholdDao;

    // 查詢所有包裹

    public List<CollectParcel> getAllCollectParcel() {
        return collectParcelRepository.findAll();
    }
    
    // 查詢該id所對應的包裹
    
    public CollectParcel findById(int id) {
    	return collectParcelRepository.findById(id).orElse(null);
    }
    
    // 新增包裹
     
    public CollectParcel addCollectParcel(CollectParcel ctp) {
        return collectParcelRepository.save(ctp);
    }

 
    //用使用者名稱找住戶資料
    public PackHouseholdData findHouseholdByName(String userName) {
    	PackHouseholdData phUser = packHouseholdDao.findByName(userName);
    	
    	return phUser;
    }
   
    // 刪除包裹
    
    public void deleteParcel(int id) {
        collectParcelRepository.deleteById(id);
    }
   
    
    
    //修改包裹
	public CollectParcel updateCollectParcel(int id, CollectParcel ctp) {
		CollectParcel existingCollectParcel = findById(id);
		existingCollectParcel.setState(ctp.getState());
		existingCollectParcel.setParcelname(ctp.getParcelname());
        existingCollectParcel.setReceiveddate(ctp.getReceiveddate());
        existingCollectParcel.setSendername(ctp.getSendername());
        existingCollectParcel.setDescription(ctp.getDescription());
        return collectParcelRepository.save(existingCollectParcel);
	}
	
	// 以包裹名稱查詢
//	public List<CollectParcel> getByParcelname(String parcelname) {
//		List<CollectParcel> fbpn= collectParcelRepository.findByParcelname(parcelname);
//		return fbpn;
//    }
//	
//	// 以收件人名稱查詢
//	public List<CollectParcel> getByRecipientname(String recipientname) {
//		List<CollectParcel> fbrn= collectParcelRepository.findByRecipientname(recipientname);
//		return fbrn;
//
//    }
//	
//	// 以包裹名稱和收件人名稱查詢
//	public List<CollectParcel> getByParcelnameAndRecipientname(String parcelname,String recipientname) {
//		List<CollectParcel> fbpnarn= collectParcelRepository.findByParcelnameAndRecipientname(parcelname,recipientname);
//		return fbpnarn;
//
//    }

	//得到所有資料的page
	public Page<CollectParcel> getPage(Integer pageNumber,String state){
		Pageable pgb = null;
		Page<CollectParcel> page = null;
		if(state.equals("未選擇")) {
			pgb = PageRequest.of(pageNumber-1,3,Sort.Direction.ASC,"receiveddate");
			page = collectParcelRepository.findAll(pgb);
		} else if(state.equals("未領取")) { 
			pgb = PageRequest.of(pageNumber-1,3,Sort.Direction.ASC,"Received_Date");
		    page = collectParcelRepository.findPageByNotReceived(pgb);
		} else if(state.equals("已領取")){
			pgb = PageRequest.of(pageNumber-1,3,Sort.Direction.ASC,"Received_Date");
			page = collectParcelRepository.findPageByReceived(pgb);
			
		}
		return page;
	}		
	
	//得到有這個使用者id的page
	public Page<CollectParcel> getPageByUser(int pageNumber, Integer userId ,String state){
		Pageable pgb = null;
		pgb = PageRequest.of(pageNumber-1,3,Sort.Direction.ASC,"Received_Date");
		Page<CollectParcel> page = null;
		if(state.equals("未選擇")) {
			page = collectParcelRepository.findPageByUserId(userId,pgb);
		}else if(state.equals("未領取")) {
		    page = collectParcelRepository.findPageByUserIdAndNotReceived(userId, pgb);
		} else if(state.equals("已領取")) {
			page = collectParcelRepository.findPageByUserIdAndReceived(userId, pgb);
		}
		return page;
	}		
		
	//得到有這包裹名稱的page
	public Page<CollectParcel> getPageByParcelname(Integer pageNumber,String state,String parcelname){
		Pageable pgb = null;
		Page<CollectParcel> page = null;
		if (state.equals("未選擇")) {
			pgb = PageRequest.of(pageNumber-1,3,Sort.Direction.ASC,"Received_Date");
			page = collectParcelRepository.findPageByParcelname(parcelname, pgb);
		}else if(state.equals("未領取")) {
			pgb = PageRequest.of(pageNumber-1,3,Sort.Direction.ASC,"Received_Date");
		    page = collectParcelRepository.findPageByParcelnameAndNotReceived(parcelname, pgb);
		} else if(state.equals("已領取")){
			pgb = PageRequest.of(pageNumber-1,3,Sort.Direction.ASC,"Received_Date");
			page = collectParcelRepository.findPageByParcelnameAndReceived(parcelname, pgb);
		}
		return page;
	}		
	
//	//更改狀態為拒收
//	public CollectParcel changetorefuse(Integer id, CollectParcel ctp) {
//		CollectParcel existingCollectParcel = findById(id);
//		existingCollectParcel.setState("拒收");
//        return collectParcelRepository.save(existingCollectParcel);
//	}
	
}