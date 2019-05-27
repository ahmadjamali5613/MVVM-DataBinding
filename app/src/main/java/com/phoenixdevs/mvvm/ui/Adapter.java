package com.phoenixdevs.mvvm.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.phoenixdevs.mvvm.database.note.NoteRepository;
import com.phoenixdevs.mvvm.database.note.NoteModel;
import com.phoenixdevs.mvvm.R;
import com.phoenixdevs.mvvm.databinding.NoteBinding;


import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.CustomView> {
    private Context context;
    private List<NoteModel> arrayList;
    private LayoutInflater layoutInflater;


    public Adapter(List<NoteModel> arrayList) {
        this.arrayList = arrayList;
    }


    class CustomView extends RecyclerView.ViewHolder {
        private NoteBinding noteBinding;

        public CustomView(NoteBinding noteBinding) {
            super(noteBinding.getRoot());
            this.noteBinding = noteBinding;
        }

        private void bind(final NoteModel noteVM) {
            this.noteBinding.setViewmodel(noteVM);
            noteBinding.executePendingBindings();
        }

    }


    @Override
    public CustomView onCreateViewHolder(final ViewGroup parent, int viewType) {

        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        final NoteBinding noteBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_note, parent, false);
        context = parent.getContext();
        return new CustomView(noteBinding);
    }

    @Override
    public void onBindViewHolder(CustomView holder, int position) {
        final NoteModel noteBinding = arrayList.get(position);

        holder.noteBinding.setPresenter(new ClickListeners() {
            @Override
            public void onclickListener() {
                Toast.makeText(context, noteBinding.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickButtonListener() {
                noteBinding.setTitle("UPDATED");
                NoteRepository.getInstance(context).update(noteBinding);
            }
        });

        holder.bind(noteBinding);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public interface ClickListeners {
        void onclickListener();
        void onClickButtonListener();
    }

}

