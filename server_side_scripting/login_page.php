<?php
$conn = mysqli_connect('13.76.213.131','root','baabteadmin123!','Rauf_Facebook');

if(isset($_REQUEST['uname']) && isset($_REQUEST['pwd']))
{

$user_email = $_REQUEST['uname'];
$user_password = $_REQUEST['pwd'];

 //$query = "select email,password from Fb_registration where email='$user_email'and password='$user_password'";

 $result = mysqli_query($conn,"select Email_id,Password from tbl_registration where Email_id='$user_email'and Password='$user_password'");

 if(mysqli_num_rows($result)==1)
 {
  $rw[]=mysqli_fetch_assoc($result);
  $rw[0]['ResponseCode']="200";
  $rw[0]['Msg']="Success";
 	echo json_encode($rw);
 }

else
 {
  $query = "select Email_id,Password from tbl_registration where Email_id='$user_email'";
  $result = mysqli_query($conn,$query);

  if(mysqli_num_rows($result)>0)
  {
  	$rw[]=mysqli_fetch_assoc($result);
    $rw[0]['ResponseCode']="500";
    $rw[0]['Msg']="Password Incorrect!";
  	echo json_encode($rw);
  }

   else
  {
    $er = array("ResponseCode"=>"404","Msg"=>"Email id does not exist");
   	echo json_encode($er);
  }

 }
}

else
{
  $er = array("ResponseCode"=>"500","Msg"=>"Email or password is not defined");
  echo json_encode($er);
}



?>