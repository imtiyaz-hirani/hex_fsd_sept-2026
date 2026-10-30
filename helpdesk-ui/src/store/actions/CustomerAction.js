import axios from "axios"
import { API_SERVER_URL_DEV } from "../../../config"

export const getTickets = (username)=>async(dispatch)=>{

    const response = await axios.get(`${API_SERVER_URL_DEV}/api/ticket/v2?username=${username}`, 
        {
            headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
        }
    )

    dispatch({
        type: 'FETCH_TICKETS_CUSTOMER',
        payload : response.data
    })
}

export const addTicket = (ticket)=>async(dispatch)=>{
    
    const response = await axios.post(`${API_SERVER_URL_DEV}/api/ticket/add`, ticket ,
        {
            headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
        })
    dispatch({
        type: 'ADD_TICKET',
        payload : response.data
    })    
}

export const deleteTicket = (id) =>async(dispatch)=>{
    const response = await axios.delete(`${API_SERVER_URL_DEV}/api/ticket/hard/${id}` ,
        {
            headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
        })
    dispatch({
        type: 'DELETE_TICKET',
        payload : id
    })        

}