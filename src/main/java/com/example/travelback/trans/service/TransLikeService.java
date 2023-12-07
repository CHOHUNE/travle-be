package com.example.travelback.trans.service;

import com.example.travelback.trans.dto.TransLike;
import com.example.travelback.trans.mapper.TransLikeMapper;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TransLikeService {

    private final TransLikeMapper mapper;

    public Map<String, Object> update(TransLike transLike, Member login) {
        transLike.setUserId(login.getUserId());

        int count = 0;
        // 처음 누르면 insert
        // 다시 누르면 delete
        // 우선 지웠을때 0으로 나오면 insert 를 실행 시키자
        if(mapper.delete(transLike) == 0) {
            count = mapper.insert(transLike);
        }
        int transLikeCount = mapper.countByTransId(transLike.getTransId());
        return Map.of("transLikeState", count == 1, "transLikeCount", transLikeCount);
    }

    public Map<String, Object> get(Integer transId, Member login) {
        int transLikeCount = mapper.countByTransId(transId);

        TransLike like = null;
        if(login != null) {
            like = mapper.selectByTransIdAndUserId(transId,login.getUserId());

        }
        return Map.of("like",like != null,"transLikeCount", transLikeCount);
    }
}
