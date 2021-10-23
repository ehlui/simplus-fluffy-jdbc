#!/bin/bash

# Automate the removing and deployment
# of the current docker compose services (current dir file)
# =========================================================
# - It reads the .env to perform the maping (to extract names)
#
# Author: ehLui

# 1- Load names
DB_CONTAINER_NAME=$(tr <.env ' ' _ | grep DB_CONTAINER_NAME= | cut -d '=' -f2)
ADMINER_CONTAINER_NAME=$(tr <.env ' ' _ | grep ADMINER_CONTAINER_NAME= | cut -d '=' -f2)
# 2- clean it up (just in case)
docker rm -f "$DB_CONTAINER_NAME" "$ADMINER_CONTAINER_NAME"
docker rmi -f "$DB_CONTAINER_NAME" "$ADMINER_CONTAINER_NAME"

# 3- Setting up the services/containers
echo "Previous settings: "
# *Prints the whole structure of the yml with the env variables loaded
docker-compose config
# *Builds(if it's needed) and runs in detached mode the services within the yml
docker-compose up -d
