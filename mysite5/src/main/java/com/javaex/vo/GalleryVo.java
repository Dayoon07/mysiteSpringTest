package com.javaex.vo;

public class GalleryVo {
	private int galleryId;
	private String writer;
	private String filename;
	
	public GalleryVo() {
		super();
	}
	
	public GalleryVo(int galleryId, String writer, String filename) {
		super();
		this.galleryId = galleryId;
		this.writer = writer;
		this.filename = filename;
	}

	public int getGalleryId() {
		return galleryId;
	}
	public void setGalleryId(int galleryId) {
		this.galleryId = galleryId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	
}
