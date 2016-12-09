package com.example.mathpresso.togethermju;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mathpresso.togethermju.RegisterActivity.EmailRegisterActivity;
import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.model.User;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mathpresso.togethermju.core.AppController.user;


public class LoginActivity extends AppCompatActivity {
    EditText editUserEmailText ;
    EditText editUserPasswordText ;
    String userEmail;//userEmail
    String userPassword;//userPassword
    GoogleCloudMessaging gcm;

    ProgressDialog progress;//wating progress diagram

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void clickRegisterButton(View view) {
        startActivity(new Intent(this, EmailRegisterActivity.class));
    }

    public void clickLoginButton(View view) {
        editUserEmailText = (EditText) findViewById(R.id.user_email);
        editUserPasswordText = (EditText) findViewById(R.id.user_password);

        if (editUserEmailText.getText().toString().equals("")) {
            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else if (editUserPasswordText.getText().toString().equals("")) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            userEmail = editUserEmailText.getText().toString();
            userPassword = editUserPasswordText.getText().toString();
            //User Auth
            userAuth(userEmail, userPassword);
            //Wait
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();

// To dismiss the dialog

        }
    }
    //after UserAuth, if Success Start MainActivity
    public void startMainActivity(){

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        userEmail = editUserEmailText.getText().toString();
        userPassword = editUserPasswordText.getText().toString();

        finish();
    }



    public void userAuth(String email, String password) {
        Call<User> call = AppController.getInstance().getRestManager().getUserService().getUserAuth(email, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //Login Success
                if (response.isSuccess()) {
                    User user = response.body();
                    //current Loging Userinfo store

                    if(user.getName()==null || user.getEmail() ==null){
                        progress.dismiss();//PROGRESS DIAGRAM 실행 종료
                        Toast.makeText(getBaseContext(), "Email과 Password를 확인해주세요.", Toast.LENGTH_SHORT).show();
                        return ;
                    }
                    AppController.user = user;
                    AppController.setUserinfo(AppController.getInstance());

                    Toast.makeText(getBaseContext(), "로그인되었습니다.", Toast.LENGTH_SHORT).show();
                    progress.dismiss();//PROGRESS DIAGRAM 실행 종료
                    new RegisterTask().execute(null,null,null);

                    startMainActivity();

                } else {
                    //Login problem
                    int statusCode = response.code();
                    Log.i("MY TAG", "응답 코드: " + statusCode);
                    progress.dismiss();//PROGRESS DIAGRAM 실행 종료
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("MY TAG", "서버 onFailure 내용: " + t.getMessage());
                //Login problem
                progress.dismiss();//PROGRESS DIAGRAM 실행 종료
                Toast.makeText(getApplicationContext(),"Login failure : Server Problem",Toast.LENGTH_LONG).show();

            }
        });
    }

    protected void registerRID(String email ,String rid) {
        HttpURLConnection connection;
        DataOutputStream request = null;

        URL url = null;
        String response = null;

        try {
            url = new URL("http://125.130.223.88:8000/gcm/v1/device/register/");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",email);
            jsonObject.put("reg_id",rid);
            jsonObject.put("dev_id","phone");//for test

            request = new DataOutputStream(connection.getOutputStream());
            request.write(jsonObject.toString().getBytes());
            request.flush();
            request.close();
            String line = "";
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            // Response from server after login process will be stored in response variable.
            response = sb.toString();
            // You can perform UI operations here
            Log.d("Message from Server",response);
            isr.close();
            reader.close();

        } catch (IOException e) {
            // Error
        } catch (JSONException e){

        }
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
                user.setRid(gcm.register(AppController.PROJECTNUM));
                registerRID(user.getEmail(), user.getRid());
                Log.i("RECEIVERID", "REGISTER ID: " + user.getRid());
                string = "REGISTER ID IS\n" + user.getRid();
            } catch (IOException e) {
                string = "ERROR IN REGISTERING" +e.getMessage();
            }
            return string;
        }

        @Override
        protected void onPostExecute(String msg) {

        }
    }
}
