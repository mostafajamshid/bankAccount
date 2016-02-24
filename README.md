

A simple example for combining JPA and JAX-RS to create RESTful service with (JAX_RS EJB JPA HSQLDB/MYSQL) without ui

# GamBank Account Sample

### This application features:

_1-add a new account to accounts_

_2-search account by account owner, account number or account type_

_3-delete one (or multiple accounts simultaneously) from the system_

_4-update account information in the system (except account number)_

_5-transfer money from one account to another one if the same type_



All action is done in rest way I explained in detail that:

**1-Get all account list (GET http://localhost:8080/gambank/api/account)**

`curl -X GET -H "Content-Type:application/json" -i localhost:8080/gambank/api/account `

![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/01getAllAcount.jpg)

**1-1-Get an account (GET http://localhost:8080/gambank/api/account/{id})**

`curl -X GET -H "Content-Type:application/json" -i localhost:8080/gambank/api/account/3 `
![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/02getAccount.jpg)

**2-Add a new account(POST http://localhost:8080/gambank/api/account)**

`curl X POST -H "Content-Type:application/json" -d '{"accountOwner": "Mostafa","accountType": "DEPOSIT","balance": 789000}' -i localhost:8080/gambank/api/account `
![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/03addAcount.jpg)
**3-Update an account(PUT http://localhost:8080/gambank/api/account/{id})**

`curl -X PUT -H 'Content-Type:application/json' -d '{"accountOwner": "Jmashid","accountType": "SAVING","balance": 10000}' -i localhost:8080/gambank/api/account/7 `
![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/04editAccount.jpg)
**4-Search an account(GET http://localhost:8080/gambank/api/account/query?owner={Owner}&number={Number} &type={Type})**

`curl -X GET -H 'Content-Type:application/json' -i localhost:8080/gambank/api/account/query?owner=m&number=3&type=DEPOSIT`
![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/07getByQuery.jpg)

**4-1-Search acounts by owner name(GET http://localhost:8080/gambank/api/account/byowner?owner={Owner}**

`curl -X GET -H 'Content-Type:application/json' -i localhost:8080/gambank/api/account/byowner?owner=Mostafa`
![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/08getByOwner.jpg)
**4-2-Search acounts by type name(GET http://localhost:8080/gambank/api/account/bytype?type={Type}**

`curl -X GET -H 'Content-Type:application/json' -i localhost:8080/gambank/api/account/bytype?type=SAVING`
![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/10getByType.jpg)
**4-3-Search acounts by account number(GET http://localhost:8080/gambank/api/account/bynumber?number={Number}**

`curl -X GET -H 'Content-Type:application/json' -i localhost:8080/gambank/api/account/bynumber?number=4 `
![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/09getByNumber.jpg)
**5-Delete account (DELETE localhost:8080/gambank/api/account/{id})**

`curl -X DELETE -H -i localhost:8080/gambank/api/account/7`
![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/05deleteAcount.jpg)

after delete 
![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/06deleteResult.jpg)

**5-1-Delete multiple account (GET localhost:8080/gambank/api/account/delete?{id}&{id}&{id}...)**


`curl -X GET -H -i localhost:8080/gambank/api/account/delete?id=1&id=2&id=3&id=4`

before bulk delete:

![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/14beforBulkDelete.jpg)

bulk delete:

![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/15bulkDelete.jpg)

after bulk delete:

![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/16afterBulkDelete.jpg)

**6-Transfer Money (GET localhost:8080/gambank/api/account/transfer?from={id}&to={id}&amount={amount})**

`curl -X GET -H 'Content-Type:application/json' -i localhost:8080/gambank/api/account/transfer?from=4&to=5&amount=1000 `

before transfer money:

![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/11beforTransfer.jpg)

transferring :

![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/12transfer.jpg)

after transfer money:

![](https://832701ebcfebacf4e434d5e275088f4195a609ad.googledrive.com/host/0ByvX9dE_kmzlRXhiSS1GY0QzLTg/13afterTransfer.jpg)
If their type is different get message "Accounts Type dose not match for account: 4 and 5" 

## Sql for table

_`CREATE TABLE IF NOT EXISTS `account` ( `_
_``account_number` int(11) NOT NULL AUTO_INCREMENT, `_
_``account_owner` varchar(50) COLLATE utf8_persian_ci NOT NULL, `_
_``account_type` varchar(50) COLLATE utf8_persian_ci NOT NULL, `_
_``balance` double NOT NULL, `_
_`PRIMARY KEY (`account_number`) `_
_`) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;`_
