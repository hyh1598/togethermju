package com.example.mathpresso.togethermju;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathpresso.togethermju.RegisterActivity.GroupResiterActivity;
import com.example.mathpresso.togethermju.adapter.ListViewAdapter;
import com.example.mathpresso.togethermju.model.Notice;
import com.melnykov.fab.FloatingActionButton;

public class NoticeDetailsActivity extends AppCompatActivity{
    ListView listview;
    ListViewAdapter adapter;
    private TextView textViewTitle;
    private TextView textViewContent;

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

        textViewTitle.setText(title);
        textViewContent.setText(content);
        // Adapter 생성
        adapter = new ListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        ListView listView = (ListView) findViewById(android.R.id.list);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(listView);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "액티비티 전환", Toast.LENGTH_LONG).show();

                // 액티비티 전환 코드
                Intent intent = new Intent(getApplicationContext(), GroupResiterActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //adapter.addItem(ContextCompat.getDrawable(this, ));

    }
}
