import { useState } from 'react';
import { ChefsService } from '../services/chefs-service';

const StarRating: React.FC<{ id:number,initialRating: number }> = ({id, initialRating }) => {
  const [rating, setRating] = useState(initialRating);

  const handleStarClick = (newRating: number) => {
    setRating(newRating);
  };

 async function handleRate(event:React.FormEvent<HTMLFormElement> ){
    
   await ChefsService.rateCalculate(id,rating)
  }

  return (
   
    <div className=' mt-0'>
        
      {[...Array(10)].map((_, index) => {
        const starValue = index + 1;
        return (
          <span 
            key={index}
            onClick={() => handleStarClick(starValue)}
            style={{
              cursor: 'pointer',
              color: starValue <= rating ? 'gold' : 'gray',
            }}
          >
            &#9733; {/* Star Unicode */}
          </span>
        );
      })}
      <form onSubmit={handleRate}>
      <p className=' mb-0'>Rating: {rating} / 10</p>
      <br />
      <button className='bg-green-600 text-stone-50 border-none rounded-xl p-2 ml-2'
      >submit rate</button>
      </form>
    </div>
  );
};

export default StarRating;