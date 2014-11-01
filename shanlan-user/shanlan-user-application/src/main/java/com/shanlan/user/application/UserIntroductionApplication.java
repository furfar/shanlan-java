
package com.shanlan.user.application;

import java.util.List;

import org.dayatang.querychannel.Page;
import com.shanlan.user.application.dto.*;

public interface UserIntroductionApplication {

    public UserIntroductionDTO getUserIntroduction(Integer id);

    public UserIntroductionDTO saveUserIntroduction(UserIntroductionDTO userIntroduction);

    public void updateUserIntroduction(UserIntroductionDTO userIntroduction);

    public void removeUserIntroduction(Integer id);

    public void removeUserIntroductions(Integer[] ids);

    public List<UserIntroductionDTO> findAllUserIntroduction();

    public Page<UserIntroductionDTO> pageQueryUserIntroduction(UserIntroductionDTO userIntroduction, int currentPage, int pageSize);


    Page<UserIntroductionDTO> pageQueryUserIntroduction(UserIntroductionDTO userIntroductionDTO, int currentPage, int pageSize, String userName, boolean isSuper, List<String> roles);
}

