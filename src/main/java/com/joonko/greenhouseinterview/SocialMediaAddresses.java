package com.joonko.greenhouseinterview;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A candidate SocialMediaAddresses
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocialMediaAddresses {
	
	private String value;
	
	public SocialMediaAddresses() { }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static boolean hasFaceBook(List<SocialMediaAddresses> socialMediaAddresses) {
		if(socialMediaAddresses == null || socialMediaAddresses.isEmpty()) {			
			return false;
		}
		return socialMediaAddresses.stream().anyMatch( s -> s.getValue().contains("facebook"));
	}
	
	

}
