package org.devinbutts.VenPalMo.dao;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AccountDAOTest {

    @ParameterizedTest()
    @CsvFileSource(resources = {"/test/accounts.csv"},delimiter = ',',numLinesToSkip = 1)
    protected void getAllAccounts(Integer id, String name, String instructor){
            

//        Course expectedCourse = new Course();
//        expectedCourse.setCId(id);
//        expectedCourse.setCName(name);
//        expectedCourse.setCInstructorName(instructor);
//
//        CourseService courseService = new CourseService();
//
//        List<Course> testCourses = courseService.getAllCourses();
//
//        Course testCourse = new Course();
//
//        for (Course c : testCourses){
//            if(c.getCId()==expectedCourse.getCId()){
//                testCourse = c;
//            }
//        }
//
//        Assertions.assertEquals(expectedCourse,testCourse);
    }


}
