<?php
require_once 'db_connect.php';

function getFileName()
{
	$con=mysqli_connect(HOST,USER,PASS,DB);
	$query="select max(pk_int_img_id) as id from tbl_image_uploading";
	$result=mysqli_query($con, $query);
	$id=0;
	while($fetch=mysqli_fetch_array($result)){

	if ($fetch['id']==null) 
		$id= 1;
	else 
		$id= ++$fetch['id'];
}
return $id;


}
?>