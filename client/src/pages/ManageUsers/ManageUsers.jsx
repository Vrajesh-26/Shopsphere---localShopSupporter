import { useEffect, useState } from 'react';
import UserForm from '../../components/UserForm/UserForm';
import UsersList from '../../components/UsersList/UsersList';
import { fetchUsers } from '../../Service/UserService';
import './ManageUsers.css';
import toast from 'react-hot-toast';

const ManageUsers = () => {

    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(false);

    //Fetch Users 
    useEffect(() => {
        async function loadUsers(){
            try{
                setLoading(true);
                const response = await fetchUsers();
                setUsers(response.data);
            }catch(e){
                console.error(e);
                toast.error("Unable to fetch users");
            }finally{
                setLoading(false);
            }
        }
        loadUsers();
    }, []);

    return(
        <div className="users-container text-light">
            <div className="left-column">
                <UserForm setUsers={setUsers} />
            </div>
            <div className="right-column">
                <UsersList users={users} setUsers={setUsers} />
            </div>
        </div>
    )
}

export default ManageUsers;