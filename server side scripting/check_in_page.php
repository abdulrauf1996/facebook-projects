<?php
 $conn = mysqli_connect('13.76.209.146','root','baabteadmin123!','Rauf_Facebook');
 if(isset($_REQUEST['Location']) && isset($_REQUEST['Status']))
 {
 	$loc=$_REQUEST['Location'];
 	$sts=$_REQUEST['Status'];

 	$querry="insert into tbl_check_in (location,status) values ('$loc','$sts')";

	 $result=mysqli_query($conn,$querry);
		

	if ($result==1)
	{
		
	
        echo ".......";
		$Success = array('ResponseCode' =>'200','Msg'=>'Success');
		$Successdata=json_encode($Success);
		echo $Successdata;
		
	}
	else
	{
		$fail = array('ResponseCode' =>'500','Msg'=>'Check_in Failed' );
		$faildata=json_encode($fail);
		echo $faildata;

		
	}
	mysqli_close($con);

}
else
{
	$data = array('ResponseCode' =>'500','Msg'=>'Fields are not defined');
	$js=json_encode($data);
	echo $js;
		
}
}





?>	
