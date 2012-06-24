package sinbad.godutch.activity;


import java.util.Date;

import sinbad.godutch.R;
import sinbad.godutch.activity.base.ActivityFrame;
import sinbad.godutch.adapter.AdapterAppGrid;
import sinbad.godutch.business.BusinessDataBackup;
import sinbad.godutch.controls.SlideMenuItem;
import sinbad.godutch.controls.SlideMenuView.OnSlideMenuListener;
import sinbad.godutch.service.ServiceDatabaseBackup;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;


/**
 * No.7 ���̳������Ϊ�Զ����ActivityFrame��
 * �������ĺô����ǣ����Զ���Frame�ܹ��ĸ�ʽ�����͡�
 * ���Frame�����б仯��ֱ���ڸ���Frame��
 * No.17 ׷�� implements �ӿ�
 * */
public class ActivityMain extends ActivityFrame implements OnSlideMenuListener,OnClickListener{
	    
	private GridView gvAppGrid;
	public BusinessDataBackup mBusinessDataBackup;
	private AdapterAppGrid mAdapterAppGrid;
	private AlertDialog mDatabaseBackupDialog;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                                
        Log.i("ActivityMain", "onCreate ����");
        AppendMainBody(R.layout.main_body);
        Log.i("ActivityMain", "main_body ���ֽ�������");
        InitVariable();
        InitView();
        InitListeners();        
        BindData();
        CreateSlideMenu(R.array.SlideMenuActivityMain);
        StartService();
    }
    
    private void StartService() {
		Intent _Intent = new Intent(this, ServiceDatabaseBackup.class);
		startService(_Intent);
	}    
    
    //No.10 ����ʵ����
    public void InitVariable()
    {
    	Log.i("ActivityMain", "InitVariable ��ʼ������");
    	mAdapterAppGrid = new AdapterAppGrid(this);
    	mBusinessDataBackup = new BusinessDataBackup(this);
    }
    
    //No.10  ��ʼ��View
    public void InitView()
    {
    	Log.i("ActivityMain", "InitView ��ʼ��View���");
    	gvAppGrid = (GridView) findViewById(R.id.gvAppGrid);
    }
    
    public void InitListeners()
    {
    	Log.i("ActivityMain", "InitListeners ��ʼ��������");
    	gvAppGrid.setOnItemClickListener(new onAppGridItemClickListener());
    }
    
    private class onAppGridItemClickListener implements OnItemClickListener
    {
		public void onItemClick(AdapterView pAdapter, View pView, int pPosition,long arg3) {
			String _MenuName = (String)pAdapter.getAdapter().getItem(pPosition);
			if(_MenuName.equals(getString(R.string.appGridTextUserManage)))
			{
				OpenActivity(ActivityUser.class);
				return;
			}
			if(_MenuName.equals(getString(R.string.appGridTextAccountBookManage)))
			{
				Log.i("ActivityMain", "����˱�����ͼ�꣬OpenActivity");
				OpenActivity(ActivityAccountBook.class);
				return;
			}
			if(_MenuName.equals(getString(R.string.appGridTextCategoryManage)))
			{
				Log.i("ActivityMain", "���������ͼ�꣬ActivityCategory");
				OpenActivity(ActivityCategory.class);
				return;
			}
			if(_MenuName.equals(getString(R.string.appGridTextPayoutAdd)))
			{
				Log.i("ActivityMain", "������Ѽ�¼ͼ�꣬ActivityPayoutAddOrEdit");
				OpenActivity(ActivityPayoutAddOrEdit.class);
				return;
			}
			if(_MenuName.equals(getString(R.string.appGridTextPayoutManage)))
			{
				Log.i("ActivityMain", "�����ѯ����ͼ�꣬ActivityPayout");
				OpenActivity(ActivityPayout.class);
				return;
			}
			if(_MenuName.equals(getString(R.string.appGridTextStatisticsManage)))
			{
				Log.i("ActivityMain", "���ͳ�ƹ���ͼ�꣬ActivityStatistics");
				OpenActivity(ActivityStatistics.class);
				return;
			}
		}
	}
    
    //No. 10 ������
    public void BindData()
    {
    	Log.i("ActivityMain", "BindData ����������");
    	gvAppGrid.setAdapter(mAdapterAppGrid);
    }
    
    public void onClick(View v) {
    	Log.i("ActivityMain", "onClick ����¼�");
		switch (v.getId()) {
		case R.id.btnDatabaseBackup:
			DatabaseBackup();
			break;
		case R.id.btnDatabaseRestore:
			DatabaseRestore();
			break;
		default:
			break;
		}		
	}

    //No.17 ��׷��һ���ӿڷ���
	public void onSlideMenuItemClick(View pView, SlideMenuItem pSlideMenuItem) {	
		Log.i("ActivityMain", "onSlideMenuItemClick ����¼�");
		SlideMenuToggle();
		if (pSlideMenuItem.getItemID() == 0) {
			ShowDatabaseBackupDialog();
		}
	}
	
	private void ShowDatabaseBackupDialog()
	{
		Log.i("ActivityMain", "ShowDatabaseBackupDialog ��ʾ���ݿⱸ�ݶԻ���");
		LayoutInflater _LayoutInflater = LayoutInflater.from(this);
		
		View _View = _LayoutInflater.inflate(R.layout.database_backup, null);
		
		Button _btnDatabaseBackup = (Button)_View.findViewById(R.id.btnDatabaseBackup);
		Button _btnDatabaseRestore = (Button)_View.findViewById(R.id.btnDatabaseRestore);
		
		_btnDatabaseBackup.setOnClickListener(this);
		_btnDatabaseRestore.setOnClickListener(this);
		
		String _Title = getString(R.string.DialogTitleDatabaseBackup);
		
		AlertDialog.Builder _Builder = new AlertDialog.Builder(this);
		_Builder.setTitle(_Title);
		_Builder.setView(_View);
		_Builder.setIcon(R.drawable.database_backup);
		_Builder.setNegativeButton(getString(R.string.ButtonTextBack), null);
		mDatabaseBackupDialog = _Builder.show();
	}
	
	private void DatabaseBackup()
	{
		Log.i("ActivityMain", "DatabaseBackup ���ݱ���");
		if(mBusinessDataBackup.DatabaseBackup(new Date()))
		{
			ShowMsg(R.string.DialogMessageDatabaseBackupSucceed);
		}
		else {
			ShowMsg(R.string.DialogMessageDatabaseBackupFail);
		}
		
		mDatabaseBackupDialog.dismiss();
	}
	
	private void DatabaseRestore()
	{
		Log.i("ActivityMain", "DatabaseRestore ���ݻ�ԭ");
		if(mBusinessDataBackup.DatabaseRestore())
		{
			ShowMsg(R.string.DialogMessageDatabaseRestoreSucceed);
		}
		else {
			ShowMsg(R.string.DialogMessageDatabaseRestoreFail);
		}
		
		mDatabaseBackupDialog.dismiss();
	}

   
}