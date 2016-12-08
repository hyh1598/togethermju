package com.example.mathpresso.togethermju.RegisterActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mathpresso.togethermju.LoginActivity;
import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.model.User;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mathpresso.togethermju.core.AppController.user;


public class FavoriteRegisterActivity extends AppCompatActivity {
    private GoogleApiClient mGoogleApiClient;
    GoogleCloudMessaging gcm;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_register);

        getIntent();
    }


    public void postUser() {
        Call<User> call = AppController.getInstance().getRestManager().getUserService().getUserInformation(user.getEmail(),
                user.getName(), user.getRid(), user.getBirth(), user.getMajor(), user.getPassword(), user.getGender());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccess()) {

                    Log.d("jsondata",String.valueOf(response.message()));
                    Toast.makeText(getBaseContext(), "가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    int statusCode = response.code();
                    Log.d("jsondata",String.valueOf(response.body()));
                    Log.i("MY TAG", "응답 코드: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("MY TAG", "서버 onFailure 내용: " + t.getMessage());
            }
        });
    }

    public void clickBackButton(View view) {
        startActivity(new Intent(this, DetailRegisterActivity.class));
    }

    public void clickRegisterButton(View view) {
        CheckBox support_checkbox = (CheckBox) findViewById(R.id.support_checkbox);
        CheckBox volunteer_checkbox = (CheckBox) findViewById(R.id.volunteer_checkbox);
        CheckBox study_checkbox = (CheckBox) findViewById(R.id.study_checkbox);
        CheckBox contest_checkbox = (CheckBox) findViewById(R.id.contest_checkbox);

        ArrayList<String> checkList = new ArrayList<String>();

        if ((volunteer_checkbox.isChecked() == false) && (study_checkbox.isChecked() == false)
                && (contest_checkbox.isChecked() == false) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");

            user.setFavorite(checkList);
            new RegisterTask().execute(null, null, null);
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == false)
                && (contest_checkbox.isChecked() == false) && (support_checkbox.isChecked() == false)) {
            checkList.add("봉사활동");

            user.setFavorite(checkList);
            new RegisterTask().execute(null, null, null);
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        } else if ((volunteer_checkbox.isChecked() == false) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == false) && (support_checkbox.isChecked() == false)) {
            checkList.add("스터디");

            user.setFavorite(checkList);
            new RegisterTask().execute(null, null, null);
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        } else if ((volunteer_checkbox.isChecked() == false) && (study_checkbox.isChecked() == false)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == false)) {
            checkList.add("공모전");

            user.setFavorite(checkList);
            new RegisterTask().execute(null, null, null);
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == false)
                && (contest_checkbox.isChecked() == false) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("봉사활동");

            user.setFavorite(checkList);
            new RegisterTask().execute(null, null, null);
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        } else if ((volunteer_checkbox.isChecked() == false) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == false) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("스터디");

            user.setFavorite(checkList);
            new RegisterTask().execute(null, null, null);
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        } else if ((volunteer_checkbox.isChecked() == false) && (study_checkbox.isChecked() == false)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("공모전");

            user.setFavorite(checkList);
            new RegisterTask().execute(null, null, null);
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == false) && (support_checkbox.isChecked() == false)) {
            checkList.add("봉사활동");
            checkList.add("스터디");

            user.setFavorite(checkList);
            new RegisterTask().execute(null, null, null);
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == false)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == false)) {
            checkList.add("봉사활동");
            checkList.add("공모전");

            user.setFavorite(checkList);
            new RegisterTask().execute(null, null, null);
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        } else if ((volunteer_checkbox.isChecked() == false) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == false)) {
            checkList.add("스터디");
            checkList.add("공모전");

            user.setFavorite(checkList);
            new RegisterTask().execute(null, null, null);
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == false) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("봉사활동");
            checkList.add("스터디");

            user.setFavorite(checkList);
            new RegisterTask().execute(null, null, null);
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == false)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("봉사활동");
            checkList.add("공모전");

            user.setFavorite(checkList);
            new RegisterTask().execute(null, null, null);
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        } else if ((volunteer_checkbox.isChecked() == false) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("스터디");
            checkList.add("공모전");

            user.setFavorite(checkList);
            new RegisterTask().execute(null, null, null);
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == false)) {
            checkList.add("봉사활동");
            checkList.add("스터디");
            checkList.add("공모전");

            user.setFavorite(checkList);
            new RegisterTask().execute(null, null, null);
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        } else if ((volunteer_checkbox.isChecked() == true) && (study_checkbox.isChecked() == true)
                && (contest_checkbox.isChecked() == true) && (support_checkbox.isChecked() == true)) {
            checkList.add("서포터즈");
            checkList.add("봉사활동");
            checkList.add("스터디");
            checkList.add("공모전");

            user.setFavorite(checkList);

            new RegisterTask().execute(null, null, null);
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();


        } else {
            Toast.makeText(this, "관심사를 선택해주세요.", Toast.LENGTH_SHORT).show();
        }
    }
    public void server_load(){
        postUser();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
    class RegisterTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {}

        @Override
        protected String doInBackground(Void... params) {
            String string;

            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                }
                user.setRid(gcm.register("241720710605"));
                Log.i("RECEIVERID", "REGISTER ID: " + user.getRid());
                string = "REGISTER ID IS\n" + user.getRid();
            } catch (IOException e) {
                string = "ERROR IN REGISTERING" +e.getMessage();
            }
            return string;
        }

        @Override
        protected void onPostExecute(String msg) {
            progress.dismiss();//PROGRESS DIAGRAM 실행 종료
            server_load();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();
    }
    @Override
    protected void onStop() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }
}