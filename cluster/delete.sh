#!/bin/bash

KUBE=~/src/kubernetes/cluster/kubecfg.sh

$KUBE resize jug 0
$KUBE list pods
$KUBE delete replicationControllers/jug
$KUBE list replicationControllers