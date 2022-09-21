# oto_man_back
oto_man_back


# POSTGRES Docker compose problem
If authentication problems are encountered when trying to access psql from port and as user defined in compose file, 
eg: this error `"FATAL:  password authentication failed for user <user>"`, then remove the postgres container and volumes and 
UP the compose file again:

- List the containers: `docker ps`
- Remove the postgres container: `docker rm <hash>`
- List the volumes: `docker volume ls`
- Remove the postgres volume: `docker rm <VOLUME NAME>`
- Restart container: `docker-compose up`