FROM eclipse-temurin:21
COPY target/demo-0.0.1-SNAPSHOT.jar /usr/apps/demo.jar
COPY target/META-INF /usr/apps/META-INF
COPY target/BOOT-INF /usr/apps/BOOT-INF
COPY target/org /usr/apps/org
ENTRYPOINT ["java", "-jar", "/usr/apps/demo.jar" ]
