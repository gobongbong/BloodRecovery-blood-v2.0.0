package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.view.UserInfoView;

public interface UserRepository {
    UserInfoView getUserInfo(String cid);
    void requestPoint(String cid, String sign, Integer point);
}
