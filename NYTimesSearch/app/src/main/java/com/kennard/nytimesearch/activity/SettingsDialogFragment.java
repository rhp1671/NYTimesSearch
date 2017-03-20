package com.kennard.nytimesearch.activity;

/**
 * Created by raprasad on 3/19/17.
 */

import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import android.support.v4.app.DialogFragment;

import com.kennard.nytimesearch.network.NYTimesNetworkHelper;
import com.kennard.nytimesearch.utility.ArticlePrefs;
import com.kennard.nytimesearch.utility.Utility;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import kennard.com.nytimessearch.R;


public class SettingsDialogFragment extends DialogFragment {

    @BindView(R.id.cbArts)
    CheckBox cbArts;

    @BindView(R.id.cbSports)
    CheckBox cbSports;

    @BindView(R.id.cbFashion)
    CheckBox cbFashion;
    @BindView(R.id.tvBeginDate)
    TextView dateView;
    @BindView(R.id.spSortOrder)
    Spinner spSortOrder;

    @BindView(R.id.btnSave)
    Button btnSave;

    ImageView imageView;
    private Calendar dtCalendar = Calendar.getInstance();
    public static final String SHAREDPREFS = "nytimesprefs";
    public static final String ARTS = "Arts";
    public static final String SPORTS = "Sports";
    public static final String FASHION = "Fashion";

    private Date mDate;

    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            dtCalendar.set(Calendar.YEAR, year);
            dtCalendar.set(Calendar.MONTH, monthOfYear);
            dtCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };


    public interface SettingsDialogListener {
        void onFinishEditDialog();
    }

    private SettingsDialogListener listener;


    public SettingsDialogFragment() {

    }

    public static SettingsDialogFragment newInstance(Bundle args) {
        SettingsDialogFragment frag = new SettingsDialogFragment();
        frag.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_settings, container);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ArticlePrefs util = new ArticlePrefs(getContext());
        String sNewsParam = util.newsDeskValueString;
        String sSortOrder = util.sortOrderString;
        long l = util.timeinms;

        if (l != 0) {
            mDate = new Date(l);
        }
        if (sNewsParam.contains(ARTS)) {
            cbArts.setChecked(true);
        }
        if (sNewsParam.contains(SPORTS)) {
            cbSports.setChecked(true);
        }
        if (sNewsParam.contains(FASHION)) {
            cbFashion.setChecked(true);
        }
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDatePicker();
            }
        });
        if (mDate != null) {
            dateView.setText(Utility.getFriendlyDayString(getContext(), mDate.getTime()));
        }
        if (!sSortOrder.isEmpty()) {
            setSpinnerToValue(sSortOrder);
        }

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        Button save = (Button) view.findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onSettingsSave(v);
            }
        });
    }

    public void setSpinnerToValue(String value) {
        int index = 0;
        SpinnerAdapter adapter = spSortOrder.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).toString().toLowerCase().equals(value)) {
                index = i;
                break; // terminate loop
            }
        }
        spSortOrder.setSelection(index);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (SettingsDialogListener) context;
        } catch (Exception ex) {

        }
    }

    public void onSettingsSave(View view) {

        String s = NYTimesNetworkHelper.QueryStringBuiler(cbArts.isChecked() ? ARTS : "", cbSports.isChecked() ? SPORTS : "", cbFashion.isChecked() ? FASHION : "");

        ArticlePrefs util = new ArticlePrefs(getContext());

        String selSortValue = "";
        if (spSortOrder.getSelectedItemPosition() != 0) {
            selSortValue = spSortOrder.getSelectedItem().toString().toLowerCase();
        }
        util.persist(s, mDate != null ? mDate.getTime() : 0, selSortValue);
        listener.onFinishEditDialog();
        dismiss();
    }


    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateView.setText(sdf.format(dtCalendar.getTime()));
        mDate = dtCalendar.getTime();
    }

    private void launchDatePicker() {
        new DatePickerDialog(getContext(), dateListener, dtCalendar
                .get(Calendar.YEAR), dtCalendar.get(Calendar.MONTH),
                dtCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    public void onResume() {
        super.onResume();

        Window window = getDialog().getWindow();
        Point size = new Point();

        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        int width = size.x;

        window.setLayout((int) (width * 0.80), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
    }

}