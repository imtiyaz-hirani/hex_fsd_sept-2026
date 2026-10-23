const products = [
  {
    "id": 1,
    "name": "Oppo mobile A10",
    "price": 20000,
    "category": "mobiles"
  },
  {
    "id": 2,
    "name": "Samsung Galaxy M14",
    "price": 14999,
    "category": "mobiles"
  },
  {
    "id": 3,
    "name": "Redmi Note 12",
    "price": 16999,
    "category": "mobiles"
  },
  {
    "id": 4,
    "name": "Realme Narzo 60",
    "price": 17999,
    "category": "mobiles"
  },
  {
    "id": 5,
    "name": "Vivo T2x",
    "price": 12999,
    "category": "mobiles"
  },
  {
    "id": 6,
    "name": "OnePlus Nord CE 3 Lite",
    "price": 19999,
    "category": "mobiles"
  },
  {
    "id": 7,
    "name": "PCO X5 Pro",
    "price": 22999,
    "category": "mobiles"
  },
  {
    "id": 8,
    "name": "Moto G54",
    "price": 13999,
    "category": "mobiles"
  },
  {
    "id": 9,
    "name": "iQOO Z7s",
    "price": 18999,
    "category": "mobiles"
  },
  {
    "id": 10,
    "name": "Infinix Zero 30",
    "price": 21999,
    "category": "mobiles"
  }
]

// We have Array of objects 

// Iterate over products array
products.forEach(p => console.log(p))

// Clone it 
const products_clone = [...products]

// search - filter : fetch me the product having id as ?
let id = 205
let prod = products.find(p => p.id === id)
console.log('---------------------find op----------------')
console.log((prod === undefined) ? `product with id : ${id} does not exist`
  : `product is ${prod.id} , ${prod.name}`)

console.log('---------------------filter op by price----------------')
// display products having price > 20000 
let filteredArray = products.filter(p => p.price > 30000);

filteredArray.length > 0 ? filteredArray.forEach(p => console.log(p))
  : console.log('No matching products')

console.log('---------------------map op----------------')
const nameArry = products.map(p => p.name)
nameArry.forEach(e => console.log(e))

products
  .map((p) => `Product : ${p.name} , with price= ${p.price}`)
  .forEach(element => console.log(element))

// Compute the sum of all prices of all elements(products) 
console.log('---------------------reduce op----------------')
console.log(`Total price: ${products.reduce((sum, p) => sum + p.price, 0)}`)
