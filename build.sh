#! /bin/bash

if [ -d "./target/" ]
then
	rm -r target/
fi
docker build -t jerome/nphc .