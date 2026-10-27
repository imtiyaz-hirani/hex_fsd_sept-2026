import AddTicket from "./components/AddTicket"
import Auth from "./components/Auth"
import CourseList from "./components/CourseList"
import Navbar from "./components/Navbar"
import Nums from "./components/Nums"
import Todos from "./components/todos"
import UsersDashboard from "./users-cs/Dashboard"
 
const App = () => { //parent component 
  return (
    <UsersDashboard />
    // <div className="container-fluid">
    //   <div className="row">
    //     <div className="col-md-10">
    //       <Navbar />
    //     </div>
    //     <div className="col-md-2">
    //       <Auth />
    //     </div>
    //   </div>
    //   <div className="row">
    //     <div className="col-md-4">
    //       <Nums />
    //     </div>
    //     <div className="col-md-8">
    //       {/* <Todos /> */}
    //       <CourseList />
    //     </div>
    //   </div>
    //   <div className="row">
    //     <div className="col-md-4">
    //       <AddTicket />
    //     </div>
    //   </div>
    //   <div className="row">
    //     <div className="col-md-12"></div>
    //   </div>
    // </div>
  )

}

export default App
