# Docker Compose Demo

Containerized full-stack application using Docker Compose.

## Tech Stack

-   Streamlit frontend
-   Java, Maven, Apache Tomcat backend
-   MongoDB database
-   Docker, Docker Compose, Linux

## Services

-   Frontend: `8501`
-   Backend: `8080`
-   MongoDB: `27017` (host: `27018`)

## Run

``` bash
git clone https://github.com/aryankr012/docker_compose_demo.git
cd docker_compose_demo
docker compose up --build
```

## Access

-   Frontend: http://localhost:8501
-   Backend: http://localhost:8080

## Features

-   Multi-container deployment
-   Multi-stage Docker build
-   Docker Compose networking
-   MongoDB persistent volume

## Author

Aryan Kumar
