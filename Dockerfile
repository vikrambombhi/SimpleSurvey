FROM adoptopenjdk/openjdk12:alpine-slim as builder
WORKDIR /home/builder
RUN mkdir -p /jars
RUN apk update && \
    apk upgrade && \
    apk --no-cache add maven && \
    rm -rf /var/cache/apk/*
COPY . .
RUN mvn package
RUN mv /home/builder/target/survey-0.0.1-SNAPSHOT.jar /jars/server.jar

FROM adoptopenjdk/openjdk12:alpine-jre as server
RUN apk update && \
    apk upgrade && \
    apk --no-cache add tini curl ca-certificates bind-tools openssl openssl-dev yarn nodejs && \
    rm -rf /var/cache/apk/*

COPY --from=builder /jars/server.jar /jars/server.jar
RUN chmod +x /jars/server.jar

WORKDIR /home/app
COPY . .
RUN cd client && yarn install
EXPOSE 3000
COPY entrypoint.sh /entrypoint.sh
ENTRYPOINT ["/sbin/tini", "-g", "--", "/entrypoint.sh"]
