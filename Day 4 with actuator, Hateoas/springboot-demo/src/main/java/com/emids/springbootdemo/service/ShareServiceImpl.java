package com.emids.springbootdemo.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.emids.springbootdemo.entity.CompanyShare;
import com.emids.springbootdemo.exception.ShareNotFoundException;
import com.emids.springbootdemo.repository.ShareRepository;

@Service
public class ShareServiceImpl implements ShareService {

	@Autowired
	private ShareRepository shareRepository;	
	
	public List<CompanyShare> getAllShares() {
		return (List<CompanyShare>) shareRepository.findAll();
	}

	public Optional<CompanyShare> getShareByUUID(UUID shareId) {		
		return shareRepository.findById(shareId);
	}
	
	public void addShare(CompanyShare share) {
		shareRepository.save(share);
	}

	public void deleteShare(CompanyShare share) {
		shareRepository.delete(share);
	}
	
	public void updateShare(UUID shareId, CompanyShare updatedShare) {
		CompanyShare share = shareRepository.findById(shareId).get();
		share.setShareName(updatedShare.getShareName());
		share.setSharePrice(updatedShare.getSharePrice());
		share.setEquityDebtRatio(updatedShare.getEquityDebtRatio());
		share.setTotalInvestmentsInCrores(updatedShare.getTotalInvestmentsInCrores());
		shareRepository.save(share);
	}

	public Optional<CompanyShare> getShareByName(String shareName) {
		return shareRepository.findByShareName(shareName);
	}

	// Patch Request
	public void updateShareByFields(UUID shareId, Map<String, Object> fields) 
			throws ShareNotFoundException {
		
		Optional<CompanyShare> existingShare = shareRepository.findById(shareId);
		if(existingShare.isPresent()) {
			fields.forEach((key,value)-> {
				Field field=ReflectionUtils.findField(CompanyShare.class, key);
				if(field!=null) {
				field.setAccessible(true);
				ReflectionUtils.setField(field, existingShare.get(), value);
				}else {
					//throw new InvalidFieldNameInJSONException("Invalid Field in JSON:"+key);
					// throw exception
					
				}
			});
			shareRepository.save(existingShare.get());
		}else {
			throw new ShareNotFoundException("Share not found with Id:"+shareId);
		}
		
	}



}
