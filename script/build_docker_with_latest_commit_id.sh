#!/usr/bin/env bash

git_latest_id=`git rev-parse HEAD`
image_tag=gcr.io/ein-final-year-project/notice-service-image:$git_latest_id
gcloud builds submit --tag $image_tag .