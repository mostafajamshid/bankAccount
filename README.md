
A simple sample for JAX_RS EJB JPA HSQLDB/MYSQL Rest 

GamBank Account Sample

This application features:
1-add a new account to accounts
2-search account by account owner, account number or account type
3-delete one (or multiple accounts simultaneously) from the system
4-update account information in the system (except account number)
5-transfer money from one account to another one if the same type



All action is done in rest way I explained in detail that:

1-Get all account list (GET http://localhost:8080/gambank/api/account)

curl -X GET -H "Content-Type:application/json" -i localhost:8080/gambank/api/account 


2-Add a new account (POST http://localhost:8080/gambank/api/account)

curl X POST -H "Content-Type:application/json" -d '{"accountOwner": "Mostafa","accountType": "DEPOSIT","balance": 789000}' -i localhost:8080/gambank/api/account 


3-Update an account(PUT http://localhost:8080/gambank/api/account/{id})

curl -X PUT -H 'Content-Type:application/json' -d '{"accountOwner": "Jmashid","accountType": "SAVING","balance": 10000}' -i localhost:8080/gambank/api/account/7 


4-Search an account(GET http://localhost:8080/gambank/api/account/query?owner={Owner}&number={Number} &type={Type})

curl -X GET -H 'Content-Type:application/json' -i localhost:8080/gambank/api/account/query?owner=m&number=3&type=DEPOSIT


4-1-Search acounts by owner name(GET http://localhost:8080/gambank/api/account/byowner?owner={Owner}

curl -X GET -H 'Content-Type:application/json' -i localhost:8080/gambank/api/account/byowner?owner=Mostafa


4-2-Search acounts by type name(GET http://localhost:8080/gambank/api/account/bytype?type={Type}

curl -X GET -H 'Content-Type:application/json' -i localhost:8080/gambank/api/account/bytype?type=SAVING


4-3-Search acounts by account number(GET http://localhost:8080/gambank/api/account/bynumber?number={Number}

curl -X GET -H 'Content-Type:application/json' -i localhost:8080/gambank/api/account/bynumber?number=4 

5-Delete account (DELETE localhost:8080/gambank/api/account/{id})
curl -X DELETE -H -i localhost:8080/gambank/api/account/17

5-1-Delete multiple account (GET localhost:8080/gambank/api/account/delete?{id}&{id}&{id}...)

curl -X GET -H -i localhost:8080/gambank/api/account/delete?id=1&id=2&id=3&id=4


6-Transfer Money (GET localhost:8080/gambank/api/account/transfer?from={id}&to={id}&amount={amount})

curl -X GET -H 'Content-Type:application/json' -i localhost:8080/gambank/api/account/transfer?from=4&to=5&amount=1000
 

If their type is different get message "Accounts Type dose not match for account: 4 and 5" 



Sql for table


CREATE TABLE IF NOT EXISTS `account` ( 

 `account_number` int(11) NOT NULL AUTO_INCREMENT, 

 `account_owner` varchar(50) COLLATE utf8_persian_ci NOT NULL,
 
`account_type` varchar(50) COLLATE utf8_persian_ci NOT NULL,
 
`balance` double NOT NULL, 
PRIMARY KEY (`account_number`) 
) 
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
