#!/bin/sh

PROJECT_DIR=$(pwd)

gradle -i -b ${PROJECT_DIR}/alma-lib/build.gradle clean build publishToMavenLocal

for i in cone data datalink obscore reg sia soda tap;
do
  gradle -i -b ${PROJECT_DIR}/${i}/build.gradle clean build;
  VERSION=`grep "version =" ${PROJECT_DIR}/properties.gradle | awk -F \' '{print $2}'`
  echo "**";
  echo "** Building opencadc/alma-${i}:${VERSION}";
  echo "**";
  docker build -t opencadc/alma-${i}:${VERSION} -f ${PROJECT_DIR}/${i}/Dockerfile ${PROJECT_DIR}/${i};
done
