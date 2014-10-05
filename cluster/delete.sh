#!/bin/bash

./kube.sh resize jug 0
./kube.sh list pods
./kube.sh delete replicationControllers/jug
./kube.sh list replicationControllers