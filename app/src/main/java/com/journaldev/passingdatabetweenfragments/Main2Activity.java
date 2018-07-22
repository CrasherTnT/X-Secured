package com.journaldev.passingdatabetweenfragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        startService(new Intent(Main2Activity.this, SoundService.class));
        final TextView o1 = (TextView) findViewById(R.id.o1);
        final TextView o2 = (TextView) findViewById(R.id.o2);
        final TextView o3 = (TextView) findViewById(R.id.o3);
        final TextView o4 = (TextView) findViewById(R.id.o4);
        final  TextView o5 = (TextView) findViewById(R.id.o5);
        final  TextView o6 = (TextView) findViewById(R.id.o6);
        TextView o7 = (TextView) findViewById(R.id.o7);
        Button save = (Button) findViewById(R.id.buttonSave);
        Intent intent = getIntent();
        String s1 = intent.getStringExtra("username");
        String s2 = intent.getStringExtra("email");
        String s3 = intent.getStringExtra("password");
        String s4 = intent.getStringExtra("encrypt");
        String s5 = intent.getStringExtra("strength");
        int unicode = 0x26A0;
        int unicode1 = 0x1F44D;
        int unicode2 = 0x1F632;
        String emoji = getEmojiByUnicode(unicode);
        String emoji1 = getEmojiByUnicode1(unicode1);
        String emoji2 = getEmojiByUnicode2(unicode2);
        o1.setText(s1);
        o2.setText(s2);
        o3.setText(s3);
        o4.setText(s4);
        o5.setText(s5);

        if (s5.equals("Very Strong")) {
            o6.setText("Very Hard Difficulty");
            o7.setText(emoji2 + "WOW!, your password was defined as Very Hard" + "\n" + "It is very impossible for hackers to hack your password" + emoji2);
        }else if (s5.equals("Strong")) {
                o6.setText("Hard Difficulty");
                o7.setText(emoji2 + "Excellent!, your password was defined as Hard" + "\n" + "It is impossible for hackers to hack your password" + emoji2);
        } else if (s5.equals("Medium")) {
            o6.setText("Medium Difficulty");
            o7.setText(emoji1 + "Nice!, your password was defined as Medium" + "\n" + "It can be hacked but it will take up to 2 or more years" + emoji1);
        } else if (s5.equals("Weak")) {
            o6.setText("Easy Difficulty");
            o7.setText(emoji + "Warning if your password was defined as Easy" + "\n" + "Please change it immidiately!");
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String h = "Summary-" + DateFormat.format("MM-dd-yyyyy-h-mmssaa", System.currentTimeMillis()).toString();
                    // this will create a new name everytime and unique
                    File root = new File(Environment.getExternalStorageDirectory(), "SPG_Summary");
                    // if external memory exists and folder with name Notes
                    if (!root.exists()) {
                        root.mkdirs(); // this will create folder.
                    }
                    File filepath = new File(root, h + ".txt");  // file path to save
                    FileWriter writer = new FileWriter(filepath);
                    writer.append(
                            "Username: " +" " + o1.getText().toString()
                            +"\n"+ "Email Address: " +" " + o2.getText().toString()
                                    +"\n"+ "Password: " +" " + o3.getText().toString()
                                    +"\n"+ "Encrypted Password: " +" "+ o4.getText().toString()
                                    +"\n"+ "Level of Strength: " +" " + o5.getText().toString()
                                    +"\n"+ "Decryption Difficulty: " +" " + o6.getText().toString()

                    );
                    writer.flush();
                    writer.close();
                    String m = "File located at /storage/sdcard/SPG_Summary/" + h + ".txt";
                    Toast.makeText(Main2Activity.this, m, Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(Main2Activity.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
    public String getEmojiByUnicode1(int unicode1){
        return new String(Character.toChars(unicode1));
    }
    public String getEmojiByUnicode2(int unicode2){
        return new String(Character.toChars(unicode2));
    }
    protected void onDestroy() {
        //stop service and stop music
        stopService(new Intent(Main2Activity.this, SoundService.class));
        super.onDestroy();
    }
}

