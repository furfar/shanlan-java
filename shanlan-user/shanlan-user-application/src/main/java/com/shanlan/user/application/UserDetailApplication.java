
package com.shanlan.user.application;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.shanlan.common.exception.sub.business.RequestAuthenticationException;
import org.dayatang.querychannel.Page;
import com.shanlan.user.application.dto.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface UserDetailApplication {

    public UserDetailDTO getUser(Integer id);

    public UserDetailDTO saveUser(UserDetailDTO user);

    boolean saveDefaultUser(String userName, String email);

    public void updateUser(UserDetailDTO user);

    public void removeUser(Integer id);

    public void removeUsers(Integer[] ids);

    public List<UserDetailDTO> findAllUser();

    public Page<UserDetailDTO> pageQueryUser(UserDetailDTO user, int currentPage, int pageSize);

    Page<UserDetailDTO> pageQueryUser(UserDetailDTO queryVo, int currentPage, int pageSize, String userName, boolean isSuper, List<String> roles);

    public boolean register(UserBaseDTO userBaseDTO) throws Exception;

    public UserDetailDTO isLogin(String session) throws Exception;

    String uploadAvatar(String originalFileName, byte[] bytes, String contentType, UserDetailDTO userDetailDTO) throws Exception;

    public String handleAvatar(int x, int y, int srcShowWidth, int srcShowHeight, String userName, String sessionId) throws Exception;

    UserDetailDTO login(String userAccount, String password, String sessionId) throws Exception;
}

