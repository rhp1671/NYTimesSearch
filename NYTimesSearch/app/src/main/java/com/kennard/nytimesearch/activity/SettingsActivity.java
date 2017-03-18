package com.kennard.nytimesearch.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import kennard.com.nytimessearch.R;

import com.kennard.nytimesearch.utility.NYTSearchHelper;
import com.kennard.nytimesearch.utility.SettingsUtil;
import com.kennard.nytimesearch.utility.Utility;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.cbArts)
    CheckBox cbArts;

    @BindView(R.id.cbSports)
    CheckBox cbSports;

    @BindView(R.id.cbFashion) CheckBox cbFashion;
    @BindView(R.id.tvBeginDate)
    TextView dateView;
    @BindView(R.id.spSortOrder)
    Spinner spSortOrder;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.action_settings));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);

        SettingsUtil util = new SettingsUtil(getApplicationContext());
        String sNewsParam = util.newsDeskValueString;
        String sSortOrder = util.sortOrderString;
        long l = util.timeinms;

        if (l != 0){
            mDate = new Date(l);
        }
        if (sNewsParam.contains(ARTS)){
            cbArts.setChecked(true);
        }
        if (sNewsParam.contains(SPORTS)){
            cbSports.setChecked(true);
        }
        if (sNewsParam.contains(FASHION)){
            cbFashion.setChecked(true);
        }
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDatePicker();
            }
        });
        if (mDate != null) {
            dateView.setText(Utility.getFriendlyDayString(getApplicationContext(), mDate.getTime()));
        }
        if (!sSortOrder.isEmpty()){
            setSpinnerToValue(sSortOrder);
        }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSettingsSave(View view) {

        String s = NYTSearchHelper.QueryStringBuiler(cbArts.isChecked() ? ARTS: "", cbSports.isChecked() ? SPORTS : "", cbFashion.isChecked() ? FASHION: "");

        SettingsUtil util = new SettingsUtil(getApplicationContext());

        String selSortValue = "";
        if (spSortOrder.getSelectedItemPosition() != 0) {
            selSortValue =  spSortOrder.getSelectedItem().toString().toLowerCase();
        }
        util.persist(s, mDate != null? mDate.getTime(): 0, selSortValue);
        finish();
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateView.setText(sdf.format(dtCalendar.getTime()));
        mDate = dtCalendar.getTime();
    }

    private void launchDatePicker() {
        new DatePickerDialog(this, dateListener, dtCalendar
                .get(Calendar.YEAR), dtCalendar.get(Calendar.MONTH),
                dtCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
