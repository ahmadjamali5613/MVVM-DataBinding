package com.phoenixdevs.mvvm.ui;


import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


import com.phoenixdevs.mvvm.AddDialog;
import com.phoenixdevs.mvvm.BR;
import com.phoenixdevs.mvvm.baseviewmodel.activities.ActivityViewModel;
import com.phoenixdevs.mvvm.database.note.NoteRepository;
import com.phoenixdevs.mvvm.database.note.GenericsInterface;
import com.phoenixdevs.mvvm.database.note.NoteModel;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainViewModel extends ActivityViewModel<MainActivity> {

    private static Context context;

    private List<NoteModel> allnote = new ArrayList<>();

    @Bindable
    public List<NoteModel> getAllnote() {
        return allnote;
    }

    public void setAllnote(List<NoteModel> allnote) {
        this.allnote = allnote;
        notifyPropertyChanged(BR.allnote);
    }


    public MainViewModel(MainActivity activity) {
        super(activity);
        context = activity;
        setAllnote(NoteRepository.getInstance(context).getAllnotes());
    }

    public void FabClick() {
        AddDialog.getInstance(context).show(new AddDialog.ClickListener() {
            @Override
            public void OnButtonClickListener(String title, String dec) {
                if (TextUtils.isEmpty(title) && TextUtils.isEmpty(dec))
                    Toast.makeText(activity, "title & decription is null", Toast.LENGTH_SHORT).show();
                else
                    NoteRepository.getInstance(getActivity()).insert2(new NoteModel(title, dec, "https://www.theindianwire.com/wp-content/uploads/2018/10/Developer-Support-for-Huawei-Mate-SE.png"), new GenericsInterface.callBackVar<List<NoteModel>>() {
                        @Override
                        public void onSuccess(List<NoteModel> var) {
                            setAllnote(var);
                        }
                    });
            }
        });
    }


    @BindingAdapter({"bind:setupRecyclerView"})
    public static void setupRecyclerView(RecyclerView view, List<NoteModel> arrayList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(new Adapter(arrayList));
    }

}