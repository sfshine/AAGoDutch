package sinbad.godutch.controls;

import java.util.ArrayList;
import java.util.List;

import sinbad.godutch.R;
import sinbad.godutch.adapter.AdapterSlideMenu;

import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;

/**
 * No.13 ����һ��SlideMenuView����
 * */
public class SlideMenuView {
	
	private Activity mActivity;
	private List mMenuList;
	private boolean mIsClosed;
	private RelativeLayout layBottomBox;
	private OnSlideMenuListener mSlideMenuListener;
	
	public interface OnSlideMenuListener
	{
		public abstract void onSlideMenuItemClick(View pView, SlideMenuItem pSlideMenuItem);
	}
	
	//No.13 �������캯��
	public SlideMenuView(Activity pActivity) {
		mActivity = pActivity;
		InitView();
		if (pActivity instanceof OnSlideMenuListener) {
			mSlideMenuListener = (OnSlideMenuListener) pActivity;
			InitVariable();
			InitListeners();
		}
	}

	//No.13 ���������ĺ���
	public void InitVariable() {
		//Log.i("SlideMenuView", "InitVariable() ��ʼ������");
		mMenuList = new ArrayList();
		
		mIsClosed = true;
	}
	
	//No.13 ������ʼ��View�ĺ���
	public void InitView() {
		//Log.i("SlideMenuView", "InitView() ��ʼ��View���");
		layBottomBox = (RelativeLayout) mActivity.findViewById(R.id.IncludeBottom);
	}
	
	//No.13 ������ʼ��Listeners�ĺ���
	public void InitListeners() {
		//Log.i("SlideMenuView", "InitListeners()��ʼ��������");
		layBottomBox.setOnClickListener(new OnSlideMenuClick());
		
		//No.20 �ڳ�ʼ����������ʱ��׷��һ����������
		layBottomBox.setFocusableInTouchMode(true);
		
		layBottomBox.setOnKeyListener(new OnKeyListener() {
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				//������ʹ�ò˵����Ͱ�����ʱ��
				if (keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_UP) {
					Toggle();
				}				
				return false;
			}
		});
	}

	//No.13 �����򿪺���
	private void Open()
	{
		//Log.i("SlideMenuView", "Open() ������ʼ");
		//��Բ����趨������������д����������
		RelativeLayout.LayoutParams _LayoutParams = 
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,
						RelativeLayout.LayoutParams.FILL_PARENT);
		//׷��̧ͷTitle�Ĳ���
		_LayoutParams.addRule(RelativeLayout.BELOW, R.id.IncludeTitle);
		//��layBottomBox�Ĳ������ý�ȥ
		layBottomBox.setLayoutParams(_LayoutParams);
		//Log.i("SlideMenuView", "mIsClosed = false");
		mIsClosed = false;
	}
	
	//No.14�����رպ���
	private void Close()
	{
		Log.i("SlideMenuView", "Close() ������ʼ");
		//�ر�
		RelativeLayout.LayoutParams _LayoutParams = 
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, 68);
		
		_LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		
		layBottomBox.setLayoutParams(_LayoutParams);
		Log.i("SlideMenuView", "mIsClosed = true");
		mIsClosed = true;
	}
	
	//No.14 �������غ���
	public void Toggle()
	{
		//Log.i("SlideMenuView", "Toggle() ������ʼ");
		//�����ǰ״̬�ǹرյĻ����͵��ÿ�����������֮���ùرպ���
		if(mIsClosed)
		{
			Open();
		}
		else {
			Close();
		}
	}
	
	
	public void RemoveBottomBox()
	{
		//Log.i("SlideMenuView", "RemoveBottomBox() ������ʼ");
		RelativeLayout _MainLayout = (RelativeLayout)mActivity.findViewById(R.id.layMainLayout);
		_MainLayout.removeView(layBottomBox);
		layBottomBox = null;
	}

	//No.14 ����׷�Ӻ���
	//No.15 ���º�����׷�Ӳ���
	public void Add(SlideMenuItem pSliderMenuItem) {
		mMenuList.add(pSliderMenuItem);
		
	}
	
	//No.15 �������б�����List��������Ҫ����һ��Adapter��
	public void BindList()
	{
		//Log.i("SlideMenuView", "BindList ������ʼ");
		AdapterSlideMenu _AdapterSlideMenu = new AdapterSlideMenu(mActivity, mMenuList);
		//ʵ����ListView
		ListView _ListView = (ListView) mActivity.findViewById(R.id.lvSlideList);
		_ListView.setAdapter(_AdapterSlideMenu);
		
		_ListView.setOnItemClickListener(new OnSlideMenuItemClick());
	}
	
	//No.15 ����SlideMenu�������
	private class OnSlideMenuClick implements OnClickListener
	{
		public void onClick(View v) {
			Toggle();
		}
		
	}
	
	//No.15  ����SlideMenu�������
	private class OnSlideMenuItemClick implements OnItemClickListener
	{
		public void onItemClick(AdapterView<?> pAdapterView, View pView, int pPosition,long arg3) {
			//No.17 ͨ�������λ���ҵ���Ӧ��Item 
			SlideMenuItem _SlideMenuItem = (SlideMenuItem) pAdapterView.getItemAtPosition(pPosition);
			//Log.i("SlideMenuView", "Ȼ�����mSlideMenuListener �� �ӿ� onSlideMenuItemClick ���� ���ɼ�����");
			mSlideMenuListener.onSlideMenuItemClick(pView, _SlideMenuItem);
		}
	}
}
