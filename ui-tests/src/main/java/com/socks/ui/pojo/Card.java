package com.socks.ui.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Card {

	@JsonProperty("CardNumber")
	private String cardNumber;

	@JsonProperty("CVV")
	private String cVV;

	@JsonProperty("Address")
	private String address;

	@JsonProperty("Country")
	private String country;

	@JsonProperty("Exp")
	private String exp;

	@JsonProperty("IssuingNetwork")
	private String issuingNetwork;

	@JsonProperty("Name")
	private String name;

	public void setCardNumber(String cardNumber){
		this.cardNumber = cardNumber;
	}

	public String getCardNumber(){
		return cardNumber;
	}

	public void setCVV(String cVV){
		this.cVV = cVV;
	}

	public String getCVV(){
		return cVV;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setExp(String exp){
		this.exp = exp;
	}

	public LocalDate getExp(){
		return LocalDate.parse("01/".concat(exp), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public void setIssuingNetwork(String issuingNetwork){
		this.issuingNetwork = issuingNetwork;
	}

	public String getIssuingNetwork(){
		return issuingNetwork;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"Card{" + 
			"cardNumber = '" + cardNumber + '\'' + 
			",cVV = '" + cVV + '\'' + 
			",address = '" + address + '\'' + 
			",country = '" + country + '\'' + 
			",exp = '" + exp + '\'' + 
			",issuingNetwork = '" + issuingNetwork + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}