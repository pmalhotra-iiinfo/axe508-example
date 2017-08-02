FROM tomcat:8.5-alpine

MAINTAINER Rafael Troncoso<rafael.troncoso@salientcrgt.com>
ENV JAVA_OPTS="-Dspring.profiles.active=development"

RUN chgrp -R 0 /usr/local/tomcat \
  && chmod -R g+rwX /usr/local/tomcat \
  && rm -rf /usr/local/tomcat/webapps/ROOT
COPY target/calories-tracker.war /usr/local/tomcat/webapps/ROOT.war

