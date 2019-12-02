# VotationAPI

Votation API, is cloud api rest for managing agendas and voting sessions.

Sessions allow only one vote per member within the time frame determined in a voting session.

# Development

There is a pom.xml in the Server folder to compile and create the jar and build the distribution
package using Maven The current version of the application is extracted from the POM and instered
into the MANIFEST.MF at build time. 

# Tecnologies

Heroku  - Cloud Server Application;
Spring Boot Application - Spring Framework;
MYSQL  - Production Environment;
H2 - Tests in memory;
Hibernate - JPA  - Persistence data;
swagger2 - Api Documentation and URI generator;
Log4J - Log appender;

### Installation

- clone git project
- Import as a maven project

## Documentation

Application and Database properties
- application.properties

Conteins a Dyno Heroku configuration for virtualization on Cloud.
./Procfile

Logs
log4j.properties
web.xml

Swagger Documentation

https://votation-api.herokuapp.com/v1/v2/api-docs
https://votation-api.herokuapp.com/v1/swagger-ui.html
https://votation-api.herokuapp.com/v1

### Endpoints

### Headers for all
### Content-Type - application/json


## https://votation-api.herokuapp.com/v1

### Create a New Agenda
POST  - /agendas
Json Exemple:

### {"subject": "Agenda Subject exemple"}

Each AGENDA have a unique ID, you need that number for execute
the operations.

### Create a Session to vote
POST  - /sessions
Json Exemple:

### {"agendaId": 1,"sessionStatus": "NEW"}

agendaId - numeric - mandatory
sessionStatus - alphanumeric - "NEW" - mandatory, case sentitive

### Open Session for votes
PATCH  - /sessions
Json Exemple:

### {"agendaId": 1,"sessionStatus": "NEW","durationMinutes": 1}

agendaId - long -  inform the ID of Agenda
sessionStatus": "NEW" - mandatory
durationMinutes - Set the time in minutes, minimum 1 minute

### After open the session you can send Votes
POST  - /votations
Json Exemple:

### {    "agendaId": 1,    "associateId": 96222885020,    "voteOption": "YES"}

agendaId -  informe the especifi AGENDA ID to Vote
associateId - CPF user identification
voteOption - Options "YES" - "NO" - mandatory, case sentitive


### You can see the result after the votations ends
GET /results/{agendaId}

### GET All Agendas
GET /agendas
### GET a especific Agenda
GET /agendas/{agendaId}

### GET All Results
GET /results
### GET a especific Result
GET /results/{agendaId}

### GET All Sessions
GET /sessions
### GET a especific sessions
GET /sessions/{agendaId}

### GET All Votes
GET /votes
### ET a especific Votes
GET /votes/{id}

### Running Tests

- mvn clean install

## Roadmap

Add get with pagination 
Improve security with (Spring security)
Add Lombook to simplify getters
Profiling tests
Improve log messages
Add Spring Service Mix
Add Rabbit or Apache kafka to manage the queue of requests

## Authors and acknowledgment
https://www.linkedin.com/in/victor-hugo-soares-lima-ab8b0215/

### License
Spring Data REST is Open Source software released under the Apache 2.0 license.
