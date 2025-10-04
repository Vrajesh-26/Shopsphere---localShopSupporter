import { Routes, Route, useLocation } from "react-router-dom";
import Menubar from "./components/Menubar/Menubar.jsx";
import Dashboard from "./pages/Dashboard/Deshbord.jsx";
import ManageCategory from "./pages/ManageCategory/ManageCategory.jsx";
import ManageUsers from "./pages/ManageUsers/ManageUsers.jsx";
import ManageItems from "./pages/ManageItems/ManageItems.jsx";
import Explore from "./pages/Explore/Explore.jsx";
import { Toaster } from "react-hot-toast";
import Login from "./pages/Login/login.jsx";

const App = () => {
    //Location hook
    const location = useLocation();

    return(

        <div>
            {location.pathname !== "/login" && <Menubar />}
            <Toaster />
            <Routes>
                <Route path= "/deshboard" element= {<Dashboard />} />
                <Route path= "/category" element= {<ManageCategory />} />
                <Route path= "/users" element= {<ManageUsers />} />            
                <Route path= "/items" element= {<ManageItems />} />
                <Route path= "/explore" element= {<Explore />} />
                <Route path= "/login" element= {<Login />} />
                <Route path= "/" element= {<Dashboard />} />
            </Routes>
        </div>
    );
}

export default App;