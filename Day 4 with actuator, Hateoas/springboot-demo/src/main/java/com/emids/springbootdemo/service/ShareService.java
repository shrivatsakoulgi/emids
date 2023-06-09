package com.emids.springbootdemo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.emids.springbootdemo.entity.CompanyShare;
import com.emids.springbootdemo.exception.ShareNotFoundException;

public interface ShareService {
	
	List<CompanyShare> getAllShares();
	Optional<CompanyShare> getShareByUUID(UUID shareId);
	void addShare(CompanyShare share);
	void deleteShare(CompanyShare share);
	void updateShare(UUID shareId, CompanyShare updatedShare);
	Optional<CompanyShare> getShareByName(String shareName);
	void updateShareByFields(UUID shareId, Map<String, Object> fields)
			throws ShareNotFoundException;

}
