package factory;

public class BMW implements ICar {

	@Override
	public void engine() {
		System.out.println("����ķ�����");
	}

	@Override
	public void brand() {
		System.out.println("�����Ʒ��");
	}
}
