import { useState } from "react"
import courseData from "../data/courses"

function CourseList() {
    const [courses, setCourses] = useState(courseData)
    return (
        <div>
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Fee</th>
                        <th scope="col">Instructor Name</th>
                        <th scope="col">Category</th>  
                    </tr>
                </thead>
                <tbody>
                    {
                        courses.map((c, index) => (
                            <tr key={index}>
                                <th scope="row">{index+1}</th>
                                <td>{c.name}</td>
                                <td>INR. {c.fee}</td>
                                <td>{c.instructorName}</td>
                                <td>{c.category}</td>
                            </tr>
                        ))
                    }


                </tbody>
            </table>
        </div>
    )
}

export default CourseList