FROM ubuntu:24.04
LABEL authors="HAYAT"
RUN apt update -y &&  install -y openjdk-17-jdk
WORKDIR /app/user-management
COPY target/user_management-1.0.0.jar .
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "user-management-1.0.0.jar"]