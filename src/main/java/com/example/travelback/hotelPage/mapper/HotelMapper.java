package com.example.travelback.hotelPage.mapper;

import com.example.travelback.hotelPage.domain.Hotel;
import com.example.travelback.hotelPage.domain.HotelRoomType;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface HotelMapper {

    @Insert("""
            INSERT INTO hotel (name, location, description, rating, lodgingType, numberOfBed, mainImgUrl,salesFrom,salesTo,cautionMessage,pool,oceanview,pet,familyMood,romanticMood,campingMood)
             
            VALUES (#{name}, #{location}, #{description}, #{rating}, #{lodgingType},#{numberOfBed},#{mainImgUrl},#{salesFrom},#{salesTo},#{cautionMessage},#{pool},#{oceanview},#{pet},#{familyMood},#{romanticMood},#{campingMood})""")
    @Options(useGeneratedKeys = true, keyProperty = "hid")
    void insertHotel(Hotel hotel);

    @Insert("""
            INSERT INTO hotelroomtype(hId, originalPriceWeekday, salePriceWeekday, originalPriceWeekend, salePriceWeekend, roomtype,maxMinPerRoom,ableCooking,numberOfBedRoom) 
            VALUES (#{hid}, #{originalPriceWeekday}, #{salePriceWeekday}, #{originalPriceWeekend}, #{salePriceWeekend}, #{roomtype} ,#{maxMinPerRoom},#{ableCooking},#{numberOfBedRoom})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "hrtId")
 void insertHotelRoomType(HotelRoomType hotelRoomType);

    @Select("""
            SELECT * 
            FROM hotel 
            WHERE hId = #{hid}
            """)
    Hotel selectHotelById(Long id);

//    @Select("""
//            SELECT * FROM hotel
//            WHERE name LIKE #{keyword}
//            OR location LIKE #{keyword}
//            OR pet LIKE #{keyword}
//            OR pool LIKE #{keyword}
//            OR oceanview LIKE #{keyword}
//            OR campingMood LIKE #{keyword}
//            OR romanticMood LIKE #{keyword}
//            OR familyMood LIKE #{keyword}
//
//        GROUP BY hId
//        ORDER BY hId DESC
//        LIMIT #{from}, 9
//            """)
//    List<Hotel> selectAllHotels(Integer from,String keyword);

    @Select("""
    SELECT h.hId,
         h.name,
          h.location,
           h.description,
            h.mainImg,
             h.rating,
              h.lodgingType,
              h.subImg1,
               h.subImg2,
                h.mapImg,
                 h.numberOfBed,
                  h.mainImgUrl,
                   h.subImgUrl1,
                    h.mapImgUrl,
                     h.subImgUrl2,
                      h.salesFrom,
                       h.salesTo,
                        h.cautionMessage,
                         h.pool,
                          h.oceanview,
                           h.pet,
                            h.familyMood,
                             h.romanticMood,
                              h.campingMood,
                              COALESCE(MIN(hr.salePriceWeekday), null) as minSalePriceWeekday
     FROM hotel h
     LEFT JOIN hotelroomtype hr ON h.hId = hr.hId
    WHERE h.name LIKE #{keyword}
    OR h.location LIKE #{keyword}
    OR h.pet LIKE #{keyword}
    OR h.pool LIKE #{keyword}
    OR h.oceanview LIKE #{keyword}
    OR h.campingMood LIKE #{keyword}
    OR h.romanticMood LIKE #{keyword}
    OR h.familyMood LIKE #{keyword}
    GROUP BY h.hId
    ORDER BY h.hId DESC
    LIMIT #{from}, 9
""")
    List<Hotel> selectAllHotels(Integer from, String keyword);


    @Select("""
SELECT hotel.hId, MIN(hotelroomtype.salePriceWeekday) as minSalePriceWeekday
FROM hotel INNER JOIN hotelroomtype ON hotel.hId = hotelroomtype.hId
WHERE hotel.hId = #{id}
GROUP BY hotel.hId
""")
    List<Hotel> selectSalesPriceWeekdayByHotelId(Long hId);

    @Select("""
SELECT *
FROM hotelroomtype
WHERE hId=#{hid}
ORDER BY salePriceWeekday ASC
""")
    List<HotelRoomType> selectAllRoomtypeByHotelId(Long id);

    @Delete("""
    DELETE FROM hotelroomtype WHERE hrtId=#{hrtId}
""")
    void deleteHotelTypeByhrtId(Integer hrtId);

    @Delete("""
    DELETE FROM hotelroomtype WHERE hId=#{hId}
""")
    void deleteHotelAllTypeByhId(Integer hId);

    @Delete("""
            DELETE FROM hotel
            WHERE hId=#{id}
            """)
    void deleteById(Integer id);

    @Update("""
            UPDATE hotel
            SET 
                name =#{name},
                location=#{location},
                description=#{description},
                lodgingType=#{lodgingType},
                numberOfBed=#{numberOfBed},  
                mainImgUrl=#{mainImgUrl}
                
                WHERE hId=#{hid}
               """)
    void update(Hotel hotel);

    @Update("""
            UPDATE hotel
            SET mainImg=#{mainImg},mainImgUrl=#{mainImgUrl},subImgUrl1=#{subImgUrl1},
            subImgUrl2=#{subImgUrl2},mapImgUrl=#{mapImgUrl},subImg1=#{subImg1},subImg2=#{subImg2},mapImg=#{mapImg}
                        
            WHERE hId=#{hid}
            """)
    void updateImg(long hid, String mainImg, String mainImgUrl, String subImgUrl1, String subImgUrl2, String mapImgUrl, String subImg1, String subImg2, String mapImg);

    @Update("""
    UPDATE hotelroomtype
    SET 
    roomImg=#{roomImg},
    roomImgUrl=#{roomImgUrl}
    
    WHERE hrtId=#{hrtId}
""")
    void updateRoomImg(int hrtId, String roomImg, String roomImgUrl);

    @Select("""
        SELECT COUNT(*) FROM hotel
        WHERE hotel.name LIKE #{keyword}
           OR hotel.description LIKE #{keyword}
""")
    int countAll(String keyword);


@Select("""
SELECT * FROM hotelroomtype
WHERE hrtId=#{removeRoomhrtId}
""")
    HotelRoomType selectRoomtypeByhrtId(Integer removeRoomhrtId);

@Update("""
UPDATE hotelroomtype
SET
originalPriceWeekday=#{originalPriceWeekday},
salePriceWeekday=#{salePriceWeekday},
originalPriceWeekend=#{originalPriceWeekend},
salePriceWeekend=#{salePriceWeekend},
roomtype=#{roomtype},
maxMinPerRoom=#{maxMinPerRoom},
ableCooking=#{ableCooking},
numberOfBedRoom=#{numberOfBedRoom}

WHERE hrtId=#{hrtId}
""")
    void updateType(HotelRoomType hotelRoomType);
}
