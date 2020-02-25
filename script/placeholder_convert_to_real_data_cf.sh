#!/usr/bin/env bash

sed -i "s#\${APP_ENV}#${APP_ENV}#" ./src/main/resources/application.yml