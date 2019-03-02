package edu.sharif.nosense.project1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Observer{
    private NotificationCenter notificationCenter = new NotificationCenter();
    private MessageController messageController = new MessageController(this.notificationCenter);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationCenter.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notificationCenter.unRegister(this);
    }

    @Override
    public void update() {
    }
}
