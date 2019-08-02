<?php
	/* mysql_connect('localhost', 'mysql_user', 'mysql_password'); */
	$con = mysqli_connect('localhost', 'woochiwon_admin', '!rlska06!');
	
	mysql_query("set session character_set_connection=utf8;");
	mysql_query("set session character_set_results=utf8;");
	mysql_query("set session character_set_client=utf8;");
	mysql_set_charset("SET NAMES utf8");
	
	if($con){
	
		//echo "연결";

		//Injection 방어
		$_POST = array_map('mysql_escape_string', $_POST);
		$_GET = array_map('mysql_escape_string', $_GET);
		
		if( isset($_POST["email"]) ){
			$user_seq = $_POST["seq"];
			$user_email = $_POST["email"];
			$user_password = $_POST["password"];
			$user_nickname = $_POST["nickname"];
			$user_location = $_POST["location"];
		}
		
		else if( isset($_GET["userID"]) ){
			$user_seq = $_GET["seq"];
			$user_email = $_GET["email"];
			$user_password = $_GET["password"];
			$user_nickname = $_GET["nickname"];
			$user_location = $_GET["location"];
		}

	/* 	//한글 인코딩 확인 후 utf8 아니 면 utf8 로 변경
		$enc=mb_detect_encoding($userName, array("UTF-8", "EUC-KR", "SJIS"));
		if($userName !="UTF-8"){
			$userName=iconv($enc, "UTF-8", $userName);
		}	
		
		 *
		 *
		 */
		$stmt= "insert into mysql.woochiwon_user values ( '$email', '$password', '$nickname' , $location  ) ";
		mysql_query($stmt);
		
		
		
	/* 	mysqli_stmt_bind_param($statement, "sssi", $userID, $userPassword, $userName, $userAge);
		mysqli_stmt_execute($statement);
		 */
		$response = array();
		$response["success"] = true;
		
		//echo $userName;
		echo json_encode($response);
	}

?>