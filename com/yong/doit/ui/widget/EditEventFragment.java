package com.yong.doit.ui.widget;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.yong.doit.R;
import com.yong.doit.app.EventMode;
import com.yong.doit.app.PeriodType;
import com.yong.doit.data.bean.Event;
import com.yong.doit.data.bean.ProgressIdentify;
import com.yong.doit.service.EventService;
import com.yong.doit.service.ProgressIdentifyService;
import com.yong.doit.ui.widget.AddProgressIdentifyFragment.OnDismissListener;

public class EditEventFragment extends BaseFragment implements OnClickListener {

    private List<ProgressIdentify> identifies;

    private CustomSpinnerAdapter<ProgressIdentify> identifyAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.edit_event, null);

        v.findViewById(R.id.btnSave).setOnClickListener(this);

        setupEventModeSpinner(v);

        setupPeriodTypeSpinner(v);

        Spinner identify = (Spinner) v.findViewById(
                R.id.progressIdentify);
        identify.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (position == identifies.size() - 1) {
                    showAddIdentify();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button startDate = (Button) v.findViewById(R.id.startDate);
        Button startTime = (Button) v.findViewById(R.id.startTime);
        startDate.setOnClickListener(this);
        startTime.setOnClickListener(this);
        String[] start = formatDate(new Date());
        startDate.setText(start[0]);
        startTime.setText(start[1]);

        EditText eventPeriod = (EditText) v.findViewById(R.id.eventPeriod);

        return v;
    }

    private void setupEventModeSpinner(View v) {
        Spinner eventModeSpinner = (Spinner) v.findViewById(R.id.eventMode);

        List<EventMode> eventModes = new ArrayList<EventMode>();
        EnumSet<EventMode> pset = EnumSet.allOf(EventMode.class);
        for (EventMode type : pset) {
            eventModes.add(type);
        }
        CustomSpinnerAdapter<EventMode> eventModeAdapter = new CustomSpinnerAdapter<EventMode>(
                getActivity(), eventModes);

        eventModeSpinner.setAdapter(eventModeAdapter);
    }

    private void setupPeriodTypeSpinner(View v) {
        Spinner periodType = (Spinner) v.findViewById(R.id.periodType);

        List<PeriodType> periodTypes = new ArrayList<PeriodType>();
        EnumSet<PeriodType> pset = EnumSet.allOf(PeriodType.class);
        for (PeriodType type : pset) {
            periodTypes.add(type);
        }
        CustomSpinnerAdapter<PeriodType> periodTypeAdapter = new CustomSpinnerAdapter<PeriodType>(
                getActivity(), periodTypes);

        periodType.setAdapter(periodTypeAdapter);

    }

    private String[] formatDate(Date date) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:00");
        String sdate = sd.format(date);
        return sdate.split(" ");
    }

    private Date toDate(String dateStr) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date = sd.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void showAddIdentify() {
        AddProgressIdentifyFragment identifyFragment = new AddProgressIdentifyFragment();
        identifyFragment.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(boolean canceled) {
                initIdentify(!canceled);
            }
        });
        identifyFragment.setCancelable(false);
        identifyFragment.show(getFragmentManager(), "addIdentifyFragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        initIdentify(false);
    }

    protected void initIdentify(boolean showTarget) {
        Spinner identifySpinner = (Spinner) getView().findViewById(
                R.id.progressIdentify);
        identifyAdapter = new CustomSpinnerAdapter<ProgressIdentify>(getActivity(), getIdentify());
        identifySpinner.setAdapter(identifyAdapter);
        if (showTarget) {
            identifySpinner.setSelection(identifies.size() - 2, true);
        } else {
            identifySpinner.setSelection(0, true);
        }
    }

    private List<ProgressIdentify> getIdentify() {
        identifies = ProgressIdentifyService.getInstance().getAll();
        if (null == identifies) {
            identifies = new ArrayList<ProgressIdentify>();
        }
        identifies.add(0, new ProgressIdentify("选择进度跟踪方法"));
        identifies.add(new ProgressIdentify("+添加进度跟踪方法"));
        return identifies;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                saveEvent();
                break;
            case R.id.startDate:
                showDatePicker((Button) v);
                break;
            case R.id.startTime:
                showTimePicker((Button) v);
                break;
            default:
                break;
        }
    }

    private void saveEvent() {
        String name = ((EditText) getView().findViewById(R.id.eventName)).getText().toString();
        name = name.trim();
        if (TextUtils.isEmpty(name)) {
            UIUtil.showToast(getActivity(), "Set an event name,please");
            return;
        }

        Spinner eventModeSpinner = (Spinner) getView().findViewById(R.id.eventMode);
        int modePositoin = eventModeSpinner.getSelectedItemPosition();
        /*if(modePositoin == 0){
			UIUtil.showToast(getActivity(), "Choose event mode,please");
			return;
		}*/

        Spinner identifySpinner = (Spinner) getView().findViewById(
                R.id.progressIdentify);
        int identifyPosi = identifySpinner.getSelectedItemPosition();
        if (identifyPosi == 0 || identifyPosi == identifies.size() - 1) {
            UIUtil.showToast(getActivity(), "Choose progress identify,please");
            return;
        }

        EditText eventPeriod = (EditText) getView().findViewById(R.id.eventPeriod);
        Spinner periodTypeSpinner = (Spinner) getView().findViewById(R.id.periodType);
        PeriodType periodType = (PeriodType) periodTypeSpinner.getSelectedItem();
        Integer period = Integer.parseInt(eventPeriod.getText().toString().trim());
        if (period <= 0) {
            UIUtil.showToast(getActivity(), "Invalied event period!");
            return;
        }
        Event event = new Event();
        event.setName(name);

        EventMode eventMode = (EventMode) eventModeSpinner.getSelectedItem();
        event.setEventMode(eventMode.getMode());

        event.setpIdentify(identifies.get(identifyPosi));

        Button startDate = (Button) getView().findViewById(R.id.startDate);
        Button startTime = (Button) getView().findViewById(R.id.startTime);
        String dateStr = startDate.getText().toString() + " " + startTime.getText().toString();
        event.setStartDate(toDate(dateStr));

        event.setPeroid(Integer.parseInt(eventPeriod.getText().toString()));
        event.setPeriodType(periodType.getTypeId());

        EditText etNote = (EditText) getView().findViewById(R.id.eventNote);
        event.setNote(etNote.getText().toString().trim());

        EventService.getInstance().save(event);
        UIUtil.showToast(getActivity(), "saved: " + event.getName());

        switchFragment(FRAGMENT_EVENT_LIST, true);
    }

    @Override
    public void refreshFragment() {

    }

    private boolean checkEventPeriod() {
        EditText eventPeriod = (EditText) getView().findViewById(R.id.eventPeriod);
        Spinner periodType = (Spinner) getView().findViewById(R.id.periodType);
        PeriodType peroidType = (PeriodType) periodType.getSelectedItem();
        Integer period = Integer.parseInt(eventPeriod.getText().toString().trim());
        if (period > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                switchFragment(FRAGMENT_EVENT_LIST, false);
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    private void showDatePicker(final Button et) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        DatePickerDialog d = new DatePickerDialog(getActivity(),
                DatePickerDialog.THEME_HOLO_DARK, new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                et.setText(year + "-" + (monthOfYear + 1) + "-"
                        + dayOfMonth);

            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        d.show();
    }

    private void showTimePicker(final Button et) {
        TimePickerDialog td = new TimePickerDialog(getActivity(),
                TimePickerDialog.THEME_HOLO_DARK, new OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
                et.setText(hourOfDay + ":" + minute);
            }
        }, 16, 0, true);
        td.show();
    }
}
