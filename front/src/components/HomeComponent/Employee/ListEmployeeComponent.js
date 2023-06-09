import React, {useState, useEffect} from 'react'
//import {Link} from 'react-router-dom'
import EmployeeService from '../../../../../front/src/services/EmployeeService'
import './employee.css'
import Table from '@mui/material/Table'
import TableHead from '@mui/material/TableHead'
import TableBody from '@mui/material/TableBody'
import TableRow from '@mui/material/TableRow'
import Button from '@mui/material/Button'
import SearchIcon from '@mui/icons-material/Search';
import AddOrUpdate from './AddEmployeeComponent'
import Divider from '@mui/material/Divider';

import AddIcon from '@mui/icons-material/AddCircle';
import DeleteIcon from '@mui/icons-material/Delete';
import Link from '@mui/material/Link'
import TableCell from '@mui/material/TableCell'
import {Box, Modal, TableContainer, Typography, Input, InputBase, TableSortLabel, Container} from "@mui/material";
import Paper from "@mui/material/Paper";
import {useNavigate, useParams} from "react-router-dom";
import TablePagination from "@mui/material/TablePagination";
import Navbar from "../../Navbar";
import Sidebar from "../../Sidebar";

import dayjs, {Dayjs} from 'dayjs';
import {DemoContainer, DemoItem} from '@mui/x-date-pickers/internals/demo';
import {AdapterDayjs} from '@mui/x-date-pickers/AdapterDayjs';
import {LocalizationProvider} from '@mui/x-date-pickers/LocalizationProvider';
import {DateRange} from '@mui/x-date-pickers-pro';
import {DateRangePicker} from '@mui/x-date-pickers-pro/DateRangePicker';

function exportToXls() {
    console.log("Hi");
    EmployeeService.exportXls().then((response) => {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'employee.xlsx');
        document.body.appendChild(link);
        link.click();
    }).catch(error => {
        console.log(error);
    })
}

function exportToDocx() {
    EmployeeService.exportDocx().then((response) => {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'employee.docx');
        document.body.appendChild(link);
        link.click();
    }).catch(error => {
        console.log(error);
    })
}

