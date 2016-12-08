package com.example.mathpresso.togethermju.RegisterActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.model.DefaultResponse;
import com.example.mathpresso.togethermju.model.Group;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mathpresso.togethermju.R.id.btn_cancel;

public class GroupRegisterActivity extends AppCompatActivity {
    private EditText newGroup_name;
    private EditText newGroup_purpose;
    private String Notice_seq;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_register);
        Intent intent = getIntent();
        Notice_seq = intent.getStringExtra("notice_seq");

        newGroup_name = (EditText) findViewById(R.id.input_title);
        newGroup_purpose = (EditText) findViewById(R.id.input_purpose);


        Button finish = (Button) findViewById(R.id.btn_finish);
        Button cancel = (Button) findViewById(btn_cancel);
        TextView txtvTitle = (TextView)findViewById(R.id.txtvTitle);

        txtvTitle.setText("그룹만들기");

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        finish.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (newGroup_name.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "그룹 이름을 입력해주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (newGroup_purpose.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "그룹 목적을 입력해주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                //Group data 넘겨주기
                Group buffdata = new Group();
                buffdata.setName(newGroup_name.getText().toString());
                buffdata.setIntroduce(newGroup_purpose.getText().toString());
                buffdata.setNotice(Notice_seq);
                uploadGroupData(buffdata);
                progress = new ProgressDialog(GroupRegisterActivity.this);
                progress.setTitle("Loading");
                progress.setMessage("Wait while loading...");
                progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                progress.show();

            }
        });

    }
    //Group data 등록 요청
    public void uploadGroupData(Group groupdata){
        //FIXME id값으로 바꿔야함
        AppController.getInstance().getRestManager().getGroupService()
                .addGroup(groupdata.getName(),groupdata.getNotice(),groupdata.getIntroduce(),AppController.user.getEmail())
                .enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if (response.isSuccess()) {
                            DefaultResponse result = response.body();
                            Log.d("GROUP_UPLOAD",result.getResult());
                            if(result.getResult().toString().equals("success")){
                                //성공시 이전 화면으로
                                progress.dismiss();//PROGRESS DIAGRAM 실행 종료
                                finish();
                            }
                        }else{
                            int statusCode = response.code();
                            Log.i("MY TAG", "응답 코드: " + statusCode);
                            progress.dismiss();//PROGRESS DIAGRAM 실행 종료
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        progress.dismiss();//PROGRESS DIAGRAM 실행 종료
                        Toast.makeText(getApplicationContext(),"ERROR:ServerProblem",Toast.LENGTH_LONG).show();
                    }
                });

    }

}
