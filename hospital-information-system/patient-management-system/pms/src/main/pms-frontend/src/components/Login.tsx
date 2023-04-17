import { Link } from "react-router-dom";
import { generateCodeVerifier, generateCodeChallenge } from "../pkce/pkce";
import { useEffect } from "react";

const Login = () => {
    // useEffect(() => {
        const verifier = generateCodeVerifier();
        sessionStorage.setItem('codeVerifier', verifier);
        const codeChallenge = generateCodeChallenge();
        sessionStorage.setItem('codeChallenge', codeChallenge);
    // })
    // console.log("code verifier >>==> " + sessionStorage.getItem('codeVerifier'))
    // console.log("code challenge >>==> " + sessionStorage.getItem('codeChallenge'))
    
    return<Link to={"/redirect"}>Login</Link>

}

export default Login;