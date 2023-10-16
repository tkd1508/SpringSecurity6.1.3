package com.io.securityInfrun.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.io.securityInfrun.web.user.service.CustomAuthenticationProvider;

import jakarta.servlet.DispatcherType;

@Configuration 
@EnableWebSecurity
@Order(1)
public class SecurityConfig {
	
	//@Autowired
	UserDetailsService userDetailsService;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(userDetailsService); //구현체 내부에서 userDetailsService 사용하니까 provider 검증 클래스가 만들어진 이후에는 안써도 됨. 
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		return new CustomAuthenticationProvider();
	}
	
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
     //antMatchers 부분도 deprecated 되어 requestMatchers로 대체
       return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/login.do", "/css/**"), new AntPathRequestMatcher("/"), new AntPathRequestMatcher("/js/**", "/images/**"), new AntPathRequestMatcher("/webjars/**", "/**/favicon.ico"), new AntPathRequestMatcher("/WEB-INF/**", "/view/**"));
    }
	
    @Bean
    public SecurityFilterChain allfilterChain(HttpSecurity http) throws Exception { 
	   
	   /*
        http.authorizeRequests()
        	.requestMatchers("/login").permitAll()
        	.requestMatchers("/user").hasRole("USER") //  /user경로에 해당하는 페이지에는 user 권한이 있는지 확인한다.
        	.requestMatchers("/admin/pay").hasRole("ADMIN")
        	.requestMatchers("/admin/**").access("hasRozle('ADMIN') or hasRole('SYS')") // 둘다 가능하지만, pay는 어드민에게 + 권한 추가
            .anyRequest().authenticated() //들어오는 request정보에. 모두 인증를 통과해야 건너갈수있다. (인가 정책)
        ;
        */
        
	   /* 다중 로그인 셋팅 테스트 _ 근데 안됨.... 
	   http.authorizeRequests()
	   	.anyRequest().permitAll()
	   .and()
	   .formLogin() 
	   ;
       */
	   /*
	   http.authorizeRequests().requestMatchers("/login").permitAll()
	   .requestMatchers("/admin/**").access("hasRozle('ADMIN') or hasRole('SYS')")
	       .anyRequest().authenticated(); // 이게 어떻게 흘러가는지에 대한 흐름 파악
        
        
        http.formLogin() // 기본적으로 폼로그인 방식으로 인증을 하겠다.
	        //.loginPage("/loginPage") // 사용자 정의 로그인 페이지
	        .defaultSuccessUrl("/") // 로그인 성공 후 이동 페이지
	        .failureUrl("/login") // 로그인 실패 후 이동 페이지
	        .usernameParameter("userid") // 아이디 파라미터명 설정
	        .passwordParameter("passwd") // 패스워드 파라미터명 설정
	        .loginProcessingUrl("/login_proc") // 폼 테그 액션 url
	        .successHandler(new AuthenticationSuccessHandler() {
				
				@Override
				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) throws IOException, ServletException {
					System.out.println("Authentication : " + authentication.getName()); //현재 인증이 성공한 사용자 이름
					
					//로그인이 성공하면 캐시로 저장이 되어지는 로직이 있는데 이거를 꺼내오기 위한 방법
					RequestCache requestCache = new HttpSessionRequestCache();
					SavedRequest savedRequest = requestCache.getRequest(request, response); // 세션에서 저장되어있는 정보를 꺼내오는거야
					
					if(savedRequest != null) {
						String redirectUrl = savedRequest.getRedirectUrl();
						System.out.println("redirectUrl : "+redirectUrl);
						response.sendRedirect(redirectUrl); //성공 후 루트페이지로 이동
					}else {
						System.out.println("savedRequest : null");
						response.sendRedirect("/"); //성공 후 루트페이지로 이동
					}
					
					
					
				}
			})
	        .failureHandler(new AuthenticationFailureHandler() {
				
				@Override
				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
						AuthenticationException exception) throws IOException, ServletException {
					// TODO Auto-generated method stub
					System.out.println("exception : "+exception.getMessage()); //예외 발생 메세지 출력
					response.sendRedirect("/login"); //실패 후 다시 로그인 페이지로 이동
				}
			})
	        .permitAll()
	        //.authenticationDetailsSource(authenticationDetailsSource)
        
        ; // 기본적으로 폼로그인 방식으로 인증을 하겠다. http.formLogin()
        
        
        
        http.logout() // 시큐리티 로그아웃은 기본적으로 post 방식이다.
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login") //로그아웃 성공 후 다시 로그인 페이지로 이동
        .addLogoutHandler(new LogoutHandler() {
			
			@Override
			public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
				// TODO Auto-generated method stub
				HttpSession session = request.getSession();
				session.invalidate(); // 이런식으로 세션을 무효화 하는 방식이야.
				
			}
		}) // 우리가 마음대로 로그아웃 핸들러를 추가 및 조작 가능하지만, 기본적으로는 세션을 무효화 하기 위해서 사용한다. 그 외의 기능을 추가하고 싶으면 자기가 추가시키자.
        .logoutSuccessHandler(new LogoutSuccessHandler() {
			
			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException {
				// TODO Auto-generated method stub
				response.sendRedirect("/login"); // 이렇게 로그인 페이지로 이동하는 것 외에 다양한 기능을 여기에 추가 시키는 것이 가능할 듯 하다.
			}
		}) // 위의 logoutSuccessUrl과 같은 기능이지만, 위에는 경로 설정만 세팅하는 것이고 핸들러가 해당 경로를 입력받아서 실제 실행하는 함수이다.
        //.deleteCookies("remember-me") //기본적으로 서버에서 발급하는 쿠키임(아이디 기억하기 버튼). 나중에 다른 쿠키도 여기서 설정후 삭제 가능.
        .and()
        .rememberMe()
        .rememberMeParameter("remember") // jsp 체크박스 파라미터와 동일하게 일치 시켜줘야 하는 부분
        .tokenValiditySeconds(3600) //만료시간을 설정할 수 있음. 기본 14일.
        .userDetailsService(userDetailsService); // rememberMe 인증시 user객체 로그인 정보를 가져오는 서비스, 미리 위에서 autowired 해놓음.
        
        
        
        http.sessionManagement() // 동시 세션 제어 매니저
        .sessionFixation().changeSessionId() // 기본으로 해주기는 하지만, 우리는 연습이니까 알아보기 위해 적어두자. 세션 고정 공격을 막기 위한 방법.
        .maximumSessions(1) // 동시 세션의 개수 설정 -1일 경우 무한 가능. 근데 기본 1이야.
        .maxSessionsPreventsLogin(false); // 하지만 위에 1개로 설정을 해 놨기 때문에 전에 세션은 만료가 되었을 것이야. true(더이상 세션 생성 금지), false(이전 세션 없앰)
        
        
        http.exceptionHandling() // 인증예외가 발생했을시 처리하기 위한 메소드
//        .authenticationEntryPoint(new AuthenticationEntryPoint() {
//			
//			@Override
//			public void commence(HttpServletRequest request, HttpServletResponse response,
//					AuthenticationException authException) throws IOException, ServletException {
//				// TODO Auto-generated method stub
//				response.sendRedirect("/login"); // 인증이 안되었으니까 다시 인증하고 오라고 로그인 페이지로 돌려보내는 작동 근데 이거는 우리가 직접 만든 로그인 페이지로 이동하도록 설정해놓은 것이다. 시큐리티에서 제공하는 로그인 페이지가 아님. 
//			}
//		}) //인증이 예외되었을때 설정 핸들러
        .accessDeniedHandler(new AccessDeniedHandler() {
			
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				// TODO Auto-generated method stub
				response.sendRedirect("/denied"); // 해당 권한으로는 입장이 불가합니다 페이지를 뛰우기 위한 로직
			}
		}); //인가 예외가 발생하였을 경우의 핸들러로 내가 다른 권한 페이지를 침범하였을때 발생시키는 로직 설정
        	
        //SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL); // 이렇게 설정하면 메인과 자식에서의 시큐리티 정보는 쓰레드에서 이어진다. 상속 가능하게 됨.
        */
	   
    	
    	/*
	   http.csrf().disable();
	   
	   // 지금부터 프로젝트 셋팅을 시작한다
	   
	   http.authorizeRequests()
	   		.requestMatchers(new AntPathRequestMatcher("/")).permitAll()                   // 기본 경로는 로그인을 안하고도 볼 수 있어야함
	   		.requestMatchers(new AntPathRequestMatcher("/user/*")).hasRole("USER")         //유저 권한이 있는 사람만 접근 가능
	   		.requestMatchers(new AntPathRequestMatcher("/userInfo.do")).hasRole("USER")
	   		.requestMatchers(new AntPathRequestMatcher("/messages")).hasRole("MANAGER")
	   		.requestMatchers(new AntPathRequestMatcher("/config")).hasRole("ADMIN")
	   		.anyRequest().authenticated()
	   	.and()
	   		.formLogin(); 
	   */
	   
    	http.csrf().disable().cors().disable()
        .authorizeHttpRequests(request -> request
        	    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() // 맨 처음
				.requestMatchers(new AntPathRequestMatcher("/user/**"), new AntPathRequestMatcher("/login-process"), 
						new AntPathRequestMatcher("/"), new AntPathRequestMatcher("/status/**"), new AntPathRequestMatcher("/images/**"), 
						new AntPathRequestMatcher("/login.do"), new AntPathRequestMatcher("/auth/join"), new AntPathRequestMatcher("/js/**"), 
						new AntPathRequestMatcher("/util/**")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/user2.do"), new AntPathRequestMatcher("/user2Info.do")).hasRole("ADMIN")
                .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
        )
        .formLogin(login -> login	// form 방식 로그인 사용
        		.loginPage("/login.do")	// [A] 커스텀 로그인 페이지 지정
                .loginProcessingUrl("/login-process.do")	// [B] submit 받을 url
                .usernameParameter("username")	// [C] submit할 아이디
                .passwordParameter("password")	// [D] submit할 비밀번호
                .defaultSuccessUrl("/", true)	// 로그인 성공 시 /user/userInfo
                .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
        )
        .logout(withDefaults());	// 로그아웃은 기본설정으로 (/logout으로 인증해제)
	   
	   
        
      return http.build();
   }
   /*
   @Bean
   static final public InMemoryUserDetailsManager kk() { //DB연동을 안할 경우, 테스트 용으로 하는 것이다.
	   
       UserDetails user = User.withDefaultPasswordEncoder()
           .username("user")
           .password("1111")
           .roles("USER")
           .build();
       UserDetails admin = User.withDefaultPasswordEncoder()
           .username("admin")
           .password("1111")
           //.roles("ADMIN","USER")
           .roles("ADMIN","MANAGER","USER")
           .build();
       UserDetails manager = User.withDefaultPasswordEncoder()
           .username("manager")
           .password("1111")
           .roles("MANAGER","USER")
           .build();
       return new InMemoryUserDetailsManager(user, admin, manager);
   }
   */
   
    
   @Bean
   public PasswordEncoder getPasswordEncoder() {
      return new BCryptPasswordEncoder();
   }
   
   /*
   @Bean
   PasswordEncoder passwordEncoder() {
       return new SimplePasswordEncoder();
   }
   */
    /*
    @Bean
    PasswordEncoder passwordEncoder() {
        return new PasswordEncoderFactories().createDelegatingPasswordEncoder();
    }
   */
}

@Configuration
@Order(2)
class config2 {
	/* 다중 로그인 테스트 _ 근데 안됨...
	@Bean
	public SecurityFilterChain adminfilterChain(HttpSecurity http) throws Exception { 
		
		
		http.authorizeRequests()
    	.requestMatchers("/admin/**")
        .authenticated()
        .and()
        .httpBasic()
    ;
		
		
		return http.build();
	}
	*/
}
