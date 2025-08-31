# SSO Protected Resource
## Learning Objectives

- *SSO auth* through self-hosted *Keycloak*

## Instructions

1. Fork this repository
2. Clone your fork to your machine
3. Open the project in *IntelliJ*
4. Copy `application.yaml.example` to `application.yml` and fill out your *database* and *security* connection details
5. Check that `build.gradle` contains the correct dependencies and rerun gradle sync to make it all update

## Activity
### Core
Implement a new project in realm of `booleanuk-application` with following *Student* `Entity`

| Student    |         |             |
| ---------- | ------- | ----------- |
| id         | SERIAL  | PRIMARY KEY |
| first_name | TEXT    |             |
| last_name  | TEXT    |             |
| email      | TEXT    |             |
| retired    | BOOLEAN |             |

#### Routes
Follow the spec you can find in the usual folder`docs` and implement all the routes defined

| Method     | Endpoint                   | Description                                     |
| :--------- | :------------------------- | :---------------------------------------------- |
| **GET**    | `/api/public/student`      | get all students                                |
| **GET**    | `/api/public/student/{id}` | get student by id                               |
| **POST**   | `/api/student`             | create student (dto without id as request body) |
| **PUT**    | `/api/student/{id}`        | update student (dto without id as request body) |
| **DELETE** | `/api/student/{id}`        | delete student by id                            |

#### Authentication
As in the morning lesson, get protection over all *non-public* resources, so only authenticated user can perform *write* actions

##### Public

| Method     | Endpoint                   | Description                                     |
| :--------- | :------------------------- | :---------------------------------------------- |
| **GET**    | `/api/public/student`      | get all students                                |
| **GET**    | `/api/public/student/{id}` | get student by id                               |

##### Protected

| Method     | Endpoint                   | Description                                     |
| :--------- | :------------------------- | :---------------------------------------------- |
| **POST**   | `/api/student`             | create student (dto without id as request body) |
| **PUT**    | `/api/student/{id}`        | update student (dto without id as request body) |
| **DELETE** | `/api/student/{id}`        | delete student by id                            |

## Note
Make sure you are a new completely new *realm* in order to test the whole process, from Keycloak configuration to *SpringBoot* project develop
