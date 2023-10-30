FROM maven:latest

WORKDIR /app/nutricraft-soap

COPY . .

RUN mvn clean
RUN mvn compile
RUN mvn package

CMD ["java", "-jar", "./app/nutricraft-soap/target/nutricraft-soap.jar"]
