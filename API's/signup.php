<?php
$email= $_POST['email'];
$pass=$_POST['password'];
$name=$_POST['name'];
$username=$_POST['username'];
$pno=(double)$_POST['pno'];
$bloodgroup=$_POST['bloodgroup'];
$dbc = mysqli_connect('mysql.hostinger.in', 'u684030433_root', 'fastrack', 'u684030433_blood')
or
die("error connecting database");
$query = "SELECT * FROM user_info WHERE (username = '$username' OR email='$email' OR pno='$pno')";
      $data = mysqli_query($dbc, $query);
      if (mysqli_num_rows($data) == 0) {
$query = "INSERT INTO user_info (username,password,email,pno,bloodgroup,name)  VALUES ('$username', '$pass', '$email','$pno','$bloodgroup','$name')";
  mysqli_query($dbc, $query)
    or die('Error querying database.');
   echo 'User Signed Up successfully!!.';
}
else
{
echo "Account already exists for this credentials...";
}
  mysqli_close($dbc);

?>