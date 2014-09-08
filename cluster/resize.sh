#!/bin/bash

KUBE=~/src/kubernetes/cluster/kubecfg.sh

$KUBE resize jug 4
$KUBE list pods