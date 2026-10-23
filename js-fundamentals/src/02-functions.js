// standard function 
function addTax(amount){
    return amount * 1.18; 
}

console.log(`My total bill is ${addTax(1000)}`)

// Arrow Fn 
const addTaxArrow = amount => amount * 1.18 ;
console.log(`My total bill is ${addTaxArrow(1000)}`)


/* 
// give discount
function bill(amount){
    let discount = 10
    amount = amount - ((amount * discount)/100)
    return addTaxArrow(amount)
}
*/
const bill = (amount, discount = 10) => addTaxArrow(amount - ((amount * discount)/100))

console.log(`Final bill is ${bill(1000)}`)


const pi = 3.14; 

// Compute area of a circle 
function areaCircle(radius){
    return pi * radius * radius 
}

const areaOfCircle = radius => pi * radius * radius // arrow Fn

console.log(` Area of circle is ${areaCircle(10)}`)
console.log(` Area of circle is ${areaOfCircle(10)}`)
