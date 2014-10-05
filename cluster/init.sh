#!/bin/bash

docker run --rm -ti --volumes-from gcloud-config -v $HOME/.kubernetes_auth:/root/.kubernetes_auth dgageot/kube /kube/hack/dev-build-and-up.sh
