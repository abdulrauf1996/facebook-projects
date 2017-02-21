<?php
 $conn = mysqli_connect('13.76.209.146','root','baabteadmin123!','Rauf_Facebook');
 if(isset($_REQUEST['email']))
 {
 	$eml=$_REQUEST['email'];
 	$query="select pk_int_reg_id from tbl_registration where Email_id='$eml'";
	$result=mysqli_query($conn,$query);
	$row=mysqli_fetch_assoc($result);
	$value=$row['pk_int_reg_id'];
	print_r($value);
	
	if ($result==1)
	{
		$qr="select Status from tbl_posting where fk_int_reg_id='$value'";
		$res=mysqli_query($conn,$qr);

		$items = array();
		while($row1=mysqli_fetch_array($res))
		{
			$items['Status']=$row1['Status'];
		}
		
		$rw[0]['Msg']=$items;
		$rw[0]['Success']="200";
  		
 		echo json_encode($rw);
	}
	else
	{
		$rw[0]['response']="500";
		$rw[0]['Msg']="error";
  		
 		echo json_encode($rw);
	}

}

?>