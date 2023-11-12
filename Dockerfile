FROM maven:amazoncorretto
WORKDIR /app/nutricraft-soap

COPY . .

RUN mvn clean package -DskipTests

ENTRYPOINT ["java", "-jar", "target/nutricraft-soap.jar"]