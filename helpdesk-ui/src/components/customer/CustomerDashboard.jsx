import { useNavigate } from "react-router"

function CustomerDashboard(){
    const navigate = useNavigate()
    const logout = () => {
        localStorage.clear()
        navigate("/")
    }
    return(
        <div>
            <h1>CustomerDashboard</h1>
             <button className="btn btn-secondary" onClick={() => logout()}>Logout</button>
        </div>
    )
}

export default CustomerDashboard