# oto_man_back

**Required:** Java 17, Docker-compose
## To run the app

- In root folder run: `docker-compose up` for psql and pgadmin to run.
- Launch the java app.

## DB config:

- In default DB (name in compose file), create the following tables, column names are the properties in the entity classes 
in the com.oto.back.model package:
    - `user_`
    - `auto`
    - `ride`
    - `rider`

As for **column types**, all date columns must be **TIMESTAMP WITH TIMEZONE**, use VARCHAR for strings.

# POSTGRES Docker-compose problem
If authentication problems are encountered when trying to access psql as the user defined in compose file, 
eg: this error `"FATAL:  password authentication failed for user <user>"`, then remove the postgres container and volumes and 
UP the docker compose file again:

- List the containers: `docker ps`
- Remove the postgres container: `docker rm <hash>`
- List the volumes: `docker volume ls`
- Remove the postgres volume: `docker rm <VOLUME NAME>`
- Restart container: `docker-compose up`
- Access the psql in terminal: `psql -h 0.0.0.0 -p <port> -U <user>` and enter password

# TABLE CREATION COMMANDS SCRIPT:
```
 CREATE TABLE rider (id BIGSERIAL, 
    firstname VARCHAR, 
    lastName VARCHAR, 
    email VARCHAR, 
    phone VARCHAR, 
    birthDate TIMESTAMP WITH TIME ZONE, 
    PRIMARY KEY(id));
```
```
 CREATE TABLE ride (id BIGSERIAL, 
    start TIMESTAMP WITH TIME ZONE, 
    checkin TIMESTAMP WITH TIME ZONE, 
    riderid INT, 
    autoid INT, comment VARCHAR, 
    PRIMARY KEY(id), 
    CONSTRAINT fk_rider 
      FOREIGN KEY(riderid) 
        REFERENCES rider(id), 
    CONSTRAINT fk_auto 
      FOREIGN KEY(autoid) 
        REFERENCES auto(id));
```