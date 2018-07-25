package fanxing;

import java.util.ArrayList;
import java.util.List;

public class StaticFans {
	// ��̬����
	public static <T> void StaticMethod(T a) {
		// Log.d("harvic", "StaticMethod: " + a.toString());
		System.out.println("StaticMethod: " + a.toString());

	}

	public static <T> List<T> parseArray() {
		// List<T> modelList = JSON.parseArray(response, object);

		return new ArrayList<>();
	}

	// ��ͨ����
	public <T> void OtherMethod(T a) {
		// Log.d("harvic", "OtherMethod: " + a.toString());
		System.out.println("OtherMethod: " + a.toString());
	}

	public <T> T call(T a) {
		return a;
	}

	// ����
	public static <T> T[] fun1(T... arg) { // ���տɱ����
		return arg; // ���ط�������
	}

	public <T> T[] fun2(T... arg) { // ���տɱ����
		return arg; // ���ط�������
	}

	public String[] fun3(String... arge) {
		return arge;
	}

	public int[] sumUp(int... values) {
		return values;
	}
	

}
