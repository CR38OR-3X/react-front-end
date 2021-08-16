import React from 'react'
import { Component} from "react";
import { Alert } from "reactstrap";
import { 
    Container, 
    FormH1, 
    FormLabel, 
    FormWrap,
    Icon,
    FormInput,
    Text,
    FormContent,
    Form,
    FormButton,
    Img,
    ImgWrap
} from './SignInElements'
import AuthenticationService from '../../services/AuthenticationService';

class SignIn extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password: "",
            error: ""
        };
    }
    
    changeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam]: val});
    }

    doLogin = async (event) => {
        event.preventDefault();

        AuthenticationService
            .login(this.state.username, this.state.password)
            .then(
                () => {this.props.history.push('/user');
                window.location.reload();
            },
            error => {
                console.log("Login fail: error = { " + error.toString() + " }");
                this.setState({error: "Cannot login. Please check username or password."});
            }
        );
    }

    render(){
       return (
        <>
          <Container>
              <FormWrap>
                  <Icon to='/'>Bank of MA</Icon>
                  <FormContent>
                      <Form onSubmit={this.doLogin}>
                          <FormH1>Sign in to your account</FormH1>
                          <FormLabel htmlFor='for'>Email</FormLabel>
                          <FormInput autoFocus 
                                type="text"
                                name="username" id="username"
                                value={this.state.username}
                                placeholder="Enter your username"
                                autoComplete="username"
                                onChange={this.changeHandler} />
                          <FormLabel for="password">Password</FormLabel>
                          <FormInput type="password" 
                                name="password" id="password"
                                value={this.state.password}
                                placeholder="Enter your password"
                                autoComplete="password"
                                onChange={this.changeHandler} />
                          <FormButton type='submit'>Continue</FormButton>
                          <Text>Forgot password</Text>
                          {
                            this.state.error && (
                                <Alert color="danger">
                                    {this.state.error}
                                </Alert>
                            )
                        }
                      </Form>
                  </FormContent>
              </FormWrap>
          </Container>  
        </>
    ) 
    }
    
}

export default SignIn;
