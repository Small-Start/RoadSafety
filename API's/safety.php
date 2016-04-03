<?php
$lat1="";
$lat2="";
$lng1="";
$lng2="";
 function distance($lat1, $lng1, $lat2, $lng2) {
  $theta = $lng1 - $lng2;
  $dist = sin(deg2rad($lat1)) * sin(deg2rad($lat2)) +  cos(deg2rad($lat1)) * cos(deg2rad($lat2)) * cos(deg2rad($theta));
  $dist = acos($dist);
  $dist = rad2deg($dist);
  $miles = $dist * 60 * 1.1515;
    return ($miles * 1.609344*1000);
}
if($_GET['username'])
{
$username=$_GET['username'];
$lat1=(double)$_GET['lat'];
$lng1=(double)$_GET['lng'];
$dbc = mysqli_connect('localhost', 'root', '', 'roadsafety');

  // Retrieve the score data from MySQL
$query = "UPDATE user_info SET lat='$lat1',lng='$lng1' WHERE (username = '$username')";
  mysqli_query($dbc, $query)
    or die('Error querying database.');
 $query = "SELECT lat,lng FROM user_info WHERE username='$username'";
  $data = mysqli_query($dbc, $query);
 
  while ($row = mysqli_fetch_array($data)) { 
    $lat1= $row['lat'];
   $lng1= $row['lng'];
   
  }
  
$query1 = "SELECT * FROM details";
  $result = mysqli_query($dbc, $query1);
 $i=0;
$data1=array();
  while ($row = mysqli_fetch_array($result, MYSQL_ASSOC)) { 
    $lat2= $row['lat'];
   $lng2= $row['lng'];
$dist=distance($lat1, $lng1, $lat2, $lng2);
if( $dist<=990)
{
$data1[] = $row;
  $data1[$i]['metres']=$dist;
$i++;
}
  }
echo json_encode($data1);

  mysqli_close($dbc);


}
?>
