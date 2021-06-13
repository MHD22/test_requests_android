package com.example.testrequests;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    TabadolAPI tabadolAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText usernameET, passET;
        Button login = findViewById(R.id.button);
        usernameET = findViewById(R.id.username);
        passET = findViewById(R.id.pass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = usernameET.getText().toString();
                String password = passET.getText().toString();

            }
        });

//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tabadol1.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        tabadolAPI = retrofit.create(TabadolAPI.class);




        login();
//        getUser("https://tabadol1.herokuapp.com/jmyprofile");
        getCurrentUser();
//    getPosts();
//    getUser(3);
//    testPostJson();

    }

    public void getPosts(){

        Call<List<Post>> call = tabadolAPI.getPosts();


        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                Log.v("HTTP_Request: ","code: "+response.code());
                Post post = response.body().get(0);

                Log.v("HTTP_Request: ","first Post: "+post.toString());

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("HTTP_Request: ","Request failed.. something wrong in your request !  \n"+t.getMessage());
            }
        });

    }

    public void login(){

        Map<String, String> fields = new HashMap<>();
        fields.put("username","mhd.sad");
        fields.put("password","1234");
        Call<ResponseBody> call = tabadolAPI.login(fields);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.v("HTTP_Request: ","code: "+response.code());
                Log.v("HTTP_Request: ","Logged in successfully "+response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("HTTP_Request: ","Request failed.. something wrong in your request !  \n"+t.getMessage());
            }
        });

    }

    public  void getUser(long id){


        Call<User> call = tabadolAPI.getUser(id);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                TextView resTV = findViewById(R.id.response);

                Log.v("HTTP_Request: ","code: "+response.code());


                Log.v("HTTP_Request: ","response : "+response.raw());

                User user = response.body();
                resTV.setText(user.toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("HTTP_Request: ","Request failed.. something wrong in your request !  \n"+t.getMessage());
            }
        });


    }

    public  void getCurrentUser(){
        Call<User> call = tabadolAPI.getCurrentUser();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.v("HTTP_Request: ","code: "+response.code());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("HTTP_Request: ",t.getMessage());
            }
        });
    }

    public void getUser(String u){
       String url = u;

       Call<User> call = tabadolAPI.getUser(url);

       call.enqueue(new Callback<User>() {
           @Override
           public void onResponse(Call<User> call, Response<User> response) {
               Log.v("HTTP_Request: ","code: "+response.code());
           }

           @Override
           public void onFailure(Call<User> call, Throwable t) {
               Log.e("HTTP_Request: ",t.getMessage());
           }
       });
    }




    public  void testPostJson(){

        Call<ResponseJson> call = tabadolAPI.testPostJson(new Login("Rama","0000"));

        call.enqueue(new Callback<ResponseJson>() {
            @Override
            public void onResponse(Call<ResponseJson> call, Response<ResponseJson> response) {
                Log.v("HTTP_Request: ","code: "+response.code());


                ResponseJson resJson = response.body();
                Log.v("HTTP_Request: ","response : "+resJson.getMessage());
            }

            @Override
            public void onFailure(Call<ResponseJson> call, Throwable t) {
                Log.e("HTTP_Request: ","Request failed.. something wrong in your request !  \n"+t.getMessage());
            }
        });
    }
}