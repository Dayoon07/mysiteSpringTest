package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao dao;
	
	public List<GalleryVo> imgSelect() {	
		return dao.imgSelect();
	}
	
	public void imgUpload(String filename) {
		dao.imgUpload(filename);
	}
	
	
	
}
