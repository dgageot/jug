#!/bin/bash

./kube.sh -c jug.json create replicationControllers
./kube.sh list pods