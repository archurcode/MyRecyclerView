package com.example.archur.myrecyclerview;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyItemClickListener,MyItemLongClickListener{

    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager mLayout;
    private MyAdapter myAdapter;

    private String[] myDataSet = {"hello0", "hello1", "hello2", "hello3", "hello4", "hello5", "hello6", "hello7", "hello8"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        myRecyclerView.setHasFixedSize(true);

        mLayout = new LinearLayoutManager(this);
//        mLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        myRecyclerView.setLayoutManager(mLayout);

        myAdapter = new MyAdapter(myDataSet);
        myAdapter.setOnItemClickListener(this);
        myAdapter.setOnItemLongClickListener(this);

        myRecyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onItemClick(View view, int postion) {
        Toast.makeText(this, "click "+((TextView)view.findViewById(R.id._tv)).getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int postion) {
        Toast.makeText(this, "long click "+((TextView)view.findViewById(R.id._tv)).getText(), Toast.LENGTH_SHORT).show();
    }
}

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] myDataSet;
    private MyItemClickListener mItemClickListener;
    private MyItemLongClickListener mItemLongClickListener;

    public MyAdapter(String[] myDataSet) {
        super();
        this.myDataSet = myDataSet;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);

        ViewHolder viewHolder = new ViewHolder(v,mItemClickListener,mItemLongClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView tv = (TextView) holder.mView.findViewById(R.id._tv);
        ImageView iv = (ImageView) holder.mView.findViewById(R.id._iv);

        tv.setText(myDataSet[position]);
        iv.setImageResource(R.drawable._ic);
    }

    @Override
    public int getItemCount() {
        return myDataSet.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        public View mView;
        private MyItemClickListener mListener;
        private MyItemLongClickListener mLongClickListener;

        public ViewHolder(View mView,MyItemClickListener listener,MyItemLongClickListener longClickListener) {
            super(mView);
            this.mView = mView;
            this.mListener = listener;
            this.mLongClickListener = longClickListener;
            mView.setOnClickListener(this);
            mView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v,getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(mLongClickListener != null){
                mLongClickListener.onItemLongClick(v, getPosition());
            }
            return true;
        }
    }

    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(MyItemLongClickListener listener){
        this.mItemLongClickListener = listener;
    }
}


//The specified child already has a parent. You must call removeView() on the child's parent first.

/**
 * RecyclerView
 * 1 define RecyclerView in xml
 * 2 set Layout in Activity
 * 3 set adapter for recyclerView,the adapter extends RecyclerView.Adapter
 * myadapter like:
 * <p/>
 * class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
 * public MyAdapter() {
 * super();
 * }
 *
 * @Override public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
 * return viewHolder;
 * }
 * @Override public void onBindViewHolder(ViewHolder holder, int position) {
 * }
 * @Override public int getItemCount() {
 * }
 * <p/>
 * public static class ViewHolder extends RecyclerView.ViewHolder {
 * public View mView;
 * <p/>
 * public ViewHolder(View mView) {
 * super(mView);
 * this.mView = mView;
 * }
 * }
 * }
 */
