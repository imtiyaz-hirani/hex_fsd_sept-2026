import { Outlet } from "react-router"
import Home from "./Home"
import Navbar from "./Navbar"
import Welcome from "./Welcome"

function ExecutiveDashboard(){
    return(
        <div className="container-fluid">
            <div className="row">
                <div className="col-lg-12">
                    <Navbar />
                </div>
            </div>
           <div className="row">
                <div className="col-lg-12">
                     <div className="alert alert-primary">
                        <Welcome />
                     </div>
                </div>
            </div>
            <div className="row">
                <div className="col-lg-12">
                      <Outlet />
                </div>
            </div>
        </div>
    )
}

export default ExecutiveDashboard