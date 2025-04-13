package com.company.templateservice.application;

import com.company.templateservice.domain.DomainEntity;

public class ServiceLayer {
    public DomainEntity getEntity(String id) {
        return new DomainEntity(id);
    }
}
