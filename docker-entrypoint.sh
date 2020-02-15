#!/usr/bin/env bash

echo "Run app with app.env=${APP_ENV}"
./gradlew --no-daemon bootRun -Dapp.env=${APP_ENV}
