package sinbad.godutch.controls;

import sinbad.godutch.configration.CommonConstants;
import android.util.Log;

/**
 * No.12 �½�SlideMenuItem���ʵ����
 * */
public class SlideMenuItem {
	
	private int mItemID;
	private String mTitle;
	
	//No.16 ׷�ӹ��캯����ʵ������ʱ��ʡ���顣
	public SlideMenuItem(int pItemID,String pTitle)
	{
		mItemID = pItemID;
		mTitle = pTitle;
	}
	
	public int getItemID() {
		return mItemID;
	}
	
	public void setItemID(int pItemID) {
		this.mItemID = pItemID;
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public void setmTitle(String pTitle) {
		this.mTitle = pTitle;
	}
}
