package com.miraijr.karaoke.application.group_product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupProductService {
    private final GroupProductRepository groupProductRepository;

    @Autowired
    public GroupProductService(GroupProductRepository groupProductRepository) {
        this.groupProductRepository = groupProductRepository;
    }

    public GroupProductEntity getGroupProductByCode(String code) {
        return groupProductRepository.findGroupProductByCode(code);
    }

    public List<GroupProductEntity> getGroupsProduct() {
        return groupProductRepository.findAll();
    }
}
