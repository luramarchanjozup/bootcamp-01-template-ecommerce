package br.com.zup.ecomerce.nicolle.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.zup.ecomerce.nicolle.repository.UsuariosRepository;

@EnableWebSecurity
@Configuration
@Profile("prod")
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	//configura autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
		
	}
	
	//configura autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable(); 
//tive que colocar esse cors porque, de alguma forma mesmo colocando os permit all, ele tava bloqueando os posts, 
		//não sei como resolver sem ele, coloquei em baixo o csrf.disable, mas algumas coisas continuam bloq.
		
		http.authorizeRequests()
		.antMatchers("/ecomerce/*").permitAll()
		.antMatchers("/ecomerce/usuarios").permitAll()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoTokenFilter(tokenService, usuariosRepository), UsernamePasswordAuthenticationFilter.class);
		
	}
	
	//configura recursos static
	@Override
	public void configure(WebSecurity web) throws Exception {
		
	}
	
}