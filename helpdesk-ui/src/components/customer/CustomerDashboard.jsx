import { useNavigate } from "react-router"
import TicketList from "./TicketList"
import Stat from "./Stat"
import { useEffect } from "react"
import { useDispatch } from "react-redux"
import { getTickets } from "../../store/actions/CustomerAction"
import AddTicket from "./AddTicket"
import Upload from "./Upload"

function CustomerDashboard(){
    const navigate = useNavigate()
    const dispatch = useDispatch()
    const logout = () => {
        localStorage.clear()
        navigate("/")
    }

     useEffect(() => {
        dispatch(getTickets(localStorage.getItem('username')))
    }, [])

    return(
        <div>
            <h1>CustomerDashboard</h1>
             <button className="btn btn-secondary" onClick={() => logout()}>Logout</button>
             <hr />
             <Stat />
             <hr />
             <AddTicket />
             <hr />
             <TicketList />
             <hr />
             <Upload />
        </div>
    )
}

export default CustomerDashboard