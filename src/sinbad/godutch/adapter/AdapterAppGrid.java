package sinbad.godutch.adapter;
/**
 * No.8 �½�AdapterAppGrid
 * Ŀ������ȥ�ľŹ����������������
 * ����ͼ���飬����������Ŀ����AppGrid��������
 * 
 * �ý�����Ҫ����һ�����֣����Լ����½�����
 * 
 * 
 * */

import sinbad.godutch.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdapterAppGrid extends BaseAdapter {
	
	//No.9  ����һ�� Context
	private Context mContext;
	
	//No.9  ͼ������Դ�������ࣺ����ͼ�����õ�ͼ��ImageView��˵��TextView
	private class Holder
	{
		ImageView ivIcon;
		TextView tvName;
	}
	//No.9 ����һ��ImageString���飬����ͼ�����Ӧ��˵��
	private String[] mImageString = new String[6];
	
	//No.9  �½����캯��AdapterAppGrid
	public AdapterAppGrid(Context pContext)
	{
		mContext = pContext;
		//��¼����
		mImageString[0] = pContext.getString(R.string.appGridTextPayoutAdd);
		//��ѯ����
		mImageString[1] = pContext.getString(R.string.appGridTextPayoutManage);
		//ͳ�ƹ���
		mImageString[2] = pContext.getString(R.string.appGridTextStatisticsManage);
		//�˱�����
		mImageString[3] = pContext.getString(R.string.appGridTextAccountBookManage);
		//������
		mImageString[4] = pContext.getString(R.string.appGridTextCategoryManage);
		//��Ա����
		mImageString[5] = pContext.getString(R.string.appGridTextUserManage);
	}
	
	//No.9 �½�    һ��ImageString���飬����ͼ�����Ӧ��ͼƬ
	private Integer[] mImageInteger = {
			R.drawable.grid_payout,
			R.drawable.grid_bill,
			R.drawable.grid_report,
			R.drawable.grid_account_book,
			R.drawable.grid_category,
			R.drawable.grid_user,
	};

	//No.9 ȡ������ͼ����������ܳ���
	public int getCount() {
		return mImageString.length;
	}

	//No.9 ��ȡ���ͼ�����ʵ����λ��
	public Object getItem(int position) {
		return mImageString[position];
	}

	//No.9 ȡ�õ��ͼ�����Ӧ��λ��
	public long getItemId(int position) {
		return position;
	}
	//No.9 ȡ����ͼ�ķ���
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//����Holder��ͼ��������
		Holder _Holder;
		
		//���û����ͼ�Ļ�,�Ͷ�̬����һ��
		if (convertView == null) {
			//ͨ����̬���ص�ǰ������
			LayoutInflater _LayoutInflater = LayoutInflater.from(mContext);
			//Ȼ�󣬽�main_body_item���ͼ����Ĳ��֣�inflate��convertView��ȥ����Ϊ�Ǹ�Ŀ¼������û�и�Ŀ¼��null��ʾ
			convertView = _LayoutInflater.inflate(R.layout.main_body_item, null);
			
			//�½�ͼ��������
			_Holder = new Holder();
			
			//��ͼ��
			_Holder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
			//��ͼ����
			_Holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			
			//����Tag��ǩ
			convertView.setTag(_Holder);
		}
		else {
			//����Ѿ����ˣ���ô��ֱ�ӵõ�Tag��ǩ
			_Holder = (Holder) convertView.getTag();
		}
				
		//��ȫ��ͼ���鶼�����������
		
		//����Ӧ��ͼ��������Դ���ͼƬ
		_Holder.ivIcon.setImageResource(mImageInteger[position]);
		//No.11 ����Ӧ��ͼƬ����Ϊ50 X 50��ͼ��
		LinearLayout.LayoutParams _LayoutParams = new LinearLayout.LayoutParams(50, 50);
		
		//No.11 ����ͼ��Ĳ��ָ�ʽ�������Բ���
		_Holder.ivIcon.setLayoutParams(_LayoutParams);
		
		//No.11 ����ͼ����������ͣ������XY��ķ�ʽ
		_Holder.ivIcon.setScaleType(ImageView.ScaleType.FIT_XY);
		
		//No.11 ����ͼ�����ƵĶ�Ӧ�ı�����ͼƬ�ַ�����Ӧ������λ��
		_Holder.tvName.setText(mImageString[position]);
		
		//No.11 ���ض�Ӧ��View
		return convertView;
	}

}
