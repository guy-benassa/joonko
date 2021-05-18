package com.joonko.greenhouseinterview;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * A candidate applying for a job
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Candidate {
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern(DATE_FORMAT).withZoneUTC();

    private long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String company;
    private String title;
    private long createdDate;
    private long updatedDate;
    private long lastActivityDate;
    @JsonProperty("photo_url")
    private String photoUrl;
    private List<String> tags;
    @JsonProperty("custom_fields")
    private Map<String, Object> customFields;
    private List<Application> applications;
    @JsonProperty("social_media_addresses")
    private List<SocialMediaAddresses> socialMediaAddresses;

    public Candidate() {}

    @JsonSetter("created_at")
    private void setCreateDate(String createdDate) {
        if (StringUtils.isEmpty(createdDate)) {
            throw new IllegalArgumentException("Created date for application can't be empty");
        }

        this.createdDate = dateFormatter.parseMillis(createdDate);
    }

    @JsonProperty("updated_at")
    private void setUpdatedDate(String updatedDate) {
        if (StringUtils.isEmpty(updatedDate)) {
            throw new IllegalArgumentException("Updated date for application can't be empty");
        }

        this.updatedDate = dateFormatter.parseMillis(updatedDate);
    }

    @JsonSetter("last_activity")
    private void setLastActivityDate(String lastActivityDate) {
        if (StringUtils.isEmpty(lastActivityDate)) {
            throw new IllegalArgumentException("Last activity date for application can't be empty");
        }

        this.lastActivityDate = dateFormatter.parseMillis(lastActivityDate);
    }

    public long getId() {return this.id;}

    public String getFirstName() {return this.firstName;}

    public String getLastName() {return this.lastName;}

    public String getCompany() {return this.company;}

    public String getTitle() {return this.title;}

    public long getCreatedDate() {return this.createdDate;}

    public long getUpdatedDate() {return this.updatedDate;}

    public long getLastActivityDate() {return this.lastActivityDate;}

    public String getPhotoUrl() {return this.photoUrl;}

    public List<String> getTags() {return this.tags;}

    public Map<String, Object> getCustomFields() {return this.customFields;}

    public void setId(long id) {this.id = id; }

    public void setFirstName(String firstName) {this.firstName = firstName; }

    public void setLastName(String lastName) {this.lastName = lastName; }

    public void setCompany(String company) {this.company = company; }

    public void setTitle(String title) {this.title = title; }

    public void setCreatedDate(long createdDate) {this.createdDate = createdDate; }

    public void setPhotoUrl(String photoUrl) {this.photoUrl = photoUrl; }

    public void setTags(List<String> tags) {this.tags = tags; }

    public void setCustomFields(Map<String, Object> customFields) {this.customFields = customFields; }

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public List<SocialMediaAddresses> getSocialMediaAddresses() {
		return socialMediaAddresses;
	}

	public void setSocialMediaAddresses(List<SocialMediaAddresses> socialMediaAddresses) {
		this.socialMediaAddresses = socialMediaAddresses;
	}
    
    
}
