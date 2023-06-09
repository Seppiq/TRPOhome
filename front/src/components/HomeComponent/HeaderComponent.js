import React from 'react'
import Logo from "../../assets/logo.png";
import {useNavigate} from "react-router-dom";
import logo from "../../assets/logo.png";
import styled from "styled-components";

/*const HeaderComponent = () => {
    return (
        <div>
            <header>
                <nav className = "navbar navbar-expand-md navbar-dark bg-dark">
                    <div className="logo">
                        <img src={Logo} alt="logo" />
                    </div>
                    <div>
                        Admin Panel
                    </div>
                </nav>
            </header>
        </div>
    )
}*/

export default function HeaderComponent(props) {
    return (
        <StyledHeader className="flex a-center j-between">
            <div className="logo">
                <img src={logo} alt="logo"/>
            </div>
            <div className="title">
                Admin Panel
            </div>
        </StyledHeader>
    );
}
const StyledHeader = styled.header`
  padding: 0 4rem;
  img {
    height: 5rem;
  }
  title{
    
  }
`;
