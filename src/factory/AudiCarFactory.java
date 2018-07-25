package factory;

//�µ���������
public class AudiCarFactory implements BaseCarFactory {

	@Override
	public ICar create() {
		return new Audi();
	}
}
