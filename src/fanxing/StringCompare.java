package fanxing;

public class StringCompare implements Main.Comparable<StringCompare> {
	
	public String mStr;

	public StringCompare(String string) {
		this.mStr = string;
	}

	 @Override
	    public  boolean compareTo(StringCompare str) {
	        if (mStr.length() > str.mStr.length()){
	            return true;
	        }
	        return false;
	    }
}
