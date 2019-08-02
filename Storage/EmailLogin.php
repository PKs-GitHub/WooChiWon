//https://www.popit.kr/spring-security-oauth2-%EC%86%8C%EC%85%9C-%EC%9D%B8%EC%A6%9D-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4-%EC%A0%80%EC%9E%A5

<?php
	/* mysql_connect('localhost', 'mysql_user', 'mysql_password'); */
	$con = mysqli_connect('localhost', 'woochiwon_admin', '!rlska06!');
	
	mysql_query("set session character_set_connection=utf8;");
	mysql_query("set session character_set_results=utf8;");
	mysql_query("set session character_set_client=utf8;");
	mysql_set_charset("utf8");//sql 에서 한글 깨짐
	
	if($con==true) {
		
		$_POST = array_map('mysql_escape_string', $_POST);
		$_GET = array_map('mysql_escape_string', $GET);
		
		if(isset($_GET["email"])){
			$user_email = $_GET["email"];
			$user_password = $_GET["password"];
			
		}else if(isset($_POST["email"])){
			$user_email = $_POST["email"];
			$user_password = $_POST["password"];
		}

		
		if(isset($user_email) && isset($user_password)){
			
				
				$stmt = "select * from mysql.woochiwon_user where `email` = '$user_email' and `password` = '$user_password' ";
				$rs = mysql_query($stmt, $con);
				
				if($rs === FALSE) {
					die(mysql_error()); // TODO: better error handling
				}
				
			
	/* 			
				echo "<table border='1' >";
				echo
					"<tr>
				<th>번호</th>
				<th>이메일</th>
				<th>패스워드</th>
				<th>닉네임</th>
				<th>지역(null)</th>
				</tr>
				"; */
					
				$response = array();
				$response["success"] = false;
				
			if($row=mysql_fetch_array($rs)){
				
				$response["success"] = true;
				$response["user_sqe"] = $row[0];
				$response["user_email"] = $row[1];
				$response["user_password"] = $row[2];
				$response["user_nickname"] = $row[3];
				$response["user_location"] = $row[4];
			/* 	echo "
				<tr>
				<td>$row[email]</td>
				<td>$row[password] </td>
				<td>$row[userName] </td>
				<td>$row[userAge]</td>
				<td></td>
					
				</tr>"; */
				
			}
			
			
			echo json_encode($response);

			mysql_close();
		}
		

	}else{
		//echo "no - 연결 실패 하였습습니다.";
	}
?>
			
			