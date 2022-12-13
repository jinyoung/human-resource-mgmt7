## How to run

- Run axon server and mysql firstly

```
cd infra
docker-compose up
```

## Build common API & Run each service

'''
cd common-api
mvn install
cd ..

cd vacation
mvn spring-boot:run
cd ..

cd schedule
mvn spring-boot:run
cd ..

cd employee
mvn spring-boot:run
cd ..

'''

## Test RSocket APIs

- Download RSocket client
```
wget -O rsc.jar https://github.com/making/rsc/releases/download/0.4.2/rsc-0.4.2.jar
```
- Subscribe the stream
```
java -jar rsc.jar --stream  --route vacations.all ws://localhost:8088/rsocket/vacations

java -jar rsc.jar --stream  --route schedules.all ws://localhost:8088/rsocket/schedules

java -jar rsc.jar --stream  --route employees.all ws://localhost:8088/rsocket/employees

```
