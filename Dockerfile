FROM jenkins/jenkins:lts-alpine

USER root
RUN apk add docker shadow

RUN usermod -aG docker jenkins

USER jenkins
COPY user /tmp/user
COPY pass /tmp/pass
COPY security.groovy /var/jenkins_home/init.groovy.d/security.groovy

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"