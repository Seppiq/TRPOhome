import React from 'react';
import styled from "styled-components";
import {BrowserRouter, Link, Route, Router, Routes, useLocation, useNavigate} from "react-router-dom";
import ListEmployeeComponent from '../components/HomeComponent/Employee/ListEmployeeComponent'
import ListStudentComponent from '../components/HomeComponent/Student/ListStudentComponent'
import HeaderComponent from '../components/HomeComponent/HeaderComponent'
import FooterComponent from '../components/HomeComponent/FooterComponent'
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import AddEmployeeComponent from "../components/HomeComponent/Employee/AddEmployeeComponent";

function Home(props) {

    const history = useLocation()

    console.log(history)

    return (
        <Container>

        </Container>
    );
}

const Container = styled.div`

`;

export default Home;

const RoutesMap = [
    {
        path: '/employees',
        component: ListEmployeeComponent
    },
    {
        path: '/students',
        component: ListStudentComponent
    },
    {
        path: '/add-employee',
        component: AddEmployeeComponent
    },
    {
        path: '/add-employee/:id',
        component: AddEmployeeComponent
    },
]
