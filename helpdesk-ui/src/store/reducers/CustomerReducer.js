const initialState={
    customerTickets: []
}
const CustomerReducer =(state = initialState,action)=>{
    switch(action.type){
        case 'FETCH_TICKETS_CUSTOMER':
            return {
                ...state, 
                customerTickets : action.payload
            }
        case 'ADD_TICKET' :
            return{
                ...state, 
                customerTickets : [...state.customerTickets, action.payload]
            }   
        default: 
            return state
    }

}

export default CustomerReducer