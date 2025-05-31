# Use an official Maven image with JDK 21 to build the application
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

# Define build-time argument
ARG OTEL_TRACES_EXPORTER
ARG OTEL_METRICS_EXPORTER
ARG OTEL_LOGS_EXPORTER
ARG OTEL_COLLECTOR_ENDPOINT
ARG OTEL_SERVICE_NAME
ARG OTEL_ENABLED

# Set the working directory
ENV APP_HOME /app
ENV SPRING_PROFILES_ACTIVE=${SPRING_ACTIVE_PROFILE}

# Copy the pom.xml and source code
COPY src $APP_HOME/src
COPY pom.xml $APP_HOME/pom.xml
COPY opentelemetry-javaagent.jar $APP_HOME/opentelemetry-javaagent.jar

WORKDIR $APP_HOME

# Package the application
RUN mvn package -DskipTests

# Runtime image
FROM maven:3.9.9-eclipse-temurin-21-alpine

# Define runtime environment variable
ENV OTEL_TRACES_EXPORTER=${OTEL_TRACES_EXPORTER}
ENV OTEL_METRICS_EXPORTER=${OTEL_METRICS_EXPORTER}
ENV OTEL_LOGS_EXPORTER=${OTEL_LOGS_EXPORTER}
ENV OTEL_COLLECTOR_ENDPOINT=${OTEL_COLLECTOR_ENDPOINT}
ENV OTEL_SERVICE_NAME=${OTEL_SERVICE_NAME}
ENV OTEL_ENABLED=${OTEL_ENABLED}
ENV SPRING_ACTIVE_PROFILE=${SPRING_ACTIVE_PROFILE}

# Set the working directory
WORKDIR /app

# Copy the JAR file and OpenTelemetry agent from the build stage
COPY --from=build /app/target/*.jar /app/app.jar
COPY --from=build /app/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar

# Expose the port that the application will run on
EXPOSE ${SERVICE_PORT}

# Command to run the application
CMD ["sh", "-c", "if [ \"$OTEL_ENABLED\" != \"false\" ]; then java -javaagent:/app/opentelemetry-javaagent.jar -Dotel.traces.exporter=${OTEL_TRACES_EXPORTER} -Dotel.metrics.exporter=${OTEL_METRICS_EXPORTER} -Dotel.logs.exporter=${OTEL_LOGS_EXPORTER} -Dotel.exporter.otlp.endpoint=${OTEL_COLLECTOR_ENDPOINT} -Dotel.service.name=${OTEL_SERVICE_NAME} -jar /app/app.jar; else java -jar /app/app.jar; fi"]