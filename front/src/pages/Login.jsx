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


function Login() {

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

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const history = useNavigate();

    function sendLoginRequest() {
        // console.log("Hi");
        // EmployeeService.authenticate(JSON.stringify(
        //     {
        //         'userName': username,
        //         'password': password
        //     }
        // )).then((result) => {
        //     localStorage.setItem("token", result.data);
        //     history('/home')
        //
        // }).catch(error => {
        //     console.log(error);
        // })

        EmployeeService.authenticate(JSON.stringify({
            'userName': username, 'password': password
        })).then((result) => {
            localStorage.setItem("token", result.data);
            history('/home')

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
                                Sign in
                            </Typography>
                            <Typography
                                color="textSecondary"
                                gutterBottom
                                variant="body2"
                            >
                                Sign in on the internal platform
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
                                {"Login"}
                            </Button>
                        </Box>
                        <Typography
                            color="textSecondary"
                            variant="body2"
                        >
                            Don&apos;t have an account?
                            {' '}
                            <NextLink
                                href="/register"
                            >
                                <Link
                                    to="/register"
                                    variant="subtitle2"
                                    underline="hover"
                                    sx={{
                                        cursor: 'pointer'
                                    }}
                                >
                                    Sign Up
                                </Link>
                            </NextLink>
                        </Typography>
                    </form>
                </Container>
            </Box>
        </>);
};
export default Login;

//     return (
//         <>
//             <Head>
//                 <title>Login</title>
//             </Head>
//             <Box
//                 component="main"
//                 sx={{
//                     alignItems: 'center',
//                     display: 'flex',
//                     flexGrow: 1,
//                     minHeight: '100%'
//                 }}
//             >
//                 <Container maxWidth="sm">
//                     <Grid className="grid">
//                         <Paper className="paper"
//                                elevation={10}
//                                style={paperStyle}>
//                             <Grid align="center">
//                                 <Avatar></Avatar>
//                                 <h2>Sign In</h2>
//                             </Grid>
//                             <TextField
//                                 variant="standard"
//                                 label="Username"
//                                 type="username"
//                                 value={username}
//                                 fullWidth
//                                 onChange={(e) => setUsername(e.target.value)}>
//                             </TextField>
//                             <TextField
//                                 variant="standard"
//                                 label="Password"
//                                 type="password"
//                                 fullWidth
//                                 onChange={(e) => setPassword(e.target.value)}>
//                             </TextField>
//                             <FormControlLabel control={
//                                 <Checkbox name="checkbox" color="primary"></Checkbox>
//                             } label="Remember me">
//
//                             </FormControlLabel>
//                             <Button fullWidth type="submit" color="primary" variant="contained" onClick={() => {
//                                 sendLoginRequest()
//                             }}>
//                                 {"Login"}
//                             </Button>
//                             <Typography>
//                                 <Link href="#">
//                                     Забыли пароль?
//                                 </Link>
//                             </Typography>
//                         </Paper>
//                     </Grid>
//                 </Container>
//             </Box>
//         </>);
// };

// const Container = styled.div`
//   position: absolute;
//   display: grid;
//   left: 50%;
//   transform: translate(-50%, 0);

/*.content {
  position: absolute;
  top: 0;
  left: 0;
  height: 100vh;
  width: 100vw;
  background-color: rgba(0, 0, 0, 0.6);
  display: grid;
  grid-template-rows: 15vh 85vh;

  .form-container {
    gap: 2rem;
    height: 85vh;

    .form {
      padding: 2rem;
      background-color: #000000b0;
      gap: 2rem;
      color: white;
      font-size: 2rem;
      border-radius: 1rem;

      .container {
        gap: 2rem;
        margin-bottom: 1rem;

        input {
          padding: 0.7rem 1rem;
          width: 15rem;
          border-radius: 2rem;
        }

        button {
          padding: 0.5rem 1rem;
          width: min-content;
          position: relative;
          left: 50%;
          transform: translate(-50%, 0);
        }
      }
    }
  }
}*/

