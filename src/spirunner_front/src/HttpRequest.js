import axios from 'axios';
const HOST="127.0.0.1"
const PORT="7000"
let _baseUrl=`http://${HOST}:${PORT}`
export default axios.create({
    baseURL:_baseUrl,
    headers:{
        "Content-type": "application/json"
    }
})