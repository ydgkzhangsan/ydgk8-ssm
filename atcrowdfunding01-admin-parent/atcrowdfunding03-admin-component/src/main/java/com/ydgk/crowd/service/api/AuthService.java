package com.ydgk.crowd.service.api;

import com.ydgk.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-07 14:43
 */
public interface AuthService {
    List<Auth> getAll();

    List<Integer> getAuthIdByRoleId(Integer roleId);

    void saveRoleAuth(Map<String, List<Integer>> map);
}
