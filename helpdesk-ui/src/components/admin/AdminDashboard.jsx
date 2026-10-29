import { useEffect } from "react"
import { useNavigate, useParams } from "react-router"

function AdminDashboard() {

    const navigate = useNavigate()
    let params = useParams();
    useEffect(() => {
        const urlToken = params.token
        const memToken = localStorage.getItem('token')
        if (urlToken !== memToken) {
            navigate("/no-access")
        }
        console.log(`tokens match: ${urlToken === memToken}`)

        const getUserDetails = async() => {
            try {
                // verify this token by calling user-details API
                // Step 2. Fetch User Details using memToken
                const resp = await axios.get(userDetailsUrl, {
                    headers: { 'Authorization': 'Bearer ' + response.data.token }
                })
                if(! (resp.data.role === 'ADMIN')){
                    navigate("/no-access")
                }
            }
            catch (err) {

            }
            getUserDetails()
        }
    }, [])
    const logout = () => {
        localStorage.clear()
        navigate("/")
    }
    return (
        <div>
            <h1>AdminDashboard</h1>
            <button className="btn btn-secondary" onClick={() => logout()}>Logout</button>
        </div>
    )
}

export default AdminDashboard