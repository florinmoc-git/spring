// @ts-nocheck
import React, { useEffect, useState } from "react";
import { SERVER_URL } from "../../constants.js";
import { DataGrid } from "@mui/x-data-grid";
import Snackbar from "@mui/material/Snackbar";
import AddOrUpdatePatient from "./AddOrUpdatePatient.js";
import { Stack } from "@mui/material";
import IconButton from '@mui/material/IconButton';
import DeleteIcon from '@mui/icons-material/Delete';
import { CustomToolbar } from "../toolbars/CustomToolbar";

function PatientList() {
    const [patients, setPatients] = useState([]);
    const [open, setOpen] = useState(false);

    const fetchPatients = async () => {
        const token = sessionStorage.getItem("jwt");
        try {
            const response = await fetch(SERVER_URL + "api/patients", {
                headers: {'Authorization': token}
            });
            const data = await response.json();
            setPatients(data._embedded.patients);
        } catch (error) {
            console.log("Patient fetch error: " + error);
        }        
    };

    const onDelClick = (url) => {
        if (window.confirm("Are you sure to delete?")) {
            const token = sessionStorage.getItem("jwt");
            fetch(url, { 
                method: "DELETE", 
                headers: { 'Authorization' : token }
            })
                .then((response) => {
                    if (response.ok) {
                        fetchPatients();
                        setOpen(true);
                    } else {
                        alert("Something went wrong!");
                    }
                })
                .catch((err) => console.error(err));
        }
    };

    const addOrUpdatePatient = (patient, link, httpMethod) => {
        const token = sessionStorage.getItem("jwt");
        fetch(link, {
            method: httpMethod,
            headers: { "Content-Type": "application/json",'Authorization' : token  },
            body: JSON.stringify(patient),
        })
            .then((response) => {
                if (response.ok) {
                    fetchPatients();
                } else {
                    alert("Something went wrong!");
                }
            })
            .catch((err) => console.error(err));
    };


    const columns = [
        { field: "firstName", headerName: "First Name", width: 200 },
        { field: "lastName", headerName: "Last Name", width: 200 },
        { field: "nhsNumber", headerName: "NHS Number", width: 200 },
        { field: "address", headerName: "Address", width: 300 },
        { field: "dateOfBirth", headerName: "Date of Birth", width: 200 },
        {
            field: "_links.patient.href",
            headerName: "",
            sortable: false,
            filterable: false,
            renderCell: (row) => <AddOrUpdatePatient data={row} addOrUpdatePatient={addOrUpdatePatient} addOrUpdate="update"/>,
        },
        {
            field: "_links.self.href",
            headerName: "",
            sortable: false,
            filterable: false,
            renderCell: (row) => (
                <IconButton onClick={() => onDelClick(row.id)}>
                    <DeleteIcon color="error"/>
                </IconButton>
            ),
        },
    ];

    useEffect(() => {
        try {
            fetchPatients();
        } catch (err) {
            console.error(err);
        }
    }, []);
    return (
        <React.Fragment>
            <Stack mt={2} mb={2}>
                <AddOrUpdatePatient addOrUpdatePatient={addOrUpdatePatient} addOrUpdate="add" />
            </Stack>
            <div style={{ height: 500, width: "100%" }}>
                <DataGrid
                    rows={patients}
                    columns={columns}
                    disableSelectionOnClick={true}
                    getRowId={(row) => row._links.self.href}
                    components={{Toolbar: CustomToolbar}}
                />
                <Snackbar
                    open={open}
                    autoHideDuration={2000}
                    onClose={() => setOpen(false)}
                    message='Patient deleted'
                />
            </div>
        </React.Fragment>
    );
}

export default PatientList;
