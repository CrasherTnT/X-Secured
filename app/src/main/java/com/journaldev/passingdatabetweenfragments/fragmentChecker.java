package com.journaldev.passingdatabetweenfragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.journaldev.passingdatabetweenfragments.PwdStrength.PasswordStrength;

public class fragmentChecker extends Fragment implements TextWatcher {
    SendMessage2 SM2;
    public static EditText password;
    public static TextView strengthView;
    TextView test;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate
                (R.layout.checker, container, false);
        return rootView;


    }
    //NEW
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        password = (EditText) view.findViewById(R.id.login_password);
        password.addTextChangedListener(this);
        Button finish = (Button) view.findViewById(R.id.finish);


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SM2.sendData2(password.getText().toString().trim());
                Toast.makeText(getActivity(), "Done!", Toast.LENGTH_SHORT).show();
            }
        });
    }
//Sending Data inside EditText//
    interface SendMessage2 {
        void sendData2(String message2);
    }

    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            SM2 = (fragmentChecker.SendMessage2) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

//Displaying data sent by fragmentGenerate//
    protected void displayReceivedData(String message)
    {
        password.setText(message);
    }


    public void afterTextChanged(Editable s) {
    }

    public void beforeTextChanged(
            CharSequence s, int start, int count, int after) {
    }


    public void onTextChanged(CharSequence s, int start, int before, int count) {
        updatePasswordStrengthView(s.toString());
    }

    public void updatePasswordStrengthView(String password) {

        ProgressBar progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar);
        strengthView = (TextView) getActivity().findViewById(R.id.password_strength);
        if (TextView.VISIBLE != strengthView.getVisibility())
            return;

        if (password.isEmpty()) {
            strengthView.setText("");
            progressBar.setProgress(0);
        }

        PasswordStrength str = PasswordStrength.calculateStrength(password);
        strengthView.setText(str.getText(getActivity()));
        strengthView.setTextColor(str.getColor());

        progressBar.getProgressDrawable().setColorFilter(str.getColor(), android.graphics.PorterDuff.Mode.SRC_IN);
        if (str.getText(getActivity()).equals("Weak")) {
            progressBar.setProgress(25);
        } else if (str.getText(getActivity()).equals("Medium")) {
            progressBar.setProgress(50);
        } else if (str.getText(getActivity()).equals("Strong")) {
            progressBar.setProgress(75);
        } else {
            progressBar.setProgress(100);
        }
    }


}

