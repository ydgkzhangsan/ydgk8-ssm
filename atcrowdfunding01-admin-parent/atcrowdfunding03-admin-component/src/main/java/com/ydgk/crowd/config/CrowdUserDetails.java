package com.ydgk.crowd.config;

import com.ydgk.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-09 11:59
 */
public class CrowdUserDetails extends User {

    private Admin OriginalAdmin;

    public CrowdUserDetails(Admin admin, Collection<? extends GrantedAuthority> authorities) {
        super(admin.getLoginAcct(), admin.getUserPswd(), authorities);
        OriginalAdmin = admin;
    }

    public Admin getOriginalAdmin() {
        return OriginalAdmin;
    }

    public void setOriginalAdmin(Admin originalAdmin) {
        OriginalAdmin = originalAdmin;
    }
}
