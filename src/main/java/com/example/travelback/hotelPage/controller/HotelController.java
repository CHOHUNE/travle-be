package com.example.travelback.hotelPage.controller;


import com.example.travelback.hotelPage.domain.Hotel;
import com.example.travelback.hotelPage.domain.HotelRoomType;
import com.example.travelback.hotelPage.domain.Like;
import com.example.travelback.hotelPage.domain.Reservation;
import com.example.travelback.hotelPage.service.HotelService;
import com.example.travelback.hotelPage.service.LikeService;
import com.example.travelback.hotelPage.service.ReservationService;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
public class HotelController {


    private final HotelService hotelService;
    private final ReservationService reservationService;
    private final LikeService likeService;

//    검색
    @GetMapping("list")
    public Map<String, Object> getAllHotels(
            @RequestParam(value = "p", defaultValue = "1") Integer page,
            @RequestParam(value = "k", defaultValue = "") String keyword) {

        return hotelService.getAllHotels(page, keyword);
    }
    @GetMapping("/price")
    public List<Hotel> getPrice(Long hId){
        return hotelService.getPrice(hId);
    }


//    찜하기 기능
    @GetMapping("/wishList/{userId}")
    public List<Like> getAllLike(@PathVariable String userId) {
        return likeService.getLikeByUserId(userId);
    }

//    찜하기 -> 찜 목록에 던지기
    @GetMapping("/bucket/id/{userId}")
    public List<Like> getLikeByUserId(@PathVariable String userId) {

        return likeService.getLikeByUserId(userId);
    }

//    호텔 뷰 관련 ---------------------------------------------------------------------------------------------------------

//    상세 화면에서 객실 타입 불러오기
    @GetMapping("/reserv/type/{id}")
    public List<HotelRoomType> getAllRoomtypeByHotelId(@PathVariable Long id) {
        return hotelService.getHotelRoomtypeById(id);
    }

// 상세 화면에서 해당 호텔 정보 불러오기
    @GetMapping("/reserv/id/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }



//   타입 관련--------------------------------------------------------------------------------------------------------------

    //   객실 삭제
    @DeleteMapping("/delete/{hId}/type/{hrtId}")
    public void typeDelete(@PathVariable Integer hrtId,
                           @PathVariable Long hId) {
        hotelService.deleHotelType(hId,hrtId);
    }

    //   객실 추가
    @PostMapping(value = "/write/type")
    public void addType(HotelRoomType hotelRoomType,
                        @RequestParam(value = "roomImg[]", required = false) MultipartFile transRoomImg) throws IOException {

        hotelService.addHotelType(hotelRoomType, transRoomImg);
    }

//   객실타입 수정

    @PutMapping("/edit/type")
    public void editType(HotelRoomType hotelRoomType
            , @RequestParam(value = "hrtId", required = false) Integer hrtId,
                         @RequestParam(value = "roomImg[]", required = false) MultipartFile transRoomImg) throws IOException {
        hotelService.updateType(hotelRoomType, hrtId, transRoomImg);
    }


// 호텔 관련 -------------------------------------------------------------------------------------------------------------

    //     호텔 추가
    @PostMapping(value = "/write")
    public void add(Hotel hotel,
                    @RequestParam(value = "mainImg[]", required = false) MultipartFile mainImg,
                    @RequestParam(value = "subImg1[]", required = false) MultipartFile subImg1,
                    @RequestParam(value = "subImg2[]", required = false) MultipartFile subImg2,
                    @RequestParam(value = "mapImg[]", required = false) MultipartFile mapImg
    ) throws IOException {

        if (mainImg != null) {
            System.out.println("mainImgFileName:" + mainImg.getOriginalFilename());
            System.out.println("subImg1 = " + subImg1.getName());
            System.out.println("subImg2 = " + subImg2.getName());
            System.out.println("mapImg = " + mapImg.getName());
        }
        hotelService.addHotel(hotel, mainImg, subImg1, subImg2, mapImg);
    }

    //호텔 이미지 수정
    @PutMapping(value = "/edit")
    public void edit(Hotel hotel,
                     @RequestParam(value = "hid", required = false) Integer hid,
                     @RequestParam(value = "mainImg[]", required = false) MultipartFile mainImg,
                     @RequestParam(value = "subImg1[]", required = false) MultipartFile subImg1,
                     @RequestParam(value = "subImg2[]", required = false) MultipartFile subImg2,
                     @RequestParam(value = "mapImg[]", required = false) MultipartFile mapImg
    ) throws IOException {


        hotelService.update(hotel, hid, mainImg, subImg1, subImg2, mapImg);
    }

//    호텔 기본 정보 수정
    @GetMapping("/edit/id/{id}")
    public ResponseEntity<Hotel> getEditHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    //호텔 삭제
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        hotelService.deleteHotel(id);
    }
//------------------------------------------------------------------------------------------------------------------------

    //    결제 관련
    @GetMapping("/pay/{id}")
    public ResponseEntity<Map<String, Object>> getPayHotelById
    (@PathVariable Long id,
     @SessionAttribute(value = "login", required = false) Member login) {
        Hotel hotel = hotelService.getHotelById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("name", login.getUserName());
        map.put("email", login.getUserEmail());
        map.put("phoneNumber", login.getUserPhoneNumber());

        return ResponseEntity.ok(Map.of("hotel", hotel, "member", map));

    }

    @GetMapping("/pay")
    public void add(Reservation reservation) {
        reservationService.addResrvation(reservation);
    }

}


