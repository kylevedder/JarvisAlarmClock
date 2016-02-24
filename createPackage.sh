#!/bin/bash
rm -rf package/*.jar
cd PiClockWebServer
mvn clean install
cd ..
cp PiClockWebServer/target/*.jar ./package/JarvisWebServer.jar
cd PiClockJob
mvn clean install
cd ..
cp PiClockJob/target/*dependencies.jar ./package/JarvisJob.jar
tar -cvf package.tar package/
