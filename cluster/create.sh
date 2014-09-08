#!/bin/bash

KUBE=~/src/kubernetes/cluster/kubecfg.sh

$KUBE -c jug.json create replicationControllers
$KUBE list pods