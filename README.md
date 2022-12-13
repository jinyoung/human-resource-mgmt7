## How to run

- Run axon server and mysql firstly

```
cd infra
docker-compose up
```

## Build common API & Run each service

'''
cd common-api
mvn clean install
cd ..

cd vacation
mvn clean spring-boot:run
cd ..

cd schedule
mvn clean spring-boot:run
cd ..

cd employee
mvn clean spring-boot:run
cd ..


cd gateway
mvn clean spring-boot:run
cd ..

cd frontend
npm i
npm run serve

'''

## Test By UI
Head to http://localhost:8088 with a web browser

## Test Rest APIs


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
