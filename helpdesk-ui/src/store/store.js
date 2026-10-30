import CustomerReducer from "./reducers/CustomerReducer";

const { configureStore } = require("@reduxjs/toolkit");

export const store = configureStore({
    reducer: CustomerReducer
})