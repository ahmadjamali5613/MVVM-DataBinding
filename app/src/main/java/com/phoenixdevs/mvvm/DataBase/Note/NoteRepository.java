package com.phoenixdevs.mvvm.DataBase.Note;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.phoenixdevs.mvvm.DataBase.DataBase;
import com.phoenixdevs.mvvm.NoteModel;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class NoteRepository {
    private static NoteRepository instance;
    private NoteDao noteDao;
    private List<NoteModel> allnotes;


    public static NoteRepository getInstance(Context context) {
        if (instance == null)
            instance = new NoteRepository(context);
        return instance;
    }

    private NoteRepository(final Context context) {
        DataBase dataBase = DataBase.getInstance(context);
        noteDao = dataBase.noteDao();
        allnotes = noteDao.getAllNotes();

    }

    public void insert(final NoteModel note) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.insert(note);
            }
        });
    }

    public void insert2(final NoteModel note, GenericsInterface.callBackVar<List<NoteModel>> callBackVar) {
        try {
            callBackVar.onSuccess(new InsertNoteAsynTask(noteDao).execute(note).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(final NoteModel note) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.update(note);
            }
        });
    }

    public void delete(final NoteModel note) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.delete(note);
            }
        });
    }

    public void deleteAllnote() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.deleteAllNote();
            }
        });

    }


    public List<NoteModel> getAllnotes() {
        try {
            return new GetNotesAsyncTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }






    @SuppressLint("StaticFieldLeak")
    private class GetNotesAsyncTask extends AsyncTask<Void, Void, List<NoteModel>> {
        @Override
        protected List<NoteModel> doInBackground(Void... voids) {
            return noteDao.getAllNotes();
        }
    }




    private static class InsertNoteAsynTask extends AsyncTask<NoteModel, Void, List<NoteModel>> {
        private NoteDao noteDao;
        public InsertNoteAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected List<NoteModel> doInBackground(NoteModel... notes) {
            noteDao.insert(notes[0]);
            return noteDao.getAllNotes();
        }
    }


}
