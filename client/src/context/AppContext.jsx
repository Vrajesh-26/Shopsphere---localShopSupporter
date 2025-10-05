import { createContext, useEffect, useState } from "react";
import { fetchCategories } from "../Service/CategoryService";

export const AppContext = createContext(null);

export const AppContextProvider = (props) => {

    const [categories, setCategories] = useState([]);
    //set authenticaion credential 
    const [auth, setAuth] = useState({token: null, role: null});
    
    useEffect(() => {
        async function loadData(){
            const response = await fetchCategories();
            setCategories(response.data);
        }
        loadData();
    }, []);

    // Not pass direct response from login api we set on this variable then this state pass 
    const setAuthData = (token, role) => {
        setAuth({token, role});
    } 

    const contextValue = {
        categories,
        setCategories,
        auth,
        setAuthData
    }

    return <AppContext.Provider value={contextValue}>
        {props.children}
    </AppContext.Provider>
}