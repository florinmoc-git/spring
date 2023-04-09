// @ts-nocheck
import React, { useEffect, useState } from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Stack from "@mui/material/Stack";
import Snackbar from "@mui/material/Snackbar";
import { login, resetLoginFailed } from "../store/auth-state";
import { useDispatch, useSelector } from "react-redux";

function Login(props) {
    const loginFailed = useSelector((state) => state.auth.loginFailed);
    const dispatch = useDispatch();
    const [user, setUser] = useState({
        username: "",
        password: "",
    });
    const [loginFeedbackOpen, setLoginFeedbackOpen] = useState(false);

    const handleChange = (event) => {
        setUser({ ...user, [event.target.name]: event.target.value });
    };

    const loginHandler = () => {
        dispatch(login(user));
    };
    useEffect(() => {
        if (loginFailed) {
            setLoginFeedbackOpen(true);
        }
    }, [loginFailed]);

    return (
        <div>
            <Stack spacing={2} alignItems='center' mt={2}>
                <TextField
                    name='username'
                    label='Username'
                    onChange={handleChange}
                />
                <TextField
                    type='password'
                    name='password'
                    label='Password'
                    onChange={handleChange}
                />
                <Button
                    variant='outlined'
                    color='primary'
                    onClick={loginHandler}
                >
                    Login
                </Button>
            </Stack>
            <Snackbar
                open={loginFeedbackOpen}
                autoHideDuration={3000}
                onClose={() => {
                    dispatch(resetLoginFailed());
                    setLoginFeedbackOpen(false);
                }}
                message='Login failed: Please check your username and password'
            />
        </div>
    );
}

export default Login;
