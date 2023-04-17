import { useEffect } from "react";
import authorize from "../links/authorize";
import { useNavigate, useSearchParams } from "react-router-dom";
import token from "../links/token";
import {Buffer} from 'buffer';
import { generateCodeChallenge, generateCodeVerifier } from "../pkce/pkce";

const Redirect = () => {
    const [searchParams] = useSearchParams();
    const navigate = useNavigate();
    // const verifier = generateCodeVerifier();
    // sessionStorage.setItem('codeVerifier', verifier);
    // const codeChallenge = generateCodeChallenge();
    // sessionStorage.setItem('codeChallenge', codeChallenge);
    useEffect(() => {
        if(searchParams?.get('code')){
            sessionStorage.setItem('code', searchParams.get('code')!);
            const client = 'client';
            const secret = 'secret';
            const headers = new Headers();
            headers.set('Content-type', 'application/json');
            headers.set('Authorization', `Basic ${Buffer.from(`${client}:${secret}`).toString('base64')}`);

            // const verifier = generateCodeVerifier();
            // sessionStorage.setItem('codeVerifier', verifier);
            // const codeChallenge = generateCodeChallenge();
            // sessionStorage.setItem('codeChallenge', codeChallenge);
            console.log("Code verifier in redirect() ===" + sessionStorage.getItem('codeVerifier')) 
            const url = token();
            fetch(url, {
                method: 'POST',
                mode: 'cors',
                headers
            }).then(async (response) => {
                const token = await response.json();
                if(token?.id_token){
                    sessionStorage.setItem('id_token', token.id_token);
                    navigate('/home');
                }
            }).catch((err) => console.log(err))

        }
        else if(!searchParams?.get('code')){
            window.location.href = authorize();
        }

    }, [navigate, searchParams])
    return <p>Redirecting...</p>
}

export default Redirect;