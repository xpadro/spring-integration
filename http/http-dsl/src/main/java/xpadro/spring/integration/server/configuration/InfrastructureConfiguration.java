package xpadro.spring.integration.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.gateway.MessagingGatewaySupport;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.integration.http.support.DefaultHttpHeaderMapper;
import org.springframework.integration.mapping.HeaderMapper;

import xpadro.spring.integration.server.model.ServerPerson;

@Configuration
@ComponentScan("xpadro.spring.integration.server")
@EnableIntegration
public class InfrastructureConfiguration {
	
	@Bean
	public IntegrationFlow httpGetFlow() {
		return IntegrationFlows.from(httpGetGate()).channel("httpGetChannel").handle("personEndpoint", "get").get();
	}

	@Bean
	public IntegrationFlow httpPostPutFlow() {
		return null;
		return IntegrationFlows.from(httpPostPutGate()).channel("routeRequest").route("headers.http_requestMethod",
				 m -> m.prefix("http").suffix("Channel")
                 .channelMapping("PUT", "Put")
                 .channelMapping("POST", "Post")
		).get();
	}

	@Bean
	public IntegrationFlow httpPostFlow() {
		return IntegrationFlows.from("httpPostChannel").handle("personEndpoint", "post").get();
	}

	@Bean
	public IntegrationFlow httpPutFlow() {
		return IntegrationFlows.from("httpPutChannel").handle("personEndpoint", "put").get();
	}

	@Bean
	public IntegrationFlow httpDeleteFlow() {
		return IntegrationFlows.from(httpDeleteGate()).channel("httpDeleteChannel").handle("personEndpoint", "delete").get();
	}

	@Bean
	public MessagingGatewaySupport httpGetGate() {
		HttpRequestHandlingMessagingGateway handler = new HttpRequestHandlingMessagingGateway();
		handler.setRequestMapping(createMapping(new HttpMethod[]{HttpMethod.GET}, "/persons/{personId}"));
		handler.setPayloadExpression(parser().parseExpression("#pathVariables.personId"));
		handler.setHeaderMapper(headerMapper());

		return handler;
	}

	@Bean
	public MessagingGatewaySupport httpPostPutGate() {
		HttpRequestHandlingMessagingGateway handler = new HttpRequestHandlingMessagingGateway();
		handler.setRequestMapping(createMapping(new HttpMethod[]{HttpMethod.PUT, HttpMethod.POST}, "/persons", "/persons/{personId}"));
		handler.setStatusCodeExpression(parser().parseExpression("T(org.springframework.http.HttpStatus).NO_CONTENT"));
		handler.setRequestPayloadType(ServerPerson.class);
		handler.setHeaderMapper(headerMapper());

		return handler;
	}

	@Bean
	public MessagingGatewaySupport httpDeleteGate() {
		HttpRequestHandlingMessagingGateway handler = new HttpRequestHandlingMessagingGateway();
		handler.setRequestMapping(createMapping(new HttpMethod[]{HttpMethod.DELETE}, "/persons/{personId}"));
		handler.setStatusCodeExpression(parser().parseExpression("T(org.springframework.http.HttpStatus).NO_CONTENT"));
		handler.setPayloadExpression(parser().parseExpression("#pathVariables.personId"));
		handler.setHeaderMapper(headerMapper());

		return handler;
	}

	@Bean
	public ExpressionParser parser() {
		return new SpelExpressionParser();
	}

	@Bean
	public HeaderMapper<HttpHeaders> headerMapper() {
		return new DefaultHttpHeaderMapper();
	}

	private RequestMapping createMapping(HttpMethod[] method, String... path) {
		RequestMapping requestMapping = new RequestMapping();
		requestMapping.setMethods(method);
		requestMapping.setConsumes("application/json");
		requestMapping.setProduces("application/json");
		requestMapping.setPathPatterns(path);

		return requestMapping;
	}
}
