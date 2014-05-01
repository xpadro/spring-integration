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


- <b>int-error</b>: Implements and tests the different mechanisms for error handling in Spring Integration. Tests are splitted in two main areas; synchronous and asynchronous messaging.

  Blog post related to this project: 
  http://xpadro.blogspot.com.es/2013/11/how-error-handling-works-in-spring.html


- <b>int-ws-retry-adv</b>: Allows to retry a web service operation using RequestHandlerRetryAdvice. The project <k>spring-ws</k> from this same repository needs to be deployed on the server to test a succesful invocation. Otherwise, it will keep retrying invocations until it reaches a max retries limit. If the limit is reached, it will store the request to a mongoDB database (a mongod instance needs to be running). 

  Blog post related to this project:
  http://xpadro.blogspot.com.es/2013/12/retry-web-service-operations-with.html
  
  
- <b>int-ws-retry</b>: A custom version of the <k>int-ws-retry-adv</k> project without the support of Spring Retry. This application uses a cron trigger with an inbound adapter to accomplish the same functionality.


- <b>int-ws-timeout</b>: Example of how to configure a timeout for invoking a web service through a web service outbound gateway. The web service's source code can be found at https://github.com/xpadro/spring-samples/tree/master/spring-ws-courses

  Blog post related to this project:
  http://xpadro.blogspot.com.es/2014/04/spring-integration-configure-web.html
