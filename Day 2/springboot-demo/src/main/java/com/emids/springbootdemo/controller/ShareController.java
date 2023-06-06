package com.emids.springbootdemo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emids.springbootdemo.entity.CompanyShare;
import com.emids.springbootdemo.exception.ShareNotFoundException;
import com.emids.springbootdemo.service.ShareService;

@RestController
@RequestMapping("/share")
public class ShareController {
	
	@Autowired
	private ShareService shareService;
	
	@GetMapping("/name/{shareName}")
	public CompanyShare getShareByName(@PathVariable String shareName) throws ShareNotFoundException {	
		
		Optional<CompanyShare> shareOpt = shareService.getShareByName(shareName);
		if(shareOpt.isEmpty()) {
		throw new ShareNotFoundException("Share Not found with name: "+shareName);
		}
		return shareOpt.get();
	}
	
	@GetMapping("/{shareId}")
	public CompanyShare getShareByUUID(@PathVariable UUID shareId) throws ShareNotFoundException {	
		
		Optional<CompanyShare> shareOpt = shareService.getShareByUUID(shareId);
		if(shareOpt.isEmpty()) {
		throw new ShareNotFoundException("Share Not found with UUID: "+shareId);
		}
		return shareOpt.get();
	}
	@GetMapping
	public List<CompanyShare> getAllShares(){
		return shareService.getAllShares();
	}
	
	@PostMapping
	public ResponseEntity<String> addShare(@RequestBody CompanyShare share){
		shareService.addShare(share);
		return new ResponseEntity<>(share.getShareName()+ " Share is added successfully with UUID "+share.getShareId(),HttpStatus.OK);	
	}

	@DeleteMapping("/{shareId}")
	public ResponseEntity<String> deleteShareByName(@PathVariable UUID shareId) throws ShareNotFoundException{	
		shareService.deleteShare(shareService.getShareByUUID(shareId).get());
		return new ResponseEntity<>(shareId+ " Share is deleted successfully..",HttpStatus.OK);
	}

	
	@PutMapping("/{shareId}")
	public ResponseEntity<String> updateShareDetails(@PathVariable UUID shareId, @RequestBody CompanyShare updatedShare) throws ShareNotFoundException{
		shareService.updateShare(shareId, updatedShare);
		return new ResponseEntity<>(shareId+ " Share is updated successfully..",HttpStatus.OK);	
	}
	
	@PatchMapping("/{shareId}")
	public ResponseEntity<String> updateShareDetailsByFields(@PathVariable UUID shareId, @RequestBody Map<String, Object> fields) throws ShareNotFoundException{
		shareService.updateShareByFields(shareId, fields);
		return new ResponseEntity<>(shareId+ " Share is updated successfully..",HttpStatus.OK);	
	}
}
