# spits-hl7-gw
 > Java Spring Boot project

# Build Setup

For successful build and run a project, first you must open project directory in terminal and after that follow commands below:

```bash
# build a gradle project from terminal
./gradlew clean build

# run a project from terminal
./gradlew bootRun
```


## URLs

- http://localhost:8080/configuration/clients  (GET) -> fetch all clients
- http://localhost:8080/configuration/clients  (POST) -> create one client
- http://localhost:8080/configuration/clients/{id} (DELETE) -> delete one client
- http://localhost:8080/configuration/clients/{id} (PUT) -> update one client
- http://localhost:8080/configuration/client/{clientName} -> fetch client by clientName




