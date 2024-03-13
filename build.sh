#!/bin/sh

PROJECT_DIR=$(pwd)

FIRST_ARG="${1}"
SKIP_BUILD=0

if [ "${FIRST_ARG}" == "-*" ];
then
  if [ "${FIRST_ARG}" == "--skip-build" ];
  then
    SKIP_BUILD=1 
    shift
  else
    echo "Unknown switch ${FIRST_ARG}"
    exit 1
  fi
fi


if [ ! "${SKIP_BUILD}" == 0 ];
then
  gradle -b ${PROJECT_DIR}/alma-lib/build.gradle -i clean publishToMavenLocal
fi

VERSION=`grep version\ = ${PROJECT_DIR}/properties.gradle | awk -F \' '{print $2}'`

for i in cone data datalink obscore reg sia soda tap;
do
  if [ ! "${SKIP_BUILD}" == 0 ];
  then
    gradle -b ${PROJECT_DIR}/${i}/build.gradle -i clean build;
  fi
  echo "**";
  echo "** Building opencadc/alma-${i}:${VERSION}";
  echo "**";
  docker build -t opencadc/alma-${i}:${VERSION} -f ${i}/Dockerfile ${i};
done
