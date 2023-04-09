import {
    Box,
    Button,
    ButtonGroup,
    Checkbox,
    FormGroup,
    FormControlLabel,
    Grid,
    Paper,
    Stack,
    styled,
    TextField,
} from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import React, { useEffect, useState } from "react";
import { MIRTH_ENDPOINT, password, username } from "../../constants";
import { CustomToolbar } from "../toolbars/CustomToolbar";
import { DateTimePicker, LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";

function MirthMessages(props) {
    const [messages, setMessages] = useState([]);
    const [channelId, setChannelId] = useState(props.channelId);
    const [startTime, setStartTime] = React.useState(null);
    const [endTime, setEndTime] = React.useState(null);
    const handleStartTimeChange = (newStartTime) => {
        setStartTime(newStartTime);
    };
    const handleEndTimeChange = (newEndTime) => {
        setEndTime(newEndTime);
    };

    const fetchMessagesForChannel = async () => {
        try {
            const response = await fetch(
                `${MIRTH_ENDPOINT}/channels/${channelId}/messages?limit=20&includeContent=false&offset=0`,
                {
                    method: "GET",
                    mode: "cors",
                    headers: {
                        Authorization:
                            "Basic " + window.btoa(`${username}:${password}`),
                        Accept: "application/json",
                        "X-Requested-With": "OpenAPI",
                    },
                }
            );
            const data = await response.json();
            setMessages(data.list.message);
        } catch (error) {
            // console.log(error);
        }
    };

    const getConnector = (params) => {
        return params.row.connectorMessages.entry[0].connectorMessage
            .connectorName;
    };

    const getReceivedDate = (params) => {
        return new Date(params.row.receivedDate.time)
            .toISOString()
            .replace("T", " ")
            .replace("Z", "");
    };

    const getResponseDate = (params) => {
        return new Date(
            params.row.connectorMessages.entry[0].connectorMessage.responseDate.time
        )
            .toISOString()
            .replace("T", " ")
            .replace("Z", "");
    };

    const getStatus = (params) => {
        return params.row.connectorMessages.entry[0].connectorMessage.status;
    };

    const getSource = (params) => {
        return params.row.connectorMessages.entry[0].connectorMessage
            .metaDataMap.entry[1].string[1];
    };

    const getType = (params) => {
        return params.row.connectorMessages.entry[0].connectorMessage
            .metaDataMap.entry[3].string[1];
    };

    const columns = [
        { field: "messageId", headerName: "Id" },
        {
            field: "connector",
            width: 200,
            headerName: "Connector",
            valueGetter: getConnector,
        },
        {
            field: "status",
            width: 150,
            headerName: "Status",
            valueGetter: getStatus,
        },
        {
            field: "receivedDate",
            width: 250,
            headerName: "Received Date",
            valueGetter: getReceivedDate,
        },
        {
            field: "responseDate",
            width: 250,
            headerName: "Response Date",
            valueGetter: getResponseDate,
        },
        {
            field: "source",
            width: 150,
            headerName: "Source",
            valueGetter: getSource,
        },
        { field: "type", width: 150, headerName: "Type", valueGetter: getType },
    ];

    const Item = styled(Paper)(({ theme }) => ({
        backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
        ...theme.typography.body2,
        padding: theme.spacing(1),
        textAlign: "center",
        color: theme.palette.text.secondary,
    }));

    useEffect(() => {
        fetchMessagesForChannel();
    }, []);

    return (
        <Paper>
            <Box style={{ height: 300, width: "100%" }}>
                <Grid container spacing={2}>
                    <Grid item xs={3}>
                        {/* <Item>xs=8</Item> */}
                        <LocalizationProvider dateAdapter={AdapterDateFns}>
                            <Stack spacing={1}>
                                <DateTimePicker
                                    label='Start time'
                                    value={startTime}
                                    onChange={handleStartTimeChange}
                                    renderInput={(params) => (
                                        <TextField {...params} />
                                    )}
                                />
                                <DateTimePicker
                                    label='End time'
                                    value={endTime}
                                    onChange={handleEndTimeChange}
                                    renderInput={(params) => (
                                        <TextField {...params} />
                                    )}
                                />
                                <TextField
                                    id='text-search'
                                    label='Text search'
                                    variant='outlined'
                                />
                                <Box>
                                    <ButtonGroup
                                        variant='outlined'
                                        aria-label='outlined primary button group'
                                    >
                                        <Button>Advanced</Button>
                                        <Button>Reset</Button>
                                        <Button variant='contained'>
                                            Search
                                        </Button>
                                    </ButtonGroup>
                                </Box>
                            </Stack>
                        </LocalizationProvider>
                    </Grid>
                    <Grid  spacing={1} item xs={2} >
                        <FormGroup >
                            <FormControlLabel
                                control={<Checkbox  />}
                                label='RECEIVED'
                               
                            />
                            <FormControlLabel
                                control={<Checkbox />}
                                label='TRANSFORMED'
                            />
                            <FormControlLabel
                                control={<Checkbox />}
                                label='FILTERED'
                            />
                            <FormControlLabel
                                control={<Checkbox />}
                                label='QUEUED'
                            />
                            <FormControlLabel
                                control={<Checkbox />}
                                label='SENT'
                            />
                            <FormControlLabel
                                control={<Checkbox />}
                                label='ERROR'
                            />
                            <FormControlLabel
                                control={<Checkbox />}
                                label='PENDING'
                            />
                        </FormGroup>
                    </Grid>
                    <Grid item xs>
                        <Item>xs=4</Item>
                    </Grid>
                    <Grid item xs={3}>
                        <Item>xs=8</Item>
                    </Grid>
                </Grid>
            </Box>
            <Box style={{ height: 400, width: "100%" }}>
                <DataGrid
                    // @ts-ignore
                    rows={messages}
                    columns={columns}
                    disableSelectionOnClick={true}
                    getRowId={(row) => row.messageId}
                    components={{ Toolbar: CustomToolbar }}
                />
            </Box>
        </Paper>
    );
}

export default MirthMessages;
