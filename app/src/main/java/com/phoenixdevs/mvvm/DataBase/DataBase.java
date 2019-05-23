package com.phoenixdevs.mvvm.DataBase;

import android.content.Context;
import android.os.AsyncTask;


import com.phoenixdevs.mvvm.DataBase.Note.NoteDao;
import com.phoenixdevs.mvvm.DataBase.Note.NoteModel;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {NoteModel.class},version = 1)
public abstract class DataBase extends RoomDatabase {

    private static DataBase instance;

    public abstract NoteDao noteDao();

    public static synchronized DataBase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context, DataBase.class,"not_database")
                    .addCallback(roomCallback)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }



    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new PopulateDbAsynTask(instance).execute();
            super.onCreate(db);
        }
    };





    private static Callback roomCallback2 = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };







    private static class PopulateDbAsynTask extends AsyncTask<Void,Void,Void> {
        private NoteDao noteDao;

        public PopulateDbAsynTask(DataBase noteDataBase) {
            noteDao = noteDataBase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

                noteDao.insert(new NoteModel("RecyclerView","DataBinding","https://www.theindianwire.com/wp-content/uploads/2018/10/Developer-Support-for-Huawei-Mate-SE.png"));
                noteDao.insert(new NoteModel("BaseObservable","ViewModel","https://www.theindianwire.com/wp-content/uploads/2018/10/Developer-Support-for-Huawei-Mate-SE.png"));
                noteDao.insert(new NoteModel("Sqlite","RoomDataBase","https://www.theindianwire.com/wp-content/uploads/2018/10/Developer-Support-for-Huawei-Mate-SE.png"));
                noteDao.insert(new NoteModel("DialogBinding","BaseObservable","https://www.theindianwire.com/wp-content/uploads/2018/10/Developer-Support-for-Huawei-Mate-SE.png"));
                noteDao.insert(new NoteModel("Glide","Load Images","https://www.theindianwire.com/wp-content/uploads/2018/10/Developer-Support-for-Huawei-Mate-SE.png"));

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
