package io.yian.collection

// ref: https://qiita.com/hiyoko_40/items/16ddd5ceeadd440fc588
// ref: http://www.mwsoft.jp/programming/scala/foreach.html

// Tuple
// ref: https://qiita.com/f81@github/items/a8419532c316d190782d
// ref: https://syttru.hatenadiary.org/entry/20081029/1225301251
object CollectionTest {

    // Tuple
    def TupleTest001 : Unit = {
        val t = (1, "abc", 2)
        println(t)
    }

    //-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-
    // Tuple 요소 값 가져오기
    def getMaxValue(numbers: List[Int]) : (Int, Int) = {
        val max = numbers.max
        val index = numbers.indexOf(max)
        (max, index)
    }

    def TupleTest002: Unit = {
        // Tuple은 요소가 최대 22개까지이다.
        val numbers = List(1,2,3,4,5,10,6)

        val value = getMaxValue(numbers)
        printf("max = %s \n", value._1)
        printf("index = %s \n", value._2)
    }
    //-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-

    // 튜플은 최대 22개의 요소를 갖는다. 그 이상을 사용하면 에러가 발생된다.
    def TupleTest003: Unit = {
        def get = {
            (1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22)
        }
        val tuple = get
        println(tuple._1)
        println(tuple._22)
    }

    // Map의 요소는 Tuple이다. Tuple을 반환한다.
    def ForEachMapTest001: Unit = {
        val map = Map(1 -> 10, 2 -> 20)
        map.foreach(print)
    }

    // Key, Value 를 출력하는 방법
    def ForEachMapTest002: Unit = {
        val map = Map(1 -> 10, 2 -> 20)

        // 개개의 요소가 Tuple이고 _번호 를 이용해 해당 index의 요소를 출력한다
        map.foreach(item => println(item._1 + " : " + item._2))
    }

    // _1 과 같은 표현은 가독성이 없기 때문에 case 구문으로 바꿔본다.
    def ForEachMapTest003: Unit = {
        val map = Map(1 -> 10, 2 -> 20)
        map.foreach {
            case (key, value) =>
                println(key + " : " + value)
        }
    }

    def main(args:Array[String]) : Unit = {
        TupleTest001
        TupleTest002
        TupleTest003

        ForEachMapTest001
        ForEachMapTest002
        ForEachMapTest003
    }
}
