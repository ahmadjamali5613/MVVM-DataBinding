package com.phoenixdevs.mvvm;

import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.phoenixdevs.mvvm.R;

import java.io.Serializable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "not_pad")
public class NoteModel extends BaseObservable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String decription;
    private String img;


    public NoteModel() {

    }

    public NoteModel(String title, String decription, String img) {
        this.title = title;
        this.decription = decription;
        this.img = img;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDecription() {
        return decription;
    }
    public void setDecription(String decription) {
        this.decription = decription;
        notifyPropertyChanged(BR.decription);
    }


    @Bindable
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
        notifyPropertyChanged(BR.img);
    }



    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.iran)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(options)
                .into(view);
    }

}


