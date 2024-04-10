## MULTITENANT-EXAMPLE
This repository contains the code examples from the presentation "Multi-tenant Architecture As a microservice evolution" (https://docs.google.com/presentation/d/1fp0FbZLtG6we5SQzpifGp3F5S__hlV_HnmNfbR5MTZs/edit?usp=sharing)

## Repository Contents:
- ms-user: Sprintboot project without support for multitenants.
- ms-user-t: Sprintboot project with support for multitenants.

## Request para probar el servicio:
curl --location --request POST 'http://localhost:8080/api/v1/users' \
--header 'Content-Type: application/json' \
--header 'x-apikey: apikey-1' \
--data-raw '{
    "documentNumber":"7777777",
    "name":"Name X"    
}'

# DATABASES 
CREATE DATABASE db_users;
use  db_users;
CREATE TABLE user
(
   "id" int PRIMARY KEY NOT NULL,
   "document_number" varchar(50) NOT NULL,
   "name" varchar(50) NOT NULL
)
;


CREATE DATABASE tenant_db_users; 
use  tenant_db_users;
CREATE TABLE user
(
   "id" int PRIMARY KEY NOT NULL,
   "document_number" varchar(50) NOT NULL,
   "name" varchar(50) NOT NULL
)
;
