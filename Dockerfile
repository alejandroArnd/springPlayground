FROM openjdk:8

RUN apt-get update && apt-get install -y git libzip-dev unzip wait-for-it

COPY . /usr/src/myapp

WORKDIR /usr/src/myapp

CMD ["./startSpring.sh"]