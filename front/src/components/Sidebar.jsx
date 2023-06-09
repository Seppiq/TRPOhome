import {
    AccountBox, Article, Group, Home, ModeNight, Person, Settings, Storefront,
} from "@mui/icons-material";
import {
    Box, IconButton, List, ListItem, ListItemButton, ListItemIcon, ListItemText, Switch, useTheme,
} from "@mui/material";
import React from "react";
import ListEmployeeComponent from "./HomeComponent/Employee/ListEmployeeComponent";
import ListStudentComponent from "./HomeComponent/Student/ListStudentComponent";


const Sidebar = ({mode, setMode}) => {
    return (
        <Box flex={1} p={2} sx={{display: {xs: "none", sm: "block"}}}>
        <Box position="fixed">
            <List>
                <ListItem disablePadding>
                    <ListItemButton component="a" href="/home">
                        <ListItemIcon>
                            <Home/>
                        </ListItemIcon>
                        <ListItemText primary="Homepage"/>
                    </ListItemButton>
                </ListItem>
                <ListItem disablePadding>
                    <ListItemButton component="a" href="/employees">
                        <ListItemIcon>
                            <Article/>
                        </ListItemIcon>
                        <ListItemText primary="Employee"/>
                    </ListItemButton>
                </ListItem>
                <ListItem disablePadding>
                    <ListItemButton component="a" href="/students">
                        <ListItemIcon>
                            <Article/>
                        </ListItemIcon>
                        <ListItemText primary="Student"/>
                    </ListItemButton>
                </ListItem>
                <ListItem disablePadding>
                    <ListItemButton component="a" href="/employees">
                        <ListItemIcon>
                            <Article/>
                        </ListItemIcon>
                        <ListItemText primary="Inventory"/>
                    </ListItemButton>
                </ListItem>
                <ListItem disablePadding>
                    <ListItemButton component="a" href="/employees">
                        <ListItemIcon>
                            <Article/>
                        </ListItemIcon>
                        <ListItemText primary="DutySchedule"/>
                    </ListItemButton>
                </ListItem>
                <ListItem disablePadding>
                    <ListItemButton component="a" href="/employees">
                        <ListItemIcon>
                            <Article/>
                        </ListItemIcon>
                        <ListItemText primary="EvaluationOfTheRoom"/>
                    </ListItemButton>
                </ListItem>
                <ListItem disablePadding>
                    <ListItemButton component="a" href="/employees">
                        <ListItemIcon>
                            <Article/>
                        </ListItemIcon>
                        <ListItemText primary="EmployeeControl"/>
                    </ListItemButton>
                </ListItem>
                <ListItem disablePadding>
                    <ListItemButton component="a" href="/employees">
                        <ListItemIcon>
                            <Article/>
                        </ListItemIcon>
                        <ListItemText primary="StudentControl"/>
                    </ListItemButton>
                </ListItem>
                {/*<ListItem disablePadding>*/}
                {/*    <ListItemButton component="a" href="#simple-list">*/}
                {/*        <ListItemIcon>*/}
                {/*            <ModeNight/>*/}
                {/*        </ListItemIcon>*/}
                {/*        <Switch onChange={e => setMode(mode === "light" ? "dark" : "light")}/>*/}
                {/*    </ListItemButton>*/}
                {/*</ListItem>*/}
            </List>
        </Box>
    </Box>);
};

export default Sidebar;
