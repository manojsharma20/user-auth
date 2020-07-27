import axios from "axios";

const instance = axios.create({
	baseURL: "http://localhost:8080/userauth/oauth/token?grant_type=password",
});

export const axiosInstance = axios.create({
	baseURL: "http://localhost:8080/userauth/apis/v1",
});

export default instance;
