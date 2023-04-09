import {
    Box,
    Button,
    ButtonGroup,
    Checkbox,
    FormControlLabel,
    Grid,
    Paper,
    Stack,
    styled,
    TextField,
    Typography,
} from "@mui/material";
import React, { useEffect, useMemo, useState } from "react";
import { flushSync } from 'react-dom';
import { MIRTH_ENDPOINT } from "../../constants";
import { DateTimePicker, LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import useFetchWrapper from "../../hooks/use-fetch-wrapper";
import { convertMessagesJson } from "./messages-conversion-util";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";

const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: "right",
    color: theme.palette.text.secondary,
}));
const statuses = [
    "RECEIVED",
    "TRANSFORMED",
    "FILTERED",
    "QUEUED",
    "SENT",
    "ERROR",
    "PENDING",
];

const MirthMessages = (props) => {
    const [pageSize, setPageSize] = useState(20);
    const [pageNumber, setPageNumber] = useState(1);
    const [offset, setOffset] = useState(0);
    const [messageCount, setMessageCount] = useState();
    const [lastPage, setLastPage] = useState();

    const baseUrl = `${MIRTH_ENDPOINT}/channels/${props.channelId}/messages`;
    const baseUrlCount = `${MIRTH_ENDPOINT}/channels/${props.channelId}/messages/count`;
    const baseUrlMaxMessageId = `${MIRTH_ENDPOINT}/channels/${props.channelId}/messages/maxMessageId`;

    const [messages, setMessages] = useState([]);
    const [maxMessageId, setMaxMessageId] = useState();
    const [selectedMessage, setSelectedMessage] = useState("");

    const [startTime, setStartTime] = useState(null);
    const [endTime, setEndTime] = useState(null);
    const [startTimeDisplay, setStartTimeDisplay] = useState(null);
    const [endTimeDisplay, setEndTimeDisplay] = useState(null);
    const [queryParams, setQueryParams] = useState(`?includeContent=true&limit=${pageSize}&offset=${offset}` + (props.messageStatus ? `&status=${props.messageStatus}` : ""))

    const [url, setUrl] = useState(
        baseUrl + queryParams
    );
    const [textSearch, setTextSearch] = useState("");
    const [selectedStatuses, setSelectedStatuses] = useState(
        props.messageStatus ? [props.messageStatus] : []
    );
    const [selectedStatusesDisplay, setSelectedStatusesDisplay] = useState(
        props.messageStatus ? [props.messageStatus] : []
    );

    const handleStartTimeChange = (newStartTime) => {
        setStartTime(newStartTime);
    };
    const handleEndTimeChange = (newEndTime) => {
        setEndTime(newEndTime);
    };

    const requestConfigMessages = useMemo(() => {
        return {
            url: url,
        };
    }, [url]);

    const requestConfigMax = useMemo(() => {
        return {
            url: baseUrlMaxMessageId,
        };
    }, [baseUrlMaxMessageId]);

    const requestConfigMessageCount = useMemo(() => {
        return {
            url: baseUrlCount + queryParams,
        };
    }, [baseUrlCount, queryParams]);

    const { sendRequest: fetchMessagesForChannel } = useFetchWrapper();

    const { sendRequest: getMaxMessageId } = useFetchWrapper();

    const { sendRequest: getMessageCount } = useFetchWrapper();

    const handleCount = () => {
        getMessageCount(requestConfigMessageCount, (data) => setMessageCount(data.long));
    };

    const addQueryParamsToUrl = (thePageSize, theOffset) => {
        let params = `?includeContent=true&limit=${thePageSize}&offset=${theOffset}`;
        if (startTime) {
            params +=
                "&startDate=" + startTime.toISOString().replace("Z", "-0000");
            setStartTimeDisplay(startTime);
        }
        if (endTime) {
            params +=
                "&endDate=" + endTime.toISOString().replace("Z", "-0000");
            setEndTimeDisplay(endTime);
        }
        if (textSearch) {
            params += "&textSearch=" + textSearch;
        }
        if (selectedStatuses.length > 0) {
            selectedStatuses.forEach((status) => {
                params += "&status=" + status;
            });
            setSelectedStatusesDisplay(selectedStatuses);
        }
        setQueryParams(params)
        setUrl(baseUrl + params);
        console.log(url);
    }

    const handleSearch = (thePageSize, theOffset) => {
        addQueryParamsToUrl(thePageSize, theOffset);
        setMessageCount(undefined);
    };

    const handleTextSearchChange = (event) => {
        setTextSearch(event.target.value);
    };

    const handlePageSizeChange = (event) => {
        setPageSize(event.target.value);
    };

    const handlePageNumberChange = (event) => {
        setPageNumber(event.target.value);
    };

    const handleCheckBoxChange = (event) => {
        const status = event.target.id;
        // @ts-ignore
        setSelectedStatuses((selectedStatuses) =>
            // @ts-ignore
            selectedStatuses.includes(status)
                ? selectedStatuses.filter((sts) => sts !== status)
                : [...selectedStatuses, status]
        );
    };

    const handleReset = () => {
        setStartTime(null);
        setEndTime(null);
        setSelectedStatuses([]);
    };
    
    const handleGoToPage = (pgNumber) => {
        console.log(messageCount)
        if(!messageCount) handleCount();
        if(pgNumber < 1) setPageNumber(1);        
        let theLastPage;
        if(!lastPage){
            theLastPage = Math.ceil(messageCount / pageSize);
            flushSync(() => {
                setLastPage(theLastPage);
            });
        } else {
            theLastPage = lastPage;
        }       
        if(pgNumber > lastPage) setPageNumber(theLastPage);
        let newOffset = (pgNumber - 1) * pageSize;
        console.log("new offset: " + newOffset)
        setOffset(newOffset);        
        addQueryParamsToUrl(pageSize, newOffset);
    };

    const handleNext = () => {
        let nextPageNumber = +pageNumber + 1;
        setPageNumber(nextPageNumber);
        handleGoToPage(nextPageNumber);
    };

    const handlePrev = () => {
        let prevPageNumber = +pageNumber - 1;
        setPageNumber(prevPageNumber);
        handleGoToPage(prevPageNumber);
    };

    useEffect(() => {
        fetchMessagesForChannel(requestConfigMessages, (data) => data.list ? setMessages(convertMessagesJson(data)) : setMessages([]));
        getMaxMessageId(requestConfigMax, (data) => setMaxMessageId(data.long));
    }, [
        fetchMessagesForChannel,
        requestConfigMessages,
        getMaxMessageId,
        requestConfigMax,
    ]);

    return (
        <Paper>
            <Item sx={{ mb: 1 }}>
                <Box sx={{ height: 260, width: "100%" }}>
                    <Grid container spacing={2}>
                        <Grid item xs={3.2}>
                            <Stack spacing={1}>
                                <LocalizationProvider
                                    dateAdapter={AdapterDateFns}
                                >
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
                                </LocalizationProvider>
                                <TextField
                                    label='Text search'
                                    variant='outlined'
                                    type='search'
                                    value={textSearch}
                                    onChange={handleTextSearchChange}
                                />
                                <Box
                                    sx={{
                                        display: "flex",
                                        alignItems: "center",
                                        justifyContent: "space-between",
                                    }}
                                >
                                    <TextField
                                        sx={{ mr: 2 }}
                                        label='Page size'
                                        variant='outlined'
                                        type='number'
                                        size='small'
                                        value={pageSize}
                                        onChange={handlePageSizeChange}
                                    />
                                    <ButtonGroup
                                        variant='outlined'
                                        aria-label='outlined primary button group'
                                        sx={{
                                            display: "flex",
                                            alignItems: "center",
                                            justifyContent: "space-between",
                                        }}
                                    >
                                        <Button>Advanced</Button>
                                        <Button onClick={handleReset}>
                                            Reset
                                        </Button>
                                        <Button
                                            variant='contained'
                                            onClick={() => handleSearch(pageSize, offset)}
                                        >
                                            Search
                                        </Button>
                                    </ButtonGroup>
                                </Box>
                            </Stack>
                        </Grid>
                        <Grid item xs={1.5}>
                            <Stack spacing={-1}>
                                <span></span>
                                {statuses.map((status) => (
                                    <FormControlLabel
                                        key={status}
                                        control={
                                            <Checkbox
                                                id={status}
                                                checked={selectedStatuses.includes(
                                                    status
                                                )}
                                                onClick={handleCheckBoxChange}
                                            />
                                        }
                                        label={status}
                                    />
                                ))}
                            </Stack>
                        </Grid>
                        <Grid item xs>
                            <Stack
                                alignItems='flex-start'
                                sx={{
                                    height: 220,
                                    border: 1,
                                    borderRadius: 2,
                                    padding: 1,
                                }}
                            >
                                <Typography>
                                    Max Message Id: {maxMessageId}
                                </Typography>
                                <Typography>
                                    <span>Date Range: </span>
                                    {startTimeDisplay
                                        ? startTimeDisplay
                                              .toISOString()
                                              .replace("T", " ")
                                              .replace("Z", "")
                                              .slice(0, -7)
                                        : "(any)"}
                                    <span> to </span>
                                    {endTimeDisplay
                                        ? endTimeDisplay
                                              .toISOString()
                                              .replace("T", " ")
                                              .replace("Z", "")
                                              .slice(0, -7)
                                        : "(any)"}
                                </Typography>
                                <Typography>
                                    <span>Statuses: </span>
                                    {selectedStatusesDisplay.length > 0
                                        ? selectedStatusesDisplay.join(", ")
                                        : "(any)"}
                                </Typography>
                                <Typography>
                                    <span>Connectors: </span>(any)
                                </Typography>
                            </Stack>
                        </Grid>
                        <Grid item xs={2.5}>
                            <Stack
                                spacing={1}
                                direction='column'
                                justifyContent='flex-end'
                                alignItems='flex-end'
                                sx={{ height: 220 }}
                            >
                                <Box
                                    sx={{
                                        display: "flex",
                                        alignItems: "center",
                                        justifyContent: "flex-end",
                                    }}
                                >
                                    <Typography sx={{ mr: 1 }}>
                                        Results {offset + 1} - {pageSize} of {messageCount ? messageCount : "?"}
                                    </Typography>
                                    <Button variant='contained' size='small' onClick={handleCount}>
                                        Count
                                    </Button>
                                </Box>
                                <Box
                                    sx={{
                                        display: "flex",
                                        alignItems: "center",
                                        justifyContent: "flex-end",
                                    }}
                                >
                                    <Typography>Page</Typography>
                                    <TextField
                                        sx={{ width: 50, ml: 1, mr: 1 }}
                                        variant='outlined'
                                        type='number'
                                        size='small'
                                        value={pageNumber}
                                        onChange={handlePageNumberChange}
                                    />
                                    <Typography sx={{ mr: 1 }}>
                                        {/* {" "} */}
                                        of {lastPage ? lastPage : "?"}
                                    </Typography>
                                    <Button
                                        variant='contained'
                                        size='small'
                                        onClick={() => handleGoToPage(pageNumber)}
                                    >
                                        Go
                                    </Button>
                                </Box>
                                <Box>
                                    <Button
                                        variant='contained'
                                        disabled={pageNumber === 1}
                                        size='small'
                                        sx={{ mr: 1 }}
                                        onClick={handlePrev}
                                    >
                                        &lt; Prev
                                    </Button>
                                    <Button
                                        variant='contained'
                                        size='small'
                                        onClick={handleNext}
                                    >
                                        Next &gt;
                                    </Button>
                                </Box>
                            </Stack>
                        </Grid>
                    </Grid>
                </Box>
            </Item>
            <Item sx={{ mb: 1 }}>
                <TableContainer component={Paper} sx={{ height: 300 }}>
                    <Table
                        stickyHeader
                        sx={{ minWidth: 650 }}
                        size='small'
                        aria-label='a dense table'
                    >
                        <TableHead>
                            <TableRow>
                                <TableCell>Id</TableCell>
                                <TableCell>Connector</TableCell>
                                <TableCell>Status</TableCell>
                                <TableCell>Received Date</TableCell>
                                <TableCell>Response Date</TableCell>
                                <TableCell>Source</TableCell>
                                <TableCell>Type</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {messages.map((row) => (
                                <TableRow
                                    key={row.id}
                                    onClick={() =>
                                        setSelectedMessage(row.rawMessage)
                                    }
                                    // sx={{
                                    //     "&:last-child td, &:last-child th": {
                                    //         border: 0,
                                    //     },
                                    // }}
                                >
                                    <TableCell component='th' scope='row'>
                                        {row.messageId}
                                    </TableCell>
                                    <TableCell>{row.connector}</TableCell>
                                    <TableCell>{row.status}</TableCell>
                                    <TableCell>{row.receivedDate}</TableCell>
                                    <TableCell>{row.responseDate}</TableCell>
                                    <TableCell>{row.source}</TableCell>
                                    <TableCell>{row.type}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Item>
            <Item sx={{ mb: 1, textAlign: "left", height: 300 }}>
                <Typography>{selectedMessage}</Typography>
            </Item>
        </Paper>
    );
};

export default MirthMessages;
