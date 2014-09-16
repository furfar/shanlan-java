package com.albert.opf.managet.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @ClassName:SecurityTest
 * @Description: TODO
 * @Author Albert
 * @Date:2013-1-16 下午12:35:23
 * 
 * @Remarks:
 * @Version:V1.1
 */
public class UserDetailsManagerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() {

		// DaoAuthenticationProvider
		// daoAuthenticationProvider=(DaoAuthenticationProvider)
		// SpringObjectFactory
		// .getBean("daoAuthenticationProvider");

		// daoAuthenticationProvider.setUserDetailsService(jdbcUserDetailsManager);

		// Authentication authentication= new
		// UsernamePasswordAuthenticationToken("user1", "password1");
		//
		// daoAuthenticationProvider.authenticate(authentication);

//		JdbcUserDetailsManager jdbcUserDetailsManager = (JdbcUserDetailsManager) SpringObjectFactory
//				.getBean("userDetailsManager");
//
//		UserDetails userDetails = jdbcUserDetailsManager
//				.loadUserByUsername("user1");
//		
//		
//
//		List<String> groups = jdbcUserDetailsManager.findAllGroups();
//
//		System.out.println(groups.get(0));
//
//		String authority = jdbcUserDetailsManager
//				.findGroupAuthorities("primary").get(0).getAuthority();
//
//		userDetails.getUsername();
//
//		System.out.println(authority);
//
//		EhCacheBasedUserCache ehCacheBasedUserCache = (EhCacheBasedUserCache) SpringObjectFactory
//				.getBean("userCache");
//
//		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
//
//		UserDetails user = new User("user1", "password1", grantedAuthorities);
//
//		ehCacheBasedUserCache.putUserInCache(user);
//
//		UserDetails userDetails1 = ehCacheBasedUserCache
//				.getUserFromCache("user1");
//		System.out.println(userDetails1.getUsername());

	}

}
