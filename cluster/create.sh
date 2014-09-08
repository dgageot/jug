#!/bin/bash

KUBE=~/src/kuberbetes/cluster/kubecfg.sh

$KUBE -c jug/jug.json create replicationControllers
$KUBE list pods