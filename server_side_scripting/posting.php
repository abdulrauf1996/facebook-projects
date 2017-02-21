<?php
 $conn = mysqli_connect('13.76.209.146','root','baabteadmin123!','Rauf_Facebook');
 if(isset($_REQUEST['Status']))
 {
 	
 	$sts=$_REQUEST['Status'];
        if(empty($sts))
        {
          $fail = array('ResponseCode' =>'500','Msg'=>'Status posting is failed!!!!!!!' );
		$faildata=json_encode($fail);
		echo $faildata;

	}
	else
	{

 	$querry="insert into tbl_posting (Status) values ('$sts')";

	 $result=mysqli_query($conn,$querry);
		

	if ($result==1)
	{
		
	
       
		$Success = array('ResponseCode' =>'200','Msg'=>'Success');
		$Successdata=json_encode($Success);
		echo $Successdata;
		
	}
	}	
	mysqli_close($con);

}
else
{
	$data = array('ResponseCode' =>'500','Msg'=>'Some fields are missing');
	$js=json_encode($data); 
	echo $js;
		
}






?>	