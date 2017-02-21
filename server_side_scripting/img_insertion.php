<?php
$con = mysqli_connect('13.76.209.146','root','baabteadmin123!','Rauf_Facebook');
//require_once 'db_connect.php';
require_once 'getFile.php';
$upload_path='uploads/';

$server_ip= $_SERVER['SERVER_NAME'];
echo $server_ip;
$upload_url='http://'.$server_ip.'/BM-41776/'.$upload_path;
echo "haaai";
if ($_SERVER['REQUEST_METHOD']=='POST') {
	
	if (isset($_POST['name']) && isset($_FILES['image']['name'])&& isset($_REQUEST['email'])) {

	
		

	
		
		$name=$_POST['name'];
		$eml=$_REQUEST['email'];

		$fileinfo=pathinfo($_FILES['image']['name']);
		$extension=$fileinfo['extension'];
    
		$file_url=$upload_url.getFileName().'.'.$extension;
		echo $file_url;
		$file_path=$upload_path.getFileName().'.'.$extension;
		echo $file_path;
	    if (!move_uploaded_file($_FILES['image']['tmp_name'], $file_path)) {
				echo "Files are not moved";
			}
			else
			{
				echo " files are moved";
			}

			if (empty($name)||empty($file_url)||empty($eml)) 
			{
				$fail_data = array('ResponseCode' =>'500','Msg'=>'Error');
				$failed_data=json_encode($fail_data);
				echo $failed_data;
		
			}
			else
			{
				$query="select pk_int_reg_id from tbl_registration where Email_id='$eml'";
				$res=mysqli_query($con,$query);
			

				if ($res==1)
				{
		
					$row=mysqli_fetch_assoc($res);
					$id=$row['pk_int_reg_id'];
					print_r($id);

					$qry="insert into tbl_image_uploading (img_name,img_path,fk_int_id) values ('$name','$file_url',$id)";

					$result=mysqli_query($con,$qry);

					
						$Success = array('ResponseCode' =>'200','Msg'=>'Success');
						$Successdata=json_encode($Success);
						echo $Successdata;
					

	
	    		
				}

      	    }
      	   

      	 
	

   } 

 }

else
{	
	$fail = array('ResponseCode' =>'504','Msg'=>'Unable to do something');
	$fail_data=json_encode($fail);
	echo $fail_data;
}
mysqli_close($con);


?>