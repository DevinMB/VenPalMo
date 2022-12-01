package org.devinbutts.VenPalMo.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserDAO userDao;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userDao.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Username '" + email + "' not found in database");
        }


        // check the account status
        boolean accountIsEnabled = false;
        accountIsEnabled = user.getActive();

        // spring security configs
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;


        //Roles
        List<String> userRoles = Arrays.asList(user.getRole());
        //Add roles to spring roles
        Collection<? extends GrantedAuthority> springRoles = buildGrantAuthorities(userRoles);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), accountIsEnabled, accountNonExpired, credentialsNonExpired, accountNonLocked, springRoles);
    }


    private Collection<? extends GrantedAuthority> buildGrantAuthorities(List<String> userRoles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (String role : userRoles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

}
