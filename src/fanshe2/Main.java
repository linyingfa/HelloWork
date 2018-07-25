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

	// 1����ȡ���캯��
	private static void getConstructorFuntion() {

		// ��ȡpublic���͵Ĺ��캯��
		// Constructor<?>[] getConstructors();
		// Constructor<T> getConstructor(Class<?>... parameterTypes);

		// ��ȡ�������͵Ĺ��캯��
		// Constructor<?>[] getDeclaredConstructors();
		// Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)

		// �����ַ����У�getConstructors��getConstructor��ȡ��������Ϊpublic�Ĺ��캯����
		// �޷��õ�����Ϊprotected,private�Ĺ��캯����
		// ������Declared������������������getDeclaredConstructors��getDeclaredConstructor�ܻ�ȡ���������Ĺ��캯����
		// ��������public,proctected����private���ͣ����ܻ�ȡ��������������������Ϊ���á�
	}

	// 2��Constructorϵ�з���֮����ʵ��
	private static void CreateConstructor() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// ******************************************************************************

		// ��һ���֣���ȡConstructor����

		// ���ȣ�ͨ��getDeclaredConstructor(Integer.class, String.class)��ȡָ���Ĺ��캯����
		// Ҫ�õ���������캯������һ��������Integer���ͣ��ڶ���������String���͡�
		Class<?> clazz = Person.class;

		Constructor<?> constructor = clazz.getDeclaredConstructor(Integer.class, String.class);
		// ������ĺ����ǣ��Ƿ��κκ������ֶ�����Ϊ�ɷ��ʵġ��������Ϊtrue��
		// �Ͳ���������������ֶ�������Ϊprivate����public�����ǿ��Է��ʵģ�Ĭ���������false����ֻ��public�����ǿɷ��ʵġ�
		// ���û������setAccessible(true)�Ļ�����ʹ��protected����private���캯������ʵ��ʱ������ʾ�������ʾܾ���
		// �ڷ����У��ܹ����������;���setAccessible(boolean
		// flag)������Constructor,Field,Method���ֱ��Ӧ���캯������Ա�����ͳ�Ա������
		// ����֪����һ�����У�Ҳ��ֻ�й��캯������Ա�����ͳ�Ա�����������֣�
		// �����������ֶ����ܻᱻ����Ϊprivate��protected.
		// ������������������������Ƿ�ɷ��ʵ�setAccessible(boolean flag)������

		constructor.setAccessible(true);

		// �ڶ����֣�ʹ�ñ�׼��Integer,String����ʵ��

		// ����ʵ��һ,
		// ����������У�������newInstance���������д��ݵ������������ϸ�����constructor����Ӧ�Ĺ��캯���ģ�
		// ����һ��������Integer���ͣ��ڶ���������String����
		// Ȼ��constructor.newInstance�ķ���ֵǿת��Person��ʵ����
		// ���ͨ��person�ĺ���person1.getName()��person1.getAge()��ȡ�������ݡ�
		Person person1 = (Person) constructor.newInstance(new Integer(30), new String("harvic"));
		System.out.println("����Ĳ���Ϊ��" + person1.getName() + "  " + person1.getAge());

		// �������֣�ʹ�Ǳ�׼������ת���Ĳ���������ʵ��
		// ����ʵ����
		// ��������췽���У����Ǵ��ݵ��������������Ǳ�׼��Integer��String���͡�
		// ����һ������50�ǿ���ǿת��Integer���͵ģ����ڶ����ַ�����Ҳ�ǿ���ǿתΪString���͵ġ�
		// ���Զ��ڿ���ǿת��Ϊ�������͵���ֵ��
		// Ҳ�ǿ��Գɹ����ܵģ����Դӽ��Ҳ���Կ��������ǿ��Գɹ��õ�person2�е�ֵ��
		Person person2 = (Person) constructor.newInstance(50, "qijian");
		System.out.println("����Ĳ���Ϊ��" + person2.getName() + "  " + person2.getAge());

		// ���Ĳ��֣�����������򴫵ݲ���������
		// constructor��Ӧ�Ĺ��캯���ǣ�private Person(Integer age, String name)��
		// ����������newInstance��ʱ�򣬱����������Constructorʱ�Ĳ������ͣ�
		// ˳�򼰸���һ��������ȥ�����Ķ�Ӧֵ��������ǲ������ٴ����߶ഫ������������أ� wrong number of arguments
		// ����ʵ����
		// �����������Ĺ���������Ҫ��������������ʱ���Ƿ����ò���
		Constructor<?> constructor2 = clazz.getDeclaredConstructor();
		constructor2.setAccessible(true);
		Person person3 = (Person) constructor2.newInstance();// 0������
		// Person person3 = (Person) constructor.newInstance();//2������
		person3.setAge(30);
		person3.setName("qijian");
		System.out.println("����Ĳ���Ϊ��" + person3.getName() + "  " + person3.getAge());

	}

	// 3��Constructorϵ�з���֮��ȡ��������
	// Constructor�л�ȡ�������͵���Ҫ������������
	// //���ڽ���һ�㺯��
	// Class<?>[] getParameterTypes();
	// //���ڽ������Ͷ���
	// Type[] getGenericParameterTypes();

	// ������Ȼ������������getParameterTypes()���ڽ�����ͨ�������͵Ĺ��캯����
	// �� getGenericParameterTypes()�����ڽ������з������Ͳ����Ĺ��캯����
	// �����ص��ǲ����б�����Ӧ��Type���飬�й�Type���͵Ľ������縴��

	private static void getGenericParameterTypes() {
		Class<?> clazz = Person.class;
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		for (Constructor<?> c : constructors) {
			c.setAccessible(true);
			Class<?>[] types = c.getParameterTypes();

			StringBuilder builder = new StringBuilder("��ȡ��������Ϊ��");
			for (Class t : types) {
				builder.append(t.getName());
				builder.append("   ");
			}
			// ��ȡ��������Ϊ��java.lang.Integer java.lang.String
			// ��ȡ��������Ϊ��int java.lang.String
			// ��ȡ��������Ϊ��
			System.out.println(builder.toString());
			int modifier = c.getModifiers();
			System.out.println("һ���������η�Ϊ��" + Modifier.toString(modifier));
		}

	}

	// ���е���Ϣ������������֣������캯������Ա�����ͳ�Ա���������г�Ա������ص���Ϣ��������Filed���͵Ķ����
	// Class�ж�Field����Ļ�ȡ�������ĸ�������
	// ���ܻ�ȡ��������Ϊpublic�ĳ�Ա����
	// Field[] getFields();
	// Field getField(String name);
	// //���Ի�ȡȫ���ĳ�Ա����
	// Field[] getDeclaredFields();����������Ϊpublic,protected����private���ǿ��Եõ��ġ�
	// Field getDeclaredField(String name)
	// getFields()��getDeclaredFields()�ǵõ������ܹ��õ��ĳ�Ա������Field���顣
	// getField(String name)��getDeclaredField(String
	// name)�������ǵõ�ָ���������Ķ�Ӧ��Field����

	private static void getFields() {
		Class<?> clazz = Person.class;
		// ���ȣ�����clazz.getDeclaredFields()�õ����г�Ա��������Ӧ��Field�������顣
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// field.getName()�����ڵõ���ǰ��Ա���������ƣ�����Person���е�age,name
			// field.getType()�����ڵõ���ǰ��Ա���������͡�
			field.setAccessible(true);
			// ��person���У�������������Ա������һ����int age����һ����String name��
			// �ǳ�ע���������������ȫ����������Ϊprivate!����һ���棬����ֻ������getDeclaredXXX()ϵ�к������ܵõ����ǵ�Field��
			// ��һ���棬���Ҫ����ÿ����Ա��������ӦField�������Ϣ���ͱ�������field.setAccessible(true);
			// ��Constructor�У������Ѿ��ᵽ�����е������֣����캯������Ա��������Ա������
			// ���Ƕ����ܻᱻ����Ϊprivate��protected���ͣ�����Ϊ�������û�����private��protected���͵Ķ���
			// ������������������Ӧ������Constructor,Field,Method�ж���ʵ��setAccessible(boolean
			// flag)������..........
			// �����붼��ȫһ������ʾ�Ƿ񽫵�ǰ�������ó�Ϊ�ɷ��ʵġ�Ĭ����false,
			// �������ǰ�������������Ϊprivate��protected���޷����ʡ�

			Class<?> type = field.getType();
			// ö�ٵ���field:int age
			// ö�ٵ���field:java.lang.String name
			System.out.println("ö�ٵ���field:" + type.getName() + "  " + field.getName());
		}

	}

	private static void getField() {
		Class<?> clazz = Person.class;
		Field field;
		try {
			field = clazz.getDeclaredField("age");
			field.setAccessible(true);
			Class<?> type = field.getType();
			// �õ�age��Ӧ��field:int age
			System.out.println("�õ�age��Ӧ��field:" + type.getName() + "  " + field.getName());
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 2��Field��get��set����
	// ����ȡ������ָ���������ĳ������ֵʱ������ʹ��Field�е�get,set�������������£�

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
					// ��һ������objectΪҪ���õ���Ķ��󣬵ڶ���ֵ�Ǵ�Field��������Ӧ��ֵ
					// void set(Object object, Object value)
					// ������Ҫ��ȡֵ����Ķ��󣬷���ֵΪ�ڴ����ʵ���У���Ӧ��Ա������ֵ
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
