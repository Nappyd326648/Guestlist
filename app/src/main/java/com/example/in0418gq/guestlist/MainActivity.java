package com.example.in0418gq.guestlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText mEnterGuest;
    Button mSaveGuest;
    TextView mGuestlist;

    ArrayList<String> mGuests;
    private final static String GUEST_LIST_KEY="guest list bundle key";

    @Override
    protected void onSaveInstanceState(Bundle outBundle){
        outBundle.putStringArrayList(GUEST_LIST_KEY,mGuests);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            mGuests = savedInstanceState.getStringArrayList(GUEST_LIST_KEY);

        }
        if (mGuests== null) {
            mGuests = new ArrayList<String>();
        }

        
        mEnterGuest=(EditText)  findViewById(R.id.guestname);
        mSaveGuest =(Button) findViewById(R.id.saveButton);
        mGuestlist =(TextView) findViewById(R.id.guestlist);
        MainActivity.this.updateGuestlist();
        mSaveGuest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String newGuestName = mEnterGuest.getText().toString();
                if(newGuestName.length()>0){
                    mGuests.add(newGuestName);
                    mEnterGuest.getText().clear();
                    MainActivity.this.updateGuestlist();
                }
                else {
                    Toast.makeText(MainActivity.this,"Please enter a guest name",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateGuestlist() {
        String displayString= " ";
        for (String name : mGuests){
            displayString = displayString+ name+"\n";
        }
        mGuestlist.setText(displayString);
    }
}
