package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.view.UserInfoView;

public interface UserRepository {
    UserInfoView getUserInfo(String customerId);
}
