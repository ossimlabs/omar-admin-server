# OMAR Spring Boot Admin Server

## Source Location
https://github.com/ossimlabs/omar-admin-server

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

### An Example DeploymentConfig

```yaml
apiVersion: v1
kind: DeploymentConfig
metadata:
  name: omar-admin-server
  namespace: omar-dev
  selfLink: /oapi/v1/namespaces/omar-dev/deploymentconfigs/omar-admin-server
  uid: cbe2fe4c-b4ea-11e7-ba3b-0e704fd9c8b2
  resourceVersion: '6645868'
  generation: 32
  creationTimestamp: '2017-10-19T16:30:19Z'
  labels:
    app: omar-admin-server
  annotations:
    openshift.io/generated-by: OpenShiftWebConsole
spec:
  strategy:
    type: Rolling
    rollingParams:
      updatePeriodSeconds: 1
      intervalSeconds: 1
      timeoutSeconds: 600
      maxUnavailable: 25%
      maxSurge: 25%
    resources: {}
    activeDeadlineSeconds: 21600
  triggers:
    - type: ImageChange
      imageChangeParams:
        automatic: true
        containerNames:
          - omar-admin-server
        from:
          kind: ImageStreamTag
          namespace: o2
          name: 'omar-admin-server:latest'
        lastTriggeredImage: >-
          172.30.181.173:5000/o2/omar-admin-server@sha256:5734cd30dccf602f0c9d5cab34f000240b4119ffcb7dfd461b02838b0c4d5e16
    - type: ConfigChange
  replicas: 1
  test: false
  selector:
    app: omar-admin-server
    deploymentconfig: omar-admin-server
  template:
    metadata:
      creationTimestamp: null
      labels:
        app: omar-admin-server
        deploymentconfig: omar-admin-server
      annotations:
        openshift.io/generated-by: OpenShiftWebConsole
    spec:
      containers:
        - name: omar-admin-server
          image: >-
            172.30.181.173:5000/o2/omar-admin-server@sha256:5734cd30dccf602f0c9d5cab34f000240b4119ffcb7dfd461b02838b0c4d5e16
          ports:
            - containerPort: 8080
              protocol: TCP
          env:
            - name: SECURITY_USER_NAME
              value: omar
            - name: SECURITY_USER_PASSWORD
              value: omarftw123
            - name: SPRING_PROFILES_ACTIVE
              value: 'production,dev'
            - name: SPRING_CLOUD_CONFIG_LABEL
              value: master
            - name: SPRING_CLOUD_DISCOVERY_ENABLED
              value: 'true'
          resources: {}
          terminationMessagePath: /dev/termination-log
          imagePullPolicy: Always
      restartPolicy: Always
      terminationGracePeriodSeconds: 30
      dnsPolicy: ClusterFirst
      securityContext: {}
status:
  latestVersion: 23
  observedGeneration: 32
  replicas: 1
  updatedReplicas: 1
  availableReplicas: 1
  unavailableReplicas: 0
  details:
    message: image change
    causes:
      - type: ImageChange
        imageTrigger:
          from:
            kind: ImageStreamTag
            namespace: o2
            name: 'omar-admin-server:latest'
  conditions:
    - type: Available
      status: 'True'
      lastUpdateTime: '2017-10-31T18:15:52Z'
      lastTransitionTime: '2017-10-31T18:15:52Z'
      message: Deployment config has minimum availability.
    - type: Progressing
      status: 'True'
      lastUpdateTime: '2017-11-01T13:59:36Z'
      lastTransitionTime: '2017-11-01T13:59:33Z'
      reason: NewReplicationControllerAvailable
      message: replication controller "omar-admin-server-23" successfully rolled out
  readyReplicas: 1

```
