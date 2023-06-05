package com.emids.springbootdemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emids.springbootdemo.entity.Share;
import com.emids.springbootdemo.exception.ShareNotFoundException;
import com.emids.springbootdemo.service.ShareService;

@RestController
@RequestMapping("/share")
public class ShareController {
	
	@Autowired
	private ShareService shareService;
	
	@GetMapping("/{shareName}")
	public Share getShareByName(@PathVariable String shareName) throws ShareNotFoundException {	
		
		Optional<Share> shareOpt = shareService.getShareByName(shareName);
		if(shareOpt.isEmpty()) {
		throw new ShareNotFoundException("Share Not found with name: "+shareName);
		}
		return shareOpt.get();
	}
	
	@GetMapping
	public List<Share> getAllShares(){
		return shareService.getAllShares();
	}
	
	@PostMapping
	public ResponseEntity<String> addShare(@RequestBody Share share){
		shareService.addShare(share);
		return new ResponseEntity<>(share.getShareName()+ " Share is added successfully..",HttpStatus.OK);	
	}

	@DeleteMapping("/{shareName}")
	public ResponseEntity<String> deleteShareByName(@PathVariable String shareName) throws ShareNotFoundException{	
		shareService.deleteShare(shareService.getShareByName(shareName).get());
		return new ResponseEntity<>(shareName+ " Share is deleted successfully..",HttpStatus.OK);
	}

	
	@PutMapping("/{shareName}")
	public ResponseEntity<String> updateShareDetails(@PathVariable String shareName, @RequestBody Share updatedShare) throws ShareNotFoundException{
		shareService.updateShare(shareName, updatedShare);
		return new ResponseEntity<>(shareName+ " Share is updated successfully..",HttpStatus.OK);	
	}
}
