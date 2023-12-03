package com.example.travelback.trans.service;

import com.example.travelback.trans.dto.Trans;
import com.example.travelback.trans.mapper.MainImageMapper;
import com.example.travelback.trans.mapper.TransMapper;
import com.example.travelback.trans.mapper.TransTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransService {
    private final TransMapper mapper;
    private final TransTypeMapper transTypeMapper;
    private final MainImageMapper mainImageMapper;

    public void add(Trans trans, String type, MultipartFile transMainImage) {
        // 상품 추가
        mapper.insert(trans);

        // transMainImage 테이블에 정보 저장
        // -> transport(tId), 이미지 파일 이름
        if(transMainImage != null) { // 파일을 넣었을때에만 
            mainImageMapper.insert(trans.getTId(), transMainImage.getOriginalFilename());
        }

        // 실제 파일을 S3 bucket에 upload (파일이 있으면 )

        // 상품 등록할 때에 상품 타입을 등록 하는 기능
        transTypeMapper.insert(trans.getTId(), type);
    }

    public List<Trans> list() {
        return mapper.selectAll();
    }

    public Trans get(Integer id) {
        return mapper.selectByTId(id);
    }

    public void update(Trans trans) {
        mapper.update(trans);
    }

    public void delete(Integer id) {

        // 운송 상품 삭제전 type 테이블 을 삭제
        transTypeMapper.deleteByTId(id);

        // 운송 상품 삭제 하기
        mapper.deleteById(id);
    }

    public List<Trans> listPopularBus() {
        return mapper.selectPopularToBus();
    }

    public List<Trans> listPopularAir() {
        return mapper.selectPopularToAir();
    }
}
