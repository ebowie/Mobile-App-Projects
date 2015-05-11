package com.example.cnntech;

import java.util.List;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataManager {
	Context mContext;
	DatabaseHelper dbOpenHelper;
	SQLiteDatabase db;
	NoteDAO noteDao;
	
	public DataManager(Context mContext){
		this.mContext = mContext;
		dbOpenHelper = new DatabaseHelper(mContext);
		db = dbOpenHelper.getWritableDatabase();
		noteDao = new NoteDAO(db);
	}
	
	public void close(){
		db.close();
	}
	
	public long saveNote(Entry note){
		return noteDao.save(note);
	}
	
	public boolean updateNote(Entry note){
		return noteDao.update(note);
	}
	
	public boolean deleteNote(Entry note){
		return noteDao.delete(note);
	}
	
	public Entry getNote(long id){
		return noteDao.get(id);
	}
	
	public List<Entry> getAllNotes(){
		return noteDao.getAll();
	}
}
