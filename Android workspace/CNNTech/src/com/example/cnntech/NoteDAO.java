package com.example.cnntech;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NoteDAO { //Data Access Object (DAO)
	private SQLiteDatabase db;
	
	public NoteDAO(SQLiteDatabase db){
		this.db = db;
	}
	
	public long save(Entry note){
		ContentValues values = new ContentValues();
		values.put(NotesTable.NOTE_TITLE, note.getTitle());
		values.put(NotesTable.NOTE_URL, note.getUrl());
		values.put(NotesTable.NOTE_ID, note.getId());
		return db.insert(NotesTable.TABLE_NAME, null, values);
	}
	
	public boolean update(Entry note){
		ContentValues values = new ContentValues();
		values.put(NotesTable.NOTE_ID, note.getId());
		values.put(NotesTable.NOTE_TITLE, note.getTitle());
		values.put(NotesTable.NOTE_URL, note.getUrl());
		return db.update(NotesTable.TABLE_NAME, values, NotesTable.NOTE_ID+"="+ note.getId(), null) > 0;		
	}	
	
	
	public boolean delete(Entry note){
		return db.delete(NotesTable.TABLE_NAME, NotesTable.NOTE_ID+"="+note.getId(), null)>0;
	}
	
	public Entry get(long id){
		Entry note = null;
		Cursor c = db.query(true, NotesTable.TABLE_NAME, 
				new String[]{NotesTable.NOTE_ID, NotesTable.NOTE_TITLE, NotesTable.NOTE_URL}, 
				NotesTable.NOTE_ID+"="+ id, null, null, null, null, null);
		if(c != null && c.moveToFirst()){
			note = this.buildNoteFromCursor(c);			
		}	
		
		if(!c.isClosed()){
			c.close();
		}		
		return note;
	}
	
	public List<Entry> getAll(){
		List<Entry> list = new ArrayList<Entry>();
		Cursor c = db.query(NotesTable.TABLE_NAME, 
				new String[]{NotesTable.NOTE_ID, NotesTable.NOTE_TITLE, NotesTable.NOTE_URL}, 
				null, null, null, null, null);
		if(c != null && c.moveToFirst()){				
			do{
				Entry note = this.buildNoteFromCursor(c);
				if(note != null){
					list.add(note);
				}				
			} while(c.moveToNext());
			
			if(!c.isClosed()){
				c.close();
			}
		}
		return list;
	}
	
	private Entry buildNoteFromCursor(Cursor c){
		Entry note = null;		
		if(c != null){
			note = new Entry();
			note.setId(c.getLong(0));
			note.setTitle(c.getString(1));
			note.setUrl(c.getString(2));			
		}
		return note;
	}
}