import axios from "axios";
import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router"
import { API_SERVER_URL_DEV } from "../../../config";

function AdminDashboard() {

    const navigate = useNavigate()
    let params = useParams();
    const [tickets, setTickets] = useState([])
    const [totalPages, setTotalPages] = useState(0)
    const [totalRecords, setTotalRecords] = useState(0)
    const userDetailsUrl = API_SERVER_URL_DEV + '/api/auth/user-details'

    const [page, setPage] = useState(0)
    const [size, setSize] = useState(5)
    const [currentPageSize, setCurrentPageSize] = useState(0)
    useEffect(() => {
        console.log('in use effect....')
        const urlToken = params.token
        const memToken = localStorage.getItem('token')
        if (urlToken !== memToken) {
            navigate("/no-access")
        }
        console.log(`tokens match: ${urlToken === memToken}`)

        const getUserDetails = async () => {
            console.log('in user details Fn')
            try {
                // verify this token by calling user-details API
                // Step 2. Fetch User Details using memToken
                const resp = await axios.get(userDetailsUrl, {
                    headers: { 'Authorization': 'Bearer ' + memToken }
                })
                console.log(resp)
                if (!(resp.data.role === 'ADMIN')) {
                    navigate("/no-access")
                }
            }
            catch (err) {
                navigate("/no-access")
            }
        }

        const getTickets = async () => {
            const response = await axios.get(
                `${API_SERVER_URL_DEV}/api/ticket/filter?page=${page}&size=${size}`,
                {
                    headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
                }
            )
            setTickets(response.data.list)
            setTotalPages(response.data.paginationInfo.totalPages)
            setTotalRecords(response.data.paginationInfo.totalRecords)
            setCurrentPageSize(response.data.paginationInfo.numRecordsInPage)
        }
        getUserDetails()
        getTickets()
    }, [params, page, size])
    const logout = () => {
        localStorage.clear()
        navigate("/")
    }


    const onPageChange =(val)=>{
        setPage(val)
    }
    return (
        <div>
            <h1>AdminDashboard</h1>
            <button className="btn btn-secondary" onClick={() => logout()}>Logout</button>

            <hr />
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">subject</th>
                        <th scope="col">createdAt</th>
                        <th scope="col">priority</th>
                        <th scope="col">status</th>
                        <th scope="col">Created By</th>
                        <th scope="col">Assigned To</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">
                            <input type="text" placeholder="search name.." className="form-control"/>
                        </th>
                        <th scope="col">
                            <input type="date" className="form-control"/>
                        </th>
                        <th scope="col">
                            <select className="form-control">
                                <option>--select priority--</option>
                                <option>HIGH</option>
                                <option>LOW</option>
                            </select>
                        </th>
                        <th scope="col">status</th>
                        <th scope="col">Created By</th>
                        <th scope="col">Assigned To</th>
                        <th scope="col"></th>
                    </tr>
                    {
                        tickets?.map((t, index) => (
                            <tr key={index}>
                                <th scope="row">{index + 1}</th>
                                <td>{t.subject}</td>
                                <td>{t.createdAt.split("T")[0]}</td>
                                <td>{t.priority}</td>
                                <td>{t.status}</td>
                                <td>{t.customerName}</td>
                                <td>{t.executiveName === null ? "Not assigned yet" : t.executiveName}</td>
                                <button onClick={() => onDelete(t.ticketId)}>delete</button>
                            </tr>

                        ))
                    }
                </tbody>
            </table>
            <nav aria-label="Page navigation example" style={{ "alignContent": 'center' }}>
                <ul class="pagination justify-content-center">
                    <li class="page-item"><button class="page-link" onClick={()=>setPage(page-1)} 
                        disabled={page === 0}>Previous</button></li>

                    {
                    Array.from({ length: totalPages }, (e, index) => (
                        <li key={index} className="page-item">
                        <button class="page-link" onClick={()=>onPageChange(index)}>{index+1}</button></li>
                    ))}

                    <li class="page-item"><button class="page-link" onClick={()=>setPage(page+1)}
                         disabled={page === (totalPages-1)} >Next</button></li>
                    
                </ul>
                <p>{`Page ${page+1} of ${totalPages} total pages`}</p>
                    <p>{`Showing ${currentPageSize} records from total ${totalRecords} records`}</p>
            </nav>
            
        </div>
    )
}

export default AdminDashboard