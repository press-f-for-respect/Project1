package edu.sharif.nosense.project1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NotificationCenter.Observer{
    private NotificationCenter notificationCenter = new NotificationCenter();
    private MessageController messageController;
    private LinearLayout listContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationCenter.register(this);
        listContainer = findViewById(R.id.listContainer);
        messageController = MessageController.getInstance(notificationCenter, getApplicationContext());
        update();
        Button getButton = findViewById(R.id.getButton);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageController.fetch(true);
            }
        });
        Button clear = findViewById(R.id.clearButton);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listContainer.removeAllViews();
                messageController.getListOfNumbers().clear();
            }
        });
        Button refresh = findViewById(R.id.refreshButton);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageController.refresh();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notificationCenter.unRegister(this);
    }

    @Override
    public void update() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listContainer.removeAllViews();
                ArrayList<Integer> updatedListOfNumbers = messageController.getListOfNumbers();
                for (Integer number : updatedListOfNumbers) {
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText(String.valueOf(number));
                    listContainer.addView(textView);
                }
            }
        });
    }


}
