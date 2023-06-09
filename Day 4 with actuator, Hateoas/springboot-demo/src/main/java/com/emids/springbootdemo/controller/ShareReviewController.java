package com.emids.springbootdemo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ShareReviewController {	
	private static Map<String,String> reviews;
	static {
		reviews=new HashMap<>();
		reviews.put("ADANI", "Down now.. Can invest for future");
		reviews.put("HDFC", "Good share to invest as its a Banking app");
		reviews.put("ITC", "Moderate.. ");
		reviews.put("TATA", "Not a good time to invest in TATA");
	}
	@GetMapping("/{shareName}")
	public String getReview(@PathVariable String shareName) {
			return reviews.get(shareName);
	}

}
