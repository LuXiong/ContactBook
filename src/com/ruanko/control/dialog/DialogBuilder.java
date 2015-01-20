package com.ruanko.control.dialog;

import java.io.Serializable;
import java.util.ArrayList;

import android.widget.AdapterView.OnItemClickListener;

public class DialogBuilder implements Serializable {
	private int type;
	private String title;
	private String content;
	private String listDialogTipsMessage;
	private String confirmBtnStr;
	private TipsDialogClickListener mTipsClickListener;
	private ReminderDialogClickListener mReminderDialogClickListener;
	private OnItemClickListener listItemClickListener;
	private ArrayList<String> listDialogContent;

	public DialogBuilder() {
		type = CustomDialog.TIPS_DIALOG;
		title = "标题";
		content = "提示内容";
		confirmBtnStr = "确定";
	}

	// public DialogBuilder(int type, String title, String content) {
	// this.type = type;
	// this.title = title;
	// this.content = content;
	// }

	public static DialogBuilder getDialogBuilder() {
		return new DialogBuilder();
	}

	public int getType() {
		return type;
	}

	public DialogBuilder setType(int type) {
		this.type = type;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public DialogBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public DialogBuilder setContent(String content) {
		this.content = content;
		return this;
	}

	public TipsDialogClickListener getTipsClickListener() {
		return mTipsClickListener;
	}

	public DialogBuilder setTipsClickListener(
			TipsDialogClickListener mTipsClickListener) {
		this.mTipsClickListener = mTipsClickListener;
		return this;
	}

	public ArrayList<String> getListDialogContent() {
		return listDialogContent;
	}

	public DialogBuilder setListDialogContent(
			ArrayList<String> listDialogContent) {
		this.listDialogContent = listDialogContent;
		return this;
	}

	public OnItemClickListener getListItemClickListener() {
		return listItemClickListener;
	}

	public DialogBuilder setListItemClickListener(
			OnItemClickListener listItemClickListener) {
		this.listItemClickListener = listItemClickListener;
		return this;
	}

	public String getListDialogTipsMessage() {
		return listDialogTipsMessage;
	}

	public DialogBuilder setListDialogTipsMessage(String listDialogTipsMessage) {
		this.listDialogTipsMessage = listDialogTipsMessage;
		return this;
	}

	public interface TipsDialogClickListener {
		void confirmClick();

		void cancelClick();
	}

	public interface ReminderDialogClickListener {
		void confirmClick();
	}

	public String getConfirmBtnStr() {
		return confirmBtnStr;
	}

	public void setReminderDialogClickLisener(ReminderDialogClickListener l) {
		mReminderDialogClickListener = l;
	}

	public ReminderDialogClickListener getReminderDialogClickListener() {
		return mReminderDialogClickListener;
	}

	public DialogBuilder setConfirmBtnStr(String confirmBtnStr) {
		this.confirmBtnStr = confirmBtnStr;
		return this;
	}

}
