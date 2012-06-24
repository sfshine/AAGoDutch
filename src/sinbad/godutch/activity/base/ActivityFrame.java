package sinbad.godutch.activity.base;
/**
 *  No.8 �½�ActivityFrame��
 * */

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import sinbad.godutch.R;
import sinbad.godutch.controls.SlideMenuItem;
import sinbad.godutch.controls.SlideMenuView;

public class ActivityFrame extends ActivityBase {
	
	//No. 15  ׷��SlideMenuView ����
	private SlideMenuView mSlideMenuView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        //No.8 ����Ϊû��title�Ĵ���
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);		
		//Log.i("ActivityFrame", "ActivityFrame onCreate����");		
		View view = findViewById(R.id.ivAppBack);
		OnBackListener _OnBackListener = new OnBackListener();
		view.setOnClickListener(_OnBackListener);
	}
	
	private class OnBackListener implements android.view.View.OnClickListener
	{
		public void onClick(View view)
		{
			finish();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	/**
	 * ����TopBar����
	 * @param pRestID Ҫ���õ�String��ԴID
	 */
	protected void SetTopBarTitle(String pText) {
		//Log.i("ActivityFrame", "SetTopBarTitle ����TopBarTitle");
		TextView _tvTitle = (TextView) findViewById(R.id.tvTopTitle);
		_tvTitle.setText(pText);
		
	}
	
	protected void HideTitleBackButton()
	{
		//Log.i("ActivityFrame", "���� HideTitleBackButton ����");
		findViewById(R.id.ivAppBack).setVisibility(View.GONE);
	}
	
	/**
	 * No.8
	 * ���Layout������Body��
	 * @param pResID Ҫ��ӵ�Layout��ԴID
	 */
	protected void AppendMainBody(int pResID)
	{
		//Log.i("ActivityFrame", "���� AppendMainBody ������ͨ����Դ�ļ�����");
		LinearLayout _MainBody = (LinearLayout)findViewById(GetMainBodyLayoutID());		
		View _View = LayoutInflater.from(this).inflate(pResID, null);
		RelativeLayout.LayoutParams _LayoutParams = 
				new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);
		_MainBody.addView(_View,_LayoutParams);
	}
	
	/**
	 * ���Layout������Body��
	 * @param pView Ҫ��ӵ�View
	 */
	protected void AppendMainBody(View pView)
	{
		//Log.i("ActivityFrame", "���� AppendMainBody ������ͨ����ͼ�ļ�����");
		LinearLayout _MainBody = (LinearLayout)findViewById(GetMainBodyLayoutID());		
		RelativeLayout.LayoutParams _LayoutParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);
		_MainBody.addView(pView,_LayoutParams);
	}
	
	/**
	 * No.15 ׷���½�SlideMenu�ĺ���
	 * */
	protected void CreateSlideMenu(int pResID) {
		//Log.i("ActivityFrame", "���������˵� CreateSlideMenu");
		mSlideMenuView = new SlideMenuView(this);
		
		String[] _MenuItemArray = getResources().getStringArray(pResID);				
		
		//No.16  ׷���߼�����
		for (int i = 0; i < _MenuItemArray.length; i++) {
			SlideMenuItem _Item = new SlideMenuItem(i, _MenuItemArray[i]);
			mSlideMenuView.Add(_Item);
		}		
		mSlideMenuView.BindList();
	}
	
	/**
	 * No.8
	 * ���Layout��ԴID������Layout��
	 */
	private int GetMainBodyLayoutID()
	{
		//Log.i("ActivityFrame", "���� �����沼�ֵ�ID    GetMainBodyLayoutID");
		//��������������Բ���Layout��Դ
		return R.id.layMainBody;
	}
	
	/**
	 * No.31
	 * ���SlideMenuToogle
	 */
	protected void SlideMenuToggle() {
		//Log.i("ActivityFrame", "���û����˵�����   SlideMenuToggle");
		mSlideMenuView.Toggle();
	}
	
	protected void RemoveBottomBox()
	{
		//Log.i("ActivityFrame", "ɾ���ײ���  RemoveBottomBox");
		mSlideMenuView = new SlideMenuView(this);
		mSlideMenuView.RemoveBottomBox();
	}
	
	protected void CreateMenu(Menu pMenu)
	{
		//Log.i("ActivityFrame", "�����˵�  CreateMenu");
		int _GroupID = 0;
		int _Order = 0;
		int[] pItemID = {1,2};
		
		for(int i=0;i<pItemID.length;i++)
		{
			switch(pItemID[i])
			{
			case 1:
				pMenu.add(_GroupID, pItemID[i], _Order, R.string.MenuTextEdit);
				break;
			case 2:
				pMenu.add(_GroupID, pItemID[i], _Order, R.string.MenuTextDelete);
				break;
			default:
				break;
			}
		}
	}
	
}
