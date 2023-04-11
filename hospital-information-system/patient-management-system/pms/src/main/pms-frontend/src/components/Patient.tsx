import { Button, Paper } from '@mui/material';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { ChangeEvent, MouseEvent, MouseEventHandler, useEffect, useState } from 'react';

export default function Patient() {
    const paperStyle = { padding: '50px 20px', width: 600, margin: '20px auto' };

    const [firstName, setFirstName] = useState<String>('');
    const [lastName, setLastName] = useState<String>('');
    const [birthDate, setBirthDate] = useState<String>('');
    const [wardId, setWardId] = useState<String>('');
    const [patients, setPatients] = useState<Array<any>>([]);

    var handleFirstNameChange = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>): void => {
        setFirstName(event.target.value);
    }

    var handleLastNameChange = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>): void => {
        setLastName(event.target.value);
    }

    var handleBirthDateChange = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>): void => {
        setBirthDate(event.target.value);
    }

    var handleWardIdChange = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>): void => {
        setWardId(event.target.value);
    }

    var handleSavePatient = async (event: MouseEvent<HTMLElement>): Promise<void> => {
        const patient = { firstName, lastName, birthDate, wardId };
        const response = await fetch(
            'http://localhost:8080/pms/patients/admit/triage',
            {
                method: 'POST',
                headers: { 'Content-type': 'application/json' },
                body: JSON.stringify(patient)
            }
        )
        const jsonData = await response.json();
    }

    useEffect(() => {
        const getPatientsList =  async () => {
            const response = await fetch('http://localhost:8080/pms/patients/get/all');
            const patientsList = await response.json();
            setPatients(patientsList);
        }
        getPatientsList().catch(console.error);
    }, []);

    return (
        <>
        <Paper elevation={3} style={paperStyle}>
            <h1>Add Patient</h1>
            <Box
                component="form"
                sx={{
                    '& > :not(style)': { m: 1 },
                }}
                noValidate
                autoComplete="off"
            >
                <TextField id="outlined-basic" label="First Name" variant="outlined" fullWidth
                    value={firstName}
                    onChange={handleFirstNameChange}
                />
                <TextField id="outlined-basic" label="Last Name" variant="outlined" fullWidth
                    value={lastName}
                    onChange={handleLastNameChange}
                />
                <TextField id="outlined-basic" label="Birth Date" variant="outlined" fullWidth
                    value={birthDate}
                    onChange={handleBirthDateChange}
                />
                <TextField id="outlined-basic" label="Ward" variant="outlined" fullWidth
                    value={wardId}
                    onChange={handleWardIdChange}
                />
            </Box>
            <Button variant="contained" color="secondary" onClick={handleSavePatient}>Save</Button>
        </Paper>
        <h1>Students</h1>
        <Paper elevation={3} style={paperStyle}>
            {patients.map(patient => (
                <Paper elevation={6} style={{margin: '10px', padding: '15px', textAlign: 'left'}} key={patient.id}>
                    Id: {patient.id} <br/>
                    First Name: {patient.firstName} <br/>
                    Last Neme: {patient.lastName} <br/>
                    Date of Birth: {patient.dateOfBirth} <br/>
                    Ward: {patient.wardId}
                </Paper>
            ))}
        </Paper>
        </>
    );
}

// {
//     "firstName": "Fivi",
//     "lastName": "Mocanu",
//     "birthDate": "12.03.1975",
//     "wardId": 102
// }
