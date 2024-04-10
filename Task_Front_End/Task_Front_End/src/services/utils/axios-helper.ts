import axios, { AxiosRequestConfig } from "axios"
import { baseUrl } from "../../services/auth-service";

export const request = (requestConfig:AxiosRequestConfig ={}) => {
// const token=localStorage.getItem("token")??"";
// if(!token){
//     throw new Error("must be Logged in");
// }
const client=axios.create({baseURL:baseUrl})
// client.defaults.headers.common.Authorization=`Bearer`
return client(requestConfig).catch(e=>{
    let message="Unknown Error Message"
    if (
        e.message != null &&
        typeof e.message == "object" &&
        "message" in e.message &&
        typeof e.message == "string"
      ) {
       message=e["message"];
      }
    console.log(message)
    throw{message};

})
};
