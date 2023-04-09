// @ts-nocheck
import { useEffect, useState } from "react";
import { MIRTH_ENDPOINT, username, password } from "../../constants";
import { DataGrid } from "@mui/x-data-grid";
import { CustomToolbar } from "../toolbars/CustomToolbar";
import MirthChannelsStateControls from "./MirthChannelsStateControls";
import { Tooltip, IconButton, Typography, Box, Paper } from "@mui/material";
import WysiwygIcon from "@mui/icons-material/Wysiwyg";

export const MirthChannels = (props) => {
    const [channels, setChannels] = useState([]);

    const fetchChannelsAndStatuses = async () => {
        try {
            const response = await fetch(
                `${MIRTH_ENDPOINT}/channels/statuses`,
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
            setChannels(data.list.dashboardStatus);
        } catch (error) {
            console.log(error);
        }
    };

    const getState = (row) => {
        const state = row.row.state;
        let color = "";
        switch (state) {
            case "STOPPED":
                color = "red";
                break;
            case "PAUSED":
                color = "orange";
                break;
            case "STARTED":
                color = "green";
                break;
            default:
                color = "grey";
        }
        return (
            <Typography style={{ color: color, fontWeight: "bold" }}>
                {row.row.state}
            </Typography>
        );
    };

    const getChannelId = (row) => (
        <MirthChannelsStateControls
            initialState={row.row.state}
            channelId={row.row.channelId}
            fetchChannelsAndStatuses={fetchChannelsAndStatuses}
        />
    );

    const getMessages = (row) => (
        <Tooltip title='View' arrow>
            <IconButton
                onClick={() => props.handleShowMessages(row.row.channelId)}
            >
                <WysiwygIcon color='success' />
            </IconButton>
        </Tooltip>
    );

    const columns = [
        // { field: 'state', headerName: 'Status', width: 150 },
        {
            field: "state",
            headerName: "Status",
            width: 150,
            renderCell: getState,
        },
        { field: "name", headerName: "Name", width: 200 },
        {
            field: "channelId",
            headerName: "State Controls",
            sortable: false,
            filterable: false,
            width: 150,
            renderCell: getChannelId,
        },
        {
            field: "messages",
            headerName: "Messages",
            sortable: false,
            filterable: false,
            width: 100,
            renderCell: getMessages,
        },
    ];

    useEffect(() => {
        try {
            fetchChannelsAndStatuses();
        } catch (error) {
            console.log(error);
        }
    }, []);

    return (
        <Paper>
            <Box style={{ height: 800, width: "100%" }}>
                <DataGrid
                    rows={channels}
                    columns={columns}
                    disableSelectionOnClick={true}
                    getRowId={(row) => row.channelId}
                    components={{ Toolbar: CustomToolbar }}
                />
            </Box>
        </Paper>
    );
};
