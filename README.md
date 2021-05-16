
# Aplicatie Bursa de Transport


## Table of contents


### Application  description
* [General description](#general-description)
* [Technologies used](#technologies-used)
* [Signing up for an account and logging in](#signing-up-for-an-account-and-logging-in)
* [The Admin Account](#the-manager-account)
* [The Client Account](#the-client-account)

### Setup & Run

* [Prerequisites](#prerequisites)
* [Download & Run](#download--run)


## Application description

### General description

This application aims to facilitate the distribution of goods between companies, as well as increase the income of carriers, providing easy access. Such an application improves the operation of the supply-demand economic model.

### Technologies used
* Java 15
* JavaFX 15 - UI;
* Maven - dependencies and build tool;
* Nitrite - database;

### Signing up for an account and logging in

After registering, the user have to choose the role between:
* Transport client
* Trucking Operator

![Alt text](src/main/resources/login.png?raw=true "Login Page")

### Transport Client

After logging in, the transport client will be redirected to a home page. The following operations are available here:
* **Add goods to be transported**;
* **See a list of goods he created**;
* **View a list of proposed offers**, the transport client can accept either reject the offer;
* **View a list of closed transactions**;
* **Exit from the app**;

### Trucking Operator

After logging in, a Trucking Operator will be redirected to a home page. The following operations are available here:
* **Find goods to be transported**, where the trucking operator can propose an offer;
* **My Transactions List**, where the trucking operator can close the transaction, and give a rating to the transport client;
* **My Offers List**, where the trucking operator can see if his offer was accepted or rejected;
* **Exit from the app**;

## Setup & Run

### Prerequisites
* **Java 11 or higher**. To check your Java version you can run `java -version` in the command line;
* **Maven**. To check if you have Maven installed run `mvn -v` in the command line.
* **JavaFX**. Make sure you install JavaFX SDK on your machine, using the instructions provided in the [Official Documentation](https://openjfx.io/openjfx-docs/#install-javafx);

### Download & Run
To set up and run the project locally on your machine, please follow the next steps:

* **Clone the repository**. You can do this using `git clone https://github.com/fis2021/Aplicatie_Bursa_Transport.git`;
* **Verify that the project build locally**. Open the project folder in the command line, and you should be able to run `maven clean install`;
* **Open in Intellij IDEA**;
* **Run the project with Maven**. To start the project use `mvn javafx:run`
