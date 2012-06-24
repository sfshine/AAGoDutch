package sinbad.godutch.model;

import java.io.Serializable;
import java.util.Date;

public class ModelCategory implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 907837048323397148L;
		//��������ID
		private int mCategoryID;
		//�������
		private String mCategoryName;
		//���ͱ������
		private String mTypeFlag;
		//������ID
		private int mParentID = 0;
		//·��
		private String mPath;	
		//�������
		private Date mCreateDate = new Date();
		//״̬ 0ʧЧ 1����
		private int mState = 1;
		
		/**
		 * �˱�������ID
		 */
		public int GetCategoryID() {
			return mCategoryID;
		}
		/**
		 * �˱�������ID
		 */
		public void SetCategoryID(int pCategoryID) {
			this.mCategoryID = pCategoryID;
		}
		/**
		 * �˱�����
		 */
		public String GetCategoryName() {
			return mCategoryName;
		}
		/**
		 * ����������
		 */
		public void SetCategoryName(String pCategoryName) {
			this.mCategoryName = pCategoryName;
		}
		/**
		 * �õ����ͱ������
		 */
		public String GetTypeFlag() {
			return mTypeFlag;
		}
		/**
		 * �������ͱ������
		 */
		public void SetTypeFlag(String pTypeFlag) {
			this.mTypeFlag = pTypeFlag;
		}
		/**
		 * �õ�������ID
		 */
		public int GetParentID() {
			return mParentID;
		}
		/**
		 * ���ø�����ID
		 */
		public void SetParentID(int pParentID) {
			this.mParentID = pParentID;
		}
		/**
		 * �õ�·��
		 */
		public String GetPath() {
			return mPath;
		}
		/**
		 * ����·��
		 */
		public void SetPath(String pPath) {
			this.mPath = pPath;
		}
		/**
		 * �õ��������
		 */
		public Date GetCreateDate() {
			return mCreateDate;
		}
		/**
		 * ���ô�������
		 */
		public void SetCreateDate(Date pCreateDate) {
			this.mCreateDate = pCreateDate;
		}
		/**
		 * �õ�״̬
		 */
		public int GetState() {
			return mState;
		}
		/**
		 * ����״̬
		 */
		public void SetState(int pState) {
			this.mState = pState;
		}
		
		@Override
		public String toString() {
			return mCategoryName;
		}
}
