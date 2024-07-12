# Discount Calculator

## Requirements
- Java 17 or higher
- Maven

## UML can be found in resources/templates

## Curl to test with Employee User
- curl -u employee:password -X POST http://localhost:8080/api/discount/calculate -H "Content-Type: application/json" -d '{
  "items": [
  {"name": "item1", "price": 200, "grocery": false},
  {"name": "item2", "price": 300, "grocery": true},
  {"name": "item3", "price": 500, "grocery": false}
  ]
  }'

## curl to test with Affiliate User
 - curl -u affiliate:password -X POST http://localhost:8080/api/discount/calculate -H "Content-Type: application/json" -d '{
   "items": [
   {"name": "item1", "price": 200, "grocery": false},
   {"name": "item2", "price": 300, "grocery": true},
   {"name": "item3", "price": 500, "grocery": false}
   ]
   }'

## curl to test with Loyal Customer User
- curl -u loyal_customer:password -X POST http://localhost:8080/api/discount/calculate -H "Content-Type: application/json" -d '{
  "items": [
  {"name": "item1", "price": 200, "grocery": false},
  {"name": "item2", "price": 300, "grocery": true},
  {"name": "item3", "price": 500, "grocery": false}
  ]
  }'

## curl to test with Regular Customer User
- curl -u customer:password -X POST http://localhost:8080/api/discount/calculate -H "Content-Type: application/json" -d '{
  "items": [
  {"name": "item1", "price": 200, "grocery": false},
  {"name": "item2", "price": 300, "grocery": true},
  {"name": "item3", "price": 500, "grocery": false}
  ]
  }'

## How to generate code coverage report
- mvn clean verify

## How to build the project
```bash
mvn clean install