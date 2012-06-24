package sinbad.godutch.database.base;


import java.util.ArrayList;

import sinbad.godutch.R;
import sinbad.godutch.configration.CommonConstants;
import android.content.Context;
import android.util.Log;


/**
 * No.18 �������ݿ�����
 * */
public class SQLiteDataBaseConfig {

	//No.18 ��ʼ������
	private static final String DATABASE_NAME = "GoDutchDataBase";
	private static final int VERSION = 1;
	private static SQLiteDataBaseConfig INSTANCE;
	private static Context mContext;
	
	//No.18  ���캯��
	private SQLiteDataBaseConfig()
	{
		
	}
	
	//No.18 ����ģʽ��ʵ����
	public static SQLiteDataBaseConfig GetInstance(Context pContext)
	{
		Log.i("SQliteDataBaseConfig", "ʵ���� SQLiteDataBaseConfig ��ʵ��");
		if (INSTANCE == null) {
			INSTANCE = new SQLiteDataBaseConfig();
			mContext = pContext;
		}
		
		return INSTANCE;
	}
	
	//No.18 �������ݿ���
	public String GetDataBaseName()
	{
		Log.i("SQliteDataBaseConfig", " ���� GetDataBaseName");
		return DATABASE_NAME;
	}
	
	//No.18 �������ݿ�汾
	public int GetVersion()
	{
		Log.i("SQliteDataBaseConfig", "���� ���ݿ�汾VERSION");
		return VERSION;
	}
	
	//No.18 ȡ�����ݿ�ı���
	public ArrayList<String> GetTables() {
		Log.i("SQliteDataBaseConfig", "��ȡ���ݿ�ı���");
		//�½�ArrayList���ݣ�����ȡ��ArrayList
		ArrayList<String> _ArrayList = new ArrayList<String>();
		
		//No.25 ���������ļ�
		//���������Դ��������ȡ��Ӧ�ı���
		String[] _SQLiteDALClassName = mContext.getResources().getStringArray(R.array.SQLiteDALClassName);
		
		//����ÿһ�������Ķ�Ӧ·����
		String _PackagePath = mContext.getPackageName() + ".database.sqlitedal.";
		
		for (int i = 0; i < _SQLiteDALClassName.length; i++) {
			_ArrayList.add(_PackagePath + _SQLiteDALClassName[i]);
		}
		return _ArrayList;
	}
	
}
