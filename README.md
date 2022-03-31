# CZ4013 Project : Distributed Banking System

## Server

### At-Least-Once
* Retransmit packet when packet time-out
* Error message send to user when client retry retransmission for 3 times

## Client

### At-Most-Once
* Dulipcate packet flitering with cache table
* if dulipicated packet is recieved, the server will not execute request

## To Run
Run server.java to run the server
Run mainfunction.java to run the client
