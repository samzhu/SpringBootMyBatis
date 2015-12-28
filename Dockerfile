FROM ingensi/oracle-jdk

MAINTAINER wiselyman

COPY build/libs/*.jar /opt/

WORKDIR /opt/
