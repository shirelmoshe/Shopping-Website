export interface Orders {
  id?: string;
  user_id?: string;
  first_name: string;
  last_name: string;
  email: string;
  street: string;
  city: string;
  country: string;
  card_type: string;
  name_on_card: string;
  card_number: string;
  security_code: string;
  expiration_month: string;
  expiration_year: string;
}
