#!/bin/sh

wait-for-it -h mysql -p 3306 -t 120

./mvnw spring-boot:run