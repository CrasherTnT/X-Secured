package com.journaldev.passingdatabetweenfragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class fragmentEncrypt_Decrypt extends Fragment {
    EditText outputEncrypt;
    public static TextView output;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_encrypt__decrypt, container, false);
        Button encrypt = (Button) rootView.findViewById((R.id.buttonEncrypt));
        outputEncrypt = (EditText) rootView.findViewById(R.id.outputEncrypt);
        output = (TextView) rootView.findViewById(R.id.finalOutputEncrypt);

        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String encrypt_pass;
                encrypt_pass = outputEncrypt.getText().toString();
                output.setText("MD5: " + encryptMD5(encrypt_pass));
                Toast.makeText(getActivity(), "Password Encrypted!", Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }

    protected void displayReceivedData(String message2)
    {
        outputEncrypt.setText(message2);
    }

    private static String encryptMD5 (String data)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(data.getBytes());
            BigInteger num = new BigInteger(1, messageDigest);
            String hashtext = num
                    .toString(16);
            while (hashtext.length() < 32)
            {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

}
