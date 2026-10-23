import courseData from "./courses.mjs";
import { countCoursesByCategory, deleteCourse, displayAllCourses, getById, getCoursesByInstructor, sortByFee, totalFee } from "./courseUtil.mjs";

// import the courses 
const courses = courseData;
// clone 
const coursesClone = [...courses]

console.log('-------All Courses--------')
displayAllCourses(courses)

console.log('-------Find by id--------')
console.log(getById(courses, 2))

console.log('-------Find by instrucutor name--------')
console.log(getCoursesByInstructor(courses, 'Rajesh Kumar'))

console.log('-------count courses by category------')
console.log(`Number of courses for web dev category ${countCoursesByCategory(courses, 'Web Development')
    }`)

console.log('-------total fee ------')
console.log(`Fee for all courses ${totalFee(courses)}`)

console.log('-------delete course------')
const updatedCoursesList = [...deleteCourse(courses, 1)]

console.log('-------All Courses after delete--------')
displayAllCourses(updatedCoursesList)

console.log('-------Sort by fee ASC/DESC--------')
console.log(sortByFee(courses, 'DESC'))
