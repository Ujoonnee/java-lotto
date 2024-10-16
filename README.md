## 요구사항 정의
### 기능 요구사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

## 체크리스트
- [ ] 입력
  - [x] 구입 금액
  - [x] 당첨 번호

- [ ] 출력
  - [x] 구매한 게임 수
  - [x] 구매한 번호 오름차순
  - [ ] 당첨 통계
    - [ ] 각 등수 별 당첨 수
    - [ ] 수익률

- [ ] 입력값 검증
  - [x] 구입 금액은 오직 숫자만 허용
  - [x] 당첨 번호는 ","로 구분

- [x] 번호 자동 생성
  - [x] 1 이상 45 이하
  - [x] 6개
  - [x] 중복 불가

- [ ] 당첨 기준
  - [ ] 1등 : 6개 일치
  - [ ] 2등 : 5개 일치
  - [ ] 3등 : 4개 일치
  - [ ] 4등 : 3개 일치

- [ ] 상금 기준
  - [ ] 1등 :  2,000,000,000 원
  - [ ] 2등 :      1,500,000 원
  - [ ] 3등 :         50,000 원
  - [ ] 4등 :          5,000 원

- [ ] 게임
  - [x] 구입 금액에 따른 게임 수 계산
  - [ ] 해당 회차의 당첨 여부(등수)
