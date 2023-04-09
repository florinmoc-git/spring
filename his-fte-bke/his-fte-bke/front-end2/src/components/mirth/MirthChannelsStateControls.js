// @ts-nocheck
import { React, useEffect, useState } from "react";
import { Tooltip, IconButton } from "@mui/material";
import PlayCircleFilledWhiteIcon from "@mui/icons-material/PlayCircleFilledWhite";
import StopCircleIcon from "@mui/icons-material/StopCircle";
import PauseCircleOutlineIcon from "@mui/icons-material/PauseCircleOutline";
import NotStartedIcon from "@mui/icons-material/NotStarted";
import { MIRTH_ENDPOINT, username, password } from "../../constants";

const ChannelAction = {
    START: "_start",
    STOP: "_stop",
    PAUSE: "_pause",
    RESUME: "_resume",
};
// const stoppedStateBools = [false, false, true]

const MirthChannelsStateControls = (props) => {
    const [stopped, setStopped] = useState();
    const [started, setStarted] = useState();
    const [paused, setPaused] = useState();

    const changeChannelState = async (channelId, action) => {
        console.log("Running change channel state.....");
        try {
            const response = await fetch(
                `${MIRTH_ENDPOINT}/channels/${channelId}/${action}`,
                {
                    method: "POST",
                    mode: "cors",
                    headers: {
                        Authorization:
                            "Basic " + window.btoa(`${username}:${password}`),
                        Accept: "application/json",
                        "X-Requested-With": "OpenAPI",
                    },
                }
            );
            if (response.ok) {
                console.log("Response: " + response);
                return response.ok;
            }
        } catch (error) {
            console.log(error);
        }
    };

    const setCurrentState = (isStarted, isStopped, isPaused) => {
        setStarted(isStarted);
        setPaused(isStopped);
        setStopped(isPaused);
    };

    const handleStateButton = async(isStarted, isStopped, isPaused, newState) => {
        let channelStateChanged = await changeChannelState(props.channelId, newState);
        if (channelStateChanged){
            setCurrentState(isStarted, isStopped, isPaused);
            props.fetchChannelsAndStatuses();
        }        
    };

    useEffect(() => {
        switch (props.initialState) {            
            case "STOPPED":
                setCurrentState(false, false, true);
                break;
            case "PAUSED":
                setCurrentState(false, true, false);
                break;
            case "STARTED":
                setCurrentState(true, false, false);
                break;
            default:
                console.log("Throwing tantrum!");
                setTimeout(() => {props.fetchChannelsAndStatuses()}, 300);
        }
    });

    return (
        <span>
            {stopped && (
                <Tooltip title='Start' arrow>
                    <IconButton onClick={() => handleStateButton(true, false, false, ChannelAction.START)}>
                        <PlayCircleFilledWhiteIcon color='primary' />
                    </IconButton>
                </Tooltip>
            )}
            {started && (
                <Tooltip title='Pause' arrow>
                    <IconButton onClick={() => handleStateButton(false, true, false, ChannelAction.PAUSE)}>
                        <PauseCircleOutlineIcon color='primary' />
                    </IconButton>
                </Tooltip>
            )}
            {paused && (
                <Tooltip title='Resume' arrow>
                    <IconButton onClick={() => handleStateButton(true, false, false, ChannelAction.RESUME)}>
                        <NotStartedIcon color='primary' />
                    </IconButton>
                </Tooltip>
            )}
            {(started || paused) && (
                <Tooltip title='Stop' arrow>
                    <IconButton onClick={() => handleStateButton(false, false, true, ChannelAction.STOP)}>
                        <StopCircleIcon color='primary' />
                    </IconButton>
                </Tooltip>
            )}
        </span>
    );
};

export default MirthChannelsStateControls;
