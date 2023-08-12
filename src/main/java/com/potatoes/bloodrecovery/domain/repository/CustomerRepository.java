package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.view.CustomerInfoView;

public interface CustomerRepository {
    CustomerInfoView getCustomerInfo(String customerId);
}
