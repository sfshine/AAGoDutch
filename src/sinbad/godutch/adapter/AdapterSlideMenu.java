package sinbad.godutch.adapter;

import java.util.List;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import sinbad.godutch.R;
import sinbad.godutch.adapter.base.AdapterBase;
import sinbad.godutch.controls.SlideMenuItem;

/**
 * No.14 ����Ϊ�˰�List�������Ҫ�½�һ��Adapter�ؼ�
 *       ��Ȼ�����½�Adatper�ؼ�֮ǰ����������Ҫ׷��һ��Adapter�ĸ���
 *       Ȼ�����������������,Ȼ����  AdapterBase ��Ϊ ���ĸ���
 * */
public class AdapterSlideMenu extends AdapterBase{
	
	//No.14  ����������
	private class Holder
	{
		//ֻ׷��TextView
		TextView tvMenuName;
	}

	/* No.14  �Զ�ʵ�ֵĺ��� */
	public AdapterSlideMenu(Context pContext, List pList) {
		super(pContext, pList);
	}
	
	/* No.15  �Զ�ʵ�ֵĺ���   ���� getView �õ�����Ϣ*/
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Holder _Holder;
		//���convertViewΪ�գ���ô�½�һ��
		if (convertView == null) {			
			convertView = GetLayoutInflater().inflate(R.layout.slidemenu_list_item, null);			
			_Holder = new Holder();			
			_Holder.tvMenuName = (TextView) convertView.findViewById(R.id.tvMenuName);			
			convertView.setTag(_Holder);
		}
		else {
			_Holder = (Holder) convertView.getTag();
		}
		
		SlideMenuItem _Item = (SlideMenuItem) GetList().get(position);		
		_Holder.tvMenuName.setText(_Item.getTitle());		
		return convertView;
	}

}
