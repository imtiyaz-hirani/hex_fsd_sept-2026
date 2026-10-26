function Nums() {
    const nums = [1, 4, 7, 2, 3, 8, 9, 6, 4]
    return (
        <div>
            {/** Iterating over the array using map */}
            {
                nums.map((n, index) => (
                    <li key={index}>{n}</li>
                ))
            }
        </div>
    )
}
export default Nums