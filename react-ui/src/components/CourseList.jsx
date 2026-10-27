import { useState } from "react"
import courseData from "../data/courses"

function CourseList() {
    const [courses, setCourses] = useState(courseData)
    const [id,setId] = useState("")
    const [name,setName] = useState("")
    const [fee,setFee] = useState(0)
    const [instructorName,setInstructorName] = useState("")
    const [category,setCategory] = useState("")
    const [onAdd, setOnAdd] = useState(false)

    const onDelete = (courseId) => {
        setCourses([...courses.filter(c => c.id !== courseId)])
    }

    const addCourse = ()=>{
        let courseObj = {
            'id' : Math.ceil((Math.random()*10000)),
            'name': name,
            'fee' : fee,
            'instructorName' : instructorName,
            'category' : category
        }
        console.log(courseObj)
        // Add this object to the courses state 
        setCourses([courseObj,...courses] ) // [...courses, obj]

    }

    const onEdit = (course)=>{
        setId(course.id)
        setName(course.name)
        setFee(course.fee)
        setInstructorName(course.instructorName)
        setCategory(course.category)
        setOnAdd(false)
    }

    const editCourse = ()=>{
        let temp = [...courses]
        // remove the old value from the array 
        temp = temp.filter(c => c.id !== id)

        // add the new value 
        let newObj = {
             'id' : id,
            'name': name,
            'fee' : fee,
            'instructorName' : instructorName,
            'category' : category
        }
        setCourses([newObj,...temp] )
    }
    return (
        <div>
            <div className="card">
                <div className="card-header">
                    <button className="btn btn-primary" onClick={()=>{
                        setOnAdd(true)
                        setName("")
                        setFee(0)
                        setInstructorName("")
                        setCategory("")
                    }} data-bs-toggle="modal" data-bs-target="#staticBackdrop" 
                    >Add Course</button>
                </div>
                <div className="card-body">
                    <div className="card-title">
                        <h4>Courses List</h4>
                    </div>
                    <table className="table">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Name</th>
                                <th scope="col">Fee</th>
                                <th scope="col">Instructor Name</th>
                                <th scope="col">Category</th>
                                <th scope="col"> </th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                courses.map((c, index) => (
                                    <tr key={index}>
                                        <th scope="row">{index + 1}</th>
                                        <td>{c.name}</td>
                                        <td>INR. {c.fee}</td>
                                        <td>{c.instructorName}</td>
                                        <td>{c.category}</td>
                                        <td><a href="javascript:void(0)" onClick={(e) => e.preventDefault()}
                                            style={{ textDecoration: 'none', color: 'inherit' }}> <i className="bi bi-trash3" onClick={() => onDelete(c.id)}></i></a></td>
                                         <td><a href="javascript:void(0)" onClick={(e) => e.preventDefault()}
                                            style={{ textDecoration: 'none', color: 'inherit' }} ><i className="bi bi-pencil-square" onClick={()=> onEdit(c)} 
                                            data-bs-toggle="modal" data-bs-target="#staticBackdrop"></i></a></td>
                                    </tr>
                                ))
                            }


                        </tbody>
                    </table>
                </div>

            </div>

            {/* Modal / Dialog */}
            
            

           
            <div className="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false"   >
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h1 className="modal-title fs-5" id="staticBackdropLabel">Add Course</h1>
                            <button type="button" className="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div className="modal-body">
                           
                                <div>
                                    <label>Name</label>
                                    <input type="text" value={name} onChange={(e)=>setName(e.target.value)} 
                                    className="form-control" required="required" />
                                </div>
                                <div>
                                    <label>Fee in INR</label>
                                    <input type="number" value={fee} onChange={(e)=> setFee(e.target.value)} 
                                    className="form-control" required="required"/>
                                </div>
                                <div>
                                    <label>Instructor Name</label>
                                    <input type="text"  value={instructorName} onChange={(e)=>setInstructorName(e.target.value)} 
                                    className="form-control" required="required"/>
                                </div>
                                <div>
                                    <label>Category</label>
                                    <input type="text" value={category} onChange={(e)=>setCategory(e.target.value)} 
                                    className="form-control" required="required"/>
                                </div>
                            
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            {
                                onAdd ? 
                                <button type="button" className="btn btn-primary" onClick={()=>addCourse()} data-bs-dismiss="modal">Add</button>
                                :
                                <button type="button" className="btn btn-info" onClick={()=>editCourse()} data-bs-dismiss="modal">Edit</button>
                            }
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CourseList