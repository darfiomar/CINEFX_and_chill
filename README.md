# CINEFX AND CHILL

Cinema ticket desktop application. Written in jave SE / JavaFX
<img align="right" height="260" src="https://github.com/darfiomar/CINEFX_and_chill/blob/master/logo.png">

## Application sneak peeks

- Browse projectable movies :

![alt text](https://github.com/darfiomar/CINEFX_and_chill/blob/master/app-screenshots/movies.JPG)

- View more informations about a movie :

![alt text](https://github.com/darfiomar/CINEFX_and_chill/blob/master/app-screenshots/moreinfo.JPG)

- About the project :

![alt text](https://github.com/darfiomar/CINEFX_and_chill/blob/master/app-screenshots/about.JPG)

- Subscribe :

![alt text](https://github.com/darfiomar/CINEFX_and_chill/blob/master/app-screenshots/subscribe.JPG)

- Subscription Emails :

![alt text](https://github.com/darfiomar/CINEFX_and_chill/blob/master/app-screenshots/subsription-emails.JPG)

- Buy a ticket :

![alt text](https://github.com/darfiomar/CINEFX_and_chill/blob/master/app-screenshots/buyticket.JPG)

- Ticket reception email :

![alt text](https://github.com/darfiomar/CINEFX_and_chill/blob/master/app-screenshots/ticket-email.JPG)

- Generated ticket :

![alt text](https://github.com/darfiomar/CINEFX_and_chill/blob/master/app-screenshots/ticket.JPG)

- Administrator login :

![alt text](https://github.com/darfiomar/CINEFX_and_chill/blob/master/app-screenshots/adminlogin.JPG)

- Dashboard :

![alt text](https://github.com/darfiomar/CINEFX_and_chill/blob/master/app-screenshots/admindashboard.JPG)

### Prerequisites

Things you need to install for the application 

- At least JDK 8
- Eclipse IDE
- e(fx)clipse JavaFX 8.0 SDK
- WAMP,LAMP,MAMP or XAMP

 Note : all the required jars are in the jars folder.

### Configuration

 - MySQL settings :
 Open phpmyadmin ("localhost" , "root" ,""), create a new database (name : cinefx), import the cinefx.sql file.
 - Mailers settings :
 Open the CINEFX_and_chill/src/controllers/SendHTMLEmail.java file and affect your "GMAIL" and password to the final variables.
 Do the same for the CINEFX_and_chill/src/controllers/Subscribemailer.java file.
 PS: Don't forget to check this https://myaccount.google.com/lesssecureapps for your new gmail sender.
 
 ### Test

 - Use this credit card : 1111 2222 3333 4444        01/21       123   Or check CarteBancaires table for more test cards.
 - Use this amin ID : admin1@gmail.com     1234       Or check Administrateurs table for more admin test accounts.
 
### More : Specifications analysis and UML modeling

Specifications : View the CINEFX_and_chill/Cahier de charge.pdf file.
Modeling tool : Visual Paradigm.
Brief in some steps :

 - Specifications Textual Analysis :
 
 ![alt text](https://github.com/darfiomar/CINEFX_and_chill/blob/master/app-architecture/textual-analysis.PNG)
 
 - USE CASE Diagram :
 
 ![alt text](https://github.com/darfiomar/CINEFX_and_chill/blob/master/app-architecture/usecase%20diagram.PNG)
 
 - Classes Diagram :
 
 ![alt text](https://github.com/darfiomar/CINEFX_and_chill/blob/master/app-architecture/class%20diagram.PNG)
 
 - Entity Relationship Diagram :
 
 ![alt text](https://github.com/darfiomar/CINEFX_and_chill/blob/master/app-architecture/erd.PNG)
 
## Authors

* **National School of applied sciences** - *JAVA OOP project* - [DarfiOmar](https://github.com/darfiomar)

## License

This project is licensed under the CINEFX License - see the [LICENSE.md](LICENSE.md) file for details

