package s;

import java.util.ArrayList;

public class AA {
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	public static void main(String[] args) {
		ArrayList<String> s = new ArrayList<>();
		String[]  srcBytes = new String[4];  // Դ����  // Ŀ������
		srcBytes[0]="2";
		srcBytes[1]="4";
		srcBytes[2]="0";
        System.arraycopy(srcBytes,1,srcBytes ,2,1);
        for (String b : srcBytes) {
    		System.out.println(b);
		}
        
        	System.out.println(4/2);
        	
        	System.out.println(Integer.MIN_VALUE);

        	System.out.println(Integer.MIN_VALUE-1);

        	System.out.println(Integer.MAX_VALUE);

        	System.out.println(Integer.MAX_VALUE-1);
	}

}
