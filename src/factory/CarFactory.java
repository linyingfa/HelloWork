package factory;

//��������
public class CarFactory {

	// ��������
	public <T extends ICar> T create(Class<T> c) {
		ICar car = null;
		try {
			// ��������newһ����
			car = (ICar) Class.forName(c.getName()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) car;
	}

}
