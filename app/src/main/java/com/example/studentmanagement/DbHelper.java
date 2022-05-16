package com.example.studentmanagement;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;


public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "students_management.db";
    private static final int DB_VERSION  = 1;



    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME,null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE students(student_regno INT PRIMARY KEY, student_name TEXT UNIQUE NOT NULL,student_address TEXT NOT NULL, student_email TEXT UNIQUE NOT NULL,student_password TEXT) ");
        db.execSQL("CREATE TABLE admins(admin_email TEXT PRIMARY KEY  UNIQUE NOT NULL, admin_name TEXT UNIQUE NOT NULL, admin_password TEXT NOT NULL)");
        db.execSQL("CREATE TABLE courses(course_name TEXT PRIMARY KEY  UNIQUE NOT NULL, course_description TEXT UNIQUE NOT NULL,course_period TEXT NOT NULL)");
        db.execSQL("CREATE TABLE course_units(" +
                "unit_name TEXT PRIMARY KEY  UNIQUE NOT NULL," +
                "teacher TEXT UNIQUE NOT NULL," +
                "course_id INTEGER  NOT NULL," +
                "students TEXT  NOT NULL," +
                "FOREIGN KEY(course_id) REFERENCES courses(id)," +
                " FOREIGN KEY(students) REFERENCES students(student_regno))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS students");
        db.execSQL("DROP TABLE IF EXISTS admins");
        db.execSQL("DROP TABLE IF EXISTS courses");
        onCreate(db);

    }
    //inserting  student data
    public Boolean RegisterStudent(String student_regno,String student_name,String student_email,String student_address,String student_password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("student_regno", student_regno);
        cv.put("student_name", student_name);
        cv.put("student_email",student_email);
        cv.put("student_address",student_address);
        cv.put("student_password",student_password);

        long result = db.insert("students",null,cv);
        //testing the insertion
        if(result==-1){
            return  false;
        }else {
            return true;
        }

    }

    //updating  student data
//    public Boolean UpdateStudent(String student_regno,String student_email,String student_address,String student_password){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("student_regno", student_regno);
//        cv.put("student_email",student_email);
//        cv.put("student_address",student_address);
//        cv.put("student_password",student_password);
//        Cursor cusrsor = db.rawQuery("SELECT  * FROM students WHERE student_regno = ?", new String[] {student_regno} );
//
//        long result = db.insert("students",null,cv);
//        //testing the insertion
//        if(result==-1){
//            return  false;
//        }else {
//            return true;
//        }
//
//    }
////new admins
    public Boolean RegisterAmin(String admin_email,String admin_name,String admin_password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("admin_name", admin_name);
        cv.put("admin_email",admin_email);
        cv.put("admin_password",admin_password);

        long result = db.insert("admins",null,cv);
        //testing the insertion
        if(result==-1){
            return  false;
        }else {
            return true;
        }

    }

    //new courses
    public Boolean AminAddNewCourse(String course_name,String course_description,String course_period){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("course_name", course_name);
        cv.put("course_description",course_description);
        cv.put("course_period",course_period);

        long result = db.insert("courses",null,cv);
        //testing the insertion
        if(result==-1){
            return  false;
        }else {
            return true;
        }

    }
    //checking if course name  exists
    public Boolean checkCourseName(String course_name){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cusrsor = db.rawQuery("SELECT  * FROM courses WHERE course_name = ?", new String[] {course_name} );
        if(cusrsor.getCount()>0){
            return  true;
        }
        else{
            return false;
        }

    }

    //checking if course description exists
    public Boolean checkCourseDescription(String course_description){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cusrsor = db.rawQuery("SELECT  * FROM courses WHERE course_description = ?", new String[] {course_description} );
        if(cusrsor.getCount()>0){
            return  true;
        }
        else{
            return false;
        }

    }

    //checking if admin email  exists
    public Boolean checkAminEmail(String admin_email){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cusrsor = db.rawQuery("SELECT  * FROM admins WHERE admin_email = ?", new String[] {admin_email} );
        if(cusrsor.getCount()>0){
            return  true;
        }
        else{
            return false;
        }

    }

    //checking if admin name  exists
    public Boolean checkAminName(String admin_name){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cusrsor = db.rawQuery("SELECT  * FROM admins WHERE admin_name = ?", new String[] {admin_name} );
        if(cusrsor.getCount()>0){
            return  true;
        }
        else{
            return false;
        }

    }
    //checking if admin email and password exists
    public Boolean checkAdminLogin(String admin_email,String admin_password){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cusrsor = db.rawQuery("SELECT  * FROM admins WHERE admin_email = ? AND  admin_password = ?", new String[] {admin_email,admin_password} );
        if(cusrsor.getCount()>0){
            return  true;
        }
        else{
            return false;
        }

    }

    //checking if student registration number exists
    public Boolean checkStudentReg(String student_regno){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cusrsor = db.rawQuery("SELECT  * FROM students WHERE student_regno = ?", new String[] {student_regno} );
        if(cusrsor.getCount()>0){
            return  true;
        }
        else{
            return false;
        }

    }

    //checking if student name exists
    public Boolean checkStudentName(String student_name){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cusrsor = db.rawQuery("SELECT  * FROM students WHERE student_name = ?", new String[] {student_name} );
        if(cusrsor.getCount()>0){
            return  true;
        }
        else{
            return false;
        }

    }

    //checking if student email exists
    public Boolean checkStudentEmail(String student_email){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cusrsor = db.rawQuery("SELECT  * FROM students WHERE student_email = ?", new String[] {student_email} );
        if(cusrsor.getCount()>0){
            return  true;
        }
        else{
            return false;
        }

    }

    //checking if student regno and password exists
    public Boolean checkStudentLogin(String student_regno,String student_password){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cusrsor = db.rawQuery("SELECT  * FROM students WHERE student_regno = ? AND  student_password = ?", new String[] {student_regno,student_password} );
        if(cusrsor.getCount()>0){
            return  true;
        }
        else{
            return false;
        }

    }
//retrieve all students
//    public Cursor showStudents()
//    {
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        String query = "select * from "+ "students";
//        Cursor studentList = sqLiteDatabase.rawQuery(query,null);
//        return studentList;
//    }

//student list
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getStudents(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> studentList = new ArrayList<>();
        String query = "SELECT student_regno,student_name, student_email, student_address FROM "+ "students";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> student = new HashMap<>();
            student.put("regno",cursor.getString(cursor.getColumnIndex("student_regno")));
            student.put("name",cursor.getString(cursor.getColumnIndex("student_name")));
            student.put("email",cursor.getString(cursor.getColumnIndex("student_email")));
            student.put("address",cursor.getString(cursor.getColumnIndex("student_address")));
            studentList.add(student);
        }
        return  studentList;
    }
    //course list
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getCourses(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> courseList = new ArrayList<>();
        String query = "SELECT course_name, course_description, course_period FROM "+ "courses";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> course = new HashMap<>();
            course.put("name",cursor.getString(cursor.getColumnIndex("course_name")));
            course.put("description",cursor.getString(cursor.getColumnIndex("course_description")));
            course.put("period",cursor.getString(cursor.getColumnIndex("course_period")));
            courseList.add(course);
        }
        return  courseList;
    }
}
