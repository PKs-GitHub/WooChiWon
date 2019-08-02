<?php
	/* mysql_connect('localhost', 'mysql_user', 'mysql_password'); */
	$con = mysqli_connect('localhost', 'woochiwon_admin', '!rlska06!');
	
	mysql_query("set session character_set_connection=utf8;");
	mysql_query("set session character_set_results=utf8;");
	mysql_query("set session character_set_client=utf8;");
	mysql_set_charset("utf8");//sql 에서 한글 깨짐
	
	$stmt = "select * from woochiwon.user";
	$rs = mysql_query($stmt, $con);
	$response = array();
	
	while( $row = mysql_fetch_array($rs) ){
		
		array_push(
			$response, 
			array(
				"user_seq"=> $row[0],
				"user_email"=>$row[1],
				"user_password"=>$row[2],
				"nickname"=>$row[3],
				"location" =>$row[4]
			) 
		);
	}
	
	echo json_encode(array("response"=>$response));
	
	mysql_close($con);
?>