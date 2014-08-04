#!/bin/bash
BASE_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $BASE_DIR
gradle clean
DEMO_DIR=$BASE_DIR/../cubie-sdk-android-demo
cd $DEMO_DIR
gradle assembleDebug || exit 1
if [ -z "$DROPBOX_HOME" ]; then
   echo "DROPBOX_HOME not defined, apk not copied"
   exit 1
fi
cp $DEMO_DIR/build/apk/cubie-openapi-demo-debug-unsigned*.apk $DROPBOX_HOME/android_releases
