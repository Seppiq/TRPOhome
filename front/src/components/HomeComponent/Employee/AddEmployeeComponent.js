import React, {useState, useEffect} from 'react'
import {useNavigate, useParams} from 'react-router-dom';
import EmployeeService from '../../../../../front/src/services/EmployeeService'
import "./styleAddEmployee.css"
import {useFormik, Form, FormikProvider} from "formik"

import Button from "@mui/material/Button";
import Paper from '@mui/material/Paper'
import Grid from '@mui/material/Grid'
import Avatar from '@mui/material/Avatar'
import Checkbox from '@mui/material/Checkbox'
import {Box, Container, FormControlLabel, Link, TextField, Typography, Modal, Input} from "@mui/material";
import styled from "styled-components";
import employeeService from "../../../../../front/src/services/EmployeeService";


const AddEmployeeComponent = () => {

    const [name, setName] = useState("")
    const [lastname, setLastname] = useState('')
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [birthdate, setBirthdate] = useState('')
    const [position, setPosition] = useState('')

    const history = useNavigate();
    const {id} = useParams();

    const saveOrUpdateEmployee = (e) => {
        e.preventDefault();

        const employee = {name, lastname, username, password, birthdate, position}

        if (id) {
            EmployeeService.updateEmployee(id, employee).then((response) => {
                console.log(response.data)
                history('/home');
            }).catch(error => {
                console.log(error)
            })

        } else {
            EmployeeService.createEmployee(employee).then((response) => {

                console.log(response.data)

            }).catch(error => {
                console.log(error)
            })
        }

    }

    useEffect(() => {

        EmployeeService.getEmployeeById(id).then((response) => {
            setName(response.data.name)
            setLastname(response.data.lastname)
            setUsername(response.data.username)
            setPassword(response.data.password)
            setBirthdate(response.data.birthdate)
            setPosition(response.data.position)
        }).catch(error => {
            console.log(error)
        })
    }, [id])

    const title = () => {

        if (id) {
            return <h2 className="text-center">Update Employee</h2>
        } else {
            return <h2 className="text-center">Add Employee</h2>
        }
    }

    return (
        <div style={{padding: "0", margin: "0",
            display: "flex", justifyContent :"center"}}>
            <br/><br/>
            <div className="containeradd">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        {
                            title()
                        }
                        <div className="card-body">
                            <form>
                                <div className="form-group mb-2">
                                    <Input
                                        type="text"
                                        placeholder="Enter name"
                                        name="firstname"
                                        className="form-control"
                                        value={name || ''}
                                        onChange={(e) => setName(e.target.value)}
                                    >
                                    </Input>
                                </div>

                                <div className="form-group mb-2">
                                    <Input
                                        type="text"
                                        placeholder="Enter lastname"
                                        name="lastName"
                                        className="form-control"
                                        value={lastname}
                                        onChange={(e) => setLastname(e.target.value)}
                                    >
                                    </Input>
                                </div>

                                <div className="form-group mb-2">
                                    <Input
                                        type="text"
                                        placeholder="Enter username"
                                        name="surName"
                                        className="form-control"
                                        value={username}
                                        onChange={(e) => setUsername(e.target.value)}
                                    >
                                    </Input>
                                </div>
                                <div className="form-group mb-2">
                                    <Input
                                        type="text"
                                        placeholder="Enter password"
                                        name="numberOfPhone"
                                        className="form-control"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                    >
                                    </Input>
                                </div>
                                <div className="form-group mb-2">
                                    <Input
                                        type="date"
                                        placeholder="Enter birthdate"
                                        name="position"
                                        className="form-control"
                                        value={birthdate}
                                        onChange={(e) => setBirthdate(e.target.value)}
                                    >
                                    </Input>
                                </div>
                                <div className="form-group mb-2">
                                    <Input
                                        type="text"
                                        placeholder="Enter position"
                                        name="position"
                                        className="form-control"
                                        value={position}
                                        onChange={(e) => setPosition(e.target.value)}
                                    >
                                    </Input>
                                </div>


                                <div className="form-button">
                                    <Button variant="contained" className="btn btn-success"
                                            onClick={(e) => saveOrUpdateEmployee(e)}
                                            style={{marginTop: "10px", backgroundColor: "green"}}> Submit
                                    </Button>

                                    <Link to="/home" className="btn btn-danger"> <Button variant="contained"
                                        className="btn btn-success"
                                          style={{
                                          marginTop: "10px",
                                          backgroundColor: "red"
                                    }}> Cancel
                                    </Button> </Link>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>)
}

export default AddEmployeeComponent;
