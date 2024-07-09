package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ModifyBloodRequestCommandServiceTest {

    @InjectMocks
    ModifyBloodRequestCommandService modifyBloodRequestCommandService;

    @Mock
    BloodRequestRepository bloodRequestRepository;
    @Mock
    UserRepository userRepository;


}
