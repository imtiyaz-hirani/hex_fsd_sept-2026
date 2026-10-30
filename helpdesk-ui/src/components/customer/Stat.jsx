import { useEffect, useState } from "react"
import { useSelector } from "react-redux"

function Stat(){
    const list = useSelector(state=>state.customerTickets)
    const [openCount, setOpenCount] = useState(0)
    const [inProcessCount, setInProcessCount] = useState(0)
    
    useEffect(()=>{
        setOpenCount([...list.filter(t=> t.status === 'OPEN')].length)
        setInProcessCount([...list.filter(t=> t.status === 'IN_PROCESS')].length)
    },[])
    return(
        <div>
            Total OPEN Tickets: {openCount} <br />
            Tocal IN_PROCESS Tickets: {inProcessCount}
        </div>
    )
}
export default Stat