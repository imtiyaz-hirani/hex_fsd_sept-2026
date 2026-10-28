import { useState } from "react"
import { Link } from "react-router"
import { API_SERVER_URL_DEV } from "../../../config"
import axios from "axios"

function Login() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [errMsg, SetErrMsg] = useState("")

    const apiUrl = API_SERVER_URL_DEV + '/api/auth/login'
    const processLogin =async (e)=>{
        e.preventDefault()
        const token = 'Basic ' + window.btoa(username + ':' + password)
        // console.log(token)

        const response = await axios.get(apiUrl, {
            headers : { 'Authorization': token }
        } )
        // console.log(response)
        // console.log(response.data.token)
        localStorage.setItem('token', response.data.token)
    }
    return (
        <div className="container">
            <div className="row justify-content-center">
                <div className="col-12 col-sm-9 col-md-7 col-lg-5 col-xl-4">

                    {/* <!-- Login Card --> */}
                    <div className="card shadow-sm border-0 rounded-4 p-4 p-sm-5 bg-white">
                        <div className="card-body p-0">

                            {/* <!-- Branding / Header --> */}
                            <div className="text-center mb-4">
                                
                                <h1 className="h4 fw-bold text-dark mb-1">Helpdesk</h1>
                                <p className="text-muted small">Please enter your details to sign in.</p>
                            </div>

                            {/* <!-- Login Form --> */}
                            <form onSubmit={(e)=>processLogin(e)}>
                                {/* <!-- Username Field --> */}
                                <div className="mb-3">
                                    <label className="form-label small fw-semibold text-secondary">Username</label>
                                    <input type="text" className="form-control form-control-lg"required 
                                    onChange={(e)=> setUsername(e.target.value)}/>
                                </div>

                                {/* <!-- Password Field --> */}
                                <div className="mb-3">
                                    <div className="d-flex justify-content-between align-items-center">
                                        <label   className="form-label small fw-semibold text-secondary">Password</label>
                                        <a href="#" className="small text-decoration-none text-primary fw-semibold">Forgot password?</a>
                                    </div>
                                    <input type="password" className="form-control form-control-lg" placeholder="••••••••" required 
                                    onChange={(e)=> setPassword(e.target.value)}/>  
                                </div>

                                {/* <!-- Submit Button --> */}
                                <div className="d-grid mb-3">
                                    <button type="submit" className="btn btn-primary btn-lg fw-semibold"  >Sign in</button>
                                </div>

                                {/* <!-- Footer Link --> */}
                                <div className="text-center">
                                    <p className="small text-muted mb-0">Don't have an account? <Link to="/customer/signup" className="text-primary text-decoration-none fw-semibold">Sign up</Link></p>
                                </div>
                            </form>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    )
}

export default Login