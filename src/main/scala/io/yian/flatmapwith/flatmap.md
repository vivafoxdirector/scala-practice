# for, foreach, map, flatMap

# 개요
for 문이 실제로 어떤형식으로 동작이 되는지 알아본다.

# 사용방법
yield의 유무에 따라서 사용방법이 구분된다.

* yield가 없으면 for문은 foreach로 수행
* yield가 있으면 for문은 flatMap/map 로 수행

대략 설명을 하자면
* 단순하게 값을 처리하고 싶은 경우는 yield가 없는 for문 (foreach 수행)
* 값을 map (형변환등)이용하여 변경작업을 하고 싶은 경우는 yield을 사용한 for문 (flatMap/map 수행)

부가적으로 for문안에서 if 조건문은 withFilter로 변환된다.

## foreach (yield없는 for문) 
for문을 foreach로 변환 해본다. 

### 제너레이션이 1개인 경우 foreach변경 (* 제너레이션이란?  "p <- e"표기를 말함)
* for문
```scala
for (p <- e) expr
```
* foreach문
```scala
e.foreach(p => expr)
```

### 제너레이션이 복수개인 경우 foreach변경
* for문
```scala
for (p1 <- e1; p2 <- e2) expr
for {
    p1 <- e1
    p2 <- e2
} expr
```
* foreach문
```scala
e.foreach(p1 => e2.foreach(p2 => expr))
```
이것을 아래와 같이 풀어서 보면 이해할 수 있다.
```scala
e1.foreach(
    p1 =>
        e2.foreach(
            p2 =>
                expr
        )
    )
)
```

## flatMap/map (yield있는 for문) 
for문을 flatMap/map으로 변환 해본다. 

### 제너레이션이 1개인 경우 map변경 (* 제너레이션이란?  "p <- e"표기를 말함)
* for문
```scala
for (p <- e) yield expr
```
* map문
```scala
e.map(p => expr)
```

### 제너레이션이 복수개인 경우 flatMap 사용하여 변경
* for문
```scala
for (p1 <- e1; p2 <- e2) yield expr
for {
    p1 <- e1
    p2 <- e2
} yield expr
```
* flatMap문
```scala
// p1 <- e1만을 전개
e.flatMap(p1 => for { p2 <- e2 } yield expr)

// (p1 <- e1), (p2 <- e2) 모두 전개
e.flatMap(p1 => e2.map(p2 => expr)) 

```
결국, flatMap/map 은 결과 값을 반환 받는다.
여러개의 제너레이터(p <- e)가 있는 경우, 제일 마지막 제너레이터가 map이 되고 나머지는 flatMap로 전재된다.



# 참조
* 내용: for式のforeach/flatMap(map)展開について
    * 참조: https://qiita.com/harry0000/items/e37ca3bfb68df839bf55
* 내용: for文はループじゃない!? 〜徹底理解：Scalaのfor文（２）
    * 참조: https://medium.com/nyle-engineering-blog/for%E6%96%87%E3%81%AF%E3%83%AB%E3%83%BC%E3%83%97%E3%81%98%E3%82%83%E3%81%AA%E3%81%84-%E5%BE%B9%E5%BA%95%E7%90%86%E8%A7%A3-scala%E3%81%AEfor%E6%96%87-%EF%BC%92-e0ea86726c7c