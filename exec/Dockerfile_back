FROM openjdk:11-jdk

COPY ./ ./
RUN chmod +x ./gradlew
RUN ./gradlew clean build --no-daemon

EXPOSE 5000

ENTRYPOINT ["java", "-jar", "./build/libs/liargame-0.0.1-SNAPSHOT.jar"]