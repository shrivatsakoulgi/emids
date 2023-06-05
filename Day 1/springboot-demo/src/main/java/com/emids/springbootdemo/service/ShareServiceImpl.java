package com.emids.springbootdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emids.springbootdemo.entity.Share;
import com.emids.springbootdemo.repository.ShareRepository;

@Service
public class ShareServiceImpl implements ShareService {

	@Autowired
	private ShareRepository shareRepository;	
	
	public List<Share> getAllShares() {
		return (List<Share>) shareRepository.findAll();
	}

	public Optional<Share> getShareByName(String shareName) {		
		return shareRepository.findById(shareName);
	}
	
	public void addShare(Share share) {
		shareRepository.save(share);
	}

	public void deleteShare(Share share) {
		shareRepository.delete(share);
	}
	
	public void updateShare(String shareName, Share updatedShare) {		
		shareRepository.save(updatedShare);
	}

}
