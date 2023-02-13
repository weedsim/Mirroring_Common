## 코드 컨벤션
### Prettier
- 자바스크립트, html, css는 prettier 사용
- 자바는 Google Java Convention 사용
-------
## git branch 정책
### github flow
1. `master` 브런치는 어떤 때든 배포가 가능하다.
2. 새로운 일을 시작하기 위해 브런치를 `master`에서 딴다면 이름은 어떤 일을 하는지 명확하게 작성한다.
3. 원격지 브런치로 수시로 push를 한다.
4. 피드백이나 도움이 필요할 때, 그리고 머징 준비가 완료되었을 때는 `pull request`를 생성한다.
5. 기능에 대한 리뷰와 사인이 끝난 후 `master`로 머지한다.
6.  `master`로 머지되고 푸시되었을 때는 즉시 배포되어야 한다.
