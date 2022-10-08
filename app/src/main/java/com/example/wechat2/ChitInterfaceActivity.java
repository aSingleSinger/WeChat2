package com.example.wechat2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.adapter.CustomAdapter;

public class ChitInterfaceActivity extends AppCompatActivity {

    // 进入页面后接收的联系人数据
    private int receiverPicture;
    private String chitName;
    private String chitTime;
    private String chitContent;
    private String contact_receiver;
    private int index; // 联系人下标

    private int senderPicture;
    private int[] contentChitWith = {R.array.chit_content_with_girl,R.array.chit_content_with_girl2};
    private int[] timeChitWith = {R.array.chit_time_with_girl,R.array.chit_time_with_girl2};
    private int[] booleanSendWith = {R.array.chit_is_receiver_with_girl,R.array.chit_is_receiver_with_girl2};

    // RecyclerView
    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] contents;
    protected Boolean[] receiver;
    protected String[] time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chit_interface);

        Intent intent = getIntent();
        receiverPicture = intent.getIntExtra("picture",0);
        chitName = intent.getStringExtra("name");
        chitContent = intent.getStringExtra("content");
        chitTime = intent.getStringExtra("time");
        contact_receiver = intent.getStringExtra("contact_receiver");
        index = intent.getIntExtra("index",0);
        this.setTitle(chitName);

        initDataset();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        System.out.println("receiver:"+receiverPicture+"senderPicture:"+senderPicture);
        mAdapter = new CustomAdapter(contents,receiver,time,receiverPicture,senderPicture);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initDataset(){

        contents = getResources().getStringArray(contentChitWith[index]);
        String[] arr = getResources().getStringArray(booleanSendWith[index]);
        receiver = new Boolean[arr.length];
        for(int i=0;i<arr.length;i++){
            if(arr[i].equals("false")){
                receiver[i] = false;
            }else{
                receiver[i] = true;
            }
        }
        time = getResources().getStringArray(timeChitWith[index]);
        senderPicture = R.drawable.boy;

    }

    public void clickToBack(View view){
        Intent intent = new Intent();
        intent.putExtra("","");

        this.setResult(1,intent);
        this.finish();
    }
}