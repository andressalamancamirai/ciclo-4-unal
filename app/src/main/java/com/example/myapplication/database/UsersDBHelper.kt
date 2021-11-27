package com.example.myapplication.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UsersDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertUser(user:UserModel): Boolean{
        val db=writableDatabase
        val values = ContentValues()
        values.put(DBContract.UserEntry.COLUMN_USER_ID, user.userId)
        values.put(DBContract.UserEntry.COLUMN_AGE, user.age)
        values.put(DBContract.UserEntry.COLUMN_NAME, user.name)

        val newRowId = db.insert(DBContract.UserEntry.TABLE_NAME,null,values)
        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteUser(userid: String):Boolean{
        val db=writableDatabase
        val selection = DBContract.UserEntry.COLUMN_USER_ID + " LIKE ?"
        val selectionArgs = arrayOf(userid)
        db.delete(DBContract.UserEntry.TABLE_NAME,selection,selectionArgs)
        return true
    }

    fun readUser(userId: String): ArrayList<UserModel>{
        val users = ArrayList<UserModel> ()
        val db = writableDatabase
        var cursor:Cursor? = null
        try{
            cursor = db.rawQuery("SELECT * FROM "+DBContract.UserEntry.TABLE_NAME +
            " WHERE " + DBContract.UserEntry.COLUMN_USER_ID + "='" + userId + "'",null)
        } catch (e : SQLException){
            db.execSQL(SQL_CREATE_ENTRIES)
            return  ArrayList()
        }

        var name:String
        var age:String
        if(cursor!!.moveToFirst()){
            while(cursor.isAfterLast == false){
                name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_NAME))
                age = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_AGE))

                users.add(UserModel(userId,name,age))
                cursor.moveToNext()
            }
        }
        return users
    }

    fun readAllUsers():ArrayList<UserModel>{
        val users = ArrayList<UserModel> ()
        val db = writableDatabase
        var cursor:Cursor? = null
        try{
            cursor = db.rawQuery("select * from "+DBContract.UserEntry.TABLE_NAME,null)
        } catch (e : SQLException){
            db.execSQL(SQL_CREATE_ENTRIES)
            return  ArrayList()
        }

        var name:String
        var age:String
        var id:String
        if(cursor!!.moveToFirst()){
            while(cursor.isAfterLast == false){
                name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_NAME))
                age = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_AGE))
                id = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_ID))
                users.add(UserModel(id,name,age))
                cursor.moveToNext()
            }
        }
        return users
    }
    companion object{
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "FeedReader.db"

        private val SQL_CREATE_ENTRIES = "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME + " (" +
                DBContract.UserEntry.COLUMN_USER_ID + " TEXT PRIMARY KEY," +
                DBContract.UserEntry.COLUMN_NAME + " TEXT," +
                DBContract.UserEntry.COLUMN_AGE + " TEXT)"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXIST" + DBContract.UserEntry.TABLE_NAME

    }
}