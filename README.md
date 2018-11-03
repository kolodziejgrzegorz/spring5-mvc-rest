# spring5-mvc-rest

[![CircleCI](https://circleci.com/gh/kolodziejgrzegorz/spring5-mvc-rest.svg?style=svg)](https://circleci.com/gh/kolodziejgrzegorz/spring5-mvc-rest)
> Simple Spring Boot app with RESTFul api, MapStruct and H2 database.

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Inspiration](#inspiration)

## General info
Main purpose was to gain practise with creating Spring Boot RESTFul APIs. It provides the main functions for CRUD operations: create, read, update, patch and delete data from database.  

## Technologies
* Maven 3.5.0
* Spring Boot 2.0.0
* Spring Framework 5.0.4
* Java 1.8
* MapStruct 1.2.0.CR2
* H2 database 1.4.196
* Project Lombok 1.16.20

## Setup
1. Clone github repository <br />
2. Download maven dependencies <br />
3. Hit run button <br />
4. If IDE don't create mapper class from MapStruct execute -mvn compile in terminal 

## Features
* CRUD endpoints for customers and vendors
* Read endpoint for categories

## Inspiration
This app is basen on Spring Framework 5: Beginner to Guru course from udemy.com <br />
https://www.udemy.com/spring-framework-5-beginner-to-guru/
