<?php
$skiers = file_get_contents('http://localhost:9999/MyServer/skiers/alljson');
print "All the skiers in json string\n".$skiers;
print "\n\n";
$skiers = file_get_contents('http://localhost:9999/MyServer/skiers?age=41');
print "Get object from json string and print only the name attributes: \n";
$skiers = json_decode(file_get_contents('http://localhost:9999/MyServer/skiers/alljson'));
foreach ($skiers->skiers as $skier) {
    print "- ".$skier->name ."\n";
}

