FROM openjdk:11-jdk

WORKDIR /root/S08P12A706/back-end/liargame

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew cleanQuerydslSourcesDir
RUN ./gradlew build

ONBUILD COPY ./build/libs/*.jar ./app.jar

EXPOSE 5000
ENTRYPOINT ["java","-jar","./app.jar"]
