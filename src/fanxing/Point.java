package fanxing;

/**
 * �����ඨ�弰ʹ��
 * 
 * @author xiaolin
 *
 * @param <T>
 */

/*
 * �ڲ�ͬ���龳��ʹ�õ���ĸ�������£� E �� Element��������java
 * Collection��磺List<E>,Iterator<E>,Set<E> K,V �� Key��Value������Map�ļ�ֵ�� N ��
 * Number������ T �� Type�����ͣ���String��Integer�ȵ�
 */
public class Point<T> {

	private T x;
	private T y;

	public Point() {

	}

	public Point(T x, T y) {
		this.x = x;
		this.y = y;
	}

	public void setX(T x) {// ��Ϊ����
		this.x = x;
	}

	public void setY(T y) {
		this.y = y;
	}

	public T getX() {// ��Ϊ����ֵ
		return this.x;
	}

	public T getY() {
		return this.y;

	}
}
