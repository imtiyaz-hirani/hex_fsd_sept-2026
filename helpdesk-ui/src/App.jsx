import { Route, Routes } from "react-router"
import Login from "./components/auth/Login"
import SignUp from "./components/customer/SignUp"
import PageNotFound from "./components/PageNotFound"

function App() {
return(
  <Routes>
    <Route path="" element={<Login />}/>
    <Route path="customer/signup" element={<SignUp />}/>
    <Route path="*" element={<PageNotFound />}/>
  </Routes>
) 
  
}

export default App
