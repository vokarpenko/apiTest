package com.example.apitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public String pid;
    public MyTask myTask;
    static char dm = (char)34;
    EditText editText;
    static String path;
    String NullString = "{"+dm+"success"+dm+":0,"+dm+"message"+dm+":"+dm+"Project is empty"+dm+"}";
    static String line = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
    }

    public void OnClick(View view) {
        pid = editText.getText().toString();
        if (editText.getText().toString().equals("")) {
            showToast("Введите pid");
        }
        else {
            path = "http://mytj.ru/api/get_all_workers_in_project.php?pid="+pid+"&list=all";
            myTask = new MyTask();
            myTask.execute();
            try {
                line = myTask.get();
                if (!NullString.equals(line)) {
                    Intent intent = new Intent(MainActivity.this, List.class);
                    intent.putExtra("json", line);
                    myTask.cancel(true);
                    startActivity(intent);
                }
                else {showToast("Такого pid нет в базе");}
            }
            catch (Exception e){showToast(e.toString());}
        }
    }

    static public class MyTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            if (isCancelled()) return null;
            try {
                line="";
                URL url = new URL(path);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
                String temp;
                while (true) {
                    temp = reader.readLine();
                    if (temp != null)
                        line += temp;
                    else
                        return line;
                }
            } catch (Exception e) {}
            return line;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    public void showToast(String message){
        Toast toast = Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG);
        toast.show();
    }
}



