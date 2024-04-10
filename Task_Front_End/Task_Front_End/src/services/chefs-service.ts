import { request } from "./utils/axios-helper"
import { baseUrl } from "./auth-service";
import axios from "axios";



const getAllChefAxios=async()=>{

const res= await axios.get(baseUrl)

return res.data;

}

const searchChefByname=async(name:String)=>{

  const res= await  request({url:`${baseUrl}/search/${name}`});
  
  return res.data;
  
  }
  const searchKitchenKindName=async(Kitchen_Kind:String)=>{

    const res= await  request({url:`${baseUrl}/searchKitchenKindName/${Kitchen_Kind}`});
    
    return res.data;
    
    }
    const searchByDishName=async(dish:String)=>{

      const res= await  request({url:`${baseUrl}/searchByDishName/${dish}`});
      
      return res.data;
      
      }
      const searchByResidence=async(residence:String)=>{

        const res= await  request({url:`${baseUrl}/searchResidence/${residence}`});
        
        return res.data;
        
        }
        const rateCalculate=async(id:number,rate:number)=>{

          const res= await  axios.post(`${baseUrl}/addRate?ChefID=${id}&rate=${rate}`);
         
          return res.data;
          
          }
    






// const getPosts = async () => {
//   const token = localStorage.getItem("token") ?? "";

//   if (!token) {
//     throw new Error("Must be logged in");
//   }

//   const res = await fetch(`${baseUrl}/posts`, {
//     method: "GET",
//     headers: {
//       Authorization: `bearer ${token}`,
//     },
//   });
  
//   const json = await res.json();

//   if (!res.ok) {
//     throw json;
//   }
//   return json;
// };
export const ChefsService = {getAllChefAxios,searchChefByname,
  searchKitchenKindName,searchByResidence,searchByDishName,rateCalculate}