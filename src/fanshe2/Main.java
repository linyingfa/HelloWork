package fanshe2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class Main {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// CreateConstructor();
		// getGenericParameterTypes();
		// getFields();
		// getField();
		getsetField();
	}

	private static void getConstructorFuntion() {

		// Constructor<?>[] getConstructors();
		// Constructor<T> getConstructor(Class<?>... parameterTypes);


		// Constructor<?>[] getDeclaredConstructors();
		// Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)

	}

	// 2??Constructor??з???????????
	private static void CreateConstructor() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// ******************************************************************************

		// ???????????Constructor????

		// ????????getDeclaredConstructor(Integer.class, String.class)???????????????
		// ????????????????????????????Integer????????????????String?????
		Class<?> clazz = Person.class;

		Constructor<?> constructor = clazz.getDeclaredConstructor(Integer.class, String.class);
		// ?????????????????κκ????????????????????????????true??
		// ??????????????????????????private????public????????????????????????false???????public????????????
		// ??????????setAccessible(true)??????????protected????private?????????????????????????????????
		// ??????У?????????????????setAccessible(boolean
		// flag)??????Constructor,Field,Method????????????????????????????????
		// ???????????????У??????й???????????????????????????????
		// ???????????????????????private??protected.
		// ???????????????????????????????setAccessible(boolean flag)??????

		constructor.setAccessible(true);

		// ???????????????Integer,String???????

		// ????????,
		// ??????????У???????newInstance?????????д?????????????????????constructor???????????????
		// ?????????????Integer????????????????String????
		// ???constructor.newInstance??????????Person???????
		// ??????person?????person1.getName()??person1.getAge()????????????
		Person person1 = (Person) constructor.newInstance(new Integer(30), new String("harvic"));
		System.out.println("???????????" + person1.getName() + "  " + person1.getAge());

		// ?????????????????????????????????????
		// ?????????
		// ????????????У???????????????????????????Integer??String?????
		// ???????????50?????????Integer?????????????????????????????String??????
		// ?????????????????????????????
		// ???????????????????????????????????????????person2?е????
		Person person2 = (Person) constructor.newInstance(50, "qijian");
		System.out.println("???????????" + person2.getName() + "  " + person2.getAge());

		// ?????????????????????????????
		// constructor?????????????private Person(Integer age, String name)??
		// ??????????newInstance???????????????Constructor???????????
		// ????????????????????????????????????????????????????????????? wrong number of arguments
		// ?????????
		// ?????????????????????????????????????????????ò???
		Constructor<?> constructor2 = clazz.getDeclaredConstructor();
		constructor2.setAccessible(true);
		Person person3 = (Person) constructor2.newInstance();// 0??????
		// Person person3 = (Person) constructor.newInstance();//2??????
		person3.setAge(30);
		person3.setName("qijian");
		System.out.println("???????????" + person3.getName() + "  " + person3.getAge());

	}

	// 3??Constructor??з???????????????
	// Constructor?л??????????????????????????
	// //????????????
	// Class<?>[] getParameterTypes();
	// //??????????????
	// Type[] getGenericParameterTypes();

	// ???????????????????getParameterTypes()??????????????????????????
	// ?? getGenericParameterTypes()????????????з???????????????????
	// ?????????????б????????Type???飬?й?Type???????????縴??

	private static void getGenericParameterTypes() {
		Class<?> clazz = Person.class;
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		for (Constructor<?> c : constructors) {
			c.setAccessible(true);
			Class<?>[] types = c.getParameterTypes();

			StringBuilder builder = new StringBuilder("??????????????");
			for (Class t : types) {
				builder.append(t.getName());
				builder.append("   ");
			}
			// ??????????????java.lang.Integer java.lang.String
			// ??????????????int java.lang.String
			// ??????????????
			System.out.println(builder.toString());
			int modifier = c.getModifiers();
			System.out.println("??????????η????" + Modifier.toString(modifier));
		}

	}

	// ???е????????????????????????????????????????????????г?????????????????????Filed??????????
	// Class?ж?Field??????????????????????
	// ???????????????public????????
	// Field[] getFields();
	// Field getField(String name);
	// //?????????????????
	// Field[] getDeclaredFields();???????????public,protected????private????????????
	// Field getDeclaredField(String name)
	// getFields()??getDeclaredFields()????????????????????????Field???顣
	// getField(String name)??getDeclaredField(String
	// name)?????????????????????????Field????

	private static void getFields() {
		Class<?> clazz = Person.class;
		// ?????????clazz.getDeclaredFields()??????г?????????????Field???????顣
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// field.getName()?????????????????????????????Person???е?age,name
			// field.getType()?????????????????????????
			field.setAccessible(true);
			// ??person???У?????????????????????????int age?????????String name??
			// ???????????????????????????????private!????????棬???????????getDeclaredXXX()??к??????????????Field??
			// ??????棬???????????????????????Field???????????????????field.setAccessible(true);
			// ??Constructor?У???????????????е??????????????????????????????????
			// ???????????????private??protected???????????????????????private??protected????????
			// ?????????????????????????Constructor,Field,Method?ж??????setAccessible(boolean
			// flag)??????..........
			// ?????????????????????????????ó??????????????false,
			// ??????????????????????private??protected??????????

			Class<?> type = field.getType();
			// ??????field:int age
			// ??????field:java.lang.String name
			System.out.println("??????field:" + type.getName() + "  " + field.getName());
		}

	}

	private static void getField() {
		Class<?> clazz = Person.class;
		Field field;
		try {
			field = clazz.getDeclaredField("age");
			field.setAccessible(true);
			Class<?> type = field.getType();
			// ???age?????field:int age
			System.out.println("???age?????field:" + type.getName() + "  " + field.getName());
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 2??Field??get??set????
	// ???????????????????????????????????????Field?е?get,set???????????????

	private static void getsetField() {
		Class<?> clazz = Person.class;
		Constructor<?> constructor;
		try {
			constructor = clazz.getConstructor();
			Person person;
			try {
				person = (Person) constructor.newInstance();
				Field fName = null;
				try {
					// ?????????object?????????????????????Field????????????
					// void set(Object object, Object value)
					// ??????????????????????????????????У??????????????
					// Object get(Object object)

					fName = clazz.getDeclaredField("name");
					fName.setAccessible(true);
					fName.set(person, "qijian");
					String val = (String) fName.get(person);
					System.out.println("fieldName:" + val + "   personName:" + person.getName());
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
