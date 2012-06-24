package sinbad.godutch.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import sinbad.godutch.R;
import sinbad.godutch.activity.base.ActivityFrame;
import sinbad.godutch.adapter.AdapterUser;
import sinbad.godutch.business.BusinessUser;
import sinbad.godutch.configration.CommonConstants;
import sinbad.godutch.controls.SlideMenuItem;
import sinbad.godutch.controls.SlideMenuView.OnSlideMenuListener;
import sinbad.godutch.model.ModelUser;
import sinbad.godutch.utility.RegexTools;
/**
 * No.25 �½�ActivityUser��
 * */
public class ActivityUser extends ActivityFrame implements OnSlideMenuListener {

	//No.25 ��������
	private ListView lvUserList;
	private AdapterUser mAdapterUser;
	private BusinessUser mBusinessUser;
	private ModelUser mSelectModlUser;
	
	/* No.25  */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("ActivityUser", " AppendMainBody������ʼʹ�ã���ʼ��User����");
		AppendMainBody(R.layout.user);		
		InitVariable();		
        InitView();        
        InitListeners();        
        BindData();
        Log.i("ActivityUser", "ActivityUser CreateSlideMenu������ʼʹ�ã�����˵��û�");
        CreateSlideMenu(R.array.SlideMenuUser);
	}


	
	//No.25  �½���ʼ������
	public void InitVariable()
    {
		Log.i("ActivityUser", "ActivityUser InitVariable()������ʼʹ�ã���ʼ������");
    	mBusinessUser = new BusinessUser(this);
    }
    
	//No.25  �½���ʼView
    public void InitView()
    {
    	Log.i("ActivityUser", "ActivityUser InitView()������ʼʹ�ã���ʼ��View��ͼ");
    	lvUserList = (ListView) findViewById(R.id.lvUserList);
    }
    
    //No.25  �½���ʼ��������
    public void InitListeners()
    {
    	Log.i("ActivityUser", "ActivityUser InitListeners()������ʼʹ�ã����ɼ�����");
    	registerForContextMenu(lvUserList);
    }
    
    //No.32 �½�onCreateContextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	
    	Log.i("ActivityUser", "onCreateContextMenu");
    	AdapterContextMenuInfo _AdapterContextMenuInfo = (AdapterContextMenuInfo) menuInfo;
    	ListAdapter _ListAdapter = lvUserList.getAdapter();    	
    	mSelectModlUser = (ModelUser)_ListAdapter.getItem(_AdapterContextMenuInfo.position);    	
    	menu.setHeaderIcon(R.drawable.user_small_icon);
    	menu.setHeaderTitle(mSelectModlUser.getUserName());
    	
    	CreateMenu(menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	Log.i("ActivityUser", "onContextItemSelected");
    	switch (item.getItemId()) {
		case 1:
			ShowUserAddOrEditDialog(mSelectModlUser);
			break;
		case 2:
			Delete();
			break;
		default:
			break;
		}

    	return super.onContextItemSelected(item);
    }
    
    //No.25  �½���ʼ��������
    public void BindData()
    {
    	Log.i("ActivityUser", "ActivityUser BindData()������ʼʹ�ã�������");
    	mAdapterUser = new AdapterUser(this);
    	lvUserList.setAdapter(mAdapterUser);
    	SetTitle();
    }
    
	public void onSlideMenuItemClick(View pView, SlideMenuItem pSlideMenuItem) {
		Log.i("ActivityUser", "onSlideMenuItemClick ");
		SlideMenuToggle();
		if (pSlideMenuItem.getItemID() == 0) {
			ShowUserAddOrEditDialog(null);
		}
	}
    
    //No.29   �����û��½����߱༭�Ի���
	private void SetTitle() {
		Log.i("ActivityUser", "SetTitle ");
		int _Count = mAdapterUser.getCount();
		String _Titel = getString(R.string.ActivityTitleUser, new Object[]{_Count});
		SetTopBarTitle(_Titel);
	}
	
	//No.29   �����û��½����߱༭�Ի���
	private void ShowUserAddOrEditDialog(ModelUser pModelUser)
	{
		Log.i("ActivityUser", "ShowUserAddOrEditDialog ");
		View _View = GetLayouInflater().inflate(R.layout.user_add_or_edit, null);		
		EditText _etUserName = (EditText) _View.findViewById(R.id.etUserName);		
		if (pModelUser != null) {
			_etUserName.setText(pModelUser.getUserName());
		}
		
		String _Title;
		
		if(pModelUser == null)
		{
			_Title = getString(R.string.DialogTitleUser,new Object[]{getString(R.string.TitleAdd)});
		}
		else {
			_Title = getString(R.string.DialogTitleUser,new Object[]{getString(R.string.TitleEdit)});
		}
		
		AlertDialog.Builder _Builder = new AlertDialog.Builder(this);
		_Builder.setTitle(_Title)
				.setView(_View)
				.setIcon(R.drawable.user_big_icon)
				.setNeutralButton(getString(R.string.ButtonTextSave), new OnAddOrEditUserListener(pModelUser,_etUserName,true))
				.setNegativeButton(getString(R.string.ButtonTextCancel), new OnAddOrEditUserListener(null,null,false))
				.show();
	}
	
	//No.29  ��ӻ��߱༭�û��ļ�����
	private class OnAddOrEditUserListener implements DialogInterface.OnClickListener
	{
		private ModelUser mModelUser;
		private EditText etUserName;
		private boolean mIsSaveButton;
		
		public OnAddOrEditUserListener(ModelUser pModelUser,EditText petUserName,Boolean pIsSaveButton)
		{
			Log.i("ActivityUser", "OnAddOrEditUserListener OnAddOrEditUserListener");
			mModelUser = pModelUser;
			etUserName = petUserName;
			mIsSaveButton = pIsSaveButton;
		}
		
		//���ǿ��ж�
		public void onClick(DialogInterface dialog, int which) {
			if(mIsSaveButton == false)
			{
				SetAlertDialogIsClose(dialog, true);
				return;
			}
			
			if (mModelUser == null) {
				mModelUser = new ModelUser();
			}
			
			String _UserName = etUserName.getText().toString().trim();
			
			boolean _CheckResult = RegexTools.IsChineseEnglishNum(_UserName);
			
			if (!_CheckResult) {
				Toast.makeText(getApplicationContext(), getString(R.string.CheckDataTextChineseEnglishNum,new Object[]{etUserName.getHint()}), SHOW_TIME).show();
				SetAlertDialogIsClose(dialog, false);
				return;
			}
			else {
				SetAlertDialogIsClose(dialog, true);
			}
			
			_CheckResult = mBusinessUser.IsExistUserByUserName(_UserName, mModelUser.getUserID());
			
			if (_CheckResult) {
				Toast.makeText(getApplicationContext(), getString(R.string.CheckDataTextUserExist), SHOW_TIME).show();
				
				SetAlertDialogIsClose(dialog, false);
				return;
			}
			else {
				SetAlertDialogIsClose(dialog, true);
			}
			
			mModelUser.setUserName(etUserName.getText().toString());
			
			boolean _Result = false;
			
			if (mModelUser.getUserID() == 0) {
				_Result = mBusinessUser.InsertUser(mModelUser);
			}
			else {
				_Result = mBusinessUser.UpdateUserByUserID(mModelUser);
			}
			
			if (_Result) {
				BindData();
			}
			else {
				Toast.makeText(ActivityUser.this, getString(R.string.TipsAddFail), 1).show();
			}
		}
		
	}
	
	//No.33
	private void Delete() {
		Log.i("ActivityUser", "Delete() ");
		String _Message = getString(R.string.DialogMessageUserDelete,new Object[]{mSelectModlUser.getUserName()});
		ShowAlertDialog(R.string.DialogTitleDelete,_Message,new OnDeleteClickListener());
	}
	
	private class OnDeleteClickListener implements DialogInterface.OnClickListener
	{
		public void onClick(DialogInterface dialog, int which) {
			boolean _Result = mBusinessUser.HideUserByUserID(mSelectModlUser.getUserID());
			Log.i("ActivityUser", "OnDeleteClickListener onClick");
			if (_Result == true) {
				BindData();
			}
		}
	}

}
