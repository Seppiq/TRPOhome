import React, {useState} from "react";
import styled from "styled-components";
import {useNavigate} from "react-router-dom";
import BackgroundImage from "../components/BackgroundImage";
import Header from "../components/Header";
import Button from "@mui/material/Button";
import Paper from '@mui/material/Paper'
import Grid from '@mui/material/Grid'
import Avatar from '@mui/material/Avatar'
import Checkbox from '@mui/material/Checkbox'
import {Box, Container, FormControlLabel, Link, TextField, Typography} from "@mui/material";
import EmployeeService from "../../../front/src/services/EmployeeService";
import Head from "next/head";
import ArrowBackIcon from '@mui/icons-material/ArrowBack';

import {Facebook as FacebookIcon} from '../icons/facebook';
import {Google as GoogleIcon} from '../icons/google';


import NextLink from 'next/link';
import Router from 'next/router';
import {useFormik} from 'formik';
import * as Yup from 'yup';


function Register() {

    const formik = useFormik({

        initialValues: {
            email: '', password: ''
        }, validationSchema: Yup.object({
            email: Yup
                .string()
                .min(1, "Mininum 1 characters")
                .max(15, "Maximum 15 characters")
                .required("You must enter a username"), password: Yup
                .string()
                .max(255)
                .required('Password is required')
        }), onSubmit: () => {
            Router
                .push('/')
                .catch(console.error);
        }
    });

    const [googleName, setGoogleName] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');
    const [lastname, setLastname] = useState('');

    const history = useNavigate();

    function sendLoginRequest() {

        EmployeeService.signup(JSON.stringify({
            'googleName': googleName, 'username': username, 'password': password, 'name': name, 'lastname': lastname
        })).then((result) => {
            localStorage.setItem("token", result.data);
            history('/login')

        }).catch(error => {
            console.log(error);
        })
    }

    const navigate = useNavigate();

    return (<>
        <Head>
            <title>Login</title>
        </Head>
        <Box
            component="main"
            sx={{
                alignItems: 'center', display: 'flex', flexGrow: 1, minHeight: '100%'
            }}
        >
            <Container maxWidth="sm">
                <NextLink
                    href="/"
                    passHref
                >
                    <Button
                        component="a"
                        startIcon={<ArrowBackIcon fontSize="small"/>}
                    >
                        Home
                    </Button>
                </NextLink>
                <form onSubmit={formik.handleSubmit}>
                    <Box sx={{my: 3}}>
                        <Typography
                            color="textPrimary"
                            variant="h4"
                        >
                            Sign up
                        </Typography>
                        <Typography
                            color="textSecondary"
                            gutterBottom
                            variant="body2"
                        >
                            Sign up on the internal platform
                        </Typography>
                    </Box>
                    <Grid
                        container
                        spacing={3}
                    >
                        <Grid
                            item
                            xs={12}
                            md={6}
                        >
                            <Button
                                color="info"
                                fullWidth
                                startIcon={<FacebookIcon/>}
                                onClick={() => formik.handleSubmit()}
                                size="large"
                                variant="contained"
                            >
                                Login with Facebook
                            </Button>
                        </Grid>
                        <Grid
                            item
                            xs={12}
                            md={6}
                        >
                            <Button
                                color="error"
                                fullWidth
                                onClick={() => formik.handleSubmit()}
                                size="large"
                                startIcon={<GoogleIcon/>}
                                variant="contained"
                            >
                                Login with Google
                            </Button>
                        </Grid>
                    </Grid>
                    <Box
                        sx={{
                            pb: 1, pt: 3
                        }}
                    >
                        <Typography
                            align="center"
                            color="textSecondary"
                            variant="body1"
                        >
                            or login with email address
                        </Typography>
                    </Box>
                    <TextField
                        fullWidth
                        label="Enter email"
                        // margin="normal"
                        name="email"
                        onBlur={formik.handleBlur}
                        onChange={formik.handleChange}
                        onChange={(e) => setGoogleName(e.target.value)}
                        type="email"
                        variant="outlined"
                    />
                    <TextField
                        fullWidth
                        label="Enter the username"
                        // margin="normal"
                        // name="email"
                        onBlur={formik.handleBlur}
                        onChange={formik.handleChange}
                        onChange={(e) => setUsername(e.target.value)}
                        // type="email"
                        variant="outlined"
                    />
                    <TextField
                        fullWidth
                        label="Enter the name"
                        // margin="normal"
                        // name="email"
                        onBlur={formik.handleBlur}
                        onChange={formik.handleChange}
                        onChange={(e) => setName(e.target.value)}
                        // type="email"
                        variant="outlined"
                    />
                    <TextField
                        fullWidth
                        label="Enter the lastname"
                        // margin="normal"
                        // name="email"
                        onBlur={formik.handleBlur}
                        onChange={formik.handleChange}
                        onChange={(e) => setLastname(e.target.value)}
                        // type="email"
                        variant="outlined"
                    />
                    <TextField
                        error={Boolean(formik.touched.password && formik.errors.password)}
                        fullWidth
                        // helperText={formik.touched.password && formik.errors.password}
                        label="Enter the password"
                        // margin="normal"
                        // name="password"
                        onBlur={formik.handleBlur}
                        onChange={formik.handleChange}
                        onChange={(e) => setPassword(e.target.value)}
                        type="password"
                        variant="outlined"
                    />
                    <Box sx={{py: 2}}>
                        <Button
                            color="primary"
                            disabled={formik.isSubmitting}
                            fullWidth
                            size="large"
                            type="submit"
                            variant="contained"
                            onClick={() => {
                                sendLoginRequest()
                            }}>
                            {"Sign up"}
                        </Button>
                    </Box>
                    <Typography
                        color="textSecondary"
                        variant="body2"
                    >
                        Do you have an account?
                        {' '}
                        <NextLink
                            href="/login"
                        >
                            <Link
                                to="/login"
                                variant="subtitle2"
                                underline="hover"
                                sx={{
                                    cursor: 'pointer'
                                }}
                            >
                                Sign in
                            </Link>
                        </NextLink>
                    </Typography>
                </form>
            </Container>
        </Box>
    </>);
};
export default Register;

