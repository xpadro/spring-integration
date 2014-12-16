package xpadro.spring.integration.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpMethod;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.gateway.MessagingGatewaySupport;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;

@Configuration
@ComponentScan("xpadro.spring.integration.server")
@EnableIntegration
public class InfrastructureConfiguration {

	@Bean
	public ExpressionParser parser() {
		return new SpelExpressionParser();
	}
	
	@Bean
	public MessagingGatewaySupport httpGetGate() {
		HttpRequestHandlingMessagingGateway handler = new HttpRequestHandlingMessagingGateway();
		handler.setRequestMapping(createMapping(new HttpMethod[]{HttpMethod.GET}, "/persons/{personId}"));
		handler.setPayloadExpression(parser().parseExpression("#pathVariables.personId"));

		return handler;
	}
	
	@Bean
	public IntegrationFlow httpGetFlow() {
		return IntegrationFlows.from(httpGetGate()).channel("httpGetChannel").handle("personEndpoint", "get").get();
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
