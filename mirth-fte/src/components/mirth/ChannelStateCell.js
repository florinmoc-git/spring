import React from "react";
import { useEffect, useState } from "react";
import { MIRTH_ENDPOINT, password, username } from "../../constants";

export const ChannelStateCell = (props) => {
    const [state, setState] = useState();  
    const fetchState = async (channelId) => {
        try {
            const response = await fetch(
                `${MIRTH_ENDPOINT}/channels/${channelId}/status`,
                {
                    method: "GET",
                    mode: "cors",
                    headers: {
                        Authorization:
                            "Basic " +
                            window.btoa(`${username}:${password}`),
                        Accept: "application/json",
                        "X-Requested-With": "OpenAPI",
                    },
                }
            );
            const data = await response.json();
            setState(data.dashboardStatus.state);
        } catch (error) {
            console.log(error);
        }
    };      
    useEffect(() => {
        try {
            fetchState(props.channelId);
        } catch (error) {
            console.log("Fetch state: " + error)
        }
    });
    return (<p>{state}</p>)
};

// Can be added in Mirth grid
// {
//     field: "",
//     headerName: "State",
//     // sortable: false,
//     // filterable: false,
//     renderCell: (row) => <ChannelStateCell channelId={row.id}/>,
// },