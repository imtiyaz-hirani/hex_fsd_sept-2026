const product = {
    "id" : 1,
    "name" : "Oppo mobile A10",
    "price" : 20000,
    "category": "mobiles"
}
// Clone of original object 
const prod = {...product} // Spread op: ... array or object 

product.price = product.price * 0.9; 

console.log(`id is ${product.id} Price is ${product.price}`)
console.log(product)
console.log(prod)

