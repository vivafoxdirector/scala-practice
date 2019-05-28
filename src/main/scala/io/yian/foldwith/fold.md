# Fold, FoldLeft, FoldRight
fold, foldLeft, foldRight 에 대해서 다루어 본다.

# Fold
fold에는 값이 None이면 기본값을 미리 설정해둔다는 이미지를 생각해 볼 수 있다.

## 정의
```scala
def fold[B](ifEmpty: => B)(f: (A) => B):B
```
* 첫번째 인자 (ifEmpty: => B)는 None일 경우 함수
* 둥번째 인자 (f:(A) => B)는 Some(A)일 경우 함수

## 값이 있는경우
```scala
val yongdon:Option[Int] = Some(1000)
val message = yongdon.fold("hav not money!") {
  money => s"${money}won"
  }
message: String = "1000won"
```

## 값이 없는경우
```scala
val yongdon:Option[Int] = None
val message = yongdon.fold("hav not money!") {
  money => s"${money}won"
  }
message: String = "1000won"
```

# 참조
ref: https://qiita.com/Kazuhiro23/items/3a1a21544d71b5c76e78