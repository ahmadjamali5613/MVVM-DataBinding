package com.phoenixdevs.mvvm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;


import com.phoenixdevs.mvvm.databinding.AddBinding;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.BaseObservable;
import androidx.databinding.DataBindingUtil;


public class AddDialog extends BaseObservable {
    private static AddDialog instance;
    private Context context;
    private Button btnmap, btn_exit;
    private AlertDialog dialog;
    private LayoutInflater layoutInflater;
    private ClickListener clickListener;

    public AddDialog(Context context) {
        this.context = context;
    }


    public static AddDialog getInstance(Context context) {
        instance = new AddDialog(context);
        return instance;
    }



    public void show(ClickListener clickListener) {
        this.clickListener = clickListener;

        AddBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_add, null, false);
        binding.setAddviewmodel(this);
        View view = binding.getRoot();
        showDialog(view);


    }


    public void ButtonClick(String title,String dec) {
        clickListener.OnButtonClickListener(title,dec);
        dialog.dismiss();
    }




    private void showDialog(View view) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(view);
        alert.setCancelable(true);
        dialog = alert.create();
        dialog.show();
    }


    public interface  ClickListener{
        void OnButtonClickListener(String title, String dec);
    }
}
