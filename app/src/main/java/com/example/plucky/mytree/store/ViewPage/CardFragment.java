package com.example.plucky.mytree.store.ViewPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.connection.RemoteData;


/**
 * Created by Nate on 2016/7/22.
 */
public class CardFragment extends Fragment {
    private static final String INDEX_KEY = "index_key";
    int num=0,sum=0,taskId=0;
    private Button buttonEnd;
    private TextView mwordTitle,mmeaning,mcontent0,mcontent1;

    private RemoteData mRemoteData=new RemoteData(getActivity());
    public static CardFragment newInstance(Bundle mBundle) {
        CardFragment fragment = new CardFragment();
        fragment.setArguments(mBundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final Bundle bundle = getArguments();
            num = bundle.getInt("num");
            sum = bundle.getInt("sum");
            taskId=bundle.getInt("taskId");
            if (num != sum) {
                final View v = inflater.inflate(R.layout.card, container, false);
                mwordTitle = (TextView) v.findViewById(R.id.word_title);
                mmeaning = (TextView) v.findViewById(R.id.meaning);
                mcontent0 = (TextView) v.findViewById(R.id.content0);
                mcontent1 = (TextView) v.findViewById(R.id.content1);

                mwordTitle.setText(bundle.getString("title"));
                mmeaning.setText(bundle.getString("meaning"));
                mcontent0.setText(bundle.getString("content0"));
                mcontent1.setText(bundle.getString("content1"));
                return v;
            } else {
                final View v = inflater.inflate(R.layout.card_end, container, false);
                buttonEnd = (Button) v.findViewById(R.id.buttonend);
                buttonEnd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mRemoteData.setStatus(taskId,2);
                        getActivity().finish();
                    }
                });
                return v;
            }
        }
}