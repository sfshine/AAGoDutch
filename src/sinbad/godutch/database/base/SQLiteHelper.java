package sinbad.godutch.database.base;

import java.util.ArrayList;

import sinbad.godutch.utility.Reflection;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * No.18 �½�һ��SQLiteHelper
 * */
public class SQLiteHelper extends SQLiteOpenHelper {

	
	//No.18 �½�����
	private static SQLiteDataBaseConfig SQLITE_DATEBASE_CONFIG;
	private static SQLiteHelper INSTANCE;
	private Context mContext;
	private Reflection mReflection;
	
	//No.21 �½������ӿڣ������½��û���Ϣ�͸����û���Ϣ
	public interface SQLiteDataTable
	{
		public void OnCreate(SQLiteDatabase pDataBase);
		public void OnUpgrade(SQLiteDatabase pDataBase);
	}
	
	
	
	//No.18 �½����캯��  ˽��
	private SQLiteHelper(Context pContext)
	{
		super(pContext, SQLITE_DATEBASE_CONFIG.GetDataBaseName(), null, SQLITE_DATEBASE_CONFIG.GetVersion());
		mContext = pContext;
		Log.i("SQLiteHelper", "SQLiteHelper(Context pContext) ���캯��");
	}
	
	/* No.18 �Զ�ʵ�ֺ��� */
	public SQLiteHelper(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
		Log.i("SQLiteHelper", "SQLiteHelper(Context context, String name, CursorFactory factory,int version) ���캯��");
	}
	
	//No.18 �½�һ������ʵ��
	public static SQLiteHelper GetInstance(Context pContext)
	{
		Log.i("SQLiteHelper", "GetInstance ȡ��һ��ʵ��");
		if (INSTANCE == null) {
			Log.i("SQLiteHelper", "���û�У���ô���½�һ��");
			SQLITE_DATEBASE_CONFIG = SQLITE_DATEBASE_CONFIG.GetInstance(pContext);
			INSTANCE = new SQLiteHelper(pContext);
		}
		
		return INSTANCE;
	}
	
	/* No.18 �Զ����ú��� */
	@Override
	public void onCreate(SQLiteDatabase pDataBase) {
		Log.i("SQLiteHelper", "onCreate ��������");
		ArrayList<String> _ArrayList = SQLITE_DATEBASE_CONFIG.GetTables();
		
		//No.17 ��ʱ��������Ҫ���������  ���������е�ʱ��֪��������Щ�����½�һ��Reflection
		/*_ArrayList.add("");
		return _ArrayList;*/
		
		mReflection = new Reflection();
		
		for(int i=0;i<_ArrayList.size();i++)
		{
			try {
				((SQLiteDataTable)mReflection.
						newInstance(_ArrayList.get(i), new Object[]{mContext}, new Class[]{Context.class})).OnCreate(pDataBase);
				Log.i("SQLiteHelper", "�����������ݿ��");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/* No.18 �Զ����ú��� */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
