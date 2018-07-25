package fanxing;

/**
 * ��������У����ǹ�����һ��������InfoImpl<T>��Ȼ��ѷ��ͱ���T������Info<T>�� ��˵���ӿںͷ�����ʹ�õĶ���ͬһ�����ͱ�����
 * Ȼ����ʹ��ʱ�����ǹ���һ���������ʵ���Ĺ��̣�ʹ�ù���Ҳ���䡣 ���磺 implements Info<T> extends Info2<T>
 * �������֤�ӿںͷ������Ƿ�ʹ��ͬһ�����ͱ������ı�һ�������ķ��ͱ�������
 */
public class InfoImpl2<T> implements Info<T> {// ���巺�ͽӿڵ�����

	/*
	 * public InfoImpl2(T t) { super(t); setVar(t); // TODO Auto-generated
	 * constructor stub }
	 */

	private T var; // ��������

	public InfoImpl2(T var) { // ͨ�����췽��������������
		this.setVar(var);
	}

	
	@Override
	public void setVar(T var) {
		this.var = var;
	}

	@Override
	public T getVar() {
		return this.var;
	}

}
