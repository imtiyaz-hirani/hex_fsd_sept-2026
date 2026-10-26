const App = () =>{ //component 

  const nums = [1,4,7,2,3,8,9,6,4]

  return(
    <div>
      <h1>My react app</h1>
      <p>Your full array:{nums}</p> 
      <hr />
      {/** Iterating over the array using map */}
      {
        nums.map((n,index)=>(
          <li key={index}>{n}</li>
        ))
      }
    </div>
  )
}

export default App
