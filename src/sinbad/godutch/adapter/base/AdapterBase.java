package sinbad.godutch.adapter.base;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * No.14 ����Adapter�ķ����й�ͬ���ɵ�ʱ���Լ�ȥ�½�һ���µ�AdapterBase��
 *       ����Ϊ�µĻ��ࡣ
 * */
public abstract class AdapterBase extends BaseAdapter{
	
	//No.14 ׷��һ�����õı���
	private List mList;
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	
	//No.14 ׷��һ�����캯������Ϊ����п����ڹ��캯���л�ȡString������R��Դ
	public AdapterBase(Context pContext,List pList)
	{
		mContext = pContext;
		mList = pList;
		//ֱ�Ӷ�̬����
		mLayoutInflater = LayoutInflater.from(mContext);
	}
	
	//No.14  �½�һ��Get���������� Context
	public Context GetContext()
	{
		return mContext;
	}
	
	
	//No.14  �½�һ��Get����������mLayoutInflater
	public LayoutInflater GetLayoutInflater()
	{
		return mLayoutInflater;
	}

	//No.15 �½�һ�� Get���������� mList
	public List GetList()
	{
		return mList;
	}
	
	//No.25  ��Ҫ�½�һ�� SetList ���������� mList
	public void SetList(List pList) {
		mList = pList;
	}
	
	/* No. 14  �Զ����ɵĺ����� ����ȡ�ö�Ӧ����ĳ��� */
	public int getCount() {
		return mList.size();
	}

	/* No. 14  �Զ����ɵĺ���  ����ȡ��mList�Ķ�Ӧλ��*/
	public Object getItem(int pPosition) {
		return mList.get(pPosition);
	}

	/* No. 14  �Զ����ɵĺ���  ����ȡ��List��Ӧ��ID*/
	public long getItemId(int pPosition) {
		return pPosition;
	}

	/* No. 14  �Զ����ɵĺ���  ��Ϊû�а취��װ�����Ծ͸��ýӿ�������*/
	/*public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
