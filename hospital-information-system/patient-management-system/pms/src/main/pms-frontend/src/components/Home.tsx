import { useEffect, useState } from "react";
import demo from "../links/demo";

const Home = (): JSX.Element => {
    const [demoStr, setDemoStr] = useState<String>('default');
    useEffect(() => {
        const token = sessionStorage.getItem('id_token');
        console.log('token =>>>> ' + token)
        console.log(token?.toString())
        const headers = new Headers();
        headers.set('Content-type', 'plain/text');
        headers.set('Authorization', `Bearer ${token}`)
        const url = demo();
        fetch(url, {
            method: 'GET',
            mode: 'cors',
            headers
        }).then(async (demoData) => {
            const demo = await demoData.text();
            console.log('demo => ' + demo);
            setDemoStr(demo);
        })

    }, []);
    return <>
        <div className="header">
            <h1>Home</h1></div>
        <div><p>{demoStr}</p></div>
    </>
}

export default Home;