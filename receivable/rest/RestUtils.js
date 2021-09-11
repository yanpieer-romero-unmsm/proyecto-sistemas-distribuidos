import axios from 'axios';

export function post(data){
    let response;

    axios.post('http://localhost:3000/api/v1/receivable', data)
  .then(res => {  
    response = res.data
  })
  .catch(error => {
    console.error(error)
  })
  return response
}

