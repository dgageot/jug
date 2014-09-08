#!/bin/bash

KUBE=~/src/kuberbetes/cluster/kubecfg.sh

$KUBE resize jug 4
$KUBE list pods