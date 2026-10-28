import { Route, Routes } from "react-router"
import AddTicket from "./components/AddTicket"
import Auth from "./components/Auth"
import CourseList from "./components/CourseList"
import Navbar from "./components/Navbar"
import Nums from "./components/Nums"
import Todos from "./components/todos"
import UsersDashboard from "./users-cs/Dashboard"
import PostDashboard from "./components/posts/PostDashBoard"

const App = () => { //parent component 

  return (
    <div className="container-fluid">
      <div className="row mb-4">
        <div className="col-lg-12">
          <Navbar />
        </div>
      </div>
      <div className="row mt-4">
        <div className="col-lg-12">
          <Routes>
        <Route path="" element={<CourseList />} />
        <Route path="users" element={<UsersDashboard />} />
        <Route path="posts" element={<PostDashboard />} />
      </Routes>
        </div>
      </div>
      
      
    </div>


  )

}

export default App
