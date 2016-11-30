package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.mathpresso.togethermju.RegisterActivity.GroupResiterActivity;
import com.example.mathpresso.togethermju.adapter.ListViewAdapter;
import com.example.mathpresso.togethermju.model.Notice;
import com.melnykov.fab.FloatingActionButton;

public class NoticeDetailsActivity extends AppCompatActivity{
    ListView listview;
    ListViewAdapter adapter;
    private TextView textViewTitle;
    private TextView textViewContent;
    private Button watch;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_details);
        getIntent();


        Intent intent = getIntent();
        Notice notice = (Notice) intent.getSerializableExtra("notice");
        String title = notice.getTitle();
        String content = notice.getContent();

        textViewTitle = (TextView) findViewById(R.id.notice_detail_title);
        textViewContent = (TextView) findViewById(R.id.notice_detail_content);
        watch = (Button) findViewById(R.id.watch);
        textViewTitle.setText(title);
        textViewContent.setText(content);
        final ToggleButton tb = (ToggleButton)this.findViewById(R.id.watch);
        // Adapter 생성
        adapter = new ListViewAdapter();


        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        //ListView listView = (ListView) findViewById(android.R.id.list);
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tb.isChecked()){
                    tb.setTextColor(Color.BLUE);

                    //서버로 watch +1
                }else{
                    tb.setTextColor(Color.RED);
                    //서버로 watch값 -1
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(listview);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "액티비티 전환", Toast.LENGTH_LONG).show();

                // 액티비티 전환 코드
                Intent intent = new Intent(getApplicationContext(), GroupResiterActivity.class);
                startActivity(intent);

            }
        });


        //adapter.addItem(ContextCompat.getDrawable(this, ));

    }
}
