package com.emids.springbootdemo.service;

import java.util.List;
import java.util.Optional;

import com.emids.springbootdemo.entity.Share;

public interface ShareService {
	
	List<Share> getAllShares();
	Optional<Share> getShareByName(String shareName);
	void addShare(Share share);
	void deleteShare(Share share);
	void updateShare(String shareName, Share updatedShare);

}
