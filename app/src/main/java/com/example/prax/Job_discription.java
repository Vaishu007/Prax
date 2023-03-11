package com.example.prax;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.prax.Json.Companyjson;
import com.example.prax.Json.Courseins;
import com.example.prax.Table.Company;
import com.example.prax.Table.Coursein;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Job_discription extends AppCompatActivity {

    String id;
    String cid,comid;
    TextView conam,coadd,cocon,comail,covac,cocourse,codis;
    CircleImageView coimg;
    ArrayList<Company> arrayList;
    ArrayList<Coursein> arrayList1;
    Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_discription);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        conam = findViewById(R.id.comname);
        coadd = findViewById(R.id.comadd);
        cocon = findViewById(R.id.comcontact);
        comail = findViewById(R.id.comemail);
        coimg = findViewById(R.id.coimg);
        covac = findViewById(R.id.comvac);
        cocourse = findViewById(R.id.comcourse);
        codis = findViewById(R.id.comdis);
        apply=findViewById(R.id.jobapply);
        Apiinterface apiinterface = Apiclient.praxware().create(Apiinterface.class);
        Call<Companyjson> call = apiinterface.companyidshow(id);
        call.enqueue(new Callback<Companyjson>() {
            @Override
            public void onResponse(Call<Companyjson> call, Response<Companyjson> response) {
                arrayList = (ArrayList<Company>) response.body().getCompanyjson();
                conam.setText(arrayList.get(0).getComName());
                coadd.setText(arrayList.get(0).getComAddress());
                cocon.setText(arrayList.get(0).getComContact());
                comail.setText(arrayList.get(0).getComEmail());
                covac.setText(arrayList.get(0).getComVacancy());
                codis.setText(arrayList.get(0).getComDetail());
                Picasso.get().load(arrayList.get(0).getComImage()).into(coimg);
                cid = arrayList.get(0).getcId();
                comid=arrayList.get(0).getComId();
                Cname(cid);

            }

            @Override
            public void onFailure(Call<Companyjson> call, Throwable t) {

            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Job_discription.this,Resumeupload.class);
                intent1.putExtra("comid",comid);
                startActivity(intent1);
            }
        });

    }

    public void Cname(String cid){
        Apiinterface apiinterface = Apiclient.praxware().create(Apiinterface.class);
        Call<Courseins> call1=apiinterface.CourseDiscription(cid);
            call1.enqueue(new Callback<Courseins>() {
                @Override
                public void onResponse(Call<Courseins> call, Response<Courseins> response) {
                    arrayList1= (ArrayList<Coursein>) response.body().getCourseins();
                    cocourse.setText(arrayList1.get(0).getcName());
                }

                @Override
                public void onFailure(Call<Courseins> call, Throwable t) {

                }
            });



    }
}