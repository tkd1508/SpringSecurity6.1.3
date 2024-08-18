package com.io.securityInfrun.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.io.securityInfrun.util.UISMap;
import com.io.securityInfrun.util.handler.CostomAccessDeniedHandler;
import com.io.securityInfrun.web.urlPathAuth.service.UrlPathAuthService;
import com.io.securityInfrun.web.user.service.CustomAuthenticationProvider;

import jakarta.servlet.DispatcherType;

@Order(1)
@Configuration 
@EnableWebSecurity
public class SecurityConfig {
	
	//@Autowired
	//UserDetailsService userDetailsService;
	
	private final UrlPathAuthService urlPathAuthService;
	
	public SecurityConfig(UrlPathAuthService urlPathAuthService) {
		super();
		this.urlPathAuthService = urlPathAuthService;
	}

	@Autowired
	private AuthenticationDetailsSource authenticationDetailsSource;
	
	@Autowired
	private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler customAuthenticationFailureHandler;
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		CostomAccessDeniedHandler accessDeniedHandler = new CostomAccessDeniedHandler();
		accessDeniedHandler.setErrorPage("/denied.do");
		return accessDeniedHandler;
	}
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
     //antMatchers 부분도 deprecated 되어 requestMatchers로 대체
       return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/login.do", "/css/**"), new AntPathRequestMatcher("/"), new AntPathRequestMatcher("/js/**", "/images/**"), new AntPathRequestMatcher("/webjars/**", "/**/favicon.ico"), new AntPathRequestMatcher("/WEB-INF/**", "/view/**"), new AntPathRequestMatcher("/sample/**"));
    }
	
	/*
	@Bean
    public AuthenticationProvider customAuthenticationProvider() {
       return new CustomAuthenticationProvider();
    }*/
	/*
	@Bean
	  public AuthenticationManager authenticationManager() throws Exception {
	    ProviderManager providerManager = (ProviderManager) authenticationConfiguration.getAuthenticationManager();
	    providerManager.getProviders().add(this.customAuthenticationProvider);
	    return authenticationConfiguration.getAuthenticationManager();
	  }
	*/
	/*
	@Bean
    public AuthenticationManager customAuthorizationFilter(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .authenticationProvider(ajaxAuthenticationProvider())
                   .build();
    }
	*/
	
	/*
	@Bean
	public CustomAuthorizationFilter customAuthorizationFilter() {
		CustomAuthorizationFilter customAuthorizationFilter = new CustomAuthorizationFilter();
		
		return customAuthorizationFilter;
	}
	*/
	
    @SuppressWarnings("unchecked")
	@Bean
    public SecurityFilterChain allfilterChain(HttpSecurity http) throws Exception {

        /*
         http.authorizeRequests()
             .requestMatchers("/login").permitAll()
             .requestMatchers("/user").hasRole("USER") //  /user°æ·Î¿¡ ÇØ´çÇÏ´Â ÆäÀÌÁö¿¡´Â user ±ÇÇÑÀÌ ÀÖ´ÂÁö È®ÀÎÇÑ´Ù.
             .requestMatchers("/admin/pay").hasRole("ADMIN")
             .requestMatchers("/admin/**").access("hasRozle('ADMIN') or hasRole('SYS')") // µÑ´Ù °¡´ÉÇÏÁö¸¸, pay´Â ¾îµå¹Î¿¡°Ô + ±ÇÇÑ Ãß°¡
             .anyRequest().authenticated() //µé¾î¿À´Â requestÁ¤º¸¿¡. ¸ðµÎ ÀÎÁõ¸¦ Åë°úÇØ¾ß °Ç³Ê°¥¼öÀÖ´Ù. (ÀÎ°¡ Á¤Ã¥)
         ;
         */
        
        /* ´ÙÁß ·Î±×ÀÎ ¼ÂÆÃ Å×½ºÆ® _ ±Ùµ¥ ¾ÈµÊ.... 
        http.authorizeRequests()
         .anyRequest().permitAll()
        .and()
        .formLogin() 
        ;
        */
        /*
        http.authorizeRequests().requestMatchers("/login").permitAll()
        .requestMatchers("/admin/**").access("hasRozle('ADMIN') or hasRole('SYS')")
            .anyRequest().authenticated(); // ÀÌ°Ô ¾î¶»°Ô Èê·¯°¡´ÂÁö¿¡ ´ëÇÑ Èå¸§ ÆÄ¾Ç
         
         
         http.formLogin() // ±âº»ÀûÀ¸·Î Æû·Î±×ÀÎ ¹æ½ÄÀ¸·Î ÀÎÁõÀ» ÇÏ°Ú´Ù.
             //.loginPage("/loginPage") // »ç¿ëÀÚ Á¤ÀÇ ·Î±×ÀÎ ÆäÀÌÁö
             .defaultSuccessUrl("/") // ·Î±×ÀÎ ¼º°ø ÈÄ ÀÌµ¿ ÆäÀÌÁö
             .failureUrl("/login") // ·Î±×ÀÎ ½ÇÆÐ ÈÄ ÀÌµ¿ ÆäÀÌÁö
             .usernameParameter("userid") // ¾ÆÀÌµð ÆÄ¶ó¹ÌÅÍ¸í ¼³Á¤
             .passwordParameter("passwd") // ÆÐ½º¿öµå ÆÄ¶ó¹ÌÅÍ¸í ¼³Á¤
             .loginProcessingUrl("/login_proc") // Æû Å×±× ¾×¼Ç url
             .successHandler(new AuthenticationSuccessHandler() {
                 
                 @Override
                 public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                         Authentication authentication) throws IOException, ServletException {
                     System.out.println("Authentication : " + authentication.getName()); //ÇöÀç ÀÎÁõÀÌ ¼º°øÇÑ »ç¿ëÀÚ ÀÌ¸§
                     
                     //·Î±×ÀÎÀÌ ¼º°øÇÏ¸é Ä³½Ã·Î ÀúÀåÀÌ µÇ¾îÁö´Â ·ÎÁ÷ÀÌ ÀÖ´Âµ¥ ÀÌ°Å¸¦ ²¨³»¿À±â À§ÇÑ ¹æ¹ý
                     RequestCache requestCache = new HttpSessionRequestCache();
                     SavedRequest savedRequest = requestCache.getRequest(request, response); // ¼¼¼Ç¿¡¼­ ÀúÀåµÇ¾îÀÖ´Â Á¤º¸¸¦ ²¨³»¿À´Â°Å¾ß
                     
                     if(savedRequest != null) {
                         String redirectUrl = savedRequest.getRedirectUrl();
                         System.out.println("redirectUrl : "+redirectUrl);
                         response.sendRedirect(redirectUrl); //¼º°ø ÈÄ ·çÆ®ÆäÀÌÁö·Î ÀÌµ¿
                     }else {
                         System.out.println("savedRequest : null");
                         response.sendRedirect("/"); //¼º°ø ÈÄ ·çÆ®ÆäÀÌÁö·Î ÀÌµ¿
                     }
                     
                     
                     
                 }
             })
             .failureHandler(new AuthenticationFailureHandler() {
                 
                 @Override
                 public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException exception) throws IOException, ServletException {
                     // TODO Auto-generated method stub
                     System.out.println("exception : "+exception.getMessage()); //¿¹¿Ü ¹ß»ý ¸Þ¼¼Áö Ãâ·Â
                     response.sendRedirect("/login"); //½ÇÆÐ ÈÄ ´Ù½Ã ·Î±×ÀÎ ÆäÀÌÁö·Î ÀÌµ¿
                 }
             })
             .permitAll()
             //.authenticationDetailsSource(authenticationDetailsSource)
         
         ; // ±âº»ÀûÀ¸·Î Æû·Î±×ÀÎ ¹æ½ÄÀ¸·Î ÀÎÁõÀ» ÇÏ°Ú´Ù. http.formLogin()
         
         
         
         http.logout() // ½ÃÅ¥¸®Æ¼ ·Î±×¾Æ¿ôÀº ±âº»ÀûÀ¸·Î post ¹æ½ÄÀÌ´Ù.
         .logoutUrl("/logout")
         .logoutSuccessUrl("/login") //·Î±×¾Æ¿ô ¼º°ø ÈÄ ´Ù½Ã ·Î±×ÀÎ ÆäÀÌÁö·Î ÀÌµ¿
         .addLogoutHandler(new LogoutHandler() {
             
             @Override
             public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                 // TODO Auto-generated method stub
                 HttpSession session = request.getSession();
                 session.invalidate(); // ÀÌ·±½ÄÀ¸·Î ¼¼¼ÇÀ» ¹«È¿È­ ÇÏ´Â ¹æ½ÄÀÌ¾ß.
                 
             }
         }) // ¿ì¸®°¡ ¸¶À½´ë·Î ·Î±×¾Æ¿ô ÇÚµé·¯¸¦ Ãß°¡ ¹× Á¶ÀÛ °¡´ÉÇÏÁö¸¸, ±âº»ÀûÀ¸·Î´Â ¼¼¼ÇÀ» ¹«È¿È­ ÇÏ±â À§ÇØ¼­ »ç¿ëÇÑ´Ù. ±× ¿ÜÀÇ ±â´ÉÀ» Ãß°¡ÇÏ°í ½ÍÀ¸¸é ÀÚ±â°¡ Ãß°¡½ÃÅ°ÀÚ.
         .logoutSuccessHandler(new LogoutSuccessHandler() {
             
             @Override
             public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                     throws IOException, ServletException {
                 // TODO Auto-generated method stub
                 response.sendRedirect("/login"); // ÀÌ·¸°Ô ·Î±×ÀÎ ÆäÀÌÁö·Î ÀÌµ¿ÇÏ´Â °Í ¿Ü¿¡ ´Ù¾çÇÑ ±â´ÉÀ» ¿©±â¿¡ Ãß°¡ ½ÃÅ°´Â °ÍÀÌ °¡´ÉÇÒ µí ÇÏ´Ù.
             }
         }) // À§ÀÇ logoutSuccessUrl°ú °°Àº ±â´ÉÀÌÁö¸¸, À§¿¡´Â °æ·Î ¼³Á¤¸¸ ¼¼ÆÃÇÏ´Â °ÍÀÌ°í ÇÚµé·¯°¡ ÇØ´ç °æ·Î¸¦ ÀÔ·Â¹Þ¾Æ¼­ ½ÇÁ¦ ½ÇÇàÇÏ´Â ÇÔ¼öÀÌ´Ù.
         //.deleteCookies("remember-me") //±âº»ÀûÀ¸·Î ¼­¹ö¿¡¼­ ¹ß±ÞÇÏ´Â ÄíÅ°ÀÓ(¾ÆÀÌµð ±â¾ïÇÏ±â ¹öÆ°). ³ªÁß¿¡ ´Ù¸¥ ÄíÅ°µµ ¿©±â¼­ ¼³Á¤ÈÄ »èÁ¦ °¡´É.
         .and()
         .rememberMe()
         .rememberMeParameter("remember") // jsp Ã¼Å©¹Ú½º ÆÄ¶ó¹ÌÅÍ¿Í µ¿ÀÏÇÏ°Ô ÀÏÄ¡ ½ÃÄÑÁà¾ß ÇÏ´Â ºÎºÐ
         .tokenValiditySeconds(3600) //¸¸·á½Ã°£À» ¼³Á¤ÇÒ ¼ö ÀÖÀ½. ±âº» 14ÀÏ.
         .userDetailsService(userDetailsService); // rememberMe ÀÎÁõ½Ã user°´Ã¼ ·Î±×ÀÎ Á¤º¸¸¦ °¡Á®¿À´Â ¼­ºñ½º, ¹Ì¸® À§¿¡¼­ autowired ÇØ³õÀ½.
         
         
         
         http.sessionManagement() // µ¿½Ã ¼¼¼Ç Á¦¾î ¸Å´ÏÀú
         .sessionFixation().changeSessionId() // ±âº»À¸·Î ÇØÁÖ±â´Â ÇÏÁö¸¸, ¿ì¸®´Â ¿¬½ÀÀÌ´Ï±î ¾Ë¾Æº¸±â À§ÇØ Àû¾îµÎÀÚ. ¼¼¼Ç °íÁ¤ °ø°ÝÀ» ¸·±â À§ÇÑ ¹æ¹ý.
         .maximumSessions(1) // µ¿½Ã ¼¼¼ÇÀÇ °³¼ö ¼³Á¤ -1ÀÏ °æ¿ì ¹«ÇÑ °¡´É. ±Ùµ¥ ±âº» 1ÀÌ¾ß.
         .maxSessionsPreventsLogin(false); // ÇÏÁö¸¸ À§¿¡ 1°³·Î ¼³Á¤À» ÇØ ³ù±â ¶§¹®¿¡ Àü¿¡ ¼¼¼ÇÀº ¸¸·á°¡ µÇ¾úÀ» °ÍÀÌ¾ß. true(´õÀÌ»ó ¼¼¼Ç »ý¼º ±ÝÁö), false(ÀÌÀü ¼¼¼Ç ¾ø¾Ú)
         
         
         http.exceptionHandling() // ÀÎÁõ¿¹¿Ü°¡ ¹ß»ýÇßÀ»½Ã Ã³¸®ÇÏ±â À§ÇÑ ¸Þ¼Òµå
           .authenticationEntryPoint(new AuthenticationEntryPoint() {
        //			@Override
        blic void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
        / TODO Auto-generated method stub
        esponse.sendRedirect("/login"); // ÀÎÁõÀÌ ¾ÈµÇ¾úÀ¸´Ï±î ´Ù½Ã ÀÎÁõÇÏ°í ¿À¶ó°í ·Î±×ÀÎ ÆäÀÌÁö·Î µ¹·Áº¸³»´Â ÀÛµ¿ ±Ùµ¥ ÀÌ°Å´Â ¿ì¸®°¡ Á÷Á¢ ¸¸µç ·Î±×ÀÎ ÆäÀÌÁö·Î ÀÌµ¿ÇÏµµ·Ï ¼³Á¤ÇØ³õÀº °ÍÀÌ´Ù. ½ÃÅ¥¸®Æ¼¿¡¼­ Á¦°øÇÏ´Â ·Î±×ÀÎ ÆäÀÌÁö°¡ ¾Æ´Ô. 
        
        //ÀÎÁõÀÌ ¿¹¿ÜµÇ¾úÀ»¶§ ¼³Á¤ ÇÚµé·¯
         .accessDeniedHandler(new AccessDeniedHandler() {
             
             @Override
             public void handle(HttpServletRequest request, HttpServletResponse response,
                     AccessDeniedException accessDeniedException) throws IOException, ServletException {
                 // TODO Auto-generated method stub
                 response.sendRedirect("/denied"); // ÇØ´ç ±ÇÇÑÀ¸·Î´Â ÀÔÀåÀÌ ºÒ°¡ÇÕ´Ï´Ù ÆäÀÌÁö¸¦ ¶Ù¿ì±â À§ÇÑ ·ÎÁ÷
             }
         }); //ÀÎ°¡ ¿¹¿Ü°¡ ¹ß»ýÇÏ¿´À» °æ¿ìÀÇ ÇÚµé·¯·Î ³»°¡ ´Ù¸¥ ±ÇÇÑ ÆäÀÌÁö¸¦ Ä§¹üÇÏ¿´À»¶§ ¹ß»ý½ÃÅ°´Â ·ÎÁ÷ ¼³Á¤
             
         //SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL); // ÀÌ·¸°Ô ¼³Á¤ÇÏ¸é ¸ÞÀÎ°ú ÀÚ½Ä¿¡¼­ÀÇ ½ÃÅ¥¸®Æ¼ Á¤º¸´Â ¾²·¹µå¿¡¼­ ÀÌ¾îÁø´Ù. »ó¼Ó °¡´ÉÇÏ°Ô µÊ.
         */
       
        
    
	   // 지금부터 프로젝트 셋팅을 시작한다
	   /*
	   http.authorizeRequests()
	   		.requestMatchers(new AntPathRequestMatcher("/")).permitAll()                   // 기본 경로는 로그인을 안하고도 볼 수 있어야함
	   		.requestMatchers(new AntPathRequestMatcher("/user/*")).hasRole("USER")         //유저 권한이 있는 사람만 접근 가능
	   		.requestMatchers(new AntPathRequestMatcher("/userInfo.do")).hasRole("USER")
	   		.requestMatchers(new AntPathRequestMatcher("/messages")).hasRole("MANAGER")
	   		.requestMatchers(new AntPathRequestMatcher("/config")).hasRole("ADMIN")
	   		.anyRequest().authenticated()
	   	.and()
	   		.formLogin()
	   */
    	
    	//큰 범위가 아래로 와야하고 작은 범위가 위로가야한다. (관리자가 위에 사용자가 아래로 가야한다.)
    	ArrayList<UISMap> urlPathAuths = urlPathAuthService.setUrlPathAuth();
    	
    	for(UISMap urlPathAuth : urlPathAuths) {
    		if(urlPathAuth.getString("roles").indexOf(",") == -1) {
    			http.authorizeHttpRequests(authz-> authz
        				.requestMatchers(new AntPathRequestMatcher(urlPathAuth.getString("url_path_name")))
        				.hasRole(urlPathAuth.getString("roles"))
        		);
    		}else {
    			String[] arrRoles = urlPathAuth.getString("roles").split(",");
    			http.authorizeHttpRequests(authz-> authz
        				.requestMatchers(new AntPathRequestMatcher(urlPathAuth.getString("url_path_name")))
        				.hasAnyRole(arrRoles)
        		);
    		}
    	}
    	
    	http
    	//.csrf(csrf ->csrf.disable())
    	.cors(cors ->cors.disable())
    	.authorizeHttpRequests(request -> request
        	    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() // 맨 처음
				.requestMatchers(new AntPathRequestMatcher("/user/**"), new AntPathRequestMatcher("/login-process.do"), new AntPathRequestMatcher("/signup.do"), 
						new AntPathRequestMatcher("/"), new AntPathRequestMatcher("/status/**"), new AntPathRequestMatcher("/images/**"), new AntPathRequestMatcher("/css/**"),
						new AntPathRequestMatcher("/login*"), new AntPathRequestMatcher("/auth/join"), new AntPathRequestMatcher("/js/**"), 
						new AntPathRequestMatcher("/util/**"), new AntPathRequestMatcher("/favicon.*"), new AntPathRequestMatcher("/*/icon-*")).permitAll()
                .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
        )
    	;
    	
    	http
    	/*.csrf(csrf ->csrf.disable())
    	//.cors(cors ->cors.disable())
        .authorizeHttpRequests(request -> request
        	    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() // 맨 처음
				.requestMatchers(new AntPathRequestMatcher("/user/**"), new AntPathRequestMatcher("/login-process.do"), 
						new AntPathRequestMatcher("/"), new AntPathRequestMatcher("/status/**"), new AntPathRequestMatcher("/images/**"), 
						new AntPathRequestMatcher("/login*"), new AntPathRequestMatcher("/auth/join"), new AntPathRequestMatcher("/js/**"), 
						new AntPathRequestMatcher("/util/**")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/user2.do"), new AntPathRequestMatcher("/user2Info.do"), new AntPathRequestMatcher("/config.do")).hasRole("ADMIN")
				.requestMatchers(new AntPathRequestMatcher("/user3.do"), new AntPathRequestMatcher("/messages.do")).hasRole("MANAGER")
				.requestMatchers(new AntPathRequestMatcher("/mypage.do")).hasRole("USER")
                .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
        )*/
        .formLogin(login -> login	// form 방식 로그인 사용
        		.loginPage("/login.do")	// [A] 커스텀 로그인 페이지 지정
                .loginProcessingUrl("/login-process.do")	// [B] submit 받을 url
                .usernameParameter("username")	// [C] submit할 아이디
                .passwordParameter("password")	// [D] submit할 비밀번호
                .authenticationDetailsSource(authenticationDetailsSource)
                .defaultSuccessUrl("/login-process.do", true)	// 로그인 성공 시 /user/userInfo
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
                
        )
        .logout(withDefaults()) // 로그아웃은 기본설정으로 (/logout으로 인증해제)
        .exceptionHandling((exceptionConfig) -> exceptionConfig
        		.accessDeniedHandler(accessDeniedHandler())
        )
        ;
    	
    	http.authenticationProvider(customAuthenticationProvider());
	   
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
   
   @Bean
   public CustomAuthenticationProvider customAuthenticationProvider() {
      return new CustomAuthenticationProvider();
   }
   
   /*
   @Bean
   PasswordEncoder passwordEncoder() {
       return new SimplePasswordEncoder();
   }
   
    @Bean
    PasswordEncoder passwordEncoder() {
        return new PasswordEncoderFactories().createDelegatingPasswordEncoder();
    }
   */
   
   /*
   private AuthorizationManager<RequestAuthorizationContext> adminAccess() {
       return (authentication, context) -> {
           // 여기서 authentication.get()을 호출하여 실제 Authentication 객체를 얻습니다.
           boolean isAdmin = authentication.get().getAuthorities().stream()
                   .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
           return new AuthorizationDecision(isAdmin);
       };
   }
   */
   
}
