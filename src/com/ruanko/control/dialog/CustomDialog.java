package com.ruanko.control.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruanko.contactbook.R;
import com.ruanko.control.dialog.DialogBuilder.ReminderDialogClickListener;
import com.ruanko.control.dialog.DialogBuilder.TipsDialogClickListener;

public class CustomDialog extends Dialog {

	private Button confirmBtn;
	private Button cancelBtn;
	private TextView titleTxt;
	private TextView contentTxt;
	private ImageView loadingImage;
	private ListView listDialogList;
	private Button listDialogCancelBtn;
	private TextView listDialogTips;
	private TextView listDialogTipsMessage;
	private TextView loadingTips;
	private AnimationDrawable loadingAnim;
	private RelativeLayout loadingLayout;
	private LinearLayout tipsLayout;
	private LinearLayout listDialogLayout;
	private LinearLayout reminderDialogLayout;

	public static final int TIPS_DIALOG = 1;
	public static final int LOADING_DIALOG = 2;
	public static final int LIST_DIALOG = 3;
	public static final int REMINDER_DIALOG = 4;

	private DialogBuilder mDialogBuilder;
	private Context mContext;

	private TipsDialogClickListener mTipsClicklistener;
	private ReminderDialogClickListener mReminderDialogClickListener;

	public CustomDialog(Context context) {
		super(context);
		mContext = context;
	}

	public CustomDialog(Context context, int theme) {
		super(context, R.style.TipsDialog);
		mContext = context;
	}

	public CustomDialog(Context context, DialogBuilder mDialogBuilder) {
		super(context, R.style.TipsDialog);
		this.mDialogBuilder = mDialogBuilder;
		mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.custom_dialog);

		loadingLayout = (RelativeLayout) findViewById(R.id.loading_layout);
		tipsLayout = (LinearLayout) findViewById(R.id.tips_layout);
		listDialogLayout = (LinearLayout) findViewById(R.id.list_dialog_layout);
		reminderDialogLayout = (LinearLayout) findViewById(R.id.reminder_layout);
		Activity a;
		init();
	}

	private void init() {
		if (mDialogBuilder.getType() == TIPS_DIALOG) {
			loadTipsLayout();
		} else if (mDialogBuilder.getType() == LOADING_DIALOG) {
			loadLoadingLayout();
			return;
		} else if (mDialogBuilder.getType() == LIST_DIALOG) {
			loadListDialogLayout();
		} else if (mDialogBuilder.getType() == REMINDER_DIALOG) {
			loadReminderDialogLayout();
		}
		this.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog

	}

	private void loadReminderDialogLayout() {
		confirmBtn = (Button) findViewById(R.id.reminder_dialog_confirm_btn);
		titleTxt = (TextView) findViewById(R.id.reminder_dialog_title);
		contentTxt = (TextView) findViewById(R.id.reminder_dialog_content);
		mReminderDialogClickListener = mDialogBuilder
				.getReminderDialogClickListener();
		confirmBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mReminderDialogClickListener != null) {
					mReminderDialogClickListener.confirmClick();
				}
			}
		});
		tipsLayout.setVisibility(View.GONE);
		loadingLayout.setVisibility(View.GONE);
		listDialogLayout.setVisibility(View.GONE);
		reminderDialogLayout.setVisibility(View.VISIBLE);
		titleTxt.setText(mDialogBuilder.getTitle());
		contentTxt.setText(mDialogBuilder.getContent());
		confirmBtn.setText(mDialogBuilder.getConfirmBtnStr());
	}

	private void loadTipsLayout() {
		confirmBtn = (Button) findViewById(R.id.dialog_confirm_btn);
		cancelBtn = (Button) findViewById(R.id.dialog_cancel_btn);
		titleTxt = (TextView) findViewById(R.id.dialog_title);
		contentTxt = (TextView) findViewById(R.id.dialog_content);
		mTipsClicklistener = mDialogBuilder.getTipsClickListener();
		confirmBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mTipsClicklistener != null) {
					mTipsClicklistener.confirmClick();
				}
			}
		});
		cancelBtn.setOnClickListener(new View.OnClickListener() {
		@Override
			public void onClick(View v) {
				if (mTipsClicklistener != null) {
					mTipsClicklistener.cancelClick();
				}
				cancel();
			}
		});
		tipsLayout.setVisibility(View.VISIBLE);
		loadingLayout.setVisibility(View.GONE);
		listDialogLayout.setVisibility(View.GONE);
		reminderDialogLayout.setVisibility(View.GONE);
		titleTxt.setText(mDialogBuilder.getTitle());
		contentTxt.setText(mDialogBuilder.getContent());
		confirmBtn.setText(mDialogBuilder.getConfirmBtnStr());
	}

	private void loadLoadingLayout() {
		tipsLayout.setVisibility(View.GONE);
		loadingLayout.setVisibility(View.VISIBLE);
		listDialogLayout.setVisibility(View.GONE);
		reminderDialogLayout.setVisibility(View.GONE);
		loadingImage = (ImageView) findViewById(R.id.loading_image);
		loadingTips = (TextView) findViewById(R.id.loading_tips);
		if (mDialogBuilder.getTitle() != null
				&& !mDialogBuilder.getTitle().equals("标题")) {
			loadingTips.setText(mDialogBuilder.getTitle());
		} else {
			loadingTips.setText("正在加载");
		}
	}

	private void loadListDialogLayout() {
		tipsLayout.setVisibility(View.GONE);
		loadingLayout.setVisibility(View.GONE);
		listDialogLayout.setVisibility(View.VISIBLE);
		listDialogTips = (TextView) findViewById(R.id.list_dialog_tips);
		listDialogTipsMessage = (TextView) findViewById(R.id.list_dialog_tips_message);
		listDialogTips.setText(mDialogBuilder.getTitle());
		if (mDialogBuilder.getListDialogTipsMessage() != null) {
			listDialogTipsMessage.setVisibility(View.VISIBLE);
			listDialogTipsMessage.setText(mDialogBuilder
					.getListDialogTipsMessage());
		}
		listDialogCancelBtn = (Button) findViewById(R.id.list_dialog_cancel);
		listDialogCancelBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				cancel();
			}
		});
		listDialogList = (ListView) findViewById(R.id.list_dialog_list);
		listDialogList.setAdapter(new ArrayAdapter<String>(getContext(),
				R.layout.item_list_dialog, mDialogBuilder
						.getListDialogContent()));
		listDialogList.setOnItemClickListener(mDialogBuilder
				.getListItemClickListener());
	}

	@Override
	protected void onStart() {
		super.onStart();
	}





	@Override
	protected void onStop() {
		super.onStop();
	}

}
