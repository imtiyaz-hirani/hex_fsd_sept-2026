import { useEffect } from "react"
import { deleteTicket, getTickets } from "../../store/actions/CustomerAction"
import { useDispatch, useSelector } from "react-redux"

function TicketList() {
    const list = useSelector(state=>state.customerTickets)
    const dispatch = useDispatch()
    const onDelete = (id)=>{
        dispatch(deleteTicket(id))
    }
    return (
        <div>
            {
                list?.map((t, index)=>(
                    <div key={index}>
                        <p>{t.subject} &nbsp;&nbsp;&nbsp; 
                            <button onClick={()=>onDelete(t.ticketId)}>delete</button>
                        </p>
                    </div>
                ))
            }
        </div>
    )
}

export default TicketList