import { createContext, useEffect, useState } from "react";
import { fetchCategories } from "../Service/CategoryService";
import { fetchItems } from "../Service/ItemService";

export const AppContext = createContext(null);

export const AppContextProvider = (props) => {

    const [categories, setCategories] = useState([]);
    const [itemsData, setItemsData] = useState([]);     // state for item
    //set authenticaion credential 
    const [auth, setAuth] = useState({token: null, role: null});
    
    useEffect(() => {
        async function loadData(){
            if( localStorage.getItem("token") && localStorage.getItem("role")){
                setAuthData(
                    localStorage.getItem("token"),
                    localStorage.getItem("role")
                );
            }
            const response = await fetchCategories();
            const itemResponse = await fetchItems();
            setCategories(response.data);
            setItemsData(itemResponse.data); //Getting items data from database to show data
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
        setAuthData,
        itemsData,
        setItemsData
    }

    return <AppContext.Provider value={contextValue}>
        {props.children}
    </AppContext.Provider>
}