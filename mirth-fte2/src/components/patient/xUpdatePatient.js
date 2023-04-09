import React, { useState } from "react";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import Button from "@mui/material/Button";
import Stack from "@mui/material/Stack";
import IconButton from "@mui/material/IconButton";
import EditIcon from "@mui/icons-material/Edit";
import TextField from "@mui/material/TextField";

function UpdatePatient(props) {
    const [open, setOpen] = useState(false);
    const [patient, setPatient] = useState({
        firstName: "",
        lastName: "",
        nhsNumber: "",
        address: "",
        dateOfBirth: "",
    });

    const handleClickOpen = () => {
        setPatient({
            firstName: props.data.row.firstName,
            lastName: props.data.row.lastName,
            nhsNumber: props.data.row.nhsNumber,
            address: props.data.row.address,
            dateOfBirth: props.data.row.dateOfBirth,
        });
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleChange = (event) => {
        setPatient({ ...patient, [event.target.name]: event.target.value });
    };

    const handleSave = () => {
        // props.updatePatient(patient, props.data.id);
        props.addOrUpdatePatient(patient, props.data.id, "PUT")
        handleClose();
    };

    return (
        <div>
            <IconButton onClick={handleClickOpen}>
                <EditIcon color='primary' />
            </IconButton>
            <Dialog fullWidth maxWidth='sm' open={open} onClose={handleClose}>
                <DialogTitle>Update Patient</DialogTitle>
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

export default UpdatePatient;
