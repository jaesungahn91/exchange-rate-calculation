# 환율 계산 모듈

## ✅ UI
![img1](https://user-images.githubusercontent.com/49400801/137638038-f240a00c-01de-4c47-8b16-293631ccc9b4.png)
![img2](https://user-images.githubusercontent.com/49400801/137638041-3866284f-419f-4df3-8c2f-2900046ed13c.png)

## ✅ 기능 요구사항
- [x] 송금국가는 미국으로 고정. 통화는 미국달러(USD) 확장성 고려
- [x] 수취국가는 한국, 일본, 필리핀 세 군데 중 하나를 select box로 선택, 각각 통화는 KRW, JPY, PHP.
- [x] 수취국가를 선택하면 변경. 환율은 1 USD 기준으로 각각 KRW, JPY, PHP의 대응 금액. 
- [x] 송금액을 USD로 입력하고 Submit을 누르면 수취금액이 KRW, JPY, PHP 중 하나로 계산되어서 나와야 한다.
- [x] 환율과 수취금액은 소숫점 2째자리까지, 3자리 이상 되면 콤마를 가운데 찍어 보여준다. 예)1234라면 1,234.00.
- [x] 환율정보는 https://currencylayer.com/ 의 무료 서비스를 이용.
  - [x] 새로운 무료 계정을 등록해서 API 키를 받아서 사용.
- [x] 환율을 미리 계산해서 html/javascript 안에 넣어두고 수취국가를 변경할 때마다 이를 자바스크립트로 바로 가져와서 보여줘도 좋고, 혹은 매번 수취국가를 선택/변경할 때마다 API로 서버에 요청을 해서 환율정보를 가져와도 된다.
- [x] Submit을 누르면 선택된 수취국가와 그 환율, 그리고 송금액을 가지고 수취금액을 계산해서 하단에 보여주면 된다. API를 이용해서 서버에서 계산해서 뿌려도 되고 자바스크립트로 미리 가져온 환율을 계산해서 수취금액을 보여줘도 되고 Submit 버튼으로 폼을 submit해서 화면을 새로 그려도 된다.
- [x] 수취금액을 입력하지 않거나, 0보다 작은 금액이거나 10,000 USD보다 큰 금액, 혹은 바른 숫자가 아니라면 “송금액이 바르지 않습니다"라는 에러 메시지를 보여 준다. 메시지는 팝업, 혹은 하단에 빨간 글씨로 나타나면 된다.

## ✅ 프로그래밍 요구사항
- [x] 스프링 숙련도를 보기 위한 테스트이며 핵심 기능을 스프링으로 구현
- [x] 사용기술은 스프링 4.0이후 버전을 사용하면 어떤 기술을 이용해도 상관없다. 스프링 부트를 사용 가능.
- [x] 테스트 코드 작성.
- [x] 클라이언트 화면은 스프링의 뷰 기술(jsp, thymeleaf 등)을 이용해도 좋고, React나 Angular 등을 상관 없다.