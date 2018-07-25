package factory;

//������������
public class BMWCarFactory implements BaseCarFactory {

	@Override
	public ICar create() {
		return new BMW();
	}
}
