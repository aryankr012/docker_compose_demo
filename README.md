# Docker Compose Demo

A containerized full-stack application deployed using **Docker and
Docker Compose**. The project brings together a Streamlit frontend, a
Java backend packaged as a WAR file and deployed on Apache Tomcat, and a
MongoDB database.

## Architecture

The application contains three services:

-   **Frontend:** Streamlit application running on port `8501`
-   **Backend:** Java application packaged with Maven and deployed to
    Apache Tomcat on port `8080`
-   **Database:** MongoDB running on container port `27017` and mapped
    to host port `27018`

All services communicate through a custom Docker Compose bridge network
named `prod`.

``` text
                    Docker Compose
                          |
              -------------------------
              |           |           |
        Frontend      Backend      Database
        Streamlit     Java WAR     MongoDB
        Port 8501     Port 8080    Port 27017
              |           |
              -------- Network --------
                    prod (bridge)
```

## Technology Stack

  Layer              Technology
  ------------------ ----------------------------
  Frontend           Python, Streamlit
  Backend            Java, Maven, Apache Tomcat
  Database           MongoDB
  Containerization   Docker, Docker Compose
  Operating System   Linux
  Version Control    Git, GitHub

## Project Structure

``` text
docker_compose_demo/
├── DataStoreFront/
│   ├── Dockerfile
│   ├── requirements.txt
│   └── app.py
├── studentstore/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
├── docker-compose.yml
└── README.md
```

## Docker Implementation

### Frontend

The frontend uses a lightweight Python image:

-   Base image: `python:3.12-slim`
-   Installs Python dependencies from `requirements.txt`
-   Copies the Streamlit application into the container
-   Runs Streamlit on port `8501`

### Backend

The backend uses a multi-stage Docker build.

#### Build stage

-   Uses Maven with Eclipse Temurin Java 17
-   Copies `pom.xml` and the application source code
-   Packages the application as a WAR file using Maven

#### Runtime stage

-   Uses Apache Tomcat 9 with JDK 17
-   Removes the default Tomcat web applications
-   Copies the generated WAR file as `ROOT.war`
-   Runs the application through Tomcat

Multi-stage builds help separate the build environment from the runtime
environment.

### Database

The database service uses the official MongoDB image.

-   Container port: `27017`
-   Host port: `27018`
-   Persistent storage: named Docker volume `data`
-   Database directory: `/data/db`

## Docker Compose Configuration

The Docker Compose stack includes:

-   A frontend service built from `DataStoreFront/`
-   A backend service built from `studentstore/`
-   A MongoDB database service
-   A custom bridge network named `prod`
-   A named volume for MongoDB persistence
-   Environment variables for database connectivity

The backend connects to MongoDB using the Compose service name:

``` text
MONGODB_HOST=database
MONGODB_PORT=27017
```

Inside the Docker network, services should communicate using container
service names and internal container ports rather than `localhost`.

## Prerequisites

Install the following tools before running the project:

-   Docker
-   Docker Compose
-   Git

Verify the installations:

``` bash
docker --version
docker compose version
git --version
```

## Run the Application

### 1. Clone the repository

``` bash
git clone https://github.com/aryankr012/docker_compose_demo.git
cd docker_compose_demo
```

### 2. Build and start the services

``` bash
docker compose up --build
```

To run the services in detached mode:

``` bash
docker compose up --build -d
```

### 3. Check running containers

``` bash
docker compose ps
```

### 4. View service logs

View logs for all services:

``` bash
docker compose logs
```

View logs for a specific service:

``` bash
docker compose logs frontend
docker compose logs backend
docker compose logs database
```

### 5. Stop the application

``` bash
docker compose down
```

To remove the named volumes as well:

``` bash
docker compose down -v
```

> Warning: Removing volumes deletes the persisted MongoDB data stored in
> those volumes.

## Access the Services

After the containers start successfully, use the following endpoints:

  Service    URL
  ---------- ---------------------------------
  Frontend   `http://localhost:8501`
  Backend    `http://localhost:8080`
  MongoDB    `localhost:27018` from the host

The backend and frontend URLs depend on the routes implemented by the
application.

## Useful Docker Commands

List running containers:

``` bash
docker ps
```

List all containers:

``` bash
docker ps -a
```

List images:

``` bash
docker images
```

Inspect the Compose configuration:

``` bash
docker compose config
```

Rebuild a specific service:

``` bash
docker compose build frontend
docker compose build backend
```

Restart a service:

``` bash
docker compose restart backend
```

Open a shell inside a running container:

``` bash
docker exec -it <container_name> sh
```

## Configuration and Security Notes

-   Use environment variables for database configuration.
-   Avoid committing production credentials to GitHub.
-   Add sensitive `.env` files to `.gitignore`.
-   Consider pinning production image versions instead of using floating
    image tags.
-   Add health checks for services that depend on database readiness.
-   Use a dedicated secrets-management solution for production
    deployments.
-   Treat Docker volumes as persistence, not as a replacement for
    backups.

## Troubleshooting

### Check container status

``` bash
docker compose ps
```

### Inspect logs

``` bash
docker compose logs -f backend
```

### Rebuild without using the cache

``` bash
docker compose build --no-cache
docker compose up
```

### Check network configuration

``` bash
docker network ls
docker network inspect <network_name>
```

### Check persistent volumes

``` bash
docker volume ls
docker volume inspect <volume_name>
```

## Key Learning Outcomes

This project demonstrates:

-   Containerization of multiple application tiers
-   Dockerfile creation for frontend and backend services
-   Multi-stage Docker builds
-   Docker Compose service orchestration
-   Container-to-container communication through a custom network
-   Environment-based application configuration
-   MongoDB data persistence using named volumes
-   Java WAR deployment on Apache Tomcat
-   Application troubleshooting using Docker commands

## Future Improvements

Potential improvements include:

-   Add Docker health checks and improve service readiness handling
-   Move credentials into environment files or a secrets-management
    system
-   Pin base image versions for reproducible builds
-   Add a reverse proxy such as Nginx
-   Add CI/CD automation using Jenkins or GitHub Actions
-   Add application and container monitoring
-   Add automated tests to the build pipeline
-   Use separate Compose configurations for development and production

## Author

**Aryan Kumar**

GitHub: [aryankr012](https://github.com/aryankr012)
