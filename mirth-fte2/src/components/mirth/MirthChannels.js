// @ts-nocheck
import { useEffect, useMemo, useState } from "react";
import { MIRTH_ENDPOINT } from "../../constants";
import MirthChannelsStateControls from "./MirthChannelsStateControls";
import { Tooltip, IconButton, Typography, Paper } from "@mui/material";
import WysiwygIcon from "@mui/icons-material/Wysiwyg";
import useFetchWrapper from "../../hooks/use-fetch-wrapper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";

export const MirthChannels = (props) => {
    const [channels, setChannels] = useState([]);
    const requestConfig = useMemo(() => {
        return {
            url: `${MIRTH_ENDPOINT}/channels/statuses`,
        };
    }, []);

    const { sendRequest: fetchChannelsAndStatuses } = useFetchWrapper();

    const getState = (row) => {
        const state = row.state;
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
                {state}
            </Typography>
        );
    };

    const setChannelData = (data) => {
        const statuses = data.list.dashboardStatus;
        const channelsForDisplay = statuses.map((status) => {
            return {
                state: status.state,
                name: status.name,
                received: status.statistics.entry[0].long,
                filtered: status.statistics.entry[1].long,
                queued: status.queued,
                sent: status.statistics.entry[2].long,
                errored: status.statistics.entry[3].long,
                channelId: status.channelId,
                messages: status.channelId,
            };
        });
        setChannels(channelsForDisplay);
    };

    const getStateControls = (row) => (
        <MirthChannelsStateControls
            initialState={row.state}
            channelId={row.channelId}
            fetchChannelsAndStatuses={() =>
                fetchChannelsAndStatuses(requestConfig, setChannelData)
            }
        />
    );

    useEffect(() => {
        fetchChannelsAndStatuses(requestConfig, setChannelData);
    }, [fetchChannelsAndStatuses, requestConfig]);

    return (
        <TableContainer component={Paper}>
            <Table
                sx={{ minWidth: 650 }}
                size='small'
                aria-label='channels table'
            >
                <TableHead>
                    <TableRow>
                        <TableCell sx={{ width: 150 }}>Status</TableCell>
                        <TableCell sx={{ width: 100 }}>Name</TableCell>
                        <TableCell align='right' sx={{ width: 100 }}>
                            Received
                        </TableCell>
                        <TableCell align='right' sx={{ width: 100 }}>
                            Filtered
                        </TableCell>
                        <TableCell align='right' sx={{ width: 100 }}>
                            Queued
                        </TableCell>
                        <TableCell align='right' sx={{ width: 100 }}>
                            Sent
                        </TableCell>
                        <TableCell align='right' sx={{ width: 100 }}>
                            Errored
                        </TableCell>
                        <TableCell align='right' sx={{ width: 150 }}>
                            State Controls
                        </TableCell>
                        <TableCell align='right' sx={{ width: 100 }}>
                            Messages
                        </TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {channels.map((row) => (
                        <TableRow
                            key={row.name}
                            sx={{
                                "&:last-child td, &:last-child th": {
                                    border: 0,
                                },
                            }}
                        >
                            <TableCell>{getState(row)}</TableCell>
                            <TableCell>{row.name}</TableCell>
                            <TableCell align='right' onClick={() =>
                                    props.handleShowMessages(
                                        row.channelId,
                                        "RECEIVED"
                                    )
                                }>{row.received}</TableCell>
                            <TableCell align='right'
                            onClick={() =>
                                props.handleShowMessages(
                                    row.channelId,
                                    "FILTERED"
                                )
                            }>{row.filtered}</TableCell>
                            <TableCell align='right' onClick={() =>
                                    props.handleShowMessages(
                                        row.channelId,
                                        "QUEUED"
                                    )
                                }>{row.queued}</TableCell>
                            <TableCell
                                align='right'
                                onClick={() =>
                                    props.handleShowMessages(
                                        row.channelId,
                                        "SENT"
                                    )
                                }
                            >
                                {row.sent}
                            </TableCell>
                            <TableCell
                                align='right'
                                sx={{
                                    ...(row.errored > 0 && {
                                        backgroundColor: "#FFCCCB",
                                        fontWeight: "bold",
                                    }),
                                }}
                                onClick={() =>
                                    props.handleShowMessages(
                                        row.channelId,
                                        "ERROR"
                                    )
                                }
                            >
                                {row.errored}
                            </TableCell>
                            <TableCell align='right'>
                                {getStateControls(row)}
                            </TableCell>
                            <TableCell align='right'>
                                <Tooltip title='View messages' arrow>
                                    <IconButton
                                        onClick={() =>
                                            props.handleShowMessages(
                                                row.channelId,
                                                null
                                            )
                                        }
                                    >
                                        <WysiwygIcon color='success' />
                                    </IconButton>
                                </Tooltip>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};
