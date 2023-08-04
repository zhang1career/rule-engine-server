#!/bin/sh

SERVER=$(cd `dirname $0`; pwd)
RUNTIME=$SERVER/../../

cd $SERVER/../
JAR_NAME=`ls *.jar`

cd $SERVER

start(){
    echo "==================== app start ===================="
    java -Xmx512m -Xms512m -jar $SERVER/../*.jar > $RUNTIME/info.log &
    echo $! > $RUNTIME/server.pid
    echo "+++++++++++++++++++++ started +++++++++++++++++++++"
}

stop(){
    echo "-------------------- app stop --------------------"
    kill -15 `cat $RUNTIME/server.pid`
    rm -rf $RUNTIME/server.pid
    echo "==================== stopped ====================="
}

restart(){
    stop
    sleep 2
    start
}

case "$1" in
start)
    start;;
stop)
    stop;;
restart)
  restart;;
*)
  echo "please give an option:"
  echo "start"
  echo "restart"
  echo "stop"
  ;;
esac
