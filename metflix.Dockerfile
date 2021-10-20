FROM mysql:8.0.27

LABEL maintainer=luisml

LABEL java8=practising

# Copy all sql scripts to be executed once is init.
COPY ./sql /docker-entrypoint-initdb.d/