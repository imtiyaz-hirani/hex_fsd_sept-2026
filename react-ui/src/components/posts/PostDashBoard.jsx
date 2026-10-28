import axios from "axios";
import { useEffect, useState } from "react"

function PostDashboard() {
    const apiUrl = 'https://jsonplaceholder.typicode.com/posts';

    const [posts, setPosts] = useState([])
    const [id, setId] = useState(0)
    const [userId, setUserId] = useState(0)
    const [title, setTitle] = useState("")
    const [body, setBody] = useState("")
 
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

    const addPost = async () => {
        let postObj = {
            'title': title,
            'body': body,
            'userId': userId
        }
        try {
            const response = await axios.post(apiUrl, postObj)
            console.log(`My obj to API: ${JSON.stringify(postObj)}`)
            console.log(`APIs resonse after saving obj: ${JSON.stringify(response.data)}`)
             

            // Add to the posts array 
            let temp = [...posts]
            temp = [response.data, ...temp]
            console.log(`First element of temp ${JSON.stringify(temp[0])}` )
            setPosts([...temp])
        }
        catch (err) { }

    }
    return (

        <div>
            <div className="mb-4 mt-4">
                <button className="btn btn-primary" data-bs-toggle="modal" data-bs-target="#postForm" >
                    +Add Post
                </button>
            </div>
            {
                posts.map((p, index) => (
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

            <div className="modal fade" id="postForm" data-bs-backdrop="static" data-bs-keyboard="false"   >
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h1 className="modal-title fs-5" id="staticBackdropLabel">Add Post</h1>
                            <button type="button" className="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div className="modal-body">

                            <div>
                                <label>Title</label>
                                <input type="text"  onChange={(e) => setTitle(e.target.value)}
                                    className="form-control" required="required" />
                            </div>
                            <div>
                                <label>User Id</label>
                                <input type="number"   onChange={(e) => setUserId(e.target.value)}
                                    className="form-control" required="required" />
                            </div>
                            <div>
                                <label>Body</label>
                                <input type="text"  onChange={(e) => setBody(e.target.value)}
                                    className="form-control" required="required" />
                            </div>


                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>

                            <button type="button" className="btn btn-primary" onClick={() => addPost()} data-bs-dismiss="modal">Add</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default PostDashboard