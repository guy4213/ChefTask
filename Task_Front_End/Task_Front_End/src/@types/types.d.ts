export type FC = (props: { children: ReactNode }) => ReactNode;

export interface Theme {
  isDark: boolean;
  toggleTheme: () => void;
}

export type ChefsPage = {
  totalPosts: number;
  pageNo: number;
  pageSize: number;
  totalPages: number;
  chefs: Array<Chef>;
};

export type Chef = {
  id: number;
  name: string;
  description: string;
  img : string;
  averageRate: number;
  food_Kosher:String;
  avgPrice:number;
  residence:string;
  numOfOrders:number;
  deliveryCost:string;
  rateAvg:RateAvg;
  available_dishes: Array<Dish>;
  kitchen_kinds: Array<Kitchen_Kind>;
};

 type RateAvg ={
  id: number;
  sum:number;
  numOfRates:number;
 }
 export type Dish = {
  id: number;
  name: string;
  description: string;
  price:number;
};
export type Kitchen_Kind = {
  id: number;
  name: string;
  description: string;
  dishes: Array<Dish>;
};
