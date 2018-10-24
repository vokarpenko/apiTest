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
    char dm = (char)34;
    EditText editText;
    public String path;
    String NullString = "{"+dm+"success"+dm+":0,"+dm+"message"+dm+":"+dm+"Project is empty"+dm+"}";
    String line = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
    }

    public void OnClick(View view) {
        pid = editText.getText().toString();
        if (editText.getText().toString().equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(),"Введите pid", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            path = "http://mytj.ru/api/get_all_workers_in_project.php?pid="+pid+"&list=all";
            MyTask myTask = new MyTask();
            myTask.execute();

        }
    }

    public class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL(path);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
                String temp;
                while (true) {
                    temp = reader.readLine();
                    line += temp;
                    if (temp == null)
                        break;
                }
            }
            catch (Exception e) { }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (line !=NullString){
            Intent intent = new Intent(MainActivity.this, List.class);
            intent.putExtra("json",line);
            startActivity(intent);
        }
        else {Toast toast = Toast.makeText(getApplicationContext(),"Такого pid нет в базе", Toast.LENGTH_LONG);
            toast.show();
            }
        }
    }
}



