import { useState } from "react"
import { addTicket } from "../../store/actions/CustomerAction"
import { useDispatch } from "react-redux"

function AddTicket() {
   const [subject, setSubject] = useState("")
   const [issue, setIssue] = useState("")
   const [priority, setPriority] = useState("")

   const dispatch = useDispatch()

   const add = (e)=>{
        e.preventDefault()
        let obj = {
            'subject': subject ,
            'issue': issue,
            'priority': priority
        }
        dispatch(addTicket(obj))
        
   }
    return (
        <div className="container">
            <h3>Raise new Ticket</h3>
            <form onSubmit={(e)=> add(e)}>
                <div className="row">
                    <div className="col-md-6">
                        <div className="mt-4">
                            <label>Subject</label>
                            <input type="text" className="form-control" 
                            onChange={(e)=> setSubject(e.target.value)}/>
                        </div>
                        <div className="mt-4">
                            <label>Issue</label>
                            <input type="text" className="form-control" 
                             onChange={(e)=> setIssue(e.target.value)}/>
                        </div>
                        <div className="mt-4">
                            <label>Priority</label>
                            <input type="text" className="form-control" 
                             onChange={(e)=> setPriority(e.target.value)}/>
                        </div>
                        <div className="mt-4">
                             
                            <button type="submit" className="btn btn-primary" >
                            +Raise Ticket
                            </button>
                        </div>
                    </div>

                </div>
            </form>
        </div>
    )
}

export default AddTicket