package fanshe;

import java.io.Serializable;

//它是一个泛型类，首先，将PointInterface<T,U>填充为PointInterface<T,Integer>，即第一个参数依然是一个泛型，
//而第二个参数填充为Integer；而我们也给PointGenericityImpl中的泛型变量T添加了限定：T extends Number&Serializable，
//给它添加了extends限定（上边界）：指定T必须派生自Number类和Serializable类。
public class PointGenericityImpl<T extends Number & Serializable> implements PointInterface<T, Integer> {

}
