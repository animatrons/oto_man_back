package com.oto.back.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends AEntity {
    @Override
    public String getTableName() {
        return "user_";
    }

    @Override
    public String getForeignKeyName() {
        return "";
    }

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<String> role;

    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }*/
}
