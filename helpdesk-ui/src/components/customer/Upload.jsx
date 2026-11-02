import axios from "axios"
import { useState } from "react"
import { API_SERVER_URL_DEV } from "../../../config"

function Upload() {
    const [imgProfile, setImgProfile] = useState("")
    const [msg, setMsg] = useState("")
   
    const uploadImg = async () => {
        console.log(imgProfile)

        const formData = new FormData()
        formData.append("file", imgProfile)
        try {
            await axios.post(API_SERVER_URL_DEV + "/api/customer/upload",
                formData,
                {
                    headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
                }
            )
            setMsg("Upload success...")
            showToaster()

        }
        catch (err) {
            console.log(err?.response?.data)
            setMsg("operation failed")
            showToaster()
        }

    }

    const showToaster = ()=>{
        const toastTrigger = document.getElementById('liveToastBtn')
        const toastLiveExample = document.getElementById('liveToast')
        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample)
        toastBootstrap.show()
    }
    return (
        <div>
            {msg} <br />
            <label> Upload your profile pic: </label> <br />
            <input type="file" onChange={(e) => setImgProfile(e.target.files[0])} /> <br />
            <button id="liveToastBtn" onClick={() => uploadImg()} className="btn btn-info">Upload</button>

            <div id="liveToast" aria-live="polite" aria-atomic="true" class="bg-body-secondary position-relative bd-example-toasts rounded-3">
        
                 <div className="toast-header">
                    <img src="..." className="rounded me-2" alt="..." />
                        <strong className="me-auto">Helpdesk</strong>
                        <small>Image uploaded</small>
                        <button type="button" className="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
                <div className="toast-body">
                    {msg} Upload Success 
                </div>
            </div>
        </div>
    )
}

export default Upload