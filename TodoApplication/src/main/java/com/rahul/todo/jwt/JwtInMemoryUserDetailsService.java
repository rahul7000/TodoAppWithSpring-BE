package com.rahul.todo.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * Service class to load the user details from memory or from db as well to authenticate and token generation
 */
@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

	static {
		inMemoryUserList.add(new JwtUserDetails(1L, "rahrajpu",
				"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println(
				"JwtInMemoryUserDetailsService.loadUserByUsername()-get the user details from inMemory(List), you can implement your business logic for DB here");

		Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
				.filter(user -> user.getUsername().equals(username)).findFirst();

		if (!findFirst.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return findFirst.get();
	}
	
//	   @Autowired 
//	   private UserRepository userRepository; 
//	   
//	   @Override 
//	   public UserDetails loadUserByUsername(String username) 
//	   throws UsernameNotFoundException { 
//	      User user = userRepository.findUserByUsername(username) 
//	         .orElseThrow(() -> new UsernameNotFoundException("User not present")); 
//	         return user; 
//	   } 
//	   public void createUser(UserDetails user) { 
//	      userRepository.save((User) user); 
//	   } 

}
