package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> imgSelect() {
		return sqlSession.selectList("gallery.imgSelect");
	}
	
	public void imgUpload(String filename) {
		sqlSession.insert("gallery.imgUpload", filename);
	}
	
}
