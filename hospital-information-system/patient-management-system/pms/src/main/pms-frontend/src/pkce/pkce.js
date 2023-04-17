import * as crypto from 'crypto-js';
import sha256 from 'crypto-js/sha256';
import Base64 from 'crypto-js/enc-base64';

const base64Url = (str) => {
    return str.toString(Base64).replace(/=/g, '').replace(/\+/g, '-').replace(/\//g, '_');
}

const generateCodeVerifier = () => {
    // return base64Url(crypto.enc.Base64.stringify(crypto.lib.WordArray.random(32)));
    const cv = base64Url(crypto.enc.Base64.stringify(crypto.lib.WordArray.random(32)));
    console.log("code verifier =>>>>> " + cv)
    // return 'qPsH306-ZDDaOE8DFzVn05TkN3ZZoVmI_6x4LsVglQI'
    return cv
}

const generateCodeChallenge = () => {
    const codeVerifier = sessionStorage.getItem('codeVerifier');
    const cc = base64Url(sha256(codeVerifier));
    console.log("code challenge =>>>>> " + cc)
    // return base64Url(sha256(codeVerifier).toString());
    // return 'QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8'
    return cc
}

export {
    base64Url,
    generateCodeVerifier,
    generateCodeChallenge
}