export const displayAllCourses = (courses) => {
    courses.forEach((course) => console.log(course));
}

export const getById = (courses, id) => courses.find(c => c.id === id)

export const getCoursesByInstructor = (courses, iName) =>
    courses.filter(c => c.instructorName === iName)

export const totalFee = (courses) =>
    courses.reduce((sum, c) => sum + c.fee, 0)

export const countCoursesByCategory = (courses, category) =>
    courses.filter(c => c.category === category)
        .reduce((count, e) => count + 1, 0)

export const deleteCourse = (courses, id) => courses.filter(c => c.id !== id)

export const sortByFee = (courses, direction) => direction === 'ASC' ?
    courses.sort((c1, c2) => c1.fee - c2.fee) :
    direction === 'DESC' ? courses.sort((c1, c2) => c2.fee - c1.fee) :
        []


