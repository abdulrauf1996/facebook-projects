<?php

$con = mysqli_connect('13.76.213.131','root','baabteadmin123!','Rauf_Facebook');

if($con !=null)
{

if( isset($_REQUEST['FirstName'])&&isset($_REQUEST['Surname'])&&isset($_REQUEST['Email_Phone'])&&isset($_REQUEST['Gender'])&&isset($_REQUEST['Date'])&&isset($_REQUEST['Month'])&&isset($_REQUEST['Year'])&&isset($_REQUEST['Password']))
{
	$fname=$_REQUEST['FirstName'];
	$sname=$_REQUEST['Surname'];
	$email=$_REQUEST['Email_Phone'];
	$gender=$_REQUEST['Gender'];
	$date=$_REQUEST['Date'];
    $mnth=$_REQUEST['Month'];
    $yr=$_REQUEST['Year'];
	$pswd=$_REQUEST['Password'];

	$querry="insert into tbl_registration (first_name,surname,Email_id,Gender,Date,Month,Year,Password) values ('$fname','$sname','$email','$gender',$date,'$mnth','$yr,'$pswd')";

	 $result=mysqli_query($con,$querry);
		

	if ($result==1)
	{
		
	
        echo ".......";
		$Success = array('ResponseCode' =>'200','Msg'=>'Success');
		$Successdata=json_encode($Success);
		echo $Successdata;
		
	}
	else
	{
		$fail = array('ResponseCode' =>'500','Msg'=>'Registration Failed' );
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
else
{
echo "not connected";
}



?>	