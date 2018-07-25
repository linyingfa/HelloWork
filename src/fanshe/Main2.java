package fanshe;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class Main2 {

	// 获取泛型超类和接口的相信信息
	public static void main(String[] args) {
		// a();
		// ParameterizedType();
		// getGenericInterfaces();
		// TypeVariable();
		GenericArrayType();
	}

	// 泛型超类
	private static void a() {

		// 获取泛型超类，获得PointImpl.class的父类，而它的父类是Point，
		// 这明显是一个泛型类型，所以它对应的类型就是ParameterizedType；
		Class<?> clazz = PointImpl.class;
		Type type = clazz.getGenericSuperclass();

		// Type是一个接口，这里意思是它是Java所有类型都会继承这个接口。但通过源码会发现String,Integer,Double这些类都没有继承这个接口，
		// 就连Object也没继承！public final class Class<T> implements Serializable,
		// AnnotatedElement, GenericDeclaration, Type {
		// 所以说，这个Type类型是泛型所特有的。那它用是来做什么的呢？他就是用来标识，当前Class中所填充的类型的。
		// 意思是，当我们在填充一个泛型时，比如上面我们的：【这个填充类型就会放在Type的保存起来，当需要用到的时候再取出来。】
		// 那问题又来了，我们这里填充的是Integer类型 ：
		// 那如果我们填充的是【数组泛型呢】，比如Point<ArrayList>，再假如我们填充的是一个【通配符呢？】这Type要怎么【识别呢？】
		// 为了解决这个问题，Java的开发者，在Type的基础上派生了另外几个接口，分别来保存不同的类型，他们分别是：
		// ParameterizedType：他代表的是一个【泛型类型】，比如Point，它就是一个泛型类型。
		// TypeVariable ：这个代表的就是【泛型变量】，例如Point,这里面的T就是泛型变量，
		// 而如果我们利用一种方法获得的对象是T,那它对应的类型就是TypeVariable；（这个类型的应用后面会细讲）
		// WildcardType:上面的TypeVariable对应的是泛型变量，而如果我们得到不是泛型变量，
		// 而是通配符比如：? extends Integer,那它对应的类型就是WildcardType；
		// GenericArrayType
		//
		//
		//
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			// 返回表示此类型实际类型参数的 Type 对象的数组
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			for (Type parameterArgType : actualTypeArguments) {
				Class parameterArgClass = (Class) parameterArgType;
				// 填充类型为：java.lang.Integer
				// 填充类型为：fanshe.Animal
				System.out.println("填充类型为：" + parameterArgClass.getName());
			}

			// 返回 Type 对象，表示声明此类型的类或接口。
			Type type1 = parameterizedType.getRawType();
			Class class22 = (Class) type1;
			// PointImpl的父类类型为：fanshe.Point
			// PointImpl的父类类型为：fanshe.Point
			System.out.println("PointImpl的父类类型为：" + class22.getName());

		}
	}

	// 类
	private static void ParameterizedType() {
		// 当获取的Type类型，对应的是一个完整泛型表达式的时候，比如，我们这里获取到的PointImpl.class的父类：
		// ParameterizedType：这就是上面我们代码中用到的，他代表的是一个泛型类型，比如Point，它就是一个泛型类型。
		// Type 包含 ParameterizedType

		// Class<?> clazz = PointImpl.class;
		// Type type = clazz.getGenericSuperclass();
		// 这时的type对应的完整表达式就是：Point

		// 在ParameterizedType中有两个极有用的函数：
		// Type[] getActualTypeArguments();
		// Type getRawType();

		// getActualTypeArguments()：用来返回当前泛型表达式中，用来填充泛型变量的真正值的列表。
		// 像我们这里得到的Point，用来填充泛型变量T的是Integer类型，所以这里返回的Integer类型所对应的Class对象。

		// getRawType()：我们从我们上面的代码中，也可以看到，它返回的值是com.harvic.blog_reflect_2.Point，
		// 所以它的意义就是声明当前泛型表达式的类或者接口的Class对象。
		// 比如，我们这里的type对应的是Point，而声明Point这个泛型的当然是Point类型。所以返回的是Point.Class

		// 下面我们再回过来看看getActualTypeArguments()：
		// 我们上面说到，这个函数将返回用来填充泛型变量真实参数列表。像我们这里的是Point，
		// 将返回Integer对应的Class对象。而并不是所有的每次都会返回填充类型对应的Class对象。
		// 我们知道我们在填充一个泛型时，是存在各种可能的，
		// 比如Point,Point<? extends Number>,Point<ArrayList>,Point<ArrayList<?
		// extend Number>>,等等
		// 虽然我们没办法穷举可能填充为哪些类型，但我们知道Type类型是用来表示填充泛型变量的类型的，
		// 而继承Type接口只有下面五个：Class,ParameterizedType,TypeVariable,WildcardType,GenericArrayType!
		// 所以这也是Type[] getActualTypeArguments();中Type[]数组的所有可能取值！

		Class<?> clazz = PointImpl.class;
		Type type = clazz.getGenericSuperclass();
		// 我们在Type type = clazz.getGenericSuperclass();之后，得到的type的值对应的是：Point,
		// 所以我们知道，type对应的是ParameterizedType，所以我们用
		// if (type instanceof ParameterizedType) {
		// …………
		// }进行判断，来识别，然后将type变量强转为ParameterizedType变量：
		// ParameterizedType parameterizedType = (ParameterizedType) type;
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			// 返回表示此类型实际类型参数的 Type 对象的数组
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			for (Type parameterArgType : actualTypeArguments) {
				// 利用parameterizedType.getActualTypeArguments()获取当前泛型变量的填充列表，
				// 我们知道Point中泛型变量T被填充为Integer，所以我们得到的数组Type[]里，只有一个值，它对应的就是Integer.Class。
				// 然后我们将得到的Type进行强转成Class类型，所以parameterArgClass对应的值就是Integer.Class。
				// 所以我们利用parameterArgClass.getName()：java.lang.Integer
				Class parameterArgClass = (Class) parameterArgType;
				System.out.println("填充类型为：" + parameterArgClass.getName());
			}

			// parameterizedType对应的值是Point,而parameterizedType.getRawType()得到的就是声明这个泛型的类的Class对象。
			// 所以这里的type1对应的值就是Point.Class。
			// 所以我们将其转换成Class对象，通过class22.getName()得到的值是：
			// PointImpl的父类类型为：fanshe.Point
			// 返回 Type 对象，表示声明此类型的类或接口。,如果是获取类，就是类，接口就是接口
			Type type1 = parameterizedType.getRawType();
			Class class22 = (Class) type1;
			System.out.println("PointImpl的父类类型为：" + class22.getName());

		}

	}

	// 接口
	private static void getGenericInterfaces() {
		// 获取所继承泛型接口的相关信息,上泛我们也说到，获取普通类所继承的接口使用的是Class.getInterfaces()函数，
		// 如果要获取泛型接口的对象需要用到：
		// public Type[] getGenericInterfaces();
		// 这里提前强调一点：大家需要注意是getGenericInterfaces()数与Class.getInterfaces()函数一样，都只能获取此类直接继承的接口列表！
		// 这里得到的一个Type数组，因为我们一个类可以继承多个接口，所以这里的每一个type对应的就是我们所继承的一个接口类型。
		Class<?> clazz = PointImpl2.class;
		// 获得PointImpl2.class所继承接口的数组，
		// PointImpl2只继承了一个接口：PointInterface<String,Double>，
		// 所以此时的Type[]中只有一个元素，即代表着PointInterface<String,Double>的type
		Type[] types = clazz.getGenericInterfaces();
		for (Type type : types) {
			if (type instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				// 返回表示此类型实际类型参数的 Type 对象的数组
				// 通过parameterizedType.getActualTypeArguments()获取到它的参数数组
				Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
				for (Type parameterArgType : actualTypeArguments) {
					Class parameterArgClass = (Class) parameterArgType;
					// 此接口的填充类型为：java.lang.String
					// 此接口的填充类型为：java.lang.Double
					// 此接口的填充类型为：fanshe.Student
					System.out.println("此接口的填充类型为：" + parameterArgClass.getName());
				}

				// 因为我们知道，PointInterface<T,U>被PointImpl2填充为PointInterface<String,Double>，
				// 所以它的真实的参数类型应该是String和Double，
				// 我们前面说过Type只有五种类型：Class,ParameterizedType,TypeVariable,WildcardType,GenericArrayType。
				// ParameterizedType代表完整的泛型表达式，1
				// TypeVariable代表泛型变量的符号即T,U等， 2
				// WildcardType代表通配符， --------------3
				// GenericArrayType代表数组类型， --------4
				// Class则表示派生于Object的所有Class类， -5
				// 明显这里的String和Double是Class类型的。
				// 所以我们将它们强转为Class类型，然后通过parameterArgClass.getName()得到它们的完整路径名。
				// 最后通过parameterizedType.getRawType()获取声明PointInterface<String,Double>的接口类类型，
				// 虽然这里得到的是Type，但我们声明接口的是PointInterface.Class所以，也是Class类型，
				// 直接将其强转为Class即可。最后通过Class.getName()获取其完整的路径名。

				// 返回 Type 对象，表示声明此类型的类或接口。
				Type type1 = parameterizedType.getRawType();
				Class class22 = (Class) type1;
				// 声明此接口的类型为：fanshe.PointInterface
				System.out.println("声明此接口的类型为：" + class22.getName());
			}
		}

	}

	private static void TypeVariable() {
		// Type的五种类型
		// ParameterizedType代表完整的泛型表达式，1
		// TypeVariable代表泛型变量的符号即T,U等， 2
		// WildcardType代表通配符， --------------3
		// GenericArrayType代表数组类型， --------4
		// Class则表示派生于Object的所有Class类， -5

		// 1、TypeVariable

		// 当type代表的类型是一个泛型变量时，它的类型就是TypeVariable。TypeVariable有两个函数：
		// String getName(); :就是得到当前泛型变量的名称；
		// Type[] getBounds(); ：返回表示此类型变量上边界的 Type 对象的数组。如果没有上边界，则默认返回Object；

		Class<?> clazz = PointGenericityImpl.class;
		Type[] types = clazz.getGenericInterfaces();
		for (Type type : types) {
			if (type instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				// 返回表示此类型实际类型参数的 Type 对象的数组
				Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
				for (Type parameterArgType : actualTypeArguments) {
					if (parameterArgType instanceof TypeVariable) {// 针对T:
						TypeVariable typeVariable = (TypeVariable) parameterArgType;
						// 此接口的填充类型为：T
						System.out.println("此接口的填充类型为：" + typeVariable.getName());
						// 返回表示此类型变量上边界的 Type 对象的数组。

						// 然后，利用typeVariable.getBounds()得到T的限定条件：上边界的数组。
						// 上边界的意思就是extends关键字[后面]的限定条件。“上”的意思就是能取到的最大父类。
						// 最大父类当然是用extends关键字来限定的。我们知道这里的T的限定条件是：
						// <T extends Number&Serializable>，所以 Type[] typebounds
						// = typeVariable.getBounds();
						// 所得到typebounds有两个变量，一个是Number,一个是Serializable;
						// 这两个都是具体的类型，所以我们可以直接将它们转换为Class类型，
						// 然后利用Class.getName()获取它们完整的路径名

						Type[] typebounds = typeVariable.getBounds();
						for (Type bound : typebounds) {
							Class<?> boundClass = (Class) bound;
							// 如果不写，则默认输出Object，如果写了，则输出对应的
							// bound为：java.lang.Number
							// bound为：java.io.Serializable
							System.out.println("bound为：" + boundClass.getName());
						}
					} else if ((parameterArgType instanceof Class)) {
						Class parameterArgClass = (Class) parameterArgType;
						// 此接口的填充类型为：java.lang.Integer
						System.out.println("此接口的填充类型为：" + parameterArgClass.getName());

					}
					// if (parameterArgType instanceof Class) {
					// Class parameterArgClass = (Class) parameterArgType;
					// // 此接口的填充类型为：java.lang.Integer
					// System.out.println("此接口的填充类型为：" +
					// parameterArgClass.getName());
					// }
				}

			}
		}

	}

	private static void GenericArrayType() {
		// ，当type对应的类型是类似于String[]、Integer[]等的数组时，那type的类型就是GenericArrayType；
		// 这里要特别说明的如果type对应的是类似于ArrayList、List这样的类型，那type的类型应该是ParameterizedType，
		// 而不是GenericArrayType，因为ArrayList是一个泛型表达式。
		// 所以当且仅当type对应的类型是类似于String[]、Integer[]这样的数组时，type的类型才是GenericArrayType！
		// Type gericComponentType：这是GenericArrayType仅有一个函数，
		// 由于getGenericComponentType所代表的表达是String[]这种的数组，
		// 所以getGenericComponentType获取的就是这里的数组类型所对应的Type，
		// 比如这里的StetGenericComponentType();
		// getGenring[]通过getGenericComponentType获取到的Type对应的就是String.

		// 在PointArrayImpl中，我们填充PointSingleInterface中泛型变量T的是Integer[]，一个Integer数组！
		// 下面我们来看看如何获取PointArrayImpl的接口信息：

		Class<?> clazz = PointArrayImpl.class;
		Type[] interfaces = clazz.getGenericInterfaces();
		// 通过clazz.getGenericInterfaces()获取PointArrayImpl.class的接口对应的type列表
		for (Type type : interfaces) {
			if (type instanceof ParameterizedType) {
				// PointArrayImpl.class只直接继承一个接口：PointSingleInterface<Integer[]>，
				// 所以interfaces数组中只有一个元素，它代表的表达式就是PointSingleInterface<Integer[]>；
				// 明显这个一个泛型表达式，所以这个type的类型就是ParameterizedType
				ParameterizedType pt = (ParameterizedType) type;
				Type[] actualArgs = pt.getActualTypeArguments();
				for (Type arg : actualArgs) {
					if (arg instanceof GenericArrayType) {
						GenericArrayType arrayType = (GenericArrayType) arg;
						Type comType = arrayType.getGenericComponentType();
						Class<?> typeClass = (Class) comType;
						System.out.println("数组类型为：" + typeClass.getName());
					}
				}
			}
		}

	}

}
