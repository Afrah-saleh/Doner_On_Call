<?php
$db = new SQLite3('../DonorOnCall_db.sqlite3');
$version = $db->querySingle('SELECT name from test where id=1');
echo $version . "\n";
?>