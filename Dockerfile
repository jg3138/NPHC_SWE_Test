# Spring Boot Payroll Application (NPHC Assignment)
# Stage 1
FROM openjdk:8-jdk-alpine AS builder
RUN java -version

COPY . /user/src/nphc/
WORKDIR /user/src/nphc/
RUN apk --no-cache add maven && mvn --version
RUN mvn package

# Stage 2
FROM openjdk:8-jdk-alpine
WORKDIR /root/
COPY --from=builder /user/src/nphc/target/nphc-1.0.jar .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./nphc-1.0.jar"]