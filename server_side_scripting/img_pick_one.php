<?php
require_once 'db_connect.php';
$con=mysqli_connect(HOST,USER,PASS,DB);

$query="select img_path from tbl_image_uploading where pk_int_img_id=4";
$result=mysqli_query($con, $query);
		
if($result==1)
{
	$row=mysqli_fetch_assoc($result);
	$img=$row['img_path'];
	$rw[]['img_path']=$row['img_path'];
	
	$rw[]['Msg']=$path;
	$rw[]['Success']="200";
  	echo "<img src='$img'>";//echo json_encode($rw);
}
else
{
		$fail=array('responsecode'=>'500','msg'=>'failed');
		$fail_data=json_encode($fail);
		echo $fail_data;
}

?>
