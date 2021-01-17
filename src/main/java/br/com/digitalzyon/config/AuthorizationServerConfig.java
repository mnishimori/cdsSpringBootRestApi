package br.com.digitalzyon.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("${security.jwt.signing-key}")
	private String signingKey;
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(this.accessTokenConverter()); 
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(this.signingKey);
		return tokenConverter;
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// para liberar o cors do oauth/token
		Map<String, CorsConfiguration> corsConfigMap = new HashMap<>();
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowedOrigins(Collections.singletonList("*"));
	    config.setAllowedMethods(Collections.singletonList("*"));
	    config.setAllowedHeaders(Collections.singletonList("*"));
	    corsConfigMap.put("/oauth/token", config);
	    
	    endpoints.getFrameworkEndpointHandlerMapping()
	            .setCorsConfigurations(corsConfigMap);
	    // para liberar o cors do oauth/token
	    
		endpoints
			.tokenStore(this.tokenStore())
			.accessTokenConverter(this.accessTokenConverter())
			.authenticationManager(this.authenticationManager);
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
			.withClient("my-angular-app")
			.secret("@321")
			.scopes("read","write")
			.authorizedGrantTypes("password")
			.accessTokenValiditySeconds(60 * 30);
		
	}

}
