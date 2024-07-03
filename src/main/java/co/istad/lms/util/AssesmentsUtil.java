package co.istad.lms.util;

import co.istad.lms.domain.Course;

public class AssesmentsUtil {

    public static String getGrade(Double score){

        if(score>=85.00&&score<=100){
            return "A";
        }else if(score>=80){
            return "B+";
        }else if(score>=70){
            return "B";
        }else if(score>=65){
            return "C+";
        }else if(score>=50){
            return "C";
        }else if(score>=45){
            return "D";
        }else{
            return "fail";
        }
    }
    public static Double getGpa(Double score){

        if(score>=85.00&&score<=100.00){
            return 4.0;
        }else if(score>=80.00){
            return 3.5;
        }else if(score>=70.00){
            return 3.0;
        }else if(score>=65.00){
            return 2.5;
        }else if(score>=50.00){
            return 2.0;
        }else if(score>=45.00){
            return 1.5;
        }else{
            return 0.0;
        }
    }

//    public static Double getAverageGpa(Set<Course>){
//
//        double totalGpa = 0.0;
//        int totalCredits = 0;
//
//        for (Course course : courses) {
//            totalGpa += course.getGpa() * course.getCredit();
//            totalCredits += course.getCredit();
//        }
//
//        return totalCredits == 0 ? 0 : totalGpa / totalCredits;
//    }
//
//    }
}
