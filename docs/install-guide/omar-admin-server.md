# OMAR Spring Boot Admin Server

## Purpose
The Spring Boot Admin Server provides a Spring Framework discovery client for all OMAR services. This discovery client is utilized for a variety of routing, metrics, and aggregation tasks.

- Show health status
- Show details, like
- JVM & memory metrics
- Counter & gauge metrics
- Datasource metrics
- Cache metrics
- Show build-info number
- Follow and download logfile
- View jvm system- & environment-properties
Easy loglevel management (currently for Logback only)
Interact with JMX-beans
View thread dump
View traces
Event journal of status changes (non persistent)

## Installation in Openshift

**Assumption:** The omar-admin-server docker image is pushed into the OpenShift server's internal docker registry and available to the project.

The OMAR Spring Boot Admin Server can be deployed into OpenShift without any special volumes or secrets.

### Environment variables

|Variable|Value|
|------|------|
|SPRING_PROFILES_ACTIVE|Comma separated profile tags|

By default, no environment variables are required. If none are specified the SPRING_PROFILES_ACTIVE is empty.
