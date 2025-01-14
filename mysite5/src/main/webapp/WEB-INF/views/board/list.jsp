<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<!-- header -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>


		<div id="container" class="clearfix">
			<div id="aside">
				<h2>게시판</h2>
				<ul>
					<li><a href="">일반게시판</a></li>
					<li><a href="">댓글게시판</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>일반게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="board">
					<div id="list">
						<form action="${pageContext.request.contextPath}/board/listform/search" method="get">
							<div class="form-group text-right">
								<input type="text" name="keyword" placeholder="검색어를 입력하세요" required>
								<button type="submit" id="btn_search">검색</button>
							</div>
						</form>

						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>
							<c:forEach items="${ requestScope.selectBoardList }" var="boardVo" varStatus="status">
							<tbody>
								<tr>
									<td>${ boardVo.bno }</td>
									<td class="text-left"><a href="${pageContext.request.contextPath}/board/readform?bno=${ boardVo.bno }">${ boardVo.title }</a></td>
									<td>${ boardVo.writer }</td>
									<td>${ boardVo.hit }</td>
									<td>${ boardVo.reg_date }</td>
									<td><a href="${pageContext.request.contextPath}/board/delete?bno=${ boardVo.bno }">[삭제]</a></td>
								</tr>

							</tbody>
							</c:forEach>
						</table>

						<div id="paging">
							<ul>
								<c:if test="${currentPage > 1}">
									<a href="${pageContext.request.contextPath}/board/listform?cPage=${currentPage - 1}&numPerPage=${numPerPage}">이전</a>
								</c:if>
							
							    <c:forEach begin="1" end="${totalPages}" var="page">
							    	<c:choose>
							        	<c:when test="${page == currentPage}">
							            	<strong>${page}</strong> <!-- 현재 페이지 강조 -->
										</c:when>
										<c:otherwise>
							        		<a href="${pageContext.request.contextPath}/board/listform?cPage=${page}&numPerPage=${numPerPage}">${page}</a>
							        	</c:otherwise>
									</c:choose>
								</c:forEach>
							
							    <c:if test="${currentPage < totalPages}">
							    	<a href="${pageContext.request.contextPath}/board/listform?cPage=${currentPage + 1}&numPerPage=${numPerPage}">다음</a>
								</c:if>
							</ul>

							<div class="clear"></div>
						</div>
						<a id="btn_write" href="${pageContext.request.contextPath}/board/writeform">글쓰기</a>

					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->


		<!-- footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
