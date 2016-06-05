# WEB SERVICES
Spring Integration Web Services support
#### Spring 3
Examples using Spring 3 release:

* [v3-spring-ws] - Shows how to implement a web service with the Spring-WS module. The example starts with a sample XML document and uses it to generate the contract. This project uses JAXB2 to generate the Java objects.
  * Blog post: http://xpadro.blogspot.com.es/2013/09/creating-contract-first-web-services_30.html

* [v3-ws-retry-adv] - Allows to retry a web service operation using RequestHandlerRetryAdvice. The project spring-ws from this same repository needs to be deployed on the server to test a succesful invocation. Otherwise, it will keep retrying invocations until it reaches a max retries limit. If the limit is reached, it will store the request to a mongoDB database (a mongod instance needs to be running).
  * Blog post: http://xpadro.blogspot.com.es/2013/12/retry-web-service-operations-with.html

* [v3-ws-retry] - A custom version of the int-ws-retry-adv project without the support of Spring Retry. This application uses a cron trigger with an inbound adapter to accomplish the same functionality.



   [v3-spring-ws]: https://github.com/xpadro/spring-integration/tree/master/webservices/v3-spring-ws
   [v3-ws-retry-adv]: https://github.com/xpadro/spring-integration/tree/master/webservices/v3-ws-retry-adv
   [v3-ws-retry]: https://github.com/xpadro/spring-integration/tree/master/webservices/v3-ws-retry
   
