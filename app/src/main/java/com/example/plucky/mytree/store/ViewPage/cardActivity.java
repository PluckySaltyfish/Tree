package com.example.plucky.mytree.store.ViewPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.store.RootDiagram;
import com.gu.library.OrientedViewPager;
import com.gu.library.transformer.VerticalStackTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nate on 2016/7/22.
 */
public class cardActivity extends AppCompatActivity {

    private OrientedViewPager mOrientedViewPager;
    private ContentFragmentAdapter mContentFragmentAdapter;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<RootDiagram> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        list=(List<RootDiagram>)getIntent().getSerializableExtra("list");
        int taskId=getIntent().getIntExtra("taskId",0);
        int length=list.size();
        mOrientedViewPager = (OrientedViewPager) findViewById(R.id.view_pager);

        //制造数据
        String title,meaning,content0,content1;
        for (int i = 0; i <length; i++) {
            RootDiagram page=list.get(i);
            String []text=page.getText();
            title=page.getTitle();
            meaning=page.getMeaning();
            content0=text[0];
            content1=text[1];

            Bundle mbundle=new Bundle();
            mbundle.putString("title",title);
            mbundle.putString("meaning",meaning);
            mbundle.putString("content0",content0);
            mbundle.putString("content1",content1);
            mbundle.putInt("num",i+1);
            mbundle.putInt("sum",length+1);
            mbundle.putInt("taskId",taskId);
            mFragments.add(CardFragment.newInstance(mbundle));
        }
        Bundle mbundle=new Bundle();
        title="";
        meaning="";
        content0="";
        content1="";
        mbundle.putString("title",title);
        mbundle.putString("meaning",meaning);
        mbundle.putString("content0",content0);
        mbundle.putString("content1",content1);
        mbundle.putInt("num",length+1);
        mbundle.putInt("sum",length+1);
        mbundle.putInt("taskId",taskId);
        mFragments.add(CardFragment.newInstance(mbundle));

        mContentFragmentAdapter = new
                ContentFragmentAdapter(getSupportFragmentManager(), mFragments);
        //设置viewpager的方向为竖直
        mOrientedViewPager.setOrientation(OrientedViewPager.Orientation.VERTICAL);
        //设置limit
        mOrientedViewPager.setOffscreenPageLimit(4);
        //设置transformer
        mOrientedViewPager.setPageTransformer(true, new VerticalStackTransformer(getApplicationContext()));
        mOrientedViewPager.setAdapter(mContentFragmentAdapter);

    }
}
