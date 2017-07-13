#!/bin/bash 
cd sbt-docker-example
sbt test
cd ../
cd better-files-example
sbt test