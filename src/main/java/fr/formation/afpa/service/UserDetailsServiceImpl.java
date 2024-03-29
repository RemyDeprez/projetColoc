package fr.formation.afpa.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.afpa.domain.AppUser;
import fr.formation.afpa.repository.RoleRepository;
import fr.formation.afpa.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private RoleRepository roleRepository;

   @Override
   public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       AppUser appUser = this.userRepository.findByUserName(userName);

       if (appUser == null) {
           System.out.println("User not found! " + userName);
           throw new UsernameNotFoundException("User " + userName + " was not found in the database");
       }

       System.out.println("Found User: " + appUser);

       // [ROLE_USER, ROLE_ADMIN,..]
       String roleNames = this.userRepository.findByUserName(userName).getStatus();

       List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
       if (roleNames != null) {
//           for (String role : roleNames) {
//               // ROLE_USER, ROLE_ADMIN,..
               GrantedAuthority authority = new SimpleGrantedAuthority(roleNames);
               grantList.add(authority);
           }
//       }

       
       UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
               appUser.getEncrytedPassword(), grantList);
       
       System.out.println(appUser.getEncrytedPassword());

       return userDetails;
   }

}