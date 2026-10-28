import { Link } from "react-router"

function Navbar() {
    return (
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
            <div className="container-fluid">
                <Link className="navbar-brand" to="">HelpDesk</Link>
                 
                <div className="collapse navbar-collapse" >
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <Link className="nav-link active" aria-current="page" to="">Home</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/users">Users</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/posts">Posts</Link>
                        </li>
                          
                    </ul>
                     
                </div>
            </div>
        </nav>
    )
}

export default Navbar