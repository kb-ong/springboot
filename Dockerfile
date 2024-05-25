FROM eclipse-temurin:21
COPY target/demo-0.0.1-SNAPSHOT.jar /usr/apps/demo.jar
ENTRYPOINT ["java", "-jar", "/usr/apps/demo.jar" ]
