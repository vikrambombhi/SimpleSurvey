#!/bin/sh

java -jar /jars/server.jar &
sh -c 'while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' localhost:8080)" != "200" ]]; do sleep 5; done'
cd client && yarn start
