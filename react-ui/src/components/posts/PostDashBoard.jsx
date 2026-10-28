import axios from "axios";
import { useEffect, useState } from "react"

function PostDashboard() {
    const apiUrl = 'https://jsonplaceholder.typicode.com/posts';

    const [posts, setPosts] = useState([])

    useEffect(() => {
        const getPostData = async () => {
            try {
                const response = await axios.get(apiUrl)
                // console.log(response)
                setPosts(response.data)
            }
            catch (err) {

            }
        }
        getPostData()
    }, [])
    return (

        <div>
            {
                    posts.map((p,index)=>(
                        <div className="card mt-2" key={index} >
                            <div className="card-header">
                                Post ID: {p.id} &nbsp;&nbsp;&nbsp;&nbsp;
                                Post By User ID: {p.userId}    
                            </div>
                            <div className="card-body">
                                 <div className="card-title">
                                    <h4>{p.title}</h4>
                                 </div>
                                 <div>
                                    {p.body}
                                 </div>
                            </div>
                        </div>    
                    ))
            }
        </div>
    )
}

export default PostDashboard