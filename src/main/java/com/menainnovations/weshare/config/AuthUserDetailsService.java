package com.menainnovations.weshare.config;
/*
import com.menainnovations.weshare.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService users;
    private org.springframework.security.core.userdetails.User userdetails;



    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        com.menainnovations.weshare.model.User user = getUserDetail(email);
        if (user != null) {
            userdetails = new User(user.getEmail(),
                    user.getPassword(),
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    getAuthorities(0)
            );
            return userdetails;
        } else {
            userdetails = new User("empty",
                    "empty",
                    false,
                    true,
                    true,
                    false,
                    getAuthorities(1)
            );
            return userdetails;
        }
    }

    public List<GrantedAuthority> getAuthorities(Integer role) {

        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        if (role.intValue() == 0) {
            authList.add(new SimpleGrantedAuthority("USER"));
        }
        return authList;
    }

    private com.menainnovations.weshare.model.User getUserDetail(String email) {

        com.menainnovations.weshare.model.User user = users.getUserByEmail(email);
        if (user == null) {
            System.out.println("user '" + email + "' on nulli!");
        } else {
            System.out.println(user.getName());
        }

        return user;
    }}
    */