const ListEmployeeComponent = () => {

    const [employees, setEmployees] = useState([])

    const [emp, setEmp] = useState('')

    const filteredEmployees = employees.filter(employee => {
        if (employee.lastname === null) {
            return employee;
        }
        return employee.lastname.includes(emp);
    });

    useEffect(() => {

        getAllEmployees();
    }, [])

    const getAllEmployees = () => {
        EmployeeService.getAllEmployees().then((response) => {
            setEmployees(response.data)
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        })
    }

    const deleteEmployee = (employeeId) => {
        EmployeeService.deleteEmployee(employeeId).then(() => {
            getAllEmployees();
        }).catch(error => {
            console.log(error);
        })
    }

    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 300,
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        p: 4,
    };

    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const [pg, setpg] = React.useState(0);
    const [rpg, setrpg] = React.useState(5);

    function handleChangePage(event, newpage) {
        setpg(newpage);
    }

    function handleChangeRowsPerPage(event) {
        setrpg(parseInt(event.target.value, 10));
        setpg(0);
    }

    function descendingComparator(a, b, orderBy) {
        if (b[orderBy] < a[orderBy]) {
            return -1
        }
        if (b[orderBy] > a[orderBy]) {
            return 1
        }
        return 0
    }

    function getComparator(order, orderBy) {
        return order === 'desc'
            ? (a, b) => descendingComparator(a, b, orderBy)
            : (a, b) => -descendingComparator(a, b, orderBy)
    }

    function EnhancedTableHead(props) {
        const {
            onSelectAllClick,
            order,
            orderBy,
            numSelected,
            rowCount,
            onRequestSort,
        } = props
        const createSortHandler = (property) => (event) => {
            onRequestSort(event, property)
        }
    }

    const [order, setOrder] = useState('asc');

    const handleSort = (property) => () => {
        setOrder(order === 'asc' ? 'desc' : 'asc');
    };

    const sortedEmployees = employees.sort((a, b) => {
        if (order === 'asc') {
            return a.id - b.id;
        } else {
            return b.id - a.id;
        }
    });

    const [value, setValue] = React.useState([
        dayjs('2023-05-17'),
        dayjs('2023-06-21'),
    ]);

    const filterDate = () => {

        const firstDate = new Date(value[0].$y, value[0].$M, value[0].$D);
        const secondDate = new Date(value[1].$y, value[1].$M, value[1].$D);
        console.log(firstDate);
        setEmployees(
            employees.filter(
                (employee) =>
                    new Date(employee.birthdate) >= firstDate &&
                    new Date(employee.birthdate) <= secondDate
            ))
    }

    return (
        <div className="container">
            <h3 className="text-center"> Employee </h3>
            <Paper
                component="form"
                sx={{p: '2px 4px', display: 'flex', alignItems: 'center', width: 400, marginLeft: 50}}
            >
                <InputBase
                    sx={{ml: 1, flex: 1}}
                    placeholder="Enter name"
                    inputProps={{'aria-label': 'search google maps'}}

                    onChange={(e) => setEmp(e.target.value)}
                />
                <Divider sx={{height: 28, m: 0.5}} orientation="vertical"/>
                <SearchIcon/>
            </Paper>
            <div className="modal-window">
                <Button onClick={handleOpen}>Add employee</Button>
                <Modal
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="modal-modal-title"
                    aria-describedby="modal-modal-description"
                >
                    <Box sx={style}>
                        <AddOrUpdate/>
                    </Box>
                </Modal>
                <Button onClick={() => exportToXls()} className="btn btn-primary mb-2" variant="contained"> Export .xlsx </Button>
                <Button onClick={() => exportToDocx()} className="btn btn-primary mb-2" variant="contained"> Export .docx </Button>

                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DemoContainer components={['DateRangePicker', 'DateRangePicker']}>
                        <DemoItem label="" component="DateRangePicker">
                            <DateRangePicker
                                value={value}
                                onChange={(newValue) => setValue(newValue)}
                            />
                        </DemoItem>
                    </DemoContainer>
                </LocalizationProvider>
                <Button onClick={filterDate}>ok</Button>
            </div>
            {/*<Link href="/add-employee" className="btn btn-primary mb-2"> Add Employee </Link>*/}
            <TableContainer component={Paper} className="TableContainer"
                            sx={{maxWidth: '=70vw'}}
            >
                <Table aria-label='simple table' className="table table-bordered table-striped">
                    <TableHead className="TableHead">
                        <TableRow className="TableRow">
                            <TableCell>
                                <TableSortLabel active={true} direction={order} onClick={handleSort('id')} key="id">
                                    id
                                </TableSortLabel>
                            </TableCell>
                            <TableCell>
                                <TableSortLabel active={true} direction={order} onClick={handleSort('name')} key="name">
                                    name
                                </TableSortLabel>
                            </TableCell>
                            <TableCell>
                                <TableSortLabel active={true} direction={order} onClick={handleSort('lastname')}
                                                key="lastname">
                                    lastname
                                </TableSortLabel>
                            </TableCell>
                            <TableCell>
                                <TableSortLabel active={true} direction={order} onClick={handleSort('username')}
                                                key="username">
                                    username
                                </TableSortLabel>
                            </TableCell>
                            <TableCell>
                                <TableSortLabel active={true} direction={order} onClick={handleSort('role')} key="role">
                                    role
                                </TableSortLabel>
                            </TableCell>
                            <TableCell>
                                <TableSortLabel active={true} direction={order} onClick={handleSort('birthdate')}
                                                key="birthdate">
                                    birthdate
                                </TableSortLabel>
                            </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {filteredEmployees.slice(pg * rpg, pg * rpg + rpg).map(employee =>
                            <TableRow className="Table_Row" key={employee.id}>
                                <TableCell> {employee.id} </TableCell>
                                <TableCell> {employee.name} </TableCell>
                                <TableCell>{employee.lastname}</TableCell>
                                <TableCell>{employee.username}</TableCell>
                                <TableCell>{employee.role}</TableCell>
                                <TableCell>{employee.birthdate}</TableCell>
                                <TableCell>
                                    <div style={{
                                        padding: "0", margin: "0",
                                        display: "flex", justifyContent: "center"
                                    }}>
                                        <Button href={`/edit-employee/${employee.id}`}>Update</Button>
                                        <Button className="btn btn-danger"
                                                onClick={() => deleteEmployee(employee.id)}> Delete</Button>

                                    </div>
                                </TableCell>
                            </TableRow>)}
                    </TableBody>
                </Table>
            </TableContainer>
            <TablePagination
                rowsPerPageOptions={[5, 10, 25]}
                component="div"
                count={employees.length}
                rowsPerPage={rpg}
                page={pg}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
            />
        </div>)
}

export default ListEmployeeComponent;