#!/bin/sh
curl http://gwyneddchase-lansdale.rhcloud.com/ > logfilcron.log
num=$(wc -c logfilcron.log | awk '{print $1}')
echo $num
if [ $num -lt 500 ]; then
ssh 578e1d4f2d5271d7a0000116@gwyneddchase-lansdale.rhcloud.com  << EOF
cd ~/app-root/data/apache-tomcat-8.5.16/bin/
export JAVA_HOME=/etc/alternatives/java_sdk_1.8.0
export PATH=$JAVA_HOME/bin:$PATH
./shutdown.sh
killall -9 java
killall -9 httpd
./startup.sh
EOF
fi
