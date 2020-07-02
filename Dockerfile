FROM openjdk:11.0.7-slim

WORKDIR /app

COPY ./gradlew settings.gradle.kts build.gradle.kts  /app/
COPY gradle  /app/gradle

RUN  ./gradlew --console=plain clean download

COPY . /app

RUN ./gradlew --console=plain clean build --no-daemon

ENTRYPOINT ["./docker-entrypoint.sh"]

RUN ["chmod", "+x", "./docker-entrypoint.sh"]

CMD ["server"]
