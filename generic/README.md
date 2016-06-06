# GENERIC
Generic examples which do not apply to a specific section.
#### Version 4
Examples using Spring Integration 4 release:

* [int-v4-full] - Implemented with Spring 4.0.3 and Spring Integration 4.0, this project shows an example of configuring an integration application without using XML. The configuration is defined in @Configuration classes.
  * Blog post: http://xpadro.blogspot.com.es/2014/05/spring-integration-40-complete-xml-free.html
  
#### Version 3
Examples using Spring Integration 3 release:

* [int-jdbc-stored] - This project is configured to call a stored procedure from a MySQL database.
  
#### Version 2
Examples using Spring Integration 2 release:

* [int-error] - Implements and tests the different mechanisms for error handling in Spring Integration. Tests are splitted in two main areas; synchronous and asynchronous messaging.
  * Blog post: http://xpadro.blogspot.com.es/2013/11/how-error-handling-works-in-spring.html
 
* [http-dsl7] - An example of a web application that exposes a RESTful API using HTTP inbound channel adapters. The configuration of this application is fully implemented with JavaConfig and Spring Integration Java DSL. This example is implemented with a Java version prior to Java 8. Hence, it does not use lambdas. To check the Java 8 version example please select the int-http-dsl project.

* [http-dsl] - An example of a web application that exposes a RESTful API using HTTP inbound channel adapters. The configuration of this application is fully implemented with JavaConfig and Spring Integration Java DSL. This example is implemented with a Java 8 version, making use of lambdas.
  * Blog post: http://xpadro.blogspot.com/2014/12/exposing-http-restful-api-with-inbound_22.html



   [int-error]: https://github.com/xpadro/spring-integration/tree/master/generic/int-error
   [int-jdbc-stored]: https://github.com/xpadro/spring-integration/tree/master/generic/int-jdbc-stored
   [int-v4-full]: https://github.com/xpadro/spring-integration/tree/master/generic/int-v4-full

