import { useContext, useState } from 'react';
import './ItemForm.css';
import { assets } from '../../assets/assets';
import {AppContext} from '../../context/AppContext';
import { addItem } from '../../Service/ItemService';
import toast from 'react-hot-toast';

const ItemForm = ({ item, onChange}) => {

    const {categories, setItemsData, itemsData, setCategories} = useContext(AppContext);

    //Createing states
    const [image, setImage] = useState(false);
    const [loading, setLoading] = useState(false);
    const [data, setData] = useState({
        name: "",
        categoryId: "",
        description:"",
        price:"",
    });

    //Create handler 
    const onChangeHandler = (e) =>{
        const value = e.target.value;
        const name = e.target.name;
        setData((data) => ({...data, [name]: value}));
    }

    const onSubmitHandler = async (e) => {
        e.preventDefault();
        setLoading(true);
        const formData = new FormData();  // Create form for data having image and JSOM form data
        formData.append("item", JSON.stringify(data));
        formData.append("file", image);
        try{
            if(!image){
                toast.error("Select image");
                return;
            }
            const response = await addItem(formData);
            
            if(response.status === 201){
                setItemsData([...itemsData, response.data]);
                //Setting all category's item number then match it true add 1
                setCategories((prevCategories) => 
                prevCategories.map((category) => category.categoryId === data.categoryId ? {...category, items: category.items + 1 } : category));
                toast.success("Item added");
                setData({
                    name: "",
                    description: "",
                    price:"",
                    categoryId: ""
                });
                setImage(false); //reset image upload option
            }else{
                toast.error("Unable to add item");
            } 
        }catch(e){
            console.error(e);
            toast.error("Unable to add item");
        }finally{
            setLoading(false);
        }
    }



    return (
        <div className="item-form-container" style={{height:'100vh', overflowY: 'auto', overflowX: 'hidden'}}>
            <div className="mx-2 mt-2">
                <div className="row">
                    <div className="card col-md-8 form-container">
                        <div className="card-body">
                            <form onSubmit={onSubmitHandler}>
                                <div className="mb-3">
                                    <label htmlFor="image" className="form-lable">
                                        <img src={image ? URL.createObjectURL(image) : assets.upload} alt="" width={48}/>
                                        <input type="file" name="image" id="image" className="form-control" hidden onChange={(e) => setImage(e.target.files[0])}/>
                                    </label>
                                </div>

                                <div className="mb-3">
                                    <label htmlFor="name" className="form-label">Name</label>
                                    <input type="text" 
                                        name="name"
                                        id="name"
                                        className="form-control"
                                        placeholder="Item Name"
                                        onChange={onChangeHandler}
                                        value={data.name}
                                    />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label" htmlFor="category">
                                        Category
                                    </label>

                                    <select name="categoryId" id="categoryId" className="form-control" onChange={onChangeHandler} value={data.categoryId}>
                                        <option value="">--SELECT CATEGORY--</option>
                                        {categories.map((category, index) => (
                                            <option key={index} value={category.categoryId}>{category.name}</option>
                                        ))};
                                    </select>
                                </div>

                                <div className="mb-3">
                                    <label htmlFor="price" className="form-label">Price</label>
                                    <input type="number" name="price" id="price" className="form-control" placeholder="&#8377;200.00" onChange={onChangeHandler} value={data.price}></input>
                                </div>

                                <div className="mb-3">
                                    <label htmlFor="description" className="form-label">Description</label>
                                    <textarea 
                                        rows='5' 
                                        name="description"
                                        id="description"
                                        className="form-control"
                                        placeholder="Write content here.."
                                        onChange={onChangeHandler}
                                        value={data.description}
                                    ></textarea>
                                </div>

                                <button type="submit" className="btn btn-warning w-100" disabled={loading}>
                                    {loading? "Loading..." : "Save"}
                                </button>
                            </form>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
    )
}

export default ItemForm;