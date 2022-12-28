package com.example.tribe.Services;


import com.example.tribe.Notifications.MyResponse;
import com.example.tribe.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {


    @Headers({"Authorization:key=AAAA-vhIXCA:APA91bHKUV7Inarow85-QRXROb5DubO8K-nvigXSjy6HjKNFLJ5j9dkUJDvekjv7LwIggp8-_pXuJ1_iJUWzRr-aZlXaMuC1xI_0Elr4Azv7L-agwpeK3RuKkCwGKABX55g2lkkxIRic", "Content-Type:application/json"})

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
