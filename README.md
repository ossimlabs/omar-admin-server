# omar-admin-server

## Description

Spring Boot Admin provides an interface to manage and monitor all Spring Boot applications. The applications register with Spring Boot are discovered using Spring Cloud (Eureka). 

Add the section below to the application.yml, and set the parameters accordingly.  They are necessary to log in to the UI, because Spring Security has been enabled for the omar-admin-server application.
##### Example:

```
---
security:
  user:
    name: changeme!
    password: changeme!

```

---
.
