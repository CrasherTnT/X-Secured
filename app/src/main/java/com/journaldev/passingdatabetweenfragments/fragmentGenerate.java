package com.journaldev.passingdatabetweenfragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class fragmentGenerate extends Fragment {

    Random random = new Random();
    private static final String _CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()";
    private static final int RANDOM_STR_LENGTH = 12;
    SendMessage SM;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.generate, container, false);
        //Initialization//
        final TextView output = (TextView) rootView.findViewById(R.id.generateOutput);
        Button generate = (Button) rootView.findViewById(R.id.generateButton);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText(getRandomString());
                Toast.makeText(getActivity(), "You Generated A Password!", Toast.LENGTH_SHORT).show();
                SM.sendData(output.getText().toString().trim());
            }
        });
        return rootView;
    }



     //Start of Sending Data//
    interface SendMessage {
        void sendData(String message);
    }

    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            SM = (fragmentGenerate.SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }
     //End of Sending Data//




    //Start of Generating Random Password//
    public String getRandomString() {
        StringBuffer randomStr = new StringBuffer();
        for (int i = 0; i < RANDOM_STR_LENGTH; i++) {
            int number = getRandomNumber();
            char ch = _CHAR.charAt(number);
            randomStr.append(ch);
        }
        return randomStr.toString();
    }

    private int getRandomNumber() {

        int randomInt = 0;
        randomInt = random.nextInt(_CHAR.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
    //End of Generating Random Password//

}