package kaibiyuanze;

public class SkinRole implements ISkinRole {

	// Ӣ��
	private String hero;
	// ����
	private String fuWen;
	// �츳
	private String genius;
	// Ƥ��
	private String skin;

	public SkinRole(String hero, String fuWen, String genius, String skin) {
		this.hero = hero;
		this.fuWen = fuWen;
		this.genius = genius;
		this.skin = skin;
	}

	@Override
	public String getSkin() {
		return skin;
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
