package com.thomas.reciept.security;

import java.util.Arrays;
import java.util.Collection;

import com.thomas.reciept.model.Member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomMemberDetails implements UserDetails{

    private static final long serialVersionUID = 1L;

    Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(member.getPosition());
        System.out.println("day la: "+member.getPosition());
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
