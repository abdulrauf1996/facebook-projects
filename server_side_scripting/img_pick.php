<?php
require_once 'db_connect.php';
$con=mysqli_connect(HOST,USER,PASS,DB);

	if(isset($_REQUEST['email']))
	{
		
		$eml=$_REQUEST['email'];
		$qry="select pk_int_reg_id from tbl_registration where Email_id='$eml'";
		$res=mysqli_query($con,$qry);
		$row=mysqli_fetch_assoc($res);
		$id=$row['pk_int_reg_id'];
		
	

 	if ($res==1) 
	{
		$query="select img_path from tbl_image_uploading where fk_int_id='$id'";

		$result=mysqli_query($con, $query);
		
	
		$items = array();
		while($row1=mysqli_fetch_array($result))
		{
			$items[]=$row1['img_path'];
			
			
		}
		$rw[]['Msg']=$items;
		$rw[]['Success']="200";
  		
 		echo json_encode($rw);
		
	
		
	}
	else
	{
		$fail=array('responsecode'=>'500','msg'=>'failed');
		$fail_data=json_encode($fail);
		echo $fail_data;
	}
	}

?>
