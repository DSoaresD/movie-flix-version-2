package com.devsuperior.movieflix.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	//implementaçao do servidor de recursos
	//primeiro override 2 metodos ResourceServerConfigurerAdapter com o botao direito 
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	
	private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**" };	
	private static final String[] VISITOR_OR_MEMBER = { "/genres/**","/movies/**" };

	
	

	//decodifica o token e valida.
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
		
	}

	//define quem pode acessar oque.
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		//liberar acesso ao H2
		//se nos profiles ativos conter o profile test faça...
		if(Arrays.asList(env.getActiveProfiles()).contains("test")){
			http.headers().frameOptions().disable();
		}
		
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()
		.antMatchers(HttpMethod.GET, VISITOR_OR_MEMBER).hasAnyRole("VISITOR","MEMBER")
		.antMatchers(VISITOR_OR_MEMBER).hasRole("MEMBER")
		.anyRequest().authenticated();
		
	}
	


}
