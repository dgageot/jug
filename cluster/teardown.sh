#!/bin/bash

docker run --rm -ti --volumes-from gcloud-config -v $HOME/.kubernetes_auth:/root/.kubernetes_auth dgageot/kube /kube/cluster/kube-down.sh
