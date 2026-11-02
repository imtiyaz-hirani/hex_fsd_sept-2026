import { useEffect } from "react"
import { deleteTicket, getTickets } from "../../store/actions/CustomerAction"
import { useDispatch, useSelector } from "react-redux"

function TicketList() {
    const list = useSelector(state => state.customerTickets)
    const dispatch = useDispatch()
    const onDelete = (id) => {
        dispatch(deleteTicket(id))
    }
    return (
        <div>
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">subject</th>
                        <th scope="col">createdAt</th>
                        <th scope="col">priority</th>
                        <th scope="col">status</th>
                        <th scope="col">Assigned To</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>

                    {
                        list?.map((t, index) => (
                            <tr key={index}>
                                <th scope="row">{index + 1}</th>
                                <td>{t.subject}</td>
                                <td>{t.createdAt.split("T")[0]}</td>
                                <td>{t.priority}</td>
                                <td>{t.status}</td>
                                <td>{t.executiveName === null ? "Not assigned yet" : t.executiveName}</td>
                                <button onClick={() => onDelete(t.ticketId)}>delete</button>
                            </tr>

                        ))
                    }

                </tbody>
            </table>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="#">Previous</a></li>

                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                </ul>
            </nav>
        </div >



    )
}

export default TicketList