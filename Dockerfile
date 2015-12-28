FROM java:8

MAINTAINER samzhu

COPY build/libs/*.jar /opt/

WORKDIR /opt/
