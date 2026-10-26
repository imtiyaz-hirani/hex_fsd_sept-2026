import { useState } from "react"

function AddTicket(){
    const priorityValues = ['HIGH', 'MEDIUM', 'LOW']
    const [subject, setSubject] = useState("")
    const [issue, setIssue] = useState("")
    const [priority, setPriority] = useState("")

    const processInput =(e)=>{
        e.preventDefault() // U prevent the page refresh
        console.log('Inside the Fn....')
        console.log(subject)
        console.log(issue)
        console.log(priority)
    }
    return(
        <div>
           
            <div className="card">
                <div className="card-header">
                     Reading Input
                </div>
                <div className="card-body">
                    <form onSubmit={(e)=>processInput(e)}>
                        <div className="mb-4 mt-4">
                            <label>Enter Subject</label>
                            <input type="text" className="form-control" required="required" 
                                onChange={(e)=>setSubject(e.target.value)}/>
                        </div>
                         <div className="mb-4">
                            <label>Enter Issue:</label>
                            <textarea className="form-control" required="required" 
                                onChange={(e)=> setIssue(e.target.value)}/> 
                        </div>
                        <div className="mb-4">
                            <label>Enter Priority:</label>
                            <select className="form-control" required="required" onChange={(e)=>setPriority(e.target.value)}>
                              <option value="">----select priority----</option>
                            {
                                priorityValues.map((val,index)=>(
                                    <option key={index}>{val}</option>
                                ))
                            }
                            </select>
                        </div>
                        <div>
                             
                            <input type="submit" className="btn btn-info" value="Process Input" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default AddTicket
// input type="date" input type="radio" 