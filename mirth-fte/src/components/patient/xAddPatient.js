import React, { useState } from "react";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import Button from "@mui/material/Button";
import Stack from "@mui/material/Stack";
import TextField from "@mui/material/TextField";
import { SERVER_URL } from "../../constants";

function AddPatient(props) {
    const [open, setOpen] = useState(false);
    const [patient, setPatient] = useState({
        firstName: "",
        lastName: "",
        nhsNumber: "",
        address: "",
        dateOfBirth: "",
    });

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleChange = (event) => {
        setPatient({ ...patient, [event.target.name]: event.target.value });
    };

    const handleSave = () => {
        // props.addPatient(patient);
        props.addOrUpdatePatient(patient, SERVER_URL + "api/patients", "POST")
        handleClose();
    };

    return (
        <div>
            <Button variant='contained' onClick={handleClickOpen}>
                New Patient
            </Button>
            <Dialog fullWidth maxWidth='sm' open={open} onClose={handleClose}>
                <DialogTitle>New Patient</DialogTitle>
                <DialogContent>
                    <Stack spacing={2} mt={1}>
                        <TextField
                            label='First Name'
                            name='firstName'
                            autoFocus
                            value={patient.firstName}
                            onChange={handleChange}
                            variant='standard'
                        />
                        <TextField
                            label='Last Name'
                            name='lastName'
                            value={patient.lastName}
                            onChange={handleChange}
                            variant='standard'
                        />
                        <TextField
                            label='NHS Number'
                            name='nhsNumber'
                            value={patient.nhsNumber}
                            onChange={handleChange}
                            variant='standard'
                        />
                        <TextField
                            label='Address'
                            name='address'
                            value={patient.address}
                            onChange={handleChange}
                            variant='standard'
                        />
                        <TextField
                            label='Date of Birth'
                            name='dateOfBirth'
                            value={patient.dateOfBirth}
                            onChange={handleChange}
                            variant='standard'
                        />
                    </Stack>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={handleSave}>Save</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}

export default AddPatient;
