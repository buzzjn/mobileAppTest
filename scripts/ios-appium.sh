#!/bin/bash

#This script checks some basic requirenments for running appium node server.
#And if the req are fulfilled the appium server is started.

isSDK=`xcodebuild -showsdks | grep iphoneos`
echo  "check for SDK installed"
if [ ! -n "$isSDK" ]
then
   echo "ios SDK not found"
   exit -1
 else echo "iphone SDK found: " "$isdSDK"
fi

isNODE=`which node`

if [ ! -n "$isNODE" ]
then
   echo "node not found"
   exit -1
 else echo "node found: " "$isNODE"
fi

isAPPIUM=`which appium`
if [ ! -n "$isAPPIUM" ]
then
   echo "appium not found"
   exit -1
 else echo "appium found: " "$isAPPIUM"
fi

runAPPIUM="/usr/local/bin/appium"
outAPPIUM="/tmp/appium.out"

case "$1" in
  start)
      PID=`pgrep node`
      if [ ! -z "$PID" ]
      then
      echo "appium node already running on port $PID"
      exit -1
      fi
      echo "Starting appium node ..."
      $runAPPIUM>${outAPPIUM}&
      echo "appium node running with PID $!"
      sleep 1
      RETVAL=$?
    ;;
  stop)
       PID=`pgrep node`
       if [ -z "$PID" ]
       then
          echo "appium node is not running"
          exit -1
       fi
       echo "Stopping appium node process with PID $PID ..."
       pkill node
       sleep 1
       PID=`pgrep node`
       if [ ! -z "$PID" ]
       then
          echo "Could not stop appium node, still running with PID $PID"
          exit -1
       fi
      ;;
    status)
       PID=`pgrep node`
       if [ ! -z "$PID" ]
       then
          echo "appium node running on port $PID"
       else
          echo "appium node is not running"
       fi
       ;;
*)
  echo $"Usage: $0 (start|stop|status)"
     exit 1
esac



