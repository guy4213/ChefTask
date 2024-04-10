import { useState } from 'react';
import { Chef } from '../@types/types';
import { Card } from './Card'
import StarRating from './star';



const ChefItem = ({id,name,description,
                  img,averageRate,food_Kosher,
                 avgPrice,residence,numOfOrders,
                  deliveryCost,rateAvg,available_dishes,
                kitchen_kinds}:Chef) => {

  const[rate,setRate]=useState<number>(0)
  return (
  <Card>
      {/* TODO: פרמטרים נדרשים לשף:
      תמונה
       שם
       אוכל כשר\ללא גלוטן\משלוחים חינם?
       דירוג לקוחות
       תיאור תמונה
       טווח מחירים
       מגורים
       מנות זמינות
       סוג מטבח*/}
       
       
       <b><h1>{name}</h1></b>
      <img width="100% " src={img} alt="Chef" />
<p>description: <b>{description}</b></p>
<p>avgRate: <b>{averageRate}</b></p>
<p>is food Kosher? <b>{food_Kosher} </b> </p>
<p> avgPrice: <b>{avgPrice}$ </b></p>
<p>residence: <b>{residence} </b></p>
<h3>   orders amount: <b></b>{numOfOrders}</h3>
<h4> delivery:<b> {deliveryCost} </b> </h4><b></b>
<h3><b>avaible dishes:</b></h3>
{available_dishes.map((dish) => (
    <div key={dish.id}>{dish.name}</div>
  ))}
  
 <div>
  <br/>
  KitchenKinds:
  {kitchen_kinds.map((kitchenKind,index) => (
    <div key={kitchenKind.id}>
      <h3>kitchen Kind {index+1} : {kitchenKind.name}</h3>
      <ul><p>dishes in {kitchenKind.name} kind: </p>
        {kitchenKind.dishes.map(dish => (
          <li key={dish.id}>{dish.name}</li>
        ))}
      </ul>
      
    </div>
    
  ))}
   <br/>
        <h1 className='font-bold pb-0'>Rate the Chef</h1>
      < StarRating key={id} id={id} initialRating={0} />
</div> 




</Card>
    
  )
}

export default ChefItem;