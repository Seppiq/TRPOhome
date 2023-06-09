import React, {useState} from 'react';
import styled from "styled-components"
import {useNavigate} from "react-router-dom";
import BackgroundImage from "../components/BackgroundImage";
import Header from "../components/Header";
import Button from "@mui/material/Button";
import Form from "@mui/material/FormGroup"
import Input from "@mui/material/TextField"


function Signup() {
    const [showPassword, setShowPassword] = useState(false);
    const [formValues, setFormValues] = useState({
        email: "",
        password: "",
    });
    const navigate = useNavigate();

    return (
        <Container showPassword={showPassword}>
            <BackgroundImage/>
            <div className="content">
                <Header login/>
                <div className="body flex column a-center j-center">
                    <div className="text flex column">
                        <h1></h1>
                    </div>
                    <Form className="form">
                        {<Input
                            variant="standard"
                            label="Login"
                            type="email"
                            placeholder="Enter the pass number"
                            onChange={(e) =>
                                setFormValues({
                                    ...formValues,
                                    [e.target.name]: e.target.value,
                                })
                            }
                            name="email"
                            value={formValues.email}
                        />}
                        {showPassword && (
                            <Input
                                variant="standard"
                                label="Login"
                                type="password"
                                placeholder="Password"
                                onChange={(e) =>
                                    setFormValues({
                                        ...formValues,
                                        [e.target.name]: e.target.value,
                                    })
                                }
                                name="password"
                                value={formValues.password}
                            />
                        )}
                        {!showPassword && (
                            <Button variant="contained" onClick={() => setShowPassword(true)}>Не жми!</Button>
                        )}
                    </Form>
                    {showPassword && <Button variant="contained">Log In</Button>}
                </div>
            </div>
        </Container>
    );
}

const Container = styled.div`
  position: relative;

  .content {
    position: absolute;
    top: 0;
    left: 0;
    font-family: "Courier Prime", monospace;
    background-color: rgba(0, 0, 0, 0.6);
    height: 100vh;
    width: 100vw;
    display: grid;
    grid-template-rows: 15vh 85vh;


    .body {
      gap: 1rem;

      .text {
        gap: 1rem;
        text-align: center;
        font-size: 2rem;


        h1 {
          border-right: 4px solid #000;
          animation: pulse 10s;
          white-space: nowrap;
          overflow: hidden;
        }

        @keyframes pulse {
          0% {
            color: #00528d;
          }
          10% {
            color: #00528d;
          }
          30% {
            color: white;
          }
          50% {
            color: #00528d;
          }
          60% {
            color: #00528d;
          }
          80% {
            color: white;
          }
          100% {
            color: #00528d;
          }
        }

        h1:after {
          content: "";
          animation: spin 10s linear;
        }

        @keyframes spin {
          0% {
            content: "Привет";
            animation-delay: initial;
          }
          50% {
            content: "Поступай в БРУ";
          }
          100% {
            content: "В АСОИР";
          }
        }

        h1:after {
          content: "В АСОИР";
        }
      }

      .form {
        grid-template-columns: ${({showPassword}) =>
                showPassword ? "1fr 1fr" : "2fr 1fr"};
        width: 30%;
        gap: 1rem;

        /*input {
          color: black;
          border: none;
          padding: 1.5rem;
          font-size: 1.2rem;
          border: 1px solid black;

          &:focus {
            outline: none;
          }*/

        Input {
            
        }
      }
    }


  }
}

`;


export default Signup;