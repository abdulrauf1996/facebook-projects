<?php
   $conn = mysqli_connect('13.76.209.146','root','baabteadmin123!','Rauf_Facebook');
   if(isset($_REQUEST['email']) )
   {

     $user_email= $_REQUEST['email'];

     $result = mysqli_query($conn,"select first_name from tbl_registration where Email_id='$user_email'");

     if(mysqli_num_rows($result)==1)
     {
       $rw[]=mysqli_fetch_assoc($result);
       $rw[0]['ResponseCode']="200";
       $rw[0]['Msg']="Success";
 	   echo json_encode($rw);
     }

     else
     {
       $rw[]=mysqli_fetch_assoc($result);
       $rw[0]['ResponseCode']="500";
       $rw[0]['Msg']="Error occured";
       echo json_encode($rw);
     }
   }

   else
    {
   	  $er = array("ResponseCode"=>"500","Msg"=>"doesnot match any names");
      echo json_encode($er);
    }

?>