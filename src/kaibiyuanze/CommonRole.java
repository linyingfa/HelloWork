package kaibiyuanze;

public class CommonRole implements IRole {

	// Ӣ��
	private String hero;
	// ����
	private String fuWen;
	// �츳
	private String genius;

	public CommonRole(String hero, String fuWen, String genius) {
		this.hero = hero;
		this.fuWen = fuWen;
		this.genius = genius;
	}

	@Override
	public String getHero() {
		return hero;
	}

	@Override
	public String getFuWen() {
		return fuWen;
	}

	@Override
	public String getGenius() {
		return genius;
	}

}
