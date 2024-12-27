// 引入axios
import axios from "axios";

let baseUrl = "http://localhost:9000/"

// 创建axios实例
const httpService = axios.create({
    baseURL:baseUrl,
    timeout:10000
})

// 添加请求拦截器
httpService.interceptors.request.use(function (config){
    // 在发送请求前执行
    config.headers.Authorization = window.sessionStorage.getItem('token')
    return config;
},function(error){
    // 对请求错误做处理
    return Promise.reject(error);
});
// 封装get请求
export function get(url,params={}){
    return new Promise((resolve,reject) => {
        httpService({
            url:url,
            method:'get',
            params:params
        }).then(response=>{
            console.log(`请求响应:`,response)
            resolve(response);
        }).catch(error =>{
            console.log(`请求响应:`,error)
            reject(error)
        });
    });
}

// 封装post请求
export function post(url, params = {}) {
    return new Promise((resolve, reject) => {
        httpService({
            url: url,
            method: 'post',
            headers: {
                'Content-Type': 'application/json', // 确保请求头为 JSON 格式
            },
            data: params
        }).then(response => {
            console.log(`请求响应:`, response);
            resolve(response);
        }).catch(error => {
            console.log(`请求响应:`, error);
            reject(error);
        });
    });
}


// 封装del请求
export function del(url,params={}){
    return new Promise((resolve,reject) => {
        httpService({
            url:url,
            method:'delete',
            data:params
        }).then(response=>{
            console.log(`请求响应:`,response)
            resolve(response);
        }).catch(error =>{
            console.log(`请求响应:`,error)
            reject(error)
        });
    });
}

// 封装上传请求
export function fileUpload(url,params={}){
    return new Promise((resolve,reject) => {
        httpService({
            url:url,
            method:'post',
            data:params,
            headers:{'Content-Type':'multipart/form-data'}
        }).then(response=>{
            console.log(`请求响应:`,response)
            resolve(response);
        }).catch(error =>{
            console.log(`请求响应:`,error)
            reject(error)
        });
    });
}

export function getServerUrl(){
    return baseUrl;
}

export default{
    get,
    post,
    del,
    fileUpload,
    getServerUrl
}