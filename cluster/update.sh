#!/bin/bash

KUBE=~/src/kuberbetes/cluster/kubecfg.sh

$KUBE -image dgageot/jug -u 5s rollingupdate jug