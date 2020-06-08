package com.example.phucnguyen.chichisocialnetwork.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.phucnguyen.chichisocialnetwork.R;
import com.example.phucnguyen.chichisocialnetwork.activity.SignUpActivity;
import com.example.phucnguyen.chichisocialnetwork.callback.OnSendDataClickListener;

import java.util.ArrayList;

public class FragmentBirthDay extends Fragment {

    Button btnContinue;
    Spinner spinnerDay, spinnerMonth, spinnerYear;

    ArrayList<String> listDay, listMonth, listYear;
    String day, month, year, birthday;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_birthday, container, false);
        btnContinue = view.findViewById(R.id.button_continue);
        spinnerDay = view.findViewById(R.id.spinner_day);
        spinnerMonth = view.findViewById(R.id.spinner_month);
        spinnerYear = view.findViewById(R.id.spinner_year);

        listDay = new ArrayList<>();
        listDay.add("1");
        listDay.add("2");
        listDay.add("3");
        listDay.add("4");
        listDay.add("5");
        listDay.add("6");
        listDay.add("7");
        listDay.add("8");
        listDay.add("9");
        listDay.add("10");
        listDay.add("11");
        listDay.add("12");
        listDay.add("13");
        listDay.add("14");
        listDay.add("15");
        listDay.add("16");
        listDay.add("17");
        listDay.add("18");
        listDay.add("19");
        listDay.add("20");
        listDay.add("21");
        listDay.add("22");
        listDay.add("23");
        listDay.add("24");
        listDay.add("25");
        listDay.add("26");
        listDay.add("27");
        listDay.add("28");
        listDay.add("29");
        listDay.add("30");
        listDay.add("31");

        listMonth = new ArrayList<>();
        listMonth.add("January");
        listMonth.add("February");
        listMonth.add("March");
        listMonth.add("April");
        listMonth.add("May");
        listMonth.add("June");
        listMonth.add("July");
        listMonth.add("August");
        listMonth.add("September");
        listMonth.add("October");
        listMonth.add("November");
        listMonth.add("December");

        listYear = new ArrayList<>();
        listYear.add("1990");
        listYear.add("1991");
        listYear.add("1992");
        listYear.add("1993");
        listYear.add("1994");
        listYear.add("1995");
        listYear.add("1996");
        listYear.add("1997");
        listYear.add("1998");
        listYear.add("1999");

        ArrayAdapter<String> adapterDay = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listDay);
        spinnerDay.setAdapter(adapterDay);

        ArrayAdapter<String> adapterMonth = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listMonth);
        spinnerMonth.setAdapter(adapterMonth);

        ArrayAdapter<String> adapterYear = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listYear);
        spinnerYear.setAdapter(adapterYear);

        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                day = listDay.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                month = listMonth.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year = listYear.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final OnSendDataClickListener onSendDataClickListener = (OnSendDataClickListener) getContext();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                birthday = day + ", " + month + ", " + year;
                if (TextUtils.isEmpty(birthday)) {
                    ((SignUpActivity) getActivity()).setPositionViewPager(4);
                    if (onSendDataClickListener != null) {
                        onSendDataClickListener.onSendDataBirthDay(birthday);
                    }
                } else {
                    Toast.makeText(getContext(), "Bạn vui lòng khai báo ngày tháng năm", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
}
