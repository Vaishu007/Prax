package com.example.prax;

import com.example.prax.Json.Companyjson;
import com.example.prax.Json.Contentjson;
import com.example.prax.Json.Courseins;
import com.example.prax.Json.Examjson;
import com.example.prax.Json.Faqjson;
import com.example.prax.Json.Feedbackjson;
import com.example.prax.Json.JobapplyJson;
import com.example.prax.Json.Materialjson;
import com.example.prax.Json.Regisjson;
import com.example.prax.Table.Jobapply;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Apiinterface {
    @Multipart
    @POST("Userinsert.php")
    Call<Regisjson> userins(@Part MultipartBody.Part file,
                            @Part("u_name")RequestBody name,
                            @Part("u_gender")RequestBody gender,
                            @Part("u_dob")RequestBody dob,
                            @Part("u_email")RequestBody email,
                            @Part("u_contact")RequestBody contact,
                            @Part("u_password")RequestBody password,
                            @Part("u_address")RequestBody address,
                            @Part("u_college")RequestBody college,
                            @Part("u_caddress")RequestBody clgaddress,
                            @Part("u_branch")RequestBody branch,
                            @Part("u_sem")RequestBody sem,
                            @Part("c_id")RequestBody cid,
                            @Part("u_cduration")RequestBody duration);
    @FormUrlEncoded
    @POST("Coursetype.php")
    Call<Courseins> Coursetype(@Field("c_type")String type);

    @FormUrlEncoded
    @POST("Couseidshow.php")
    Call<Courseins> CourseDiscription(@Field("c_id")String id);

    @GET("Companyshow.php")
    Call<Companyjson> companyshow();

    @FormUrlEncoded
    @POST("Companyidshow.php")
    Call<Companyjson> companyidshow(@Field("com_id")String id);

    @FormUrlEncoded
    @POST("Userprofile.php")
    Call<Regisjson> profileshow(@Field("u_id")String id);

    @FormUrlEncoded
    @POST("Contentshow.php")
    Call<Contentjson> contentshow(@Field("con_type")String type);

    @GET("Examshow.php")
    Call<Examjson> examshow();


    @Multipart
    @POST("Userupdate.php")
    Call<ServerResponce> profileupdate(@Part MultipartBody.Part file,
                                       @Part("u_name")RequestBody name,
                                       @Part("u_email")RequestBody email,
                                       @Part("u_contact")RequestBody contact,
                                       @Part("u_password")RequestBody password,
                                       @Part("u_address")RequestBody address,
                                       @Part("u_id")RequestBody id);

    @Multipart
    @POST("Materialupload.php")
    Call<ServerResponce> fileupload(@Part MultipartBody.Part file,
                                    @Part("m_name")RequestBody name,
                                    @Part("m_branch")RequestBody branch,
                                    @Part("m_sem")RequestBody sem,
                                    @Part("m_subject")RequestBody subject,
                                    @Part("m_subcode")RequestBody code,
                                    @Part("m_type")RequestBody type);

    @FormUrlEncoded
    @POST("Materialshow.php")
    Call<Materialjson> materialshow(@Field("m_type")String type,
                                    @Field("m_branch")String branch);

    @GET("FAQshow.php")
    Call<Faqjson> faqshow();

    @FormUrlEncoded
    @POST("Userlogin.php")
    Call<Regisjson> user(@Field("u_email")String email,
                         @Field("u_password")String password);
    @FormUrlEncoded
    @POST("Feedbackins.php")
    Call<Feedbackjson> feedbackins(@Field("u_id")String id,
                                   @Field("f_description")String feedback,
                                   @Field("f_rat")String rating);
    @Multipart
    @POST("Applyjob.php")
    Call<JobapplyJson> apply(@Part MultipartBody.Part file,
                             @Part("com_id")RequestBody comid,
                             @Part("u_id")RequestBody uid);



}
