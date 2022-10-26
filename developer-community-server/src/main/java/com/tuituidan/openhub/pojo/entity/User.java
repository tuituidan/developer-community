package com.tuituidan.openhub.pojo.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * DcArticle.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/4
 */
@Setter
@Getter
@Accessors(chain = true)
@Table(name = "dc_user", schema = "business")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = -3404101162871398085L;

    @Id
    private String id;

    private String name;

    private String username;

    private String avatar;

    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
