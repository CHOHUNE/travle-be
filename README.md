

### 📖 내용

- 통합 여행 서비스 예약 플랫폼으로 항공편, 숙소, 투어 각기 나누어진 서비스를 하나의 웹사이트에서 간편하게 제공하여  일관된 서비스 경험을 제공 합니다.
- 실제 여행사 서비스 플랫폼 처럼 제작 하기 위해 초점을 맞췄습니다.

### ✔️구현 항목

- 로그인/회원가입
- 마이페이지
- 검색
- 회원관리
- 공지사항
- 숙소 예약
- 버스 / 항공 예약
- 결제
- ADMIN 기능
    - 판매현황
    - 문의답변 등록
    - 회원 목록 관리

### 🙋‍♂️ 직접 구현한 부분

- 호텔 상품 CRUD
- 호텔 찜하기 CRUD
- 호텔 결제 CD
- 객실 CRUD
- 페이지네이션, 검색, 필터 정렬 기능

### ✔️협업 툴

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/f6d8753f-3ebd-410a-9e35-7fe0a5afe238/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/6478ef0d-3967-4421-a3a6-fe35b6cc9cb0/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/6f7976eb-4b6e-458b-96b6-46a97922342b/Untitled.png)

![와이어 프레임](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/2a664de5-90f9-4b7e-982c-767619e87d06/Untitled.png)

와이어 프레임

 

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/c723e13d-f071-45da-98d7-fa8452911b9f/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/4a139e5e-1b55-4cd2-b978-141ba25f2315/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/2dd0d1e3-d32e-49c0-b60b-1f86dd601843/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/998e0986-c4d1-4968-bc71-0f2817f90aa3/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/0b5dc80d-d161-45a0-ab21-3da3fa0e9fd4/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/799922b4-e09e-4cb5-864b-c8ca4c1f379c/Untitled.png)

### 🙋‍♂️ 구현 기능 상세

- 검색, 페이지네이션, 필터 기능
    - 해당 테마 클릭시 테마에 해당하는 호텔 리스트만 보여 줍니다.
- 호텔 찜하기 CRUD
- 호텔 리스트
    - 노출되는 호텔의 가격은 해당 호텔의 객실 타입 중 가장 낮은 가격의 객실을 노출 합니다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/254ba575-fc42-4c4d-8504-d4a02c6d3eda/Untitled.png)

### **호텔 CRUD**

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/e10aae7c-3d1d-446c-a6b6-f0a60ce7fa67/Untitled.png)

### 객실 리스트, 및 호텔 CRUD

- 날짜는 기본적으로 현재 날짜와 익일 - 1박 2일로 설정 됩니다.
- 가격 책정은 **주중 요금** ( 일, 월, 화, 수, 목)과 **주말 요금**( 금, 토) 별도로 책정 됩니다.
- 주말과 주중이 겹칠 경우 주중+주말 요금을 별도로 더하여 가격이 책정 됩니다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/403c1f85-f61b-4d85-b50d-99e0f57863d2/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/59336f1c-19d6-47d9-b4c8-216c7aa2d4b6/Untitled.png)

- 객실 CRUD
    - 확인 버튼을 누를시 객실 추가, 삭제를 일괄적으로 합니다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/7a32bba4-84c7-4d3a-9305-3dee417f4187/Untitled.png)

- Keep 만족한 부분, 계속 이어갔으면 하는 부분
    - 기술 부분
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7cff6025-8078-43ac-aa45-e0cbee33ba01/84ff0f4a-1032-45df-b7e2-67fe8cf1d1aa/Untitled.png)
        
    
    - 객실 예약이 주중, 주말 요금이 별도로 정산되어서 들어가는 부분이 가장 중요하여 이를 구현 했습니다.
    - 호텔 사이트의 경우 ‘주중’ 최저가 요금이 노출되는것, 호텔 클릭시 여러 객실이 한 번에 리스트업 되는 것.
    - 사용자 편의성을 향상 시키기 위해 고객이 원하는 호텔 성향을 한 눈에 파악할 수 있는 테마별 리스트업 버튼을 구현 했습니다.
    - 팀원과의 협업 부분
        - 이전에 여행사에서 실제 일했던 팀원이 있어서 실제 호텔 사이트 ADMIN을 볼 기회가 있었습니다.
        - 팀원의 지시에 따라 우선순위를 정하고 전체적인 개발의 가닥을 잡았습니다.
- Problem 불편하게 느낀 부분, 개선이 필요하다고 생각되는 부분
    - 기술 부분
        - 호텔 이미지 업로드를 배열로 전송했다면 불필요하게 이미지를 별도의 4개 input 창으로 받지 않아도 될것 입니다.
        - 객실을 일괄 추가, 삭제 하는데 어려움을 많이 겪어 원하는 대로 전부 구현하지 못했습니다.
            - 객실 삭제는 실시간으로 되는 반면, 객실 추가는 확인 버튼을 눌렀을 때 일괄적으로 전송
        - 호텔 기능 DB가 쓸데없이 많은 점
    - 협업 부분
        - 개발이 다소 진척이 된 이후에  피드백을 받을 때 이미 있는 기능을 대폭 수정하는 경우가 더러 있었다.
- Try Problem에 대한 해결책, 다음 회고때 판별 가능한 것
    - 실시간으로 소통. 나 혼자 판단하지 말고 팀원과 소통하여 올바른 방향으로 나아가도록 한다.
