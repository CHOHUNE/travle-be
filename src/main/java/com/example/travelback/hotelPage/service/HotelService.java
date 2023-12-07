package com.example.travelback.hotelPage.service;


import com.example.travelback.hotelPage.domain.Hotel;
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
import java.net.URL;
import java.util.List;

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

// 기존 add 코드


    public void deleteHotel(Integer id) {
//        라이크 삭제
        likeMapper.deleteLike(id);
//        파일 삭제
        deleteFile(id);
//
        hotelMapper.deleteById(id);
    }

    public Hotel getHotelById(Long id) {
        return hotelMapper.selectHotelById(id);
    }

    public List<Hotel> getAllHotels() {
        return hotelMapper.selectAllHotels();
    }

    //    기존 update
//    public void update(Hotel hotel) {
//        hotelMapper.update(hotel);
//    }

    public void update(Hotel hotel, Integer hid, MultipartFile mainImg,MultipartFile subImg1,MultipartFile subImg2,MultipartFile mapImg) throws IOException {




        // 기존 이미지 삭제
        deleteFile(hid);

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
            hotelMapper.updateImg(hotel.getHid(), mainImg.getOriginalFilename(), mainImgUrl,subImgUrl1,subImgUrl2,mapImgUrl);
        }
    }


    public void addHotel(Hotel hotel, MultipartFile mainImg,MultipartFile subImg1,MultipartFile subImg2,MultipartFile mapImg) throws IOException {


        // 이미지 업로드 및 URL 설정
        if (mainImg != null) {
            hotelMapper.insertHotel(hotel);
            String mainImgUrl = urlPrefix + uploadFile(hotel.getHid(), mainImg);
            String subImgUrl1 = urlPrefix + uploadFile(hotel.getHid(), subImg1);
            String subImgUrl2 = urlPrefix + uploadFile(hotel.getHid(), subImg2);
            String mapImgUrl = urlPrefix + uploadFile(hotel.getHid(), mapImg);


            hotelMapper.updateImg(hotel.getHid(), mainImg.getOriginalFilename(), mainImgUrl,subImgUrl1,subImgUrl2,mapImgUrl);
//            uploadFile(hotel.getHid(),mainImg);
        }
    }


    //    uploadFile
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

//       URL 설정

        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .build();

        URL imageUrl = s3Client.utilities().getUrl(getUrlRequest);
        return key;
//    return imageUrl.toString();
    }

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

//        원래 코드
////        파일명
//        String mainImg = hotelMapper.selectMainIMgByHotelId(hid);
//
////    s3 bucket
//        String key = "travel/hotel/img/" + hid + "/" + mainImg;
//        String subImg1= hotelMapper.selectSubImg1ByHotelId(hid);
//        String subImg2= hotelMapper.selectSubImg2ByHotelId(hid);
//        String mapImg= hotelMapper.selectMapImgByHotelId(hid);
//
//        DeleteObjectRequest objectRequest = DeleteObjectRequest.builder()
//                .bucket(BUCKET_NAME)
//                .key(key)
//                .build();
//
//        s3Client.deleteObject(objectRequest);
//        s3Client.deleteObject(DeleteObjectRequest.builder().bucket(BUCKET_NAME).key(mapImg).build());
//        s3Client.deleteObject(DeleteObjectRequest.builder().bucket(BUCKET_NAME).key(subImg1).build());
//        s3Client.deleteObject(DeleteObjectRequest.builder().bucket(BUCKET_NAME).key(subImg2).build());
    }

}
