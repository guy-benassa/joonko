package com.joonko.greenhouseinterview;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joonko.greenhouseinterview.json.JsonHandler;
import com.joonko.greenhouseinterview.web.RestQuery;
import com.joonko.greenhouseinterview.web.SimpleRestClient;

import okhttp3.Headers;
import okhttp3.Response;

/**
 * A REST client that can query Greenhouse
 */
public class GreenhouseClient {
    private static final String CANDIDATE_URL = "https://harvest.greenhouse.io/v1/candidates/%d";
    private static final String ALL_CANDIDATES_URL = "https://harvest.greenhouse.io/v1/candidates";

    private final String apiToken;
    private final SimpleRestClient restClient;
    private JsonHandler<Candidate> candidateJsonHandler;
    private String next = ALL_CANDIDATES_URL;
    
    public boolean hasNext() {
		return next != null;
	}

    public GreenhouseClient(String apiToken) {
        this.apiToken = Base64.getEncoder().encodeToString((apiToken + ":").getBytes());
        this.restClient = new SimpleRestClient();
        this.candidateJsonHandler = new JsonHandler(Candidate.class);
    }

    public Candidate getCandidateById(long id) throws Exception {
        RestQuery query = RestQuery.builder()
                .url(String.format(CANDIDATE_URL, id))
                .jsonHandler(candidateJsonHandler)
                .headers(getSecurityHeaders())
                .build();

        return executeQuery(query);
    }
    
    public List<Candidate> getAllCandidates() throws Exception {
    	RestQuery query = RestQuery.builder()
    			.url(String.format(next))
    			.jsonHandler(new JsonHandler(new TypeReference<List<Candidate>>(){}))
    			.headers(getSecurityHeaders())
    			.build();
    	
    	return executeQuery(query);
    }

    private <T> T executeQuery(RestQuery query) throws Exception {
        Response response = restClient.getQuery(query);
        String responseBody = response.body().string();
        setNextQuery(response.headers()); 
        response.close();
        return (T) query.getJsonHandler().deserialize(responseBody);
    }

    private void setNextQuery(Headers headers) {
    	next = null;
    	List<String> links = headers.toMultimap().get("link");
    	if(links == null || links.isEmpty()) {
    		return;
    	}
    	for(String link : links) {    		
    		String[] commaSplit = link.split(",");
    		for(int i = 0 ; i < commaSplit.length ; i ++) {    			
    			String[] split = commaSplit[i].split(";");
    			for(int j = 0 ; j < split.length ; j++) {
    				if(" rel=\"next\"".equals(split[j])) {
    					next = removeMarks(split[j-1]);
    					return;
    				}
    			}
    		}
    	}
	}

	private String removeMarks(String s) {
		return s.substring(1, s.length() - 1);
	}

	private Map<String, String> getSecurityHeaders() {
        HashMap<String, String> securityHeaders = new HashMap<>();
        securityHeaders.put("Authorization", String.format("Basic %s", apiToken));
        return securityHeaders;
    }
    
    public static void main(String[] args) throws Exception {
    	final String apiKey = "e78f1f4f1aa5a7001f43b3fc25f0fb5b-1";
    	GreenhouseClient green = new GreenhouseClient(apiKey);
    	
    	while(green.hasNext()) {
    		
    		List<Candidate> allCandidates = green.getAllCandidates();
    		
    		System.out.println(allCandidates.size());
    		
    		for(Candidate candidate : allCandidates) {
    			List<Application> applications = candidate.getApplications();
    			if(applications == null || applications.isEmpty()) {
    				continue;
    			}
    			for(Application application : applications) {
    				if(!"rejected".equals(application.getStatus())) {
    					continue;    				
    				}
    				List<Job> jobs = application.getJobs();
    				if(jobs == null || jobs.isEmpty()) {
    					continue;
    				}
    				for(Job job : jobs) {
    					System.out.println("candidate "+ candidate.getId() + " was rejected from job " +job.getId());
    				}
    				if(SocialMediaAddresses.hasFaceBook(candidate.getSocialMediaAddresses())) {
    					System.out.println("candidate "+ candidate.getId() + " has Facebook ");
    				}
    			}
    			
    		}
    		
    	}
    	
	}

	

}