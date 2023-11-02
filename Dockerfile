FROM maven:latest
WORKDIR /app/nutricraft-soap

COPY . .

RUN mvn clean
RUN mvn compile
RUN mvn package

ENTRYPOINT ["java", "-jar", "target/nutricraft-soap.jar"]