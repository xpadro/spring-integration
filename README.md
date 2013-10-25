spring-integration
==================

Samples of different Spring Integration modules (jms, batch, integration)

- <b>jms-basic</b>: Shows how to configure a basic Spring JMS application, with examples on how to send and receive messages using point-to-point messaging (queues) and publish-subscribe messaging (topics).
  
  Blog post related to this project:
  http://xpadro.blogspot.com.es/2013/07/introduction-to-messaging-with-spring.html


- <b>jms-tx</b>: Contains examples reproducing the problems related to message processing and how to solve them with local JMS transactions. 

  Blog post related to this project:
  http://xpadro.blogspot.com.es/2013/08/spring-jms-processing-messages-within.html


- <b>spring-ws</b>: Shows how to implement a web service with the Spring-WS module. The example starts with a sample XML document and uses it to generate the contract. This project uses JAXB2 to generate the Java objects.

  Blog post related to this project:
  http://xpadro.blogspot.com.es/2013/09/creating-contract-first-web-services_30.html


- <b>int-rmi</b>: This project shows a client invoking a service over RMI with Spring Integration. The first client test class uses the MessagingTemplate to explicitly send its request to the message channel. The second client shows how to do it transparently with gateways.

  Blog post related to this project:
  http://xpadro.blogspot.com.es/2013/10/spring-integration-using-rmi-channel.html
