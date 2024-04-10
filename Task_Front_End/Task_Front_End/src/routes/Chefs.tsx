import ChefItem from '../components/ChefItem';
import { ChefsService } from '../services/chefs-service';
import { useEffect, useState } from 'react';
import { ChefsPage } from '../@types/types';

const Chefs = () => {
  const [chefsPage, setChefsPage] = useState<ChefsPage>();
  const [searchType, setSearchType] = useState<string>("ChefName");
  const [fieldValue, setFieldValue] = useState<string>("");
  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const chefsResponse = await ChefsService.getAllChefAxios();
      console.log("Chefs Array: " + JSON.stringify(chefsResponse));
      setChefsPage(chefsResponse);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };
 
  async function handleChange(event: React.ChangeEvent<HTMLInputElement>) {
    const { value } = event.target;
    setFieldValue(value)
    console.log(searchType)
 
  
    if (fieldValue=='') {
      // Fetch all chefs immediately if input value is empty
      
      await refreshSearch();
      
      return; // No need to proceed with search if input value is empty
    }
    if(searchType=="ChefName"){
      
    // Delayed search request
    setTimeout(async () => {
      try {
        const searchResponse = await ChefsService.searchChefByname(value);
        console.log("Search Result: " + JSON.stringify(searchResponse));
        // Update state with search results
        setChefsPage(searchResponse);
      } catch (error) {
        console.error('Error searching:', error);
      }
    }, 500);
  }
   if(searchType=="residence"){
     setTimeout(async () => {
    try {
      const searchResponse = await ChefsService.searchByResidence(value);
      console.log("Search Result: " + JSON.stringify(searchResponse));
      // Update state with search results
      setChefsPage(searchResponse);
    } catch (error) {
      console.error('Error searching:', error);
    }
  }, 500);
}
 if(searchType=="KitchenKind"){
  
    setTimeout(async () => {
    try {
      const searchResponse = await ChefsService.searchKitchenKindName(value);
      console.log("Search Result: " + JSON.stringify(searchResponse));
      // Update state with search results
      setChefsPage(searchResponse);
    } catch (error) {
      console.error('Error searching:', error);
    }
  }, 500);}
   if(searchType=="dish"){  
     setTimeout(async () => {
    try {
   
      const searchResponse = await ChefsService.searchByDishName(value);
      console.log("Search Result: " + JSON.stringify(searchResponse));
      // Update state with search results
      setChefsPage(searchResponse);
    } catch (error) {
      console.error('Error searching:', error);
    }
  }, 500);}

    console.log(value);
  }
  const refreshSearch = async () => {
      try {
        const res = await ChefsService.getAllChefAxios();
        setChefsPage(res);
        console.log("Fetched all chefs data");
      } catch (error) {
        console.error('Error fetching all chefs:', error);
      }
   
  }
    const transferValue = async (type?: string) => {
      if (type) {
        setSearchType(type); // Set the search type if provided
      }
        
      setFieldValue("")
      // Call performSearch which now handles refreshing the db
      await refreshSearch();
    };
  return (
    <>
      <div className='text-center mt-6'>
        <input type="text" onChange={handleChange} placeholder='enter value: ' className=' text-center p-2' value={fieldValue} />
        <button  onClick={()=>transferValue("ChefName")} className='bg-green-600 text-stone-50 border-none rounded-xl p-2 ml-2'>search By Chef Name</button>
        <button  onClick={()=>transferValue("residence")} className='bg-green-600 text-stone-50 border-none rounded-xl p-2 ml-2'>search By Residence</button>
        <button  onClick={()=>transferValue("KitchenKind")} className='bg-green-600 text-stone-50 border-none rounded-xl p-2 ml-2'>search By KitchenKind</button>
        <button  onClick={()=>transferValue("dish")} className='bg-green-600 text-stone-50 border-none rounded-xl p-2 ml-2'>search By Dish</button>

      </div>
      <div className="flex flex-row gap-5 text-center">
        {chefsPage?.chefs.map((p) => (
          <ChefItem key={p.id} {...p} />
        ))}
      </div>
    </>
  );
};



export default Chefs;
