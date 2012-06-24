package sinbad.godutch.database.sqlitedal;

import java.util.Date;
import java.util.List;

import sinbad.godutch.R;
import sinbad.godutch.configration.CommonConstants;
import sinbad.godutch.database.base.SQLiteDALBase;
import sinbad.godutch.model.ModelUser;
import sinbad.godutch.utility.DateTools;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * No.21 �½�SQLite�����û� 
 * No.21 
 * */
public class SQLiteDALUser extends SQLiteDALBase{

	/* No.21 �Զ�ʵ�ֵķ���  */
	public SQLiteDALUser(Context p_Context) {
		super(p_Context);
	}
	
	//No.21 �½� ���������ķ���
	public ContentValues CreateParms(ModelUser pInfo)
	{
		Log.i("SQLiteDALUser", "SQLiteDALUser CreateParms������ʼʹ��");
		ContentValues _ContentValues = new ContentValues();		
		_ContentValues.put("UserName", pInfo.getUserName());		
		_ContentValues.put("CreateDate",DateTools.getFormatDateTime(pInfo.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
		_ContentValues.put("State",pInfo.getState());
		return _ContentValues;
	}


	//No.21 �½� ���뷽��	
	public boolean InsertUser(ModelUser pModelUser) {
		Log.i("SQLiteDALUser", "SQLiteDALUser InsertUser������ʼʹ��");
		ContentValues _ContentValues = CreateParms(pModelUser);
		//No.22  ׷���߼�
		long _NewID =  GetDataBase().insert(GetTableNameAndPK()[0], null, _ContentValues);
		pModelUser.setUserID((int)_NewID);
		//����ɹ�Ϊ�棬����Ϊ��
		return _NewID > 0;
	}
	
	//No.22 �½� ɾ���û�����	
	public boolean DeleteUser(String pCondition) {
		Log.i("SQLiteDALUser", "SQLiteDALUser DeleteUser������ʼʹ��");
		return Delete(GetTableNameAndPK()[0],pCondition);
	}
	
	//No.22 �����ض��û�����
	public boolean UpdateUser(String pCondition,ModelUser pModelUser) {
		Log.i("SQLiteDALUser", "SQLiteDALUser UpdateUser 1 ������ʼʹ��");
		ContentValues _ContentValues = CreateParms(pModelUser);
		return GetDataBase().update(GetTableNameAndPK()[0], _ContentValues, pCondition, null) > 0;
	}
	
	//No.22 �����û�����
	public boolean UpdateUser(String pCondition,ContentValues pContentValues)
	{
		Log.i("SQLiteDALUser", "SQLiteDALUser UpdateUser 2 ������ʼʹ��");
		return GetDataBase().update("User", pContentValues, pCondition, null) > 0;
	}
	
	//No.22 �½�List����
	public List<ModelUser> GetUser(String pCondition)
	{
		Log.i("SQLiteDALUser", "SQLiteDALUser GetUser������ʼʹ��");
		String _SqlText = "Select * From User Where 1=1 " + pCondition;
		return GetList(_SqlText);
	}
	
	/* No.21 �Զ�ʵ�ֵķ���  */
	//No.22  ׷�ӷ���ֵ
	@Override
	protected String[] GetTableNameAndPK() {
		Log.i("SQLiteDALUser", "SQLiteDALUser GetTableNameAndPK������ʼʹ��");
		return new String[]{"User","UserID"};
	}

	/* No.21 �Զ�ʵ�ֵķ���  */
	//No.23    ���£��ҵ�ģ���û���ʵ��
	@Override
	protected Object FindModel(Cursor pCursor) {
		Log.i("SQLiteDALUser", "SQLiteDALUser FindModel������ʼʹ��");
		ModelUser _ModelUser = new ModelUser();		
		//�����û�ID
		_ModelUser.setUserID(pCursor.getInt(pCursor.getColumnIndex("UserID")));
		//�����û���
		_ModelUser.setUserName(pCursor.getString(pCursor.getColumnIndex("UserName")));
		//���ô���ʱ��
		Date _CreateDate = DateTools.getDate(pCursor.getString(pCursor.getColumnIndex("CreateDate")), "yyyy-MM-dd HH:mm:ss");		
		_ModelUser.setCreateDate(_CreateDate);
		//����״̬
		_ModelUser.setState((pCursor.getInt(pCursor.getColumnIndex("State"))));
		
		return _ModelUser;
	}
	
	//No.23 ��ʼ��Ĭ���û�
	private void InitDefaultData(SQLiteDatabase pDatabase)
	{
		Log.i("SQLiteDALUser", "SQLiteDALUser ��ʼ��Ĭ���û�");
		ModelUser _ModelUser = new ModelUser();
		
		String _UserName[] = GetContext().getResources().getStringArray(R.array.InitDefaultUserName);
		
		for (int i = 0; i < _UserName.length; i++) {
			_ModelUser.setUserName(_UserName[i]);
			//ʵ��������
			ContentValues _ContentValues = CreateParms(_ModelUser);
			
			pDatabase.insert(GetTableNameAndPK()[0], null, _ContentValues);
		}
	}

	/* No.21 �Զ�ʵ�ֵķ���  �ӿ�Ϊ��*/
	//No.21 ʵ�� �½����ݿ�Ĺ���
	public void OnCreate(SQLiteDatabase p_DataBase) {
		Log.i("SQLiteDALUser", "SQLiteDALUser OnCreate������ʼʹ��");
		StringBuilder s_CreateTableScript = new StringBuilder();		
		s_CreateTableScript.append("Create  TABLE User(");
		s_CreateTableScript.append("	[UserID] integer PRIMARY KEY AUTOINCREMENT NOT NULL");
		s_CreateTableScript.append("	,[UserName] varchar(10) NOT NULL");
		s_CreateTableScript.append("	,[CreateDate] datetime NOT NULL");
		s_CreateTableScript.append("	,[State] integer NOT NULL");
		s_CreateTableScript.append("	)");
		Log.v("SQLiteDALUser", "�������ݿ�" + s_CreateTableScript);
		p_DataBase.execSQL(s_CreateTableScript.toString());		
		InitDefaultData(p_DataBase);
	}
	
	/* No.21 �Զ�ʵ�ֵķ���  */
	public void OnUpgrade(SQLiteDatabase p_DataBase) {
		// TODO Auto-generated method stub
		
	}
	

}
