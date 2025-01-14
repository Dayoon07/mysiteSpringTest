package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	// 필드
	@Autowired
	private BoardService boardService;

	// =============== board/form controller 모음
	// ==============================================
	// 보드리스트폼
	@RequestMapping(value = "/listform", method = { RequestMethod.GET, RequestMethod.POST })
	public String listForm(Model model,
							@RequestParam(defaultValue = "1") int cPage,
							@RequestParam(defaultValue = "10") int numPerPage) {

	    int offset = (cPage - 1) * numPerPage;

	    // 전체 게시글 수 조회
	    int totalPosts = boardService.countTotalPosts();
	    // 전체 페이지 수 계산
	    int totalPages = (int) Math.ceil((double) totalPosts / numPerPage);

	    // 페이징에 맞는 게시글 리스트 조회
	    List<BoardVo> selectBoardList = boardService.exeList(offset, numPerPage);

	    // 모델에 데이터 추가
	    model.addAttribute("selectBoardList", selectBoardList);
	    model.addAttribute("currentPage", cPage);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("numPerPage", numPerPage);

	    return "/board/list"; // 결과 반환
	}

	@RequestMapping(value = "/listform/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchListForm(Model model,
	                             @RequestParam(defaultValue = "1") int cPage,
	                             @RequestParam(defaultValue = "10") int numPerPage,
	                             @RequestParam String keyword) {

	    int offset = (cPage - 1) * numPerPage;

	    // 전체 게시글 수 조회
	    int totalPosts = boardService.countTotalPosts(keyword);  // keyword 추가
	    // 전체 페이지 수 계산
	    int totalPages = (int) Math.ceil((double) totalPosts / numPerPage);

	    // 페이징에 맞는 게시글 리스트 조회
	    List<BoardVo> selectBoardList = boardService.searchBoard(offset, numPerPage, keyword);

	    model.addAttribute("searchResult", selectBoardList);
	    model.addAttribute("searchResultCurrentPage", cPage);
	    model.addAttribute("searchResultTotalPages", totalPages);
	    model.addAttribute("searchResultNumPerPage", numPerPage);
	    model.addAttribute("keyword", keyword);
	    return "/board/searchList";
	}

	// 보드수정폼
	@RequestMapping(value = "/modifyform", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam(value = "bno") int bno, Model model) {
		System.out.println("BoardController.modifyform");

		BoardVo boardVo = boardService.exeModifyForm(bno);

		model.addAttribute("bVo", boardVo);

		return "/board/modifyForm";
	}

	// 수정폼2
	// localhost:8080/phonebook5/phone/modifyform
	@RequestMapping(value = "/modifyform2", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm2(@RequestParam(value = "bno") int bno, Model model) {
		System.out.println("phonebookController.modifyform2()");
		System.out.println(bno);

		Map<String, Object> pMap = boardService.exeModifyForm2(bno);
		model.addAttribute("pMap", pMap);

		return "/board/modifyForm2";

	}

	// 보드읽기폼
	@RequestMapping(value = "/readform", method = { RequestMethod.GET, RequestMethod.POST })
	public String readForm(@RequestParam(value = "bno") int bno, Model model) {
		System.out.println("BoardController.readform");

		BoardVo boardVo = boardService.exeModifyForm(bno);
		model.addAttribute("bVo", boardVo);

		return "/board/read";
	}

	// 보드등록폼
	@RequestMapping(value = "/writeform", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("BoardController.writeform");
		return "/board/writeForm";
	}

	// ==============================================================================================

	// =============== board 기능 모음
	// =================================================================

	// 게시판등록
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController.write");

		System.out.println(boardVo.toString());

		// 서비스를 메모리에 올리고
		// 서비스의 메소드 사용
		boardService.exeWrite(boardVo);

		// 리스트로 리다이렉트
		return "redirect:/board/listform";

	}

	// 게시판등록2
	@RequestMapping(value = "/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public String write2(@RequestParam(value = "title") String title, @RequestParam(value = "writer") String writer,
			@RequestParam(value = "content") String content) {
		System.out.println("BoardController.write2");

		// System.out.println(name);
		// System.out.println(hp);
		// System.out.println(company);

		// vo 묶는다
		// PersonVo personVo = new PersonVo(name, hp, company);

		boardService.exeWrite2(title, writer, content);

		// 리스트로 리다이렉트
		return "redirect:/board/listform";
	}

	// 수정
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute BoardVo boardVo) {

		System.out.println("BoardController.modify()");

		System.out.println(boardVo);

		boardService.exeModify(boardVo);

		// 리스트로 리다이렉트
		return "redirect:/board/listform";
	}

	// 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam(value = "bno") int bno) {

		System.out.println("BoardController.delete()");

		// System.out.println(personId);

		boardService.exeDelete(bno);

		// 리스트로 리다이렉트
		return "redirect:/board/listform";

	}

}
