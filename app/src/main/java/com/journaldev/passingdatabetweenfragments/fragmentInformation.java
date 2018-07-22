package com.journaldev.passingdatabetweenfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class fragmentInformation extends Fragment {
    public static EditText username;
    public static EditText email;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.information, container, false);

        username = (EditText) rootView.findViewById(R.id.eUser);
        email = (EditText) rootView.findViewById(R.id.eEmail);
        Button finish = (Button) rootView.findViewById(R.id.buttonFinish);
        Button clear = (Button) rootView.findViewById(R.id.buttonClear);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                intent.putExtra("username", username.getText().toString());
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("password", fragmentChecker.password.getText().toString());
                intent.putExtra("strength", fragmentChecker.strengthView.getText());
                intent.putExtra("encrypt", fragmentEncrypt_Decrypt.output.getText());
                startActivity(intent);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setText(null);
                email.setText(null);
                Toast.makeText(getActivity(), "Cleared!", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

}
