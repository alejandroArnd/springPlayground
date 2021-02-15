#!/bin/sh

wait-for-it -h mysql -p 3306 -t 120

if [ ! -d "./config/jwt" ];
    then mkdir "./config/jwt"
    chmod -R 777 ./config/jwt/
    openssl genrsa -out ./config/jwt/private.pem -passout pass:password 2048
    openssl pkcs8 -topk8 -inform PEM -outform DER -in ./config/jwt/private.pem -out ./config/jwt/private.der -nocrypt
    openssl pkey  -in ./config/jwt/private.pem  -pubout -outform DER -out ./config/jwt/public.der -pubout -passin pass:password
fi

./mvnw spring-boot:run