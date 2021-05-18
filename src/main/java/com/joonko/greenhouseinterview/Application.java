package com.joonko.greenhouseinterview;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A candidate applying Application for a job
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Application {
	
	private long id;
	private String status;
	private List<Job> jobs;
	
	public Application() { }

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Job> getJobs() {
		return jobs;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
	

}
