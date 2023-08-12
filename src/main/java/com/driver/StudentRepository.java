package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String , Student> studentHashMap=new HashMap<>();
    HashMap<String, Teacher> teacherHashMap=new HashMap<>();
    HashMap<String, List<String>> teacherStudentHashMap=new HashMap<>();

    public void addStudent(Student student){
        String studentName=student.getName();
        studentHashMap.put(studentName, student);
    }
    public void addTeacher(Teacher teacher){
        String teacherName=teacher.getName();
        teacherHashMap.put(teacherName, teacher);
    }
    public void addStudentTeacherPair(String teacherName, String studentName){
        List<String> studentsList=new ArrayList<>();
//        if(teacherStudentHashMap.containsKey(teacherName)){
//            studentsList=teacherStudentHashMap.get(teacherName);
//            studentsList.add(studentName);
//            teacherStudentHashMap.put(teacherName, studentsList);
//        }
//        else {
//            studentsList.add(studentName);
//            teacherStudentHashMap.put(teacherName,studentsList);
//        }

        if(teacherStudentHashMap.containsKey(teacherName)){
            studentsList=teacherStudentHashMap.get(teacherName);
            studentsList.add(studentName);
        }
        else studentsList.add(studentName);

        teacherStudentHashMap.put(teacherName,studentsList);

    }



    public Student getStudentByName(String studentName){
        if(studentHashMap.containsKey(studentName)) return studentHashMap.get(studentName);
//        for(String sName:studentHashMap.keySet()){
//            if(sName.equals(studentName)) return studentHashMap.get(sName);
//        }

        return null;
    }
    public Teacher getTeacherByName(String teacherName){
        if(teacherHashMap.containsKey(teacherName)) return teacherHashMap.get(teacherName);

//        for(String tName:teacherHashMap.keySet()){
//            if(tName.equals(teacherName)) return teacherHashMap.get(tName);
//        }

        return null;
    }
    public List<String> getStudentsByTeacherName(String teacherName){
        if(teacherStudentHashMap.containsKey(teacherName)) return teacherStudentHashMap.get(teacherName);

        return null;
    }
    public List<String> getAllStudents(){
        List<String> students=new ArrayList<>();
//        for(String tName:teacherStudentHashMap.keySet()) {
//            List<String> sName=teacherStudentHashMap.get(tName);
//            for(String name:sName) students.add()
//        }

        for(String sName:studentHashMap.keySet()) students.add(sName);

        return students;
    }



    public void deleteTeacherByName(String teacherName){
        if(teacherStudentHashMap.containsKey(teacherName)){
            List<String> students=teacherStudentHashMap.get(teacherName);
            for(String sName:students) {
                if(studentHashMap.containsKey(sName)) studentHashMap.remove(sName);
            }
            teacherStudentHashMap.remove(teacherName);
        }

        if(teacherHashMap.containsKey(teacherName)) teacherHashMap.remove(teacherName);

    }
    public void deleteAllTeachers(){
        for(String tName:teacherHashMap.keySet()) {
            if(teacherStudentHashMap.containsKey(tName)){
                List<String> students=teacherStudentHashMap.get(tName);
                for(String sName:students) {
                    if(studentHashMap.containsKey(sName)) studentHashMap.remove(sName);
                }
                teacherStudentHashMap.remove(tName);
            }
            teacherHashMap.remove(tName);
        }

    }
}