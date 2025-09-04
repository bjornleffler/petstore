FROM openjdk:25-slim
# WORKDIR /app
COPY /petstore/target/openapi-spring-1.0.0.jar .
CMD ["java", "-jar", "openapi-spring-1.0.0.jar"]
