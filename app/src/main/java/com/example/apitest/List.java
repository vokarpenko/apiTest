package com.example.apitest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;

public class List extends AppCompatActivity {
    static JSONObject jObj = null;
    private static final String TAG_WORKERS ="workers";
    private static final String TAG_FLAWED ="flawed";
    private static final String TAG_MISSED_COUNT = "missed_count";
    private static final String TAG_CAME_ON_TIME_COUNT = "came_on_time_count";
    private static final String TAG_LEFT_EARLY_COUNT = "left_early_count";
    private static final String TAG_GONE_ON_TIME_COUNT = "gone_on_time_count";
    private static final String TAG_CAME_TIME_AVERAGE = "came_time_average";
    private static final String TAG_GONE_TIME_AVERAGE = "gone_time_average";
    private static final String TAG_RESPONSE_TYPE = "response_type";
    private static final String TAG_WID = "wid";
    private static final String TAG_WORKERS_NAME = "workers_name";
    private static final String TAG_WORKERS_PHONE = "workers_phone";
    private static final String TAG_WORKERS_TODAY_STATUS = "workers_today_status";
    private static final String TAG_WORKERS_TODAY_BAD_STATUS = "workers_today_bad_status";
    private static final String TAG_MONDAY_SCHEDULE = "monday_schedule";
    private static final String TAG_TUESDAY_SCHEDULE = "tuesday_schedule";
    private static final String TAG_WEDNESDAY_SCHEDULE = "wednesday_schedule";
    private static final String TAG_THURSDAY_SCHEDULE = "thursday_schedule";
    private static final String TAG_FRIDAY_SCHEDULE = "friday_schedule";
    private static final String TAG_SATURDAY_SCHEDULE = "saturday_schedule";
    private static final String TAG_SUNDAY_SCHEDULE = "sunday_schedule";
    StringBuilder stringBuilder = new StringBuilder();
    JSONArray workers = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        TextView output = findViewById(R.id.Output);
        try {
            String json = getIntent().getExtras().get("json").toString();
            jObj = new JSONObject(json);}
        catch (Exception e) {
            output.setText(e.toString());
        }
        try {
            workers = jObj.getJSONArray(TAG_WORKERS);
            for(int i = 0; i < workers.length(); i++){
                JSONObject c = workers.getJSONObject(i);
                String flawed = c.getString(TAG_FLAWED);
                String missed_count = c.getString(TAG_MISSED_COUNT);
                String came_on_time_count = c.getString(TAG_CAME_ON_TIME_COUNT);
                String left_early_count = c.getString(TAG_LEFT_EARLY_COUNT);
                String gone_on_time_count = c.getString(TAG_GONE_ON_TIME_COUNT);
                String came_time_average = c.getString(TAG_CAME_TIME_AVERAGE);
                String gone_time_average = c.getString(TAG_GONE_TIME_AVERAGE);
                String response_type = c.getString(TAG_RESPONSE_TYPE);
                String wid = c.getString(TAG_WID);
                String workers_name = c.getString(TAG_WORKERS_NAME);
                String workers_phone = c.getString(TAG_WORKERS_PHONE);
                String workers_today_status = c.getString(TAG_WORKERS_TODAY_STATUS);
                String workers_today_bad_status = c.getString(TAG_WORKERS_TODAY_BAD_STATUS);
                String monday_schedule = c.getString(TAG_MONDAY_SCHEDULE);
                String tuesday_schedule = c.getString(TAG_TUESDAY_SCHEDULE);
                String wednesday_schedule = c.getString(TAG_WEDNESDAY_SCHEDULE);
                String thursday_schedule = c.getString(TAG_THURSDAY_SCHEDULE);
                String friday_schedule = c.getString(TAG_FRIDAY_SCHEDULE);
                String saturday_schedule = c.getString(TAG_SATURDAY_SCHEDULE);
                String sunday_schedule = c.getString(TAG_SUNDAY_SCHEDULE);
                String current_worker = "Cотрудник №"+i+"\n        Имя: "+workers_name+"\n        Пропущено: "+flawed+
                        "\n        Количество опозданий: "+missed_count+"\n        Количество приходов вовремя: "+came_on_time_count+
                        "\n        Количество ранних уходов с работы: "+left_early_count+"\n        Количество уходов вовремя: "+gone_on_time_count+
                        "\n        Среднее время начала работы: "+came_time_average+"\n        Среднее время ухода с работы: "+gone_time_average+
                        "\n        Телефон: "+workers_phone+"\n        Id работника: "+wid+
                        "\n        Тип запроса: "+response_type+"\n        "+"График: "+
                        "\n                Понедельник: "+monday_schedule+"\n                Вторник: "+tuesday_schedule+
                        "\n                Среда: "+wednesday_schedule+"\n                Четверг: "+thursday_schedule+
                        "\n                Пятница: "+friday_schedule+"\n                Суббота: "+saturday_schedule+
                        "\n                Восскрсенье: "+sunday_schedule;
                stringBuilder.append(current_worker).append("\n");
            }
            output.setMovementMethod(new ScrollingMovementMethod());
            String answer = stringBuilder.toString();
            output.setText(answer);
        } catch (Exception e) {
            output.setText(e.toString());
        }
    }
}
