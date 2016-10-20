#!/usr/bin/expect -f
set password laijie
spawn scp classes/artifacts/F_measure/F_measure.jar root@192.168.10.101:/opt/sparkAPP
set timeout 300
expect "root@192.168.10.101's password:"
set timeout 300
send "$password\r"
spawn ssh root@192.168.10.101 /opt/hadoop/spark/bin/spark-submit --class detected_community.realcommunity --master spark://master1.jie.com:7077 /opt/sparkAPP/F_measure.jar
set timeout 300
expect "root@192.168.10.101's password:"
set timeout 300
send "$password\r"
set timeout 300
send "exit\r"
expect eof

