package com.work.metalautomate.service.user;

import com.work.metalautomate.model.user.UserModel;

public interface UserService {
    void save(UserModel userModel);
    void delete(Long userId);
    UserModel findByUsername(String username);
}
