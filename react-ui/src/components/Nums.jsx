import { useState } from "react";

function Nums() {
    const nums = [1, 4, 7, 2, 3, 8, 9, 6, 4] // normal variable that holds array 
    const [numsArry,setNumsArry] = useState([1, 4, 7, 2, 3, 8, 9, 6, 4]);  // state variable that is saved in reacts context
    const [numsClone, setNumsClone] = useState([...numsArry])
    const sortFn = (direction)=>{
        switch(direction) {
            case 'ASC': 
                setNumsArry([...numsArry.sort((a, b) => a - b)])
                break; 
            case 'DESC': 
                setNumsArry([...numsArry.sort((a, b) => b - a)])
                break;
            default: 
                setNumsArry([...numsClone])
                break;     // {... obj} [...arry]
        }
          
    }
    return (
        <div>
            {/** Iterating over the array using map */}
            {
                numsArry.map((n, index) => (
                    <span key={index}>{n} &nbsp; &nbsp;</span> 
                ))
            }

            <div>
                <button className="btn btn-primary" onClick={()=>sortFn('ASC')}>Sort-ASC</button>&nbsp; &nbsp;
                <button className="btn btn-secondary" onClick={()=>sortFn('RESET')}>RESET</button>&nbsp; &nbsp;
                <button className="btn btn-warning" onClick={()=>sortFn('DESC')}>Sort-DESC</button>
            </div>
        </div>
    )
}
export default Nums