FROM eclipse-temurin:21 AS build
COPY target/demo-0.0.1-SNAPSHOT.jar /usr/apps/demo.jar
COPY --from=aquasec/trivy:latest /usr/local/bin/trivy /usr/local/bin/trivy
RUN trivy rootfs --no-progress /
ENTRYPOINT ["java", "-jar", "/usr/apps/demo.jar" ]
