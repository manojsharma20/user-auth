# user-auth

This repository contain two projects. 1) user-auth 2) user-auth-ui. 

1) user-auth is a spring boot based rest api, here I have used oauth2 to secure end point. this also contains data.sql and schema.sql for bootstraping intial data along with table structure. 
before running this project, be ensure that, I am using mysql as DB and before running this project, please change DB credentials and create userauth as database in mysql.

2) user_auth-ui: In this you don't need to do any thing. but before running this ui project, please ensure you have nodejs/npm install and run "npm install" command from terminal, so all library get install.
to run this project simply run "npm run start" command. This will start react application on port 3000. if you hit "http://localhost:3000" you will se home page. 
To login into application hit "http://localhost:3000/auth" and provide credentials as user id as "manojsharma20@gmail.com" and password as "manoj". you can change these credentials in data.sql file under user-auth project or directly go to DB and insert new record in respective tables.
If you try to hit "http://localhost:3000/dashboard" with login, then you resource will not be avilable.
