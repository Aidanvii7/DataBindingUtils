#!/usr/bin/env bash

read -n1 -r -p "Press space to build stage 1..." key
if [ "$key" = '' ]; then
./gradlew databinding:clean databinding:build databinding:generateRelease
fi