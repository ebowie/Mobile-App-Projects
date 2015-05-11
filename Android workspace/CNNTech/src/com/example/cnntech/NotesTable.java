package com.example.cnntech;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NotesTable {
	static final String TABLE_NAME = "notes";
	static final String NOTE_TITLE = "title";
	static final String NOTE_URL = "url";
	static final String NOTE_ID = "id";
	
	static public void onCreate(SQLiteDatabase db){		
		StringBuilder sb = new StringBuilder();		
		sb.append("CREATE TABLE " + NotesTable.TABLE_NAME + " (");
		sb.append(NotesTable.NOTE_TITLE + " integer primary key autoincrement, ");
		sb.append(NotesTable.NOTE_URL + " text not null, ");
		sb.append(");");		
		try{
			db.execSQL(sb.toString());
		} catch (SQLException e){				
			e.printStackTrace();
		}
	}
	
	static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXISTS " + NotesTable.TABLE_NAME);
		NotesTable.onCreate(db);
	}	
}
