export class Product {
    product_id: number;
    sku: string;
    image_url: string;
    active: boolean;
    date_created: Date;
    last_updated: Date;
    category_id: number;
    name: string;
    description: string;
    price: number;
    quantity: number;
  
    constructor() {
      this.product_id = 0;
      this.sku = '';
      this.image_url = '';
      this.active = false;
      this.date_created = new Date();
      this.last_updated = new Date();
      this.category_id = 0;
      this.name = '';
      this.description = '';
      this.price = 0;
      this.quantity = 0;
    }
  }
  