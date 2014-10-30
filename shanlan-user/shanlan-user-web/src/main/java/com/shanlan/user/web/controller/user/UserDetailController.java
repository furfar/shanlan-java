package com.shanlan.user.web.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.dayatang.querychannel.Page;
import org.openkoala.koala.auth.ss3adapter.AuthUserUtil;
import org.openkoala.koala.auth.ss3adapter.CustomUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shanlan.user.application.UserDetailApplication;
import com.shanlan.user.application.dto.UserDetailDTO;

@Controller
@RequestMapping("/UserDetail")
public class UserDetailController {

    @Inject
    private UserDetailApplication userDetailApplication;

    @ResponseBody
    @RequestMapping("/add")
    public Map<String, Object> add(UserDetailDTO userDetailDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        userDetailApplication.saveUser(userDetailDTO);
        result.put("result", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map<String, Object> update(UserDetailDTO userDetailDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        userDetailApplication.updateUser(userDetailDTO);
        result.put("result", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/pageJson")
    public Page pageJson(UserDetailDTO userDetailDTO, @RequestParam int page,
                         @RequestParam int pagesize, HttpServletRequest request) {
        String userName = AuthUserUtil.getLoginUserName();
        CustomUserDetails customUserDetails = AuthUserUtil.getLoginUser();
        List<String> roles = AuthUserUtil.getRolesByCurrentUser();
        Page<UserDetailDTO> all = userDetailApplication.pageQueryUser(userDetailDTO, page,
                pagesize,userName,customUserDetails.isSuper(), roles);
        return all;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestParam String ids) {
        Map<String, Object> result = new HashMap<String, Object>();
        String[] value = ids.split(",");
        Integer[] idArrs = new Integer[value.length];
        for (int i = 0; i < value.length; i++) {

            idArrs[i] = Integer.parseInt(value[i]);

        }
        userDetailApplication.removeUsers(idArrs);
        result.put("result", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public Map<String, Object> get(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", userDetailApplication.getUser(id));
        return result;
    }

}
