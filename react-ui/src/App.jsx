import Auth from "./components/Auth"
import Navbar from "./components/Navbar"
import Nums from "./components/Nums"
import Todos from "./components/todos"

const App = () => { //parent component 
  return (
    <div className="container-fluid">
      <div className="row">
        <div className="col-md-10">
          <Navbar />
        </div>
        <div className="col-md-2">
          <Auth />
        </div>
      </div>
      <div className="row">
        <div className="col-md-4">
          <Nums />
        </div>
        <div className="col-md-8">
          <Todos />
        </div>
      </div>
      <div className="row">
        <div className="col-md-12"></div>
      </div>
      <div className="row">
        <div className="col-md-12"></div>
      </div>
    </div>
  )

}

export default App
