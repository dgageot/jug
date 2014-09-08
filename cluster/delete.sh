#!/bin/bash

KUBE=~/src/kuberbetes/cluster/kubecfg.sh

$KUBE resize jug 0
$KUBE list pods
$KUBE delete replicationControllers/jug
$KUBE list replicationControllers