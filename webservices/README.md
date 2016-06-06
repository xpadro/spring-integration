# WEB SERVICES
Spring Integration Web Services support
#### Version 3
Examples using Spring Integration 3 release:

* [spring-ws] - Shows how to implement a web service with the Spring-WS module. The example starts with a sample XML document and uses it to generate the contract. This project uses JAXB2 to generate the Java objects.
  * Blog post: http://xpadro.blogspot.com.es/2013/09/creating-contract-first-web-services_30.html

* [ws-retry-adv] - Allows to retry a web service operation using RequestHandlerRetryAdvice. The project spring-ws from this same repository needs to be deployed on the server to test a succesful invocation. Otherwise, it will keep retrying invocations until it reaches a max retries limit. If the limit is reached, it will store the request to a mongoDB database (a mongod instance needs to be running).
  * Blog post: http://xpadro.blogspot.com.es/2013/12/retry-web-service-operations-with.html

* [ws-retry] - A custom version of the int-ws-retry-adv project without the support of Spring Retry. This application uses a cron trigger with an inbound adapter to accomplish the same functionality.

* [ws-timeout] - Example of how to configure a timeout for invoking a web service through a web service outbound gateway. The web service's source code can be found at https://github.com/xpadro/spring-samples/tree/master/spring-ws-courses
  * Blog post: http://xpadro.blogspot.com.es/2014/04/spring-integration-configure-web.html
  


   [spring-ws]: https://github.com/xpadro/spring-integration/tree/master/webservices/spring-ws
   [ws-retry-adv]: https://github.com/xpadro/spring-integration/tree/master/webservices/ws-retry-adv
   [ws-retry]: https://github.com/xpadro/spring-integration/tree/master/webservices/ws-retry
   [ws-timeout]: https://github.com/xpadro/spring-integration/tree/master/webservices/ws-timeout   
