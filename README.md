# sparta-commerce
## 프로젝트 소개
Java 콘솔 환경에서 동작하는 간단한 커머스 시스템입니다.
<br/><br/>
## 주요 기능
- 상품 목록 조회 및 선택 기능  
- 장바구니 추가 / 조회 / 초기화 기능  
- 재고 확인 및 주문 시 재고 차감 처리  
- 장바구니 총금액 계산 및 주문 기능  
- 사용자 입력 검증 및 예외 처리  
- 관리자 모드 (비밀번호 인증)  
- 상품 추가 / 수정 / 삭제 기능 (카테고리 기반 관리)  
- 장바구니와 상품 데이터 동기화 처리  
<br/>

## 파일 구조
```text
/src/com/example/commerce
├─commerce
│      Category.java
│      CommerceSystem.java
│      Customer.java
│      Main.java
│      Product.java
│      
├─commerce1
│      Cart.java
│      CartItem.java
│      Category.java
│      CommerceSystem.java
│      CommerceView.java
│      Customer.java
│      Main.java
│      Product.java
│
└─commerce2
        AdminService.java
        Cart.java
        CartItem.java
        Category.java
        CommerceSystem.java
        CommerceView.java
        Customer.java
        Main.java
        Product.java
```
- `commerce`: 필수 기능 - 커머스 시스템 필수 기능 (상품 조회)
- `commerce1`: 도전 기능 Lv.1 - 장바구니 및 주문 기능 구현 (상품 추가, 재고 관리, 금액 계산, 주문 처리 포함)
- `commerce2`: 도전 기능 Lv.2 - 관리자 모드 구현 (인증, 상품 추가/수정/삭제 기능 포함)
<br/>

## 구현 환경
- Java 17
- IntelliJ IDEA
<br/>

## 참고 사항
- 각 패키지에서 Main 클래스를 실행하면 시스템이 동작하게 됩니다.
- commerce2.Main 클래스의 관리자 비밀번호는 `admin123`으로 설정되어 있습니다.
