package com.djj.hiot.http;

import com.djj.hiot.entity.HolderDeviceEntity;
import com.djj.hiot.entity.LoginEntity;
import com.djj.hiot.entity.RegisterEntity;
import com.djj.hiot.entity.UserEntity;


import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

public interface HttpService {
//    String BASE_URL = "http://localhost";
//    String IMAGE_BASE_URL = "http://192.168.134.143/DJJ";//图片URL前缀

    //String base = "http://192.168.10.106/hiot";
//    String base = "http://10.10.16.15/hiot";
//    String base = "http://192.168.9.102/hiot";
//    String base = "http://192.168.10.106/hiot";
    // 本地服务器
//    String base = "http://192.168.1.199:8088/hiot";
    String base = "http://114.115.179.78:8888/hiot";


    String BASE_URL = base + "/";
    String IMAGE_BASE_URL = base;//图片URL前缀

    //登录
    @FormUrlEncoded
    @POST("auth/login")
    Observable<HttpResult<LoginEntity>> login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("loginCode") String loginCode
    );

    //注册
    @FormUrlEncoded
    @POST("user/register")
    Observable<HttpResult<RegisterEntity>> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("userType") String userType
    );

    @GET("user")
    Observable<HttpResult<UserEntity>> getUserInfo(
            @Header("Authorization") String Authorization
    );

    @POST("auth/logout")
    Observable<HttpResult> logout(
            @Header("Authorization") String Authorization
    );

    //上传头像
    @POST("user/img")
    @Multipart
    Observable<HttpResult> uploadFile(
            @Part MultipartBody.Part file,
            @Header("Authorization") String authorization
    );

    //查询用户绑定的设备
    //bonding 绑定：1绑定 0未绑定
    @GET("holder/user")
    Observable<HttpResult<List<HolderDeviceEntity>>> getDeviceList(
            @Query("bonding") int bonding,
            @Header("Authorization") String authorization
    );
}
