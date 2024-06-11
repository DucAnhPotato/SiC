# Maven build
FROM maven:3.9.2-amazoncorretto-17 AS MAVEN_BUILD
# App home
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
# Copy pom.xml & source code to WORKDIR
COPY pom.xml $APP_HOME
COPY . $APP_HOME
# Maven package
RUN mvn clean package -DskipTests

# Payment service image
FROM openjdk:17-oracle AS payment_service
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
ENV PAYMENT_SERVICE_ARTIFACT=sic-payment-service-1.0.jar
COPY --from=MAVEN_BUILD $APP_HOME/sic-payment-service/target/$PAYMENT_SERVICE_ARTIFACT /images/sic-payment-service/$PAYMENT_SERVICE_ARTIFACT
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/images/sic-payment-service/sic-payment-service-1.0.jar"]

# Project service image
FROM openjdk:17-oracle AS project_service
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
ENV PROJECT_SERVICE_ARTIFACT=sic-project-service-1.0.jar
COPY --from=MAVEN_BUILD $APP_HOME/sic-project-service/target/$PROJECT_SERVICE_ARTIFACT /images/sic-project-service/$PROJECT_SERVICE_ARTIFACT
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/images/sic-project-service/sic-project-service-1.0.jar"]
