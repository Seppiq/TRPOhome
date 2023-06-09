import React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Signup from "./pages/Signup";
import Login from "./pages/Login";
import Home from "./pages/Home";
import Register from "./pages/Register";
import './App.css'
import AddEmployeeComponent from "./components/HomeComponent/Employee/AddEmployeeComponent";
import ListEmployeeComponent from "./components/HomeComponent/Employee/ListEmployeeComponent";
import ListStudentComponent from "./components/HomeComponent/Student/ListStudentComponent";

import {theme} from '../src/theme/index';
import {ThemeProvider} from '@mui/material/styles';
import {CssBaseline, Switch} from "@mui/material";

import Sidebar from "./components/Sidebar";
import Navbar from "./components/Navbar";


function App() {
    return (
        <ThemeProvider theme={theme}>
            <CssBaseline>
                <BrowserRouter>
                    <Navbar />
                    <Sidebar />
                    <Routes>
                        <Route exact path="/" element={<Signup/>}></Route>
                        <Route exact path="/signup" element={<Signup/>}></Route>
                        <Route exact path="/login" element={<Login/>}></Route>
                        <Route exact path="/register" element={<Register/>}></Route>
                        <Route exact path ="/home" element={<Home/>}></Route>

                        <Route path ="/students" element={<ListStudentComponent/>}></Route>
                        <Route path ="/employees" element={<ListEmployeeComponent/>}></Route>

                        <Route path="/add-employee" element={<AddEmployeeComponent/>}></Route>
                        <Route path="/edit-employee/:id" element={<AddEmployeeComponent/>}></Route>
                    </Routes>
                </BrowserRouter>
            </CssBaseline>
        </ThemeProvider>);
}

export default App;