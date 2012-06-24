package sinbad.godutch.database.base;

import java.util.ArrayList;
import java.util.List;

import sinbad.godutch.database.base.SQLiteHelper.SQLiteDataTable;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * No.18 �½�һ��SQLiteBase�࣬��װ��ͨ����
 * No.21 ׷��  implements SQLiteDataTable����Ϊ�ǳ����࣬���Բ��ڱ�������ʵ��
 * */
public abstract class SQLiteDALBase implements SQLiteDataTable{

	//No.18 ��������
	private Context mContext;
	private SQLiteDatabase mDataBase;
	
	//No.18 ���캯��
	public SQLiteDALBase(Context pContext)
	{
		mContext = pContext;
	}
	
	//No.18 ȡ��Contextʵ��
	protected Context GetContext()
	{
		return mContext;
	}
	
	//No.18  ȡ��DataBaseʵ��
	public SQLiteDatabase GetDataBase()
	{
		//Log.i("SQLiteDALBase", "GetDataBase �õ����ݿ�");
		if(mDataBase == null)
		{
			//Log.i("SQLiteDALBase", "GetDataBase ���ݿ�Ϊ�գ���ô����д���ݿ�Ĳ���");
			mDataBase = SQLiteHelper.GetInstance(mContext).getWritableDatabase();
		}
		
		return mDataBase;
	}
	
	//No.18 ��ʼ����
	public void BeginTransaction()
	{
		Log.i("SQLiteDALBase", "BeginTransaction ��ʼ����");
		mDataBase.beginTransaction();
	}
	
	//No.18 ��������ɹ�
	public void SetTransactionSuccessful()
	{
		Log.i("SQLiteDALBase", "SetTransactionSuccessful ��������ɹ�");
		mDataBase.setTransactionSuccessful();
	}
	
	//No.18 ��������
	public void EndTransaction()
	{
		Log.i("SQLiteDALBase", "EndTransaction ��������");
		mDataBase.endTransaction();
	}
	
	//No.18 ȡ������
	public int GetCount(String pCondition)
	{
		String _String[] = GetTableNameAndPK();
		Cursor _Cursor = ExecSql("Select " + _String[1] + " From " + _String[0] + " Where 1=1 " + pCondition);
		Log.i("SQLiteDALBase", "Select " + _String[1] + " From " + _String[0] + " Where 1=1 " + pCondition);
		int _Count = _Cursor.getCount();
		_Cursor.close();
		return _Count;
	}
	
	//No.18 ȡ������
	public int GetCount(String pPK,String pTableName, String pCondition)
	{
		Cursor _Cursor = ExecSql("Select " + pPK + " From " + pTableName + " Where 1=1 " + pCondition);
		Log.i("SQLiteDALBase", "Cursor �� Select " + pPK + " From " + pTableName + " Where 1=1 " + pCondition );
		int _Count = _Cursor.getCount();
		_Cursor.close();
		return _Count;
	}
	
	//No.18  ɾ������
	protected Boolean Delete(String pTableName, String pCondition)
	{
		Log.i("SQLiteDALBase", "Delete ɾ�� " + pTableName + "where 1 = 1 " + pCondition);
		return GetDataBase().delete(pTableName, " 1=1 " + pCondition, null) >= 0;
	}
	
	protected abstract String[] GetTableNameAndPK();
	
	//No.18 �õ�list
	protected List GetList(String pSqlText)
	{
		Log.i("SQLiteDALBase", "GetList ȡ��List��" + pSqlText);
		Cursor _Cursor = ExecSql(pSqlText);
		return CursorToList(_Cursor);
	}
	
	protected abstract Object FindModel(Cursor pCursor);
	
	//No.18 �õ�list
	protected List CursorToList(Cursor pCursor)
	{
		Log.i("SQLiteDALBase", "CursorToList �α�ָ��List");
		List _List = new ArrayList();
		while(pCursor.moveToNext())
		{
			Object _Object = FindModel(pCursor);
			_List.add(_Object);
		}
		pCursor.close();
		return _List;
	}
	
	//No.18 ִ��Query
	public Cursor ExecSql(String pSqlText)
	{
		Log.i("SQLiteDALBase", "ExecSql ִ��SQL��" + pSqlText );
		return GetDataBase().rawQuery(pSqlText, null);
	}
}
