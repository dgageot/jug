#!/bin/bash

KUBE=~/src/kubernetes/cluster/kubecfg.sh

$KUBE -image dgageot/jug:red -u 5s rollingupdate jug