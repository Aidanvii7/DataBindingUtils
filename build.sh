#!/usr/bin/env bash

read -n1 -r -p "Press space to build stage 1..." key
if [ "$key" = '' ]; then
./gradlew kotlin:clean kotlin:build kotlin:generateRelease
./gradlew databinding:clean databinding:build databinding:generateRelease
fi

read -n1 -r -p "Press space to build stage 2..." key
if [ "$key" = '' ]; then
./gradlew kotlin-logger:clean kotlin-logger:build kotlin-logger:generateRelease
./gradlew kotlin-property-weak:clean kotlin-property-weak:build kotlin-property-weak:generateRelease
./gradlew kotlin-property-softlazy:clean kotlin-property-softlazy:build kotlin-property-softlazy:generateRelease
./gradlew kotlin-property-eco-closable:clean kotlin-property-eco-closable:build kotlin-property-eco-closable:generateRelease
./gradlew kotlin-property-eco-rx-disposable:clean kotlin-property-eco-rx-disposable:build kotlin-property-eco-rx-disposable:generateRelease
./gradlew kotlin-property-observables:clean kotlin-property-observables:build kotlin-property-observables:generateRelease
fi

read -n1 -r -p "Press space to build stage 3..." key
if [ "$key" = '' ]; then
./gradlew kotlin-lifecycle:clean kotlin-lifecycle:build kotlin-lifecycle:generateRelease
./gradlew databinding-notifiable:clean databinding-notifiable:build databinding-notifiable:generateRelease
./gradlew databinding-imageview:clean databinding-imageview:build databinding-imageview:generateRelease
./gradlew databinding-recyclerview:clean databinding-recyclerview:build databinding-recyclerview:generateRelease
fi

read -n1 -r -p "Press space to build stage 4..." key
if [ "$key" = '' ]; then
./gradlew databinding-kotlin:clean databinding-kotlin:build databinding-kotlin:generateRelease
fi

read -n1 -r -p "Press space to build stage 5..." key
if [ "$key" = '' ]; then
./gradlew databinding-notifiable-bindlistener:clean databinding-notifiable-bindlistener:build databinding-notifiable-bindlistener:generateRelease
./gradlew databinding-notifiable-kotlin:clean databinding-notifiable-kotlin:build databinding-notifiable-kotlin:generateRelease
./gradlew databinding-recyclerview-plugin-adapternotifier:clean databinding-recyclerview-plugin-adapternotifier:build databinding-recyclerview-plugin-adapternotifier:generateRelease
./gradlew databinding-imageview-glide:clean databinding-imageview-glide:build databinding-imageview-glide:generateRelease
./gradlew databinding-recyclerview-list:clean databinding-recyclerview-list:build databinding-recyclerview-list:generateRelease
./gradlew databinding-recyclerview-observablelist:clean databinding-recyclerview-observablelist:build databinding-recyclerview-observablelist:generateRelease
./gradlew databinding-recyclerview-observablesortedlist:clean databinding-recyclerview-observablesortedlist:build databinding-recyclerview-observablesortedlist:generateRelease
./gradlew databinding-recyclerview-observablemap:clean databinding-recyclerview-observablemap:build databinding-recyclerview-observablemap:generateRelease
fi

read -n1 -r -p "Press space to build stage 6..." key
if [ "$key" = '' ]; then
./gradlew databinding-recyclerview-plugin-adapternotifier-kotlin:clean databinding-recyclerview-plugin-adapternotifier-kotlin:build databinding-recyclerview-plugin-adapternotifier-kotlin:generateRelease
fi