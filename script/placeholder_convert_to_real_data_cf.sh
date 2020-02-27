#!/usr/bin/env bash

sed -i "s#\${APP_ENV}#${APP_ENV}#" ./src/main/resources/application.yml
sed -i "s#\${PASSWORD}#${PASSWORD}#" .travis.yml
sed -i "s#\${USERNAME}#${USERNAME}#" .travis.yml