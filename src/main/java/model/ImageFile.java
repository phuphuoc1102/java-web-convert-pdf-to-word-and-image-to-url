package model;

import java.util.UUID;

public class ImageFile {
	private UUID id;
	private String username;
	private String url;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ImageFile(UUID id, String username, String url) {
		super();
		this.id = id;
		this.username = username;
		this.url = url;
	}
	
}
