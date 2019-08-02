<?php
	/* mysql_connect('localhost', 'mysql_user', 'mysql_password'); */
	$con = mysqli_connect('localhost', 'woochiwon_admin', '!rlska06!');
	
	mysql_query("set session character_set_connection=utf8;");
	mysql_query("set session character_set_results=utf8;");
	mysql_query("set session character_set_client=utf8;");

	if($con){		
		//sql Injection 
		//$_POST = array_map('mysql_escape_string', $_POST);
		//$_GET=array_map('mysql_escape_string', $_GET);
		
		
/* 		$userID=null;
		if(isset($_GET["userID"])){
			$userID =$_GET["userID"];
				
		}else if(isset($_POST["userID"])){
			$userID =$_POST["userID"];
			
		} */		
		
		$user_email = $_POST["email"];
		
		$statement = "delete from woochiwon.user where email = '$user_email' " ;
		mysql_query($statement);
			
		$response = array();
		$response["success"] =true;
			
		echo json_encode($response);
		
		
	/* 	$statement="select * from braverokmc2.USER where userID ='$userID' ";
		$result=mysql_query($statement);
		$row =mysql_fetch_array($result);
		//셀렉트로 검색해서 아이디가 존재하면 삭제 후 succs 반환
		if(isset($row[0])){
			
			$statement = "delete from braverokmc2.USER where userID = '$userID' " ;
			$result=mysql_query($statement);
			
			$response=array();
			$response["success"] =true;
			
			echo json_encode($response);
			
		}else{
			
			echo "error";
		} */
		/* $response["success"] =true;
			
		echo json_encode($response); */
	}
	

?>