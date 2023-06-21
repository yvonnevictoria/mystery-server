    
## archived notes
// find a docker file and look at config
// docker run -p 8080:8080 docker.io/library/backend:0.0.1-SNAPSHOT to run. order of args matter
// set up db in docker. docker compose spinning up app and postgres

// create docker file and specify image to depend on to prevent
// it from downloading every time you run mvn spring-boot:build-image
// get flyaway to run the migrations when we start the app


// 190423 - mvn spring-boot:build-image to clean image before running docker
// this docker command only runs the java app not the db: docker run -p 8080:8080 docker.io/library/backend:0.0.1-SNAPSHOT
//    use docker compose up
// hibernate and jpa (spring-boot-starter-data-jpa) want to generate java classes and hide sql.  spring-boot-starter-data-jdbc allows you to write sql
// ORM try to hide db sql and generate java class for your entity
// ended on finding jdbc package to connect to db for flyway
// can not find connection is error we are trying to fix
// <dependency>
//    <groupId>org.springframework.boot</groupId>
//    <artifactId>spring-boot-starter-data-jdbc</artifactId>
//</dependency> ????

// run just the db via docker compose: `docker-compose up db`

// jdbc:postgresql://db:5432/compose-postgres
//                                  ^ this is the database name!!!

// MVP: 10 meals
// have backend randomly select the meals and send them all upfront

// Next steps: unlimited meals
// - avoid waiting for network call with each interaction
// - pagination: send 10 meals upfront, then trigger next api call when near the end
// - also send IDs of dismissed meals, so backend doesn't send them again
