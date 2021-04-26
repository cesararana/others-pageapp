import axios from 'axios';

const URL = `${process.env.REACT_APP_API_SERVER}`;

function errorHandler(error){
    console.warn(error)
    if (error.response) {
        let statusCode = error.response.status;
        if ( statusCode >= 400 && statusCode < 500 ) {
            let errors = [];
            switch ( statusCode ) {
                case 400:
                    errors.push(error.response.data.errors.error);
                    break;
                case 404:
                    errors.push("Not found");
                    break;
                default:
                    errors.push("Client error")
            }
            return {
                errors
            }
        }
        if ( statusCode >= 500 && statusCode < 600 ) {
            return {
                errors: ["Server error: " + error.message]
            };
        }
      } else if (error.request) {
        return {
            errors:["Error with request"]
        }
      } else {
        return {
            errors: ["Error: " + error.message]
        }
      }
}

async function getAllPages(page = 0, size = 10){
    size = size <= 0 ? 10 : size;
    return axios.get(`${URL}/page/?page=${page}&size=${size}`)
    .then(res => {
        return res.data;
    })
    .catch(error => errorHandler(error))
}

function getPage(slug){
    return axios.get(`${URL}/page/${slug}`)
    .then(res => {
        return res.data;
    })
    .catch(error => errorHandler(error))
}

function savePage({url, description, notes}){
    return axios.post(`${URL}/page/`,{
        url,
        description,
        notes
    })
    .then(res => {
        return res.data;
    })
    .catch(error => errorHandler(error))
}
const Api = {
    getAllPages,
    getPage,
    savePage
};

export default Api;