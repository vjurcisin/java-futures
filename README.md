# java-futures

Simple implementation of shared counter in client-server application, with usage of 
Java's Futures and ExecutorService.

Server shared counter and clients try to get this value, which is increased. Clients are not blocking. 