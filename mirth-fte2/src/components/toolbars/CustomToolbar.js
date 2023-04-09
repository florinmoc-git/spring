import React from "react";
import {
    GridToolbarContainer,
    GridToolbarExport,
    gridClasses,
} from "@mui/x-data-grid";

export const CustomToolbar = () => {
    return (
        <GridToolbarContainer className={gridClasses.toolbarContainer}>
            <GridToolbarExport />
        </GridToolbarContainer>
    );
};
