#!/bin/bash

docker run --rm -ti \
  --volumes-from gcloud-config \
  -v $HOME/.kubernetes_auth:/root/.kubernetes_auth \
  -v $(pwd)/jug.json:/jug.json \
  dgageot/kube "$@"
