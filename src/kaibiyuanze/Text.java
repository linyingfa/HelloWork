package kaibiyuanze;

import java.util.ArrayList;
import java.util.List;

public class Text {

	/**
	 * ����ԭ���� 1.һ�����ʵ�����ࡢģ��ͺ���Ӧ�ö�[��չ����]��[���޸Ĺر�]�� ���齨�Ĺ�����չ�ǿ��ŵģ���ԭ�д�����޸��Ƿ�յ�
	 * 
	 */
	// .����
	// �ɸ�����
	// ��ά����
	public static void main(String[] args) {
		// List<IRole> role = new ArrayList<>();
		// role.add(new CommonRole("����", "ȫ�����", "ȫ���츳"));
		//
		// System.out.println("Ӣ��:" + role.get(0).getHero());
		// System.out.println("����:" + role.get(0).getFuWen());
		// System.out.println("�츳:" + role.get(0).getGenius());

		/**
		 * ������Ŀ�ķ�չ����ѡ���ɫ���������������Ƥ�����ѡ� ��ô���ſ���ԭ�򲻿�����ԭ�еĽӿ�������Ƥ�����ѡ�������ԭ�еĽӿ������ӣ�
		 * ����Ҫ�޸Ľӿڣ�ʵ���࣬ҵ���߼�ҲҪ�����仯����õķ�ʽ�Ƕ��齨���ܽ�����չ
		 */

		List<IRole> role = new ArrayList<>();
		role.add(new CommonRole("����", "ȫ�����", "ȫ���츳"));
		role.add(new SkinRole("��ʥ", "��������", "�����츳", "���˺�һƤ��"));

		System.out.println(
				"Ӣ��:" + role.get(0).getHero() + " ����:" + role.get(0).getFuWen() + " �츳:" + role.get(0).getGenius());
		System.out.println(
				"Ӣ��:" + role.get(1).getHero() + " ����:" + role.get(1).getFuWen() + " �츳:" + role.get(1).getGenius());

		/**
		 * �ܽ� ����㾡�������ȶ���һ��ȷ���Ͳ�Ҫ�޸ģ��������͡����ö�����ʹ�ýӿڻ��߳����࣬
		 * ������ʵ���ࡣ���ղ����ú����ģʽ�������ɵ���Ӧ��ͬ�ı仯��
		 */
	}

}
