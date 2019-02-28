package com.phoenixdevs.mvvm.DataBase.Note;


import com.phoenixdevs.mvvm.NoteModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDao {

    @Insert
    void insert(NoteModel note);

    @Update
    void update(NoteModel note);

    @Delete
    void delete(NoteModel note);

    @Query("DELETE FROM not_pad")
    void deleteAllNote();

    @Query("SELECT * FROM not_pad ORDER BY id DESC")
    List<NoteModel> getAllNotes();

}
