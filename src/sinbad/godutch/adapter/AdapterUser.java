package sinbad.godutch.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import sinbad.godutch.R;
import sinbad.godutch.adapter.base.AdapterBase;
import sinbad.godutch.business.BusinessUser;
import sinbad.godutch.model.ModelUser;
/**
 * No.25 ����UserAdapter
 * */
public class AdapterUser extends AdapterBase{
	
	// No.25  ������
	private class Holder
	{
		ImageView ivUserIcon;
		TextView tvUserName;
	}

	/* No.25 �Զ����ɺ��� */
	public AdapterUser(Context pContext) {
		//No.25 ��Ӷ�Ӧ���߼�
		super(pContext, null);
		BusinessUser _BusinessUser = new BusinessUser(pContext);
		//���Ҳ����߼�ɾ���û�����Ч�б�
		List _List = _BusinessUser.GetNotHideUser();
		SetList(_List);
	}

	/* No.25 �Զ����ɺ��� */
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//No.25 �޸Ķ�Ӧ���߼�
		Holder _Holder;
		
		if (convertView == null) {
			convertView = GetLayoutInflater().inflate(R.layout.user_list_item, null);
			_Holder = new Holder();
			_Holder.ivUserIcon = (ImageView) convertView.findViewById(R.id.ivUserIcon);
			_Holder.tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
			convertView.setTag(_Holder);
		}
		else {
			_Holder = (Holder) convertView.getTag();
		}
		
		ModelUser _Info = (ModelUser) GetList().get(position);
		
		_Holder.ivUserIcon.setImageResource(R.drawable.user_big_icon);
		_Holder.tvUserName.setText(_Info.getUserName());
		
		return convertView;
	}

}
