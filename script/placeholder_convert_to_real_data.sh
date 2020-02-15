#!/usr/bin/env bash

git_latest_id=`git rev-parse HEAD`
image_tag=gcr.io/ein-final-year-project/notice-service-image:$git_latest_id
sed -i "s#\${IMAGE}#${image_tag}#" ./kubernetes/gcp_deployment.yml
sed -i "s#\${APP_ENV}#${APP_ENV}#" ./docker-entrypoint.sh