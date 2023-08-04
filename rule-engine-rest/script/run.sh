#!/bin/sh

MANAGE_DIR=$(cd `dirname $0` || return 1; pwd)

PROJ=rule-engine-rest
VERSION=1.0.0-SNAPSHOT

PROJ_DIR=$MANAGE_DIR/$PROJ

cd $PROJ_DIR/bin
sh app.sh stop

cd $MANAGE_DIR
rm -rf $PROJ_DIR

unzip $PROJ-$VERSION.zip
mv $PROJ-$VERSION $PROJ_DIR

cd $PROJ_DIR/bin
sh app.sh start

cd $MANAGE_DIR
tail -f info.log
