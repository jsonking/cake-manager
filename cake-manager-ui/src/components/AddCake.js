import React, {useState} from 'react';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';

const AddCake = (props) => {

    const [open, setOpen] = useState(false);
    const [cake, setCake] = useState({
        name:'', description:'', imageURL: ''
    });

    // Open the modal form
    const handleClickOpen = () => {
        setOpen(true);
    };

    // Close the modal form
    const handleClose = () => {
        setOpen(false);
    };

    const handleChange = (event) => {
        setCake({...cake,[event.target.name]: event.target.value});
    }

    const handleSave = () => {
        props.addCake(cake);
        handleClose();
    }

    return (
        <div>
            <button style={{margin:10}} onClick={handleClickOpen}>New Cake</button>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>New cake</DialogTitle>
                <DialogContent>
                    <input type="text" placeholder="Name" name="name" value={cake.name} onChange={handleChange}/><br/>
                    <input type="text" placeholder="Description" name="description" value={cake.description} onChange={handleChange}/><br/>
                    <input type="text" placeholder="Image URL" name="imageURL" value={cake.imageURL} onChange={handleChange}/><br/>
                </DialogContent>
                <DialogActions>
                    <button onClick={handleClose}>Cancel</button>
                    <button onClick={handleSave}>Save</button>
                </DialogActions>
            </Dialog>
        </div>
    );
};

export default AddCake;
