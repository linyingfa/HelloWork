package fanshe;

import java.io.Serializable;

/**
 * 继承关系可以不重写父类的方法，也可以重写
 * 
 * @author xiaolin
 *implements Serializable
 */
public class AnimalImpl extends Animal  {

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}

}
