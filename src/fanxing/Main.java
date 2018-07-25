package fanxing;

import java.io.Serializable;

/**
 * ʹ�÷���ʵ�ֵ����ƣ�����ǿ��ת����Object��ʽ��
 * �������Ͳ��ԣ�����ʱ�ᱨ����������Objectʵ�ַ�ʽһ����������ʱ�Żᱨǿ��ת�����󡣣��������Ͷ���Object�������ࣩ
 */
public class Main {

	public static void main(String[] args) {
		Main m = new Main();
		// m.showclass();
		// m.showinterface();
		// m.showMethod();

		// StringCompare result =m. min1(new StringCompare("123"), new
		// StringCompare("234"), new StringCompare("59897"));
		// System.out.println(result.mStr);

		String name_1 = getFruitName(new Banana());
		String name_2 = getFruitName(new Apple());

		// �ޱ߽�ͨ�������
		// �����?�����ޱ߽�ͨ�����ͨ����������������һ��δ֪�ķ��ţ������Ǵ����������
		Point<?> point;
		point = new Point<Integer>(3, 3);
		point = new Point<Float>(4.3f, 4.3f);
		point = new Point<Double>(4.3d, 4.90d);
		point = new Point<Long>(12l, 23l);

	}

	// �󶨶���޶�
	public static <T extends Fruit> String getFruitName(T t) {
		return t.getName();
	}

	// �󶨶���޶�
	public static <T extends Fruit & Serializable> String getFruitName2(T t) {
		return t.getName();
	}

	public <T extends Comparable & Serializable, U extends Runnable> T foo2(T a, U b) {
		return a;
	}

	// �����extends Comparable֮�󣬾Ϳ���Comparable��ĺ�����
	// ���Ͱ󶨣�extends,�����Ͱ����������ã�1�������ķ��ͼ����޶� 2��ʹ�÷��ͱ���Tʱ������ʹ��BoundingType�ڲ��ĺ�����
	// ������һ��ǳ�Ҫע����ǣ��������smallest.compareTo(item)��smallest��itemȫ������T���͵ģ�Ҳ����˵��compareTo�Աȵ���ͬһ�����͡�
	public <T extends Comparable> T min1(T... a) {
		T smallest = a[0];
		for (T item : a) {
			if (smallest.compareTo(item)) {
				smallest = item;
			}
		}
		return smallest;
	}

	public interface Comparable<T> {
		public boolean compareTo(T i);
	}

	private void showMethod() {
		// ��̬����
		StaticFans.StaticMethod("adfdsa");// ʹ�÷���һ
		StaticFans.<String> StaticMethod("adfdsa");// ʹ�÷�����

		// ���淽��
		StaticFans staticFans = new StaticFans();
		staticFans.OtherMethod(new Integer(123));// ʹ�÷���һ
		staticFans.<Integer> OtherMethod(new Integer(123));// ʹ�÷�����
	}

	private void showinterface() {
		// ʹ�÷���һ���Ƿ�����
		// Info info = new InfoImpl();
		// info.setVar("1231");
		// ���캯��InfoImpl��String��δ���壬�������˷��ͣ���String����
		// ��Ϊ���ǹ���ʱ���������ģ�class Point<T>,������ʹ�õ�ʱ��ҲҪ��Point���������������T��������塣
		// ����ͨĬ���ǹ����ǣ�class Point����
		// InfoImpl i = new InfoImpl("harvic");
		// System.out.println(i.getVar());
		// ʹ�÷�������������
		InfoImpl2<String> i2 = new InfoImpl2<String>("harvic");
		System.out.println(i2.getVar());

	}

	// TODO �����ඨ�弰ʹ��
	private void showclass() {
		Point<Integer> p1 = new Point<Integer>();
		p1.setX(new Integer(100));
		System.out.println(p1.getX());

		Point<Float> p2 = new Point<Float>();
		p2.setX(new Float(100.12f));
		System.out.println(p2.getX());

		// ʹ��
		MorePoint<Integer, String> morePoint = new MorePoint<Integer, String>();
		morePoint.setName("harvic");
		System.out.println("morPont.getName:" + morePoint.getName());
	}
}
