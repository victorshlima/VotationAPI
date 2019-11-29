# VotationAPI

Votation API, is cloud api rest for managing agendas and voting sessions.

Sessions allow only one vote per member within the time frame determined in a voting session.




# Development

There is a pom.xml in the Server folder to compile and create the jar and build the distribution
package using Maven The current version of the application is extracted from the POM and instered
into the MANIFEST.MF at build time. 

## Installation

./Procfile  - Contens a Dyno Heroku configration for virtualization on Cloud

Download git project
mvn clean install


## Running Tests


##Documentation

https://votation-api.herokuapp.com/v1/swagger-ui.html



#https://votation-api.herokuapp.com/v1

#Endpoints
POST
/agendas
{"subject": "1"}

POST
/sessions
{"agendaId": 1,"sessionStatus": "NEW", "durationMinutes": 2}

POST
/sessions
{    "agendaId": 1,    "sessionStatus": "NEW",    "durationMinutes": 60}

AgendaId - inform the Agenda to Open Session.
sessionStatus

POST
/votations
{    "agendaId": 1,    "associateId": 122,    "voteOption": "YES"}







## Roadmap


Improve security with (Spring security)
Add Lombook to simplify getters
Profiling tests
Improve log messages

## Authors and acknowledgment
https://www.linkedin.com/in/victor-hugo-soares-lima-ab8b0215/

### License
Spring Data REST is Open Source software released under the Apache 2.0 license.