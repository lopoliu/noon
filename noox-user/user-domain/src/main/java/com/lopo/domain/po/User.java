package com.lopo.domain.po;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class User implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<Permission> authority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<SimpleGrantedAuthority> authoritySet = new HashSet<>();
        for (Permission author: authority){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(author.getPermCode());
            authoritySet.add(simpleGrantedAuthority);
        }
        return authoritySet;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthority(List<Permission> authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    public Map<String, Object> toMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", this.id);
        map.put("username", this.username);
        return map;
    }
}
