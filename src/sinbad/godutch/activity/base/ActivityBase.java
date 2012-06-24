package sinbad.godutch.activity.base;
/**
 * No. 7 �½�Activity����
 * */
import java.lang.reflect.Field;

import sinbad.godutch.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.Toast;

public class ActivityBase extends Activity {
	
	//����һ��Toast�ĵ���ʱ��
	protected static final int SHOW_TIME = 1000;
	private ProgressDialog m_ProgressDialog;
	/**
	 * No.7   ��װһ����Ϣ���ѷ���
	 * ������ʾ������Ϣ��
	 * */
	protected void ShowMsg(String pMsg) {
		Toast.makeText(this, pMsg, SHOW_TIME).show();
	}
	
	/**
	 * ��ʾһ��Toast��Ϣ
	 * @param p_Msg Ҫ��ʾ����ϢID
	 */
	protected void ShowMsg(int p_ResID) {
		Toast.makeText(this, p_ResID, SHOW_TIME).show();
	}
	
	/**
	 * No.7   ��װһ���򿪴���ķ���
	 * */
	protected void OpenActivity(Class<?> pClass) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pClass);
		
		startActivity(_Intent);
	}
	
	/**
	 * No.29   ��װһ��ȡ�ô���ķ���
	 * */
	protected LayoutInflater GetLayouInflater() {
		LayoutInflater _LayoutInflater = LayoutInflater.from(this);
		return _LayoutInflater;
	}
	
	/**
	 * No.30   ��װһ�����ô����Ƿ�رյķ���
	 * */
	public void SetAlertDialogIsClose(DialogInterface pDialog,Boolean pIsClose)
	{
		try {
			Field _Field = pDialog.getClass().getSuperclass().getDeclaredField("mShowing");
			_Field.setAccessible(true);
		    _Field.set(pDialog, pIsClose);
		} catch (Exception e) {
		}
	}
	
	/**
	 * No.29   ��װһ�����ѶԻ���ķ���
	 * */
	protected AlertDialog ShowAlertDialog(int p_TitelResID,String p_Message,DialogInterface.OnClickListener p_ClickListener)
	{
		String _Title = getResources().getString(p_TitelResID);
		return ShowAlertDialog(_Title, p_Message, p_ClickListener);
	}
	
	/**
	 * No.29   ��װһ�����ѶԻ���ķ���
	 * */
	protected AlertDialog ShowAlertDialog(String p_Title,String p_Message,DialogInterface.OnClickListener p_ClickListener)
	{		
		return new AlertDialog.Builder(this)
		.setTitle(p_Title)
		.setMessage(p_Message)
		.setPositiveButton(R.string.ButtonTextYes, p_ClickListener)
		.setNegativeButton(R.string.ButtonTextNo, null)
		.show();
	}
	
	/**
	 * No.29   ��װһ�����ȶԻ���ķ���
	 * */
	protected void ShowProgressDialog(int p_TitleResID,int p_MessageResID) {
		m_ProgressDialog = new ProgressDialog(this);
		m_ProgressDialog.setTitle(getString(p_TitleResID));
		m_ProgressDialog.setMessage(getString(p_MessageResID));
		m_ProgressDialog.show();
	}

	/**
	 * No.29   ��װһ��ȡ�����ȶԻ���ķ���
	 * */
	protected void DismissProgressDialog() {
		if(m_ProgressDialog != null)
		{
			m_ProgressDialog.dismiss();
		}
	}
}
