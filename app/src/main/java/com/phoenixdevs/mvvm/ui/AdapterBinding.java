package com.phoenixdevs.mvvm.ui;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class AdapterBinding<T,D> extends RecyclerView.Adapter {
    private Context context;
    private List<T> arrayList;
    private LayoutInflater layoutInflater;


    public abstract int getLayoutResId();
    public abstract void onBindData(T model, int position, D dataBinding);
  //  public abstract void onItemClick(T model, int position);

    public AdapterBinding(List<T> arrayList) {
        this.arrayList = arrayList;
    }


    class CustomView extends RecyclerView.ViewHolder {
        protected D mDataBinding;

        public CustomView(ViewDataBinding binding) {
            super(binding.getRoot());
            mDataBinding = (D) binding;
        }

    }


    @Override
    public CustomView onCreateViewHolder(final ViewGroup parent, int viewType) {

        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        final ViewDataBinding dataBinding = DataBindingUtil.inflate(layoutInflater, getLayoutResId(), parent, false);
        context = parent.getContext();
        return new CustomView(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        onBindData(arrayList.get(position), position, ((CustomView) holder).mDataBinding);


       /* ((ViewDataBinding) ((CustomView) holder).mDataBinding).getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(arrayList.get(position), position);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public void addItem(T model, RecyclerView view) {
        arrayList.add(model);
        //this.notifyDataSetChanged();
        if (arrayList.size() > 1)
            view.scrollToPosition(arrayList.size() - 1);
    }

    public void addItems(List<T> arrayList,RecyclerView view) {
        this.arrayList = arrayList;
        if (arrayList.size() > 1)
            view.scrollToPosition(arrayList.size() - 1);
        //this.notifyDataSetChanged();

    }



    public T getItem(int position) {
        return arrayList.get(position);
    }

    public Context getContext() {
        return context;
    }

    public void removeAt(int position) {
        arrayList.remove(position);
        notifyItemRemoved(position);
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                notifydata(arrayList);
            }
        }, 500);*/

    }

    private void notifydata(List<T> list) {
        if (arrayList != null) {
            arrayList.clear();
            arrayList.addAll(list);

        } else {
            arrayList = list;
        }
        notifyDataSetChanged();
    }


    public interface ClickListeners {
        void onclickListener();
        void onClickButtonListener();
    }
}

