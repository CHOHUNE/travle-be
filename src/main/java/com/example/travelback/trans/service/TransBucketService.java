package com.example.travelback.trans.service;

import com.example.travelback.trans.dto.TransBucket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TransBucketService {
    private final TransBucketMapper mapper;
    public List<TransBucket> getByUserId(String userId) {
        return mapper.selectByUserId(userId);
    }
}
