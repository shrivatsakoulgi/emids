package com.emids.springbootdemo.entity;

import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity  
public class CompanyShare extends RepresentationModel<CompanyShare>{
	
	@Id  
	@GeneratedValue  
	private UUID shareId;
	private String shareName;
	private double sharePrice;
	private double equityDebtRatio;
	private double totalInvestmentsInCrores;

	
	public CompanyShare(UUID shareId, String shareName, double sharePrice, double equityDebtRatio,
			double totalInvestmentsInCrores) {
		this.shareId = shareId;
		this.shareName = shareName;
		this.sharePrice = sharePrice;
		this.equityDebtRatio = equityDebtRatio;
		this.totalInvestmentsInCrores = totalInvestmentsInCrores;
	}

	public CompanyShare() {
	}

	public UUID getShareId() {
		return shareId;
	}

	public void setShareId(UUID shareId) {
		this.shareId = shareId;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public double getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}

	public double getEquityDebtRatio() {
		return equityDebtRatio;
	}

	public void setEquityDebtRatio(double equityDebtRatio) {
		this.equityDebtRatio = equityDebtRatio;
	}

	public double getTotalInvestmentsInCrores() {
		return totalInvestmentsInCrores;
	}

	public void setTotalInvestmentsInCrores(double totalInvestmentsInCrores) {
		this.totalInvestmentsInCrores = totalInvestmentsInCrores;
	}

	@Override
	public String toString() {
		return "CompanyShare [shareId=" + shareId + ", shareName=" + shareName + ", sharePrice=" + sharePrice
				+ ", equityDebtRatio=" + equityDebtRatio + ", totalInvestmentsInCrores=" + totalInvestmentsInCrores
				+ "]";
	}
	
	
	

	
}
