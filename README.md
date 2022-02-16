# schoolregistration
Quick project for a school registration system

# Prerequisites

* Docker / Docker-Compose installed and running.
* Java 17+ installed and in `PATH`.
* Maven 3.6+ installed and in `PATH`.

# Build

Run this Maven command:
```
 mvn clean install spring-boot:build-image
```

This will build the Java / SpringBoot's fat jar, as well as the Docker image.

Check the image was built:
```
docker image ls -a | grep school
```

Start the stack:
```
docker-compose up
```

This starts the DB, creates the schema if it does not exist, start the SpringBoot application and feeds an initial set of students and courses.

# REST

In the browser, open the URL: `http://localhost:9090/swagger-ui/index.html`
Login and passwords are aksed for. Both are **`schoolregistration`**.

CRUD operations are available for `/students`:
```
curl -X 'GET' \
  'http://localhost:9090/courses?page=0&size=20' \
  -H 'accept: application/x-spring-data-compact+json'


curl -X 'POST' \
  'http://localhost:9090/courses' \
  -H 'accept: application/hal+json' \
  -H 'Content-Type: application/json' \
  -d '{
  "code": "ABC-888",
  "name": "Python for beginners",
  "comment": "start programming with Python!"
}'

  
  ```

CRUD operations are available for `/courses` as well.

Other operations are possible for `/registrations`.

**WARNING** 
To register a student to a course, call this URL:
http://localhost:9090/registration/?studentId=<studentId>&courseId=<courseId>

Please refer to Swagger UI for full and complete documentation.

## Filter operations:

cf `http://localhost:9090/swagger-ui/index.html#/registration-search-controller` to:
* Filter all students with a specific course
```
curl -X 'GET' \
  'http://localhost:9090/registrations/byCourseId/20' \
  -H 'accept: application/hal+json'
```
* Filter all courses for a specific student
```
curl -X 'GET' \
  'http://localhost:9090/registrations/byStudentId/199' \
  -H 'accept: application/hal+json'
```
* Filter all courses without any students
```
curl -X 'GET' \
  'http://localhost:9090/registrations/courses/withoutStudent' \
  -H 'accept: application/hal+json'
```
* Filter all students without any courses:
```
curl -X 'GET' \
  'http://localhost:9090/registrations/students/withoutCourse' \
  -H 'accept: application/hal+json'
```

# Technical

The application is a good old monolith. Why not microservices? Simply because I did not have time! Moreover, the difficulty of this project does not justify to complexify unnecessarily the flows and connections.
If I had had more time, 
* I would have used Feign and SpringCloud to make the microservices work with each other.
* I would have added NGinx as a front end to redirect the requests to the proper microservice.