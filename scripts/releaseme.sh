#!/bin/sh

version=$1
if [ -z "$1" ]
  then
    echo "No argument supplied"
    exit 1
fi
ssh 578e1d4f2d5271d7a0000116@gwyneddchase-lansdale.rhcloud.com  << EOF
cd ~/app-root/data/apache-tomcat-8.5.16/bin/
./shutdown.sh
killall -9 httpd
killall -9 java
cd ../webapps/
rm ROOT.war
rm -Rf ROOT
wget "https://github.com/amondel2/hoa/releases/download/"$version"/ROOT.war"
cd ../bin/
export JAVA_HOME=/etc/alternatives/java_sdk_1.8.0
export PATH=$JAVA_HOME/bin:$PATH
./startup.sh
EOF

