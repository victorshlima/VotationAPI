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


https://votation-api.herokuapp.com/v1

POST /agendas
GET /agendas/{id}
GET /agendas
/agendas
/sessions
/votations
/results/{id}

## Roadmap


Improve security with (Spring security)
Add Lombook to simplify getters
Profiling tests
Improve log messages

## Authors and acknowledgment
https://www.linkedin.com/in/victor-hugo-soares-lima-ab8b0215/

### License
Spring Data REST is Open Source software released under the Apache 2.0 license.