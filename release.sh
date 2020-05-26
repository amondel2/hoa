#!/bin/bash

rm build/libs/ROOT.war
mv build/libs/hoa*.war build/libs/ROOT.war
sftp -i ~/Downloads/LightsailDefaultKey-us-east-1.pem ubuntu@18.235.199.83 <<END_SCRIPT
cd files/
put /home/aaron/IdeaProjects/hoa/build/libs/ROOT.war
quit
END_SCRIPT
ssh -i /home/aaron/Downloads/LightsailDefaultKey-us-east-1.pem ubuntu@18.235.199.83 <<END_SCRI
sudo su
/etc/init.d/tomcat8 stop
cd /var/lib/tomcat8/
rm -Rf work/Catalina/*
rm -Rf webapps/*
rm -Rf logs/*
mv /home/ubuntu/files/ROOT.war webapps/
/etc/init.d/tomcat8 start
iptables -A PREROUTING -t nat -i eth0 -p tcp --dport 80 -j REDIRECT --to-port 8080
END_SCRI
exit
exit
