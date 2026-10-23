const storeName = "ABC Electronics"; // string
let visitors = 0;  // number 

console.log(typeof storeName , typeof visitors )

// visitor start to come in to the store 
visitors = visitors + 10; // value assignement
console.log('Visitors are now at ' , visitors)

// storeName = "XYZ Electronics"; -- const variable cannot be re-assigned 
// console.log(storeName)

// template literal 
str_output = `the store ${storeName} has ${visitors} visitors`;
console.log(str_output);