import { useEffect } from "react"
import { getTickets } from "../../store/actions/CustomerAction"
import { useDispatch, useSelector } from "react-redux"

function TicketList() {
    const list = useSelector(state=>state.customerTickets)
    return (
        <div>
            {
                list?.map((t, index)=>(
                    <div key={index}>
                        <p>{t.subject}</p>
                    </div>
                ))
            }
        </div>
    )
}

export default TicketList