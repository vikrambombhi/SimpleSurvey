#!/bin/bash

docker build -t simplesurvey .
docker run -it -p 3000:3000 simplesurvey
