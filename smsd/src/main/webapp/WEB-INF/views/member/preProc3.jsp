<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
<body>
	<div class="wrapper">
		<!-- mast head start -->
		<div id="mast-head">
			<div class="container">

				<h1 class="title" id="mastHeadTitle">오류페이지</h1>

			</div>
		</div>
		<!-- mast head end -->
		<div id="mast-body">

			<div id="infoDiv" style="">
				<div class="container">
					<div class="section __half __zero __wide">
						<h3 class="subject __underline">
							<em>정보수정에 실패 하였습니다.</em>
						</h3>
						<div class="container">						
							<h1 class="description">5. 서버 오류 일 수 있으니 고객센터에 문의해 주세요.</h1><br>			
						</div>
						<h3 class="subject __underline">
							
						</h3>	

					</div>
					<form class="form-horizontal" action="./회원가입.html" method="post"
						onsubmit="return send()" name="frm">
						<div class="section __half __center">
							<button type="button" class="ui-button __square-large __black"
								onclick="history.back()">이전</button>
							<button type="submit" class="ui-button __square-large __blue"
								>홈</button>
						</div>
						</form>
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>