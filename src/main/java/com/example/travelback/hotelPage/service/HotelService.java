package com.example.travelback.hotelPage.service;


import com.example.travelback.hotelPage.domain.Hotel;
import com.example.travelback.hotelPage.domain.HotelRoomType;
import com.example.travelback.hotelPage.mapper.HotelMapper;
import com.example.travelback.hotelPage.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelMapper hotelMapper;
    private final LikeMapper likeMapper;


    private final S3Client s3Client;

    @Value("${aws.s3.bucket.name}")
    private String BUCKET_NAME;

    @Value("${image.file.prefix}")
    private String urlPrefix;


//    호텔 관련 ------------------------------------------------------------------------------------------------------


    //    호텔 삭제
    public void deleteHotel(Integer id) {
//        라이크 삭제
        likeMapper.deleteLikeById(id);
//        파일 삭제
        deleteFile(id);
//        DB 삭제
        hotelMapper.deleteById(id);
    }

    // 호텔 정보 불러오기
    public Hotel getHotelById(Long id) {
        return hotelMapper.selectHotelById(id);
    }

    // 모든 호텔 목록 불러오기 ( 페이지 네이션 )
    public Map<String, Object> getAllHotels(Integer page, String keyword) {

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> pageInfo = new HashMap<>();

        int countAll = hotelMapper.countAll("%" + keyword + "%");
        int lastPageNumber = (countAll - 1) / 9 + 1;
        int startPageNumber = (page - 1) / 9 * 9 + 1;
        int endPageNumber = startPageNumber + 8;
        endPageNumber = Math.min(endPageNumber, lastPageNumber);

        int prevPageNumber = startPageNumber - 9;
        int nextPageNumber = endPageNumber + 1;
        pageInfo.put("currentPage", page);
        pageInfo.put("startPageNumber", startPageNumber);
        pageInfo.put("endPageNumber", endPageNumber);
        if (prevPageNumber > 0) {
            pageInfo.put("prevPageNumber", prevPageNumber);
        }
        if (nextPageNumber <= lastPageNumber) {
            pageInfo.put("nextPageNumber", nextPageNumber);
        }

        int from = (page - 1) * 9;
        map.put("hotelList", hotelMapper.selectAllHotels(from, "%" + keyword + "%"));
        map.put("pageInfo", pageInfo);
        return map;
    }

    // 호텔 업데이트 -> 완전 삭제 -> 다시 생성 ( 라이크, 객실 모두 수정 )
    public void update(Hotel hotel, Integer hid, MultipartFile mainImg, MultipartFile subImg1, MultipartFile subImg2, MultipartFile mapImg) throws IOException {

        // 기존 이미지 삭제
        deleteFile(hid);

//        좋아요 삭제
        likeMapper.deleteLikeById(hid);

        // DB에서 레코드 삭제
        hotelMapper.deleteById(hid);

        // 이미지 외 수정 정보 삽입
        hotelMapper.insertHotel(hotel);

        // 새로운 이미지 업로드 및 DB 업데이트
        if (mainImg != null) {
            String mainImgUrl = urlPrefix + uploadFile(hotel.getHid(), mainImg);
            String subImgUrl1 = urlPrefix + uploadFile(hotel.getHid(), subImg1);
            String subImgUrl2 = urlPrefix + uploadFile(hotel.getHid(), subImg2);
            String mapImgUrl = urlPrefix + uploadFile(hotel.getHid(), mapImg);

            // db추가
            hotelMapper.updateImg(hotel.getHid(), mainImg.getOriginalFilename(), mainImgUrl, subImgUrl1, subImg1.getOriginalFilename(), subImgUrl2, subImg2.getOriginalFilename(), mapImgUrl, mapImg.getOriginalFilename());
        }
    }


    //    호텔 추가
    public void addHotel(Hotel hotel, MultipartFile mainImg, MultipartFile subImg1, MultipartFile subImg2, MultipartFile mapImg) throws IOException {

        hotelMapper.insertHotel(hotel);

        // 이미지 업로드 및 URL 설정
        if (mainImg != null) {

            String mainImgUrl = urlPrefix + uploadFile(hotel.getHid(), mainImg);
            String subImgUrl1 = urlPrefix + uploadFile(hotel.getHid(), subImg1);
            String subImgUrl2 = urlPrefix + uploadFile(hotel.getHid(), subImg2);
            String mapImgUrl = urlPrefix + uploadFile(hotel.getHid(), mapImg);

            hotelMapper.updateImg(hotel.getHid(), mainImg.getOriginalFilename(), mainImgUrl, subImgUrl1, subImg1.getOriginalFilename(), subImgUrl2, subImg2.getOriginalFilename(), mapImgUrl, mapImg.getOriginalFilename());
//            uploadFile(hotel.getHid(),mainImg);
        }
    }

    //    업로드 파일
    private String uploadFile(Long hid, MultipartFile mainImg) throws IOException {

        // 파일 이름 생성
        String key = "travel/hotel/img/" + hid + "/" + mainImg.getOriginalFilename();

        // S3에 파일 업로드
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(mainImg.getInputStream(), mainImg.getSize()));


        return key;
    }


    //    호텔 삭제
    private void deleteFile(Integer hid) {

        String folderKey = "travel/hotel/img/" + hid + "/";

        // 폴더 안의 모든 객체 가져오기
        ListObjectsV2Response listObjectsResponse = s3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket(BUCKET_NAME)
                .prefix(folderKey)
                .build());

        // 폴더 안의 모든 객체 삭제
        for (S3Object object : listObjectsResponse.contents()) {
            s3Client.deleteObject(DeleteObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(object.key())
                    .build());
        }

        // 폴더 자체 삭제
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(folderKey)
                .build());
    }


    //    객실 관련 ------------------------------------------------------------------------------------------------------

    // 객실 불러오기
    public List<HotelRoomType> getHotelRoomtypeById(Long id) {
        return hotelMapper.selectAllRoomtypeByHotelId(id);
    }


    //  객실 추가
    public void addHotelType(HotelRoomType hotelRoomType, MultipartFile transRoomImg) throws IOException {


        hotelMapper.insertHotelRoomType(hotelRoomType);

        if (transRoomImg != null) {
            String roomImgUrl = urlPrefix + uploadTypeFile(hotelRoomType.getHid(), hotelRoomType.getHrtId(), transRoomImg);
            hotelMapper.updateRoomImg(hotelRoomType.getHrtId(), transRoomImg.getOriginalFilename(), roomImgUrl);
        }

    }

    // 객실 업로드
    private String uploadTypeFile(Long hid, int hrtId, MultipartFile roomImg) throws IOException {
        // 파일 이름 생성

        String key = "travel/hotel/img/" + hid + "/roomImg/" + hrtId + "/" + roomImg.getOriginalFilename();

        // S3에 파일 업로드
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(roomImg.getInputStream(), roomImg.getSize()));

        return key;
    }

    // 타입 삭제
    public void deleHotelType(Long hId, Integer hrtId) {
//        DB 지우기
        hotelMapper.deleteHotelTypeByhrtId(hrtId);


        String folderKey = "travel/hotel/img/" + hId + "/roomImg/" + hrtId + "/";

        ListObjectsV2Response listObjectsResponse = s3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket(BUCKET_NAME)
                .prefix(folderKey)
                .build());

        // 폴더 안의 모든 객체 삭제
        for (S3Object object : listObjectsResponse.contents()) {
            s3Client.deleteObject(DeleteObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(object.key())
                    .build());
        }

        // 폴더 자체 삭제
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(folderKey)
                .build());
    }


    //  객실 타입 업데이트 ( 호텔 업데이트와는 다르게 메인이미지 유무에 따라 부분적으로 작동 )
    public void updateType(HotelRoomType hotelRoomType, Integer removeRoomhrtId, MultipartFile roomImg) throws IOException {
//       이미지 존재시 삭제
        if (roomImg != null) {
//            s3 삭제

            String folderKey = "travel/hotel/img/" + hotelRoomType.getHid() + "/roomImg/" + removeRoomhrtId + "/";

            ListObjectsV2Response listObjectsResponse = s3Client.listObjectsV2(ListObjectsV2Request.builder()
                    .bucket(BUCKET_NAME)
                    .prefix(folderKey)
                    .build());

            // 폴더 안의 모든 객체 삭제
            for (S3Object object : listObjectsResponse.contents()) {
                s3Client.deleteObject(DeleteObjectRequest.builder()
                        .bucket(BUCKET_NAME)
                        .key(object.key())
                        .build());
            }

            // 폴더 자체 삭제
            s3Client.deleteObject(DeleteObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(folderKey)
                    .build());

        }

//       메인이미지 삭제 후 업로드 이미지 삽입
        if (roomImg != null) {

            uploadTypeFile(hotelRoomType.getHid(), hotelRoomType.getHrtId(), roomImg);

            String roomImgUrl = urlPrefix + uploadTypeFile(hotelRoomType.getHid(), hotelRoomType.getHrtId(), roomImg);
            hotelMapper.updateRoomImg(hotelRoomType.getHrtId(), roomImg.getOriginalFilename(), roomImgUrl);
        }
        hotelMapper.updateType(hotelRoomType);
    }

}
