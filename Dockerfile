FROM ingensi/oracle-jdk

MAINTAINER wiselyman

COPY $CIRCLE_ARTIFACTS/*.jar /opt/

WORKDIR /opt/
