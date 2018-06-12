package com.chrisdev.android.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private enum eButtons {
        GOOD,
        OK,
        BAD,
    }

    Map<eButtons, Counter> mCounters = createCounters();

    private static Map<eButtons, Counter> createCounters() {
        Map<eButtons, Counter> myMap = new HashMap<eButtons, Counter>();
        for (eButtons button : eButtons.values()) myMap.put(button, new Counter());
        return myMap;
    }

    Map<eButtons, Integer> mTextViewIDs = createTextViewIds();

    private static Map<eButtons, Integer> createTextViewIds() {
        Map<eButtons, Integer> myMap = new HashMap<eButtons, Integer>();
        myMap.put(eButtons.GOOD, R.id.goodText);
        myMap.put(eButtons.OK, R.id.okText);
        myMap.put(eButtons.BAD, R.id.badText);

        if (eButtons.values().length != myMap.size()) throw new AssertionError();
        return myMap;
    }

    private void displayValue(int textViewID, int value) {
        TextView myTextView = (TextView)findViewById(textViewID);
        myTextView.setText(Integer.toString(value));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (eButtons button : eButtons.values()) displayValue(mTextViewIDs.get(button), mCounters.get(button).currentValue());
    }

    private void onClickButton(eButtons btn) {
        mCounters.get(btn).increment();
        displayValue(mTextViewIDs.get(btn), mCounters.get(btn).currentValue());
    }

    public void onClickGoodButton(View view) {
        onClickButton(eButtons.GOOD);
    }

    public void onClickOkButton(View view) {
        onClickButton(eButtons.OK);
    }

    public void onClickBadButton(View view) {
        onClickButton(eButtons.BAD);
    }

    public void onClickResetButton(View view) {
        for (eButtons button : eButtons.values()) {
            mCounters.get(button).reset();
            displayValue(mTextViewIDs.get(button), mCounters.get(button).currentValue());
        }
    }
}
