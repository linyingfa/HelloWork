package fanshe;


////1.获取类类型对象的几种方式：
//Person person = new Person();  
//Class a = person.getClass() //方法一：
//Class b = Persion.class;//方法二：
//Class c = Class.forName(String ClassName); //方法三：
//Class d = context.getClassLoader().loadClass(String ClassName);//方法四：（不建议使用）
//
////2.获取包名类名
//public String getName();//获取完整的类名（包含包名）
//public String getSimpleName();//仅获取类名
//public Package getPackage()//获取类类型所对应的package对象
//
////3.获取超类Class对象
//public Class<?> getSuperclass();//获取普通函数的父类Class对象
//public Type getGenericSuperclass();//针对泛型父类而设计（下篇讲解）
//
////4.获取接口Class对象
//public Class<?>[] getInterfaces();//获取普通接口的方法
//public Type[] getGenericInterfaces();//获取泛型接口的方法
//
////5.类访问修饰符
//int modifiers = clazz.getModifiers();//获取类访问修饰符对应的int变量
//String Modifier.toString(int modifiers) //根据整型变量来生成对应的修饰符字符串
//boolean Modifier.isAbstract(int modifiers)//isXXX()系列函数用以检查特定的修饰符是否存在
public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		Main m = new Main();
		// m.c();
		// m.d();
//		m.e();
		m.f();

	}

	// 2、获取类类型
	void a() {
		// 使用方法一
		// 只是把泛型的填充为省略了，在泛型中，
		// 如果把泛型的填充给省略掉，那就会默认填充为无界通配符？。所以方法一的真实写法是这样的：方法二
		Class class1 = Animal.class;
		System.out.println(class1.getName());

		// 使用方法二
		// Class<?> class2 = Animal.class;//无界通配符
		Class<Animal> class2 = Animal.class;// 泛型的正规写法
		System.out.println(class2.getName());
	}

	// 2、获取类类型的方法
	void b() {
		// 方法一： 通过类实例的getClass()方法得到类类型。-------1
		Animal animal = new Animal();
		Class a = animal.getClass();
		// 方法二：直接通过类的class对象得到 -----------2
		// Class b = animal.class;
		// 方法三：通过类名得到 -----------------------3
		// 方法三和方法四中是通过类名得到，这两点要非常注意，这里的ClassName一定要从包名具体到类名
		// ，唯一定位到一个类才行，不然就会报ClassNotFound错误
		// Class c = Class.forName(类名字);
		// 方法四：（不建议使用）
		// Class d = getClassLoader().loadClass(String ClassName);

		// 在上面我们提到过，类只会被加载一次，所以a,b,c,d都是相等的，因为他们都是指向同一个对象，如果用等号操作符来判断的话：
		// boolean result = (clazz1 == clazz2 && clazz3 == clazz4 && clazz1 ==
		// clazz3);

	}

	// 1、类名，包名获取
	void c() {
		Class<?> class1 = Animal.class;
		Package package1 = class1.getPackage();
		System.out.println("完整的类名：" + class1.getName());// 完整的类名（包含包名,内部类有$)：fanshe.Main$Animal
		System.out.println("仅获取类名：" + class1.getSimpleName());// 仅获取类名：Animal
		System.out.println("包名：" + package1.getName());// 包名(获取类类型所对应的package对象)：fanshe
	}

	// 获取超类Class对象
	void d() throws ClassNotFoundException {
		Class<?> class2 = AnimalImpl.class;
		// Class<?> class2 = Class.forName("fanshe.AnimalImpl");
		Class<?> parentClass = class2.getSuperclass();
		System.out.println("父类：" + parentClass.getName());// 父类：fanshe.Main$Animal
		// public Class<?> getSuperclass(); //获取普通函数的父类Class对象
		// public Type getGenericSuperclass();//针对泛型父类而设计
	}

	void e() {

		// 获取Animal类的接口列表
		Class<?> class3 = Animal.class;
		Class<?>[] interfaces = class3.getInterfaces();
		for (Class interItem : interfaces) {
			// Animal继承的接口：fanshe.IAnimal
			System.out.println("Animal继承的接口：" + interItem.getName());
		}

		// 获取AnimalImpl的接口列表
		class3 = AnimalImpl.class;
		interfaces = class3.getInterfaces();
		if (interfaces.length > 0) {
			for (Class interItem : interfaces) {
				System.out.println("AnimalImpl继承的接口：" + interItem.getName());
			}
		} else {
			// AnimalImpl无继承的接口
			System.out.println("AnimalImpl无继承的接口");
		}

	}

	/**
	 * 获取所传类类型的所有继承的接口列表
	 * 
	 * @param clazz
	 * @return
	 */
	public Class<?>[] getAllInterface(Class<?> clazz) {

		// 获取自身的所有接口
		Class<?>[] interSelf = clazz.getInterfaces();
		// 递规调用getAllInterface获取超类的所有接口
		Class<?> superClazz = clazz.getSuperclass();
		Class<?>[] interParent = null;
		if (null != superClazz) {
			interParent = getAllInterface(superClazz);
		}

		// 返回值
		if (interParent == null && interSelf != null) {
			return interSelf;
		} else if (interParent == null && interSelf == null) {
			return null;
		} else if (interParent != null && interSelf == null) {
			return interParent;
		} else {
			final int length = interParent.length + interSelf.length;
			Class<?>[] result = new Class[length];
			System.arraycopy(interSelf, 0, result, 0, interSelf.length);
			System.arraycopy(interParent, 0, result, interSelf.length, interParent.length);
			return result;
		}

	}

	void f() {
		// 调用
		Class<?>[] clazzes = getAllInterface(AnimalImpl.class);
		StringBuilder builder = new StringBuilder();
		for (Class<?> clazz : clazzes) {
			builder.append(clazz.getName());
			builder.append("   ");
		}
		//AnimalImpl继承的所有接口:java.io.Serializable   fanshe.IAnimal   java.io.Serializable  
		//AnimalImpl继承的所有接口:java.io.Serializable   fanshe.IAnimal   
		//AnimalImpl继承的所有接口:fanshe.IAnimal   
		System.out.println("AnimalImpl继承的所有接口:"+ builder.toString());

	}
}
