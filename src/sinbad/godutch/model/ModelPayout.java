package sinbad.godutch.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ModelPayout implements Serializable {

		//֧��������ID
		private int mPayoutID;
		//�˱�ID���
		private int mAccountBookID;
		//�˱�����
		private String mAccountBookName;
		//֧�����ID���
		private int mCategoryID;
		//�������
		private String mCategoryName;
		//·��
		private String mPath;
		//���ʽID���
		private int mPayWayID;
		//���ѵص�ID���
		private int mPlaceID;
		//���ѽ��
		private BigDecimal mAmount;
		//��������
		private Date mPayoutDate;
		//���㷽ʽ
		private String mPayoutType;
		//������ID���
		private String mPayoutUserID;
		//��ע
		private String mComment;
		//�������
		private Date mCreateDate = new Date();
		//״̬ 0ʧЧ 1����
		private int mState = 1;
		
		/**
		 * ֧��������ID
		 */
		public int GetPayoutID() {
			return mPayoutID;
		}
		/**
		 * ֧��������ID
		 */
		public void SetPayoutID(int pPayoutID) {
			this.mPayoutID = pPayoutID;
		}
		/**
		 * �˱�����ID���
		 */
		public int GetAccountBookID() {
			return mAccountBookID;
		}
		/**
		 * �˱�ID���
		 */
		public void SetAccountBookID(int pAccountBookID) {
			this.mAccountBookID = pAccountBookID;
		}
		/**
		 * �˱�����
		 */
		public String GetAccountBookName() {
			return mAccountBookName;
		}
		/**
		 * �˱�����
		 */
		public void SetAccountBookName(String pAccountBookName) {
			this.mAccountBookName = pAccountBookName;
		}
		/**
		 * ֧�����ID���
		 */
		public int GetCategoryID() {
			return mCategoryID;
		}
		/**
		 * ֧�����ID���
		 */
		public void SetCategoryID(int pCategoryID) {
			this.mCategoryID = pCategoryID;
		}
		/**
		 * ·��
		 */
		public String GetPath() {
			return mPath;
		}
		/**
		 * ·��
		 */
		public void SetPath(String pPath) {
			this.mPath = pPath;
		}
		/**
		 * �˱�����
		 */
		public String GetCategoryName() {
			return mCategoryName;
		}
		/**
		 * �˱�����
		 */
		public void SetCategoryName(String pCategoryName) {
			this.mCategoryName = pCategoryName;
		}
		/**
		 * 	���ʽID���
		 */
		public int GetPayWayID() {
			return mPayWayID;
		}
		/**
		 * 	���ʽID���
		 */
		public void SetPayWayID(int pPayWayID) {
			this.mPayWayID = pPayWayID;
		}
		/**
		 * ���ѵص�ID���
		 */
		public int GetPlaceID() {
			return mPlaceID;
		}
		/**
		 * ���ѵص�ID���
		 */
		public void SetPlaceID(int pPlaceID) {
			this.mPlaceID = pPlaceID;
		}
		/**
		 * ���ѽ��
		 */
		public BigDecimal GetAmount() {
			return mAmount;
		}
		/**
		 * ���ѽ��
		 */
		public void SetAmount(BigDecimal pAmount) {
			this.mAmount = pAmount;
		}
		/**
		 * ��������
		 */
		public Date GetPayoutDate() {
			return mPayoutDate;
		}
		/**
		 * ��������
		 */
		public void SetPayoutDate(Date pPayoutDate) {
			this.mPayoutDate = pPayoutDate;
		}
		/**
		 * ���㷽ʽ
		 */
		public String GetPayoutType() {
			return mPayoutType;
		}
		/**
		 * ���㷽ʽ
		 */
		public void SetPayoutType(String pPayoutType) {
			this.mPayoutType = pPayoutType;
		}
		/**
		 * ������ID���
		 */
		public String GetPayoutUserID() {
			return mPayoutUserID;
		}
		/**
		 * ������ID���
		 */
		public void SetPayoutUserID(String pPayoutUserID) {
			this.mPayoutUserID = pPayoutUserID;
		}	
		/**
		 * ��ע
		 */
		public String GetComment() {
			return mComment;
		}
		/**
		 * ��ע
		 */
		public void SetComment(String pComment) {
			this.mComment = pComment;
		}
		/**
		 * �������
		 */
		public Date GetCreateDate() {
			return mCreateDate;
		}
		/**
		 * �������
		 */
		public void SetCreateDate(Date pCreateDate) {
			this.mCreateDate = pCreateDate;
		}
		/**
		 * ״̬ 0ʧЧ 1����
		 */
		public int GetState() {
			return mState;
		}
		/**
		 * ״̬ 0ʧЧ 1����
		 */
		public void SetState(int pState) {
			this.mState = pState;
		}

		
}
