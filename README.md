# java-calculator-precourse

## 📝 구현 기능 목록

### 1. 입출력 기능

- 사용자에게 시작 안내 메시지 출력한다
- 계산에 사용할 숫자와 연산자 입력받는다
- 계산 결과 출력한다

### 2. 핵심 로직 기능

- 커스텀 구분자를 지정한다
- 쉼표 또는 콜론 혹은 커스텀 구분자로 숫자를 분리한다
- 분리된 숫자를 합한다

### 3. 예외 처리 기능

- 커스텀 구분자가 쉼표, 콜론일 경우에  `IllegalArgumentException` 을 발생시킨 후 app을 종료시킨다.
- 구분자가 연달아서 2번 이상 나올 경우에 `IllegalArgumentException` 을 발생시킨 후 app을 종료시킨다.
- 커스텀 구분자가 2글자 이상일 경우에 `IllegalArgumentException` 을 발생시킨 후 app을 종료시킨다.
- 구분자로 지정되지 않은 문자가 나올 경우에 `IllegalArgumentException` 을 발생시킨 후 app을 종료시킨다.
- 커스텀 구분자 지정 양식이 틀릴 경우에 `IllegalArgumentException` 을 발생시킨 후 app을 종료시킨다.
- 구분자를 가진 문자열의 시작 혹은 끝이 구분자일 경우 `IllegalArgumentException` 을 발생시킨 후 app을 종료시킨다.
- 빈 문자열을 입력받을 경우 0을 반환한다.
- 덧셈 대상이 숫자가 아닌 경우 `IllegalArgumentException` 을 발생시킨 후 app을 종료시킨다.
- 덧셈 대상이 소수인 경우 `IllegalArgumentException` 을 발생시킨 후 app을 종료시킨다.
- 공백이 커스텀 구분자인 경우에도 정상 작동한다.

### 4. 기타

- app 종료시에 Console.close을 호출한다


---

## 💌 커밋 컨벤션

Following convention : https://gist.github.com/stephenparish/9941e89d80e2bc58a153

```
# basic structure
<type>(<scope>): <subject>
<BLANK LINE>
<body>
<BLANK LINE>
<footer>

# <type>
feat (feature)
fix (bug fix)
docs (documentation)
style (formatting, missing semi colons, …)
refactor
test (when adding missing tests)
chore (maintain)

# <scope>
console - I/O
domain - 핵심 로직
validation - 유효성검사
test - 테스트코드 추가
```