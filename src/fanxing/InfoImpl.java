package fanxing;

/**
 * ���������һ�����ͣ�����û��ȷ����������ͣ��ͻ�Ĭ����Object���� public class InfoImpl implements Info
 * <Object>
 * 
 * @author xiaolin
 *
 */
public class InfoImpl implements Info<String> {

	private String var; // ��������

	public InfoImpl(String var) { // ͨ�����췽��������������
		this.setVar(var);
	}

	@Override
	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public String getVar() {
		return this.var;
	}

	// @Override
	// public Object getVar() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public void setVar(Object x) {
	// // TODO Auto-generated method stub
	// System.out.println((String)x);
	//
	// }

}
