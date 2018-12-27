package de.easterapps.sotclock;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends WearableActivity {

    private TextView tv_time;
    private TextView tv_date;
    private PirateDate p = new PirateDate();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer timer = new Timer();
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_date = (TextView) findViewById(R.id.tv_date);

        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        p.updateCurrentTime();
                        tv_time.setText(p.getAmPmTime());
                        tv_date.setText(p.getDayText() );

                    }
                });
            }
        }, 0, 1000);

        setAmbientEnabled();
    }
}
