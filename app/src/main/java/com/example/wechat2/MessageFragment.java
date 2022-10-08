package com.example.wechat2;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int DATASET_COUNT;
    private String[] name;
    private String[] content;
    private String[] time;
    private int[] picture;

    public MessageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        initDataset();
        Toolbar toolbar = (Toolbar)(getActivity().findViewById(R.id.toolbar));
        toolbar.setTitle("微信("+DATASET_COUNT+")");

        ListView listView = (ListView) getActivity().findViewById(R.id.contactList);
        // 设置监听器进行页面跳转
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity().getApplicationContext(),ChitInterfaceActivity.class);

                intent.putExtra("picture",picture[i]);
                intent.putExtra("name",name[i]);
                intent.putExtra("time",time[i]);
                intent.putExtra("content",content[i]);
                intent.putExtra("index",i);

                startActivityForResult(intent,i);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    // 获取数据资源并设置adapter
    private void initDataset(){

        name = getResources().getStringArray(R.array.contact_name);
        time = getResources().getStringArray(R.array.contact_last_time);
        content = getResources().getStringArray(R.array.contact_last_content);
        Context ctx = getActivity().getBaseContext();
        TypedArray pictureArray = ctx.getResources().obtainTypedArray(R.array.contact_head_portrait);
        //int resId;
        System.out.println(Arrays.toString(name));
        System.out.println(Arrays.toString(time));
        System.out.println(Arrays.toString(content));


        DATASET_COUNT = name.length;
        picture = new int[DATASET_COUNT]; // 不分配出现null

        // 使用this.setTitle
        getActivity().setTitle("微信("+DATASET_COUNT+")");


        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for(int i=0;i<DATASET_COUNT;i++){
            Map<String,Object> item = new HashMap<String,Object>();
            picture[i]=pictureArray.getResourceId(i,0);
            System.out.println("picture------------"+picture[i]);
            item.put("head_portrait",picture[i]);
            item.put("contact_name",name[i]);
            item.put("contact_last_time",time[i]);
            item.put("contact_last_content",content[i]);
            String[] receivers =  getResources().getResourceName(picture[i]).split("/");
            item.put("contact_receiver", receivers[1]);

            listItems.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(),listItems,R.layout.contact_row_item,
                new String[]{"head_portrait","contact_name","contact_last_time","contact_last_content"},
                new int[]{R.id.head_portrait,R.id.contact_name,R.id.contact_last_time,R.id.contact_last_content});
        ListView listView = (ListView) getActivity().findViewById(R.id.contactList);
        listView.setAdapter(adapter);

        Log.i("Init","Initialize data success");
    }
}