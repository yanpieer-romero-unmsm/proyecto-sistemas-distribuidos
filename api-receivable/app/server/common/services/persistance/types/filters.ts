export interface FiltersIDV {
  codes?: string[];
  product_codes?: string[];
  ids?: number[];
  skus_mkp?: string[];
  skus_seller?: string[];
  skus_mirakl?: string[];
  skus_commerce?: string[];
  shop_ids?: number[];
  condition?: string;
  type?: string;
  limit: number;
  offset: number;  
}


export interface FiltersProduct {
  product_codes?: string[];
  ids?: number[];
  skus_mkp?: string[];
  skus_seller?: string[];
  skus_mirakl?: string[];
  skus_commerce?: string[];
  shop_ids?: number[];
  condition?: string;
  type?: string;
  limit: number;
  offset: number;  
  body?: any; 
}

export interface FiltersParameters {
  name: string[],
  value: string[],
  limit: number;
  offset: number;  
  body?: any; 
}
