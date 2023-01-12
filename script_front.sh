#!/bin/bash

start() {
   cd /home/ubuntu/PAW/proiect-paw-tadam/Frontend_OceanCross
   npm install
   npm start   
#ng serve --host 0.0.0.0 > /dev/null 2>&1 &
}

stop() {
   pid=$(ps -ef | grep 'ng start' | grep -v grep | awk '{print $2}')
   if [ -z "$pid" ]
   then
       echo "Service is not running."
   else
       kill -9 $pid
   fi
}

case "$1" in
   start)
       start
       ;;
   stop)
       stop
       ;;
   restart)
       stop
       start
       ;;
   *)
       echo "Usage: $0 {start|stop|restart}"
esac
