package com.example.Study.service;

import com.example.Study.model.entity.AdminUser;
import com.example.Study.model.network.Header;
import com.example.Study.model.network.request.AdminUserApiRequest;
import com.example.Study.model.network.response.AdminUserApiResponse;
import org.springframework.stereotype.Service;

@Service
//public class AdminUserApiLogicService implements CrudInterface<AdminUserApiRequest, AdminUserApiResponse> {
public class AdminUserApiLogicService extends BaseService<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {

//    @Autowired
//    private AdminUserRepository adminUserRepository;

    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {

        AdminUserApiRequest body = request.getData();

        AdminUser newAdminUser = baseRepository.save(
                AdminUser.builder()
                .account(body.getAccount())
                .password(body.getPassword())
                .status(body.getStatus())
                .role(body.getRole())
                .registeredAt(body.getRegisteredAt())
                .build()
        );

        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {

        return baseRepository.findById(id)
                .map(adminUser -> response(adminUser))
                .orElseGet(() -> Header.ERROR("NONE DATA"));
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {

        AdminUserApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(adminUser -> {
                    adminUser.setAccount(body.getAccount())
                            .setPassword(body.getPassword())
                            .setStatus(body.getStatus())
                            .setRole(body.getRole())
                            .setLastLoginAt(body.getLastLoginAt())
                            .setLoginFailCount(body.getLoginFailCount())
                            .setPasswordUpdatedAt(body.getPasswordUpdatedAt())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt());

                    return adminUser;
                })
                .map(adminUser -> baseRepository.save(adminUser))
                .map(adminUser -> response(adminUser))
                .orElseGet(() -> Header.ERROR("NONE DATA"));
    }

    @Override
    public Header delete(Long id) {
         return baseRepository.findById(id)
                .map(adminUser -> {
                    baseRepository.delete(adminUser);
                    return Header.OK();
                })
                .orElseGet(()-> Header.ERROR("NONE DATE"));
    }

    private Header<AdminUserApiResponse> response(AdminUser adminUser){

        AdminUserApiResponse adminUserApiResponse = AdminUserApiResponse.builder()
                .id(adminUser.getId())
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .lastLoginAt(adminUser.getLastLoginAt())
                .loginFailCount(adminUser.getLoginFailCount())
                .passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
                .registeredAt(adminUser.getRegisteredAt())
                .unregisteredAt(adminUser.getUnregisteredAt()).build();

        return Header.OK(adminUserApiResponse);
    }
}

