package com.example.zmy.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PageFragment2 extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static PageFragment2 newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment2 fragment = new PageFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        //设置adapter
        recyclerView.setAdapter(new MyAdapter());
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration();
        return view;
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(PageFragment2.this.getActivity());
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(10, 20, 10, 20);
            MyViewHolder holder = new MyViewHolder(textView);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            holder.tv.setText("position = "+position);
        }

        @Override
        public int getItemCount() {
            return 40;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            public TextView tv;
            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView;
            }
        }
    }
}