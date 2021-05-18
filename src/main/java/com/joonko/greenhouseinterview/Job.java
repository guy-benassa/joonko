package com.joonko.greenhouseinterview;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A candidate applying for a job
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Job {
	
	private long id;
	private String name;
	
	public Job() { }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
