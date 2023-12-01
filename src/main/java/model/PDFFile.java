package model;

import java.util.UUID;

public class PDFFile {
	private UUID id;
	private String username;
	private String path;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public PDFFile(UUID id, String username,String path ) {
		super();
		this.id = id;
		this.username = username;
		this.path = path;
	}

}

