package com.example.mathpresso.togethermju;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mathpresso.togethermju.Network.urlToImageProcessor;
import com.example.mathpresso.togethermju.core.AppController;
import com.example.mathpresso.togethermju.model.DefaultResponse;
import com.example.mathpresso.togethermju.tool.ImageFilePath;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private static final int PICK_IMAGE_REQUEST = 1001;
    private static final String[] TAB_TITLES = {
            "NOTICE",
            "FAVORITE",
            "GROUP"
    };
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView mNavigationView;
    TextView emailTextView;
    TextView nameTextView;
    ImageView imgvProfile;
    MainImageLoadProcessor imageloader;

    @Override
    protected void onStart() {
        super.onStart();
        //User정보확인 없을 경우 , Login다시요청
        if (!AppController.UpdateUserinfo(AppController.getInstance())) {
            //로그아웃
            AppController.getInstance().clearLocalStore();
            moveToLoginActivity();
        } else {
            Log.d("MAIN:NAME", AppController.user.getName());
            Log.d("MAIN:EMAIL", AppController.user.getEmail());
            Log.d("MAIN:RID", AppController.user.getRid());
            emailTextView.setText(AppController.user.getEmail());
            nameTextView.setText(AppController.user.getName());
            loadProfileImage();
            //            imageloader = new MainImageLoadProcessor();
            //imageloader.execute(AppController.user.getEmail());

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLayout();

        if (shouldAskPermissions()) {
            askPermissions();
        }

    }

    private void initializeLayout() {
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //drawer Layout
        View view = LayoutInflater.from(this).inflate(R.layout.drawer_header, null);

        emailTextView = (TextView) view.findViewById(R.id.email_text_view);
        nameTextView = (TextView) view.findViewById(R.id.name_text_view);
        imgvProfile = (ImageView) view.findViewById(R.id.user_imageView);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.addHeaderView(view);
        imgvProfile.setOnClickListener(this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new simpleAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_setting:
                Toast.makeText(this, "move to setting activity", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_logout:
                AppController.getInstance().clearLocalStore();
                moveToLoginActivity();
                break;
        }
        return false;
    }

    private void moveToLoginActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private class simpleAdapter extends FragmentPagerAdapter {
        public simpleAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return TAB_TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new NoticeFragment();
                case 1:
                    return new FavoriteFragment();
                case 2:
                    return new GroupFragment();
            }

            throw new IllegalStateException("There's no fragment for position " + position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TAB_TITLES[position];
        }
    }

    private class MainImageLoadProcessor extends urlToImageProcessor {
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                //upload image on AppController user instance
                Log.d("IMAGESTATUS", "SUCCESS");
                imgvProfile.setImageBitmap(bitmap);
            }
        }
    }

    private void loadProfileImage() {
        String server_url = AppController.getBaseUrl() + "loaduserimage/?email=" + AppController.user.getEmail();

        Glide.with(this).load(server_url)
                .fitCenter()
                .bitmapTransform(new CropCircleTransformation(this))
                .into(imgvProfile);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_imageView:
                AppController.getInstance().getRestManager().getUserService().uploadProfileImage()
                        .enqueue(new Callback<DefaultResponse>() {
                            @Override
                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                Toast.makeText(MainActivity.this,"",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                                Toast.makeText(MainActivity.this,"",Toast.LENGTH_SHORT).show();

                            }
                        });

//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                this.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST) {
            Uri uri = data.getData();
            String filePath = getFilePathFromUri(this, uri);
            if (filePath != null) {
                File imageFile = new File(filePath);
                String email = AppController.getInstance().getStringValue("email", "");
                RequestBody file = RequestBody.create(MediaType.parse("image/*"), imageFile);

                AppController.getInstance().getRestManager().getUserService().uploadProfileImage(email, file)
                        .enqueue(new Callback<DefaultResponse>() {
                            @Override
                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                if (response.isSuccess() && response.body().getResult().equals("success")) {
                                    loadProfileImage();
                                }
                            }

                            @Override
                            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                                Log.d("upload profile", t.getMessage().toString());
                            }
                        });

            } else {
            }
        }
    }

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static Uri handleImageUri(Uri uri) {
        Pattern pattern = Pattern.compile("(content://media/.*\\d)");
        if (uri.getPath().contains("content")) {
            Matcher matcher = pattern.matcher(uri.getPath());
            if (matcher.find())
                return Uri.parse(matcher.group(1));
            else
                throw new IllegalArgumentException("Cannot handle this URI");
        } else
            return uri;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String getFilePathFromKitkatUri(Context context, Uri uri) {
        Cursor cursor = null;
        try {
            Uri newUri = handleImageUri(uri);
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(newUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            if (cursor.moveToFirst()) {
                String filePath = cursor.getString(column_index);
                if (filePath != null) {
                    return filePath;
                }
            }
        } catch (Exception ignored) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        try {
            String wholeId = DocumentsContract.getDocumentId(uri);
            String id = wholeId.split(":")[1];
            String[] column = {MediaStore.Images.Media.DATA};

            String sel = MediaStore.Images.Media._ID + "=?";
            cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    column, sel, new String[]{id}, null);
            int columnIndex = cursor.getColumnIndex(column[0]);
            if (cursor.moveToFirst()) {
                String filePath = cursor.getString(columnIndex);
                if (filePath != null) {
                    return filePath;
                }
            }
        } catch (Exception ignored) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return null;
    }

    protected static String getFilePathFromUri(Context context, Uri uri) {
        if (Build.VERSION.SDK_INT < 19) {
            return ImageFilePath.getPath(context, uri);
        } else {
            return getFilePathFromKitkatUri(context, uri);
        }
    }
}
