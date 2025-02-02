package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	// 전체가져오기
	public List<BoardVo> boardSelect() {
		System.out.println("BoardDao.boardSelect()");

		List<BoardVo> boardList = sqlSession.selectList("board.selectList");

		// System.out.println(personList);

		return boardList;
	}
	
	public List<BoardVo> searchBoard(int startRow, int pageSize, String keyword) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("startRow", startRow);
	    params.put("pageSize", pageSize);
	    params.put("keyword", keyword);  // keyword도 추가

	    return sqlSession.selectList("board.searchBoard", params);  // Map 전달
	}
	
	public List<BoardVo> exeList(int startRow, int pageSize) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("startRow", startRow);
	    params.put("pageSize", pageSize);
	    return sqlSession.selectList("board.exeList", params);
	}

	public int countTotalPosts() {
	    return sqlSession.selectOne("board.countTotalPosts");
	}
	
	public int countTotalPosts(String keyword) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("keyword", keyword);
	    return sqlSession.selectOne("board.countTotalPostsByKeyword", params);
	}
	
	// 게시판등록
	public int boardInsert(BoardVo boardVo) {

		int count = sqlSession.insert("boardInsert", boardVo);

		return count;
	}

	// 게시판등록2
	public int boardInsert2(Map<String, String> bMap) {
		System.out.println("boardDao.boardInsert2()");
		System.out.println(bMap);

		int count = sqlSession.insert("boardDao.boardInsert2", bMap);

		return count;
	}

	// 1개 가져오기
	public BoardVo boardSelectOne(int bno) {
		System.out.println("BoardDao.boardSelectOne()");

		BoardVo boardVo = sqlSession.selectOne("board.selectOne", bno);
		System.out.println(boardVo);
		return boardVo;
	}

	// 1개 가져오기2
	public Map<String, Object> boardSelectOne2(int bno) {
		System.out.println("BoardDao.boardSelectOne()");

		Map<String, Object> pMap = sqlSession.selectOne("board.selectOne2", bno);
		// System.out.println(pMap);
		// System.out.println(pMap.get("name"));

		return pMap;
	}

	// 수정
	public int boardModify(BoardVo boardVo) {
		System.out.println("BoardDao.boardModify()");

		int count = sqlSession.update("board.boardUpdate", boardVo);

		return count;

	}

	// 삭제
	public int boardDelete(int bno) {

		int count = sqlSession.delete("board.boardDelete", bno);
		// System.out.println(count);

		return count;
	}

}